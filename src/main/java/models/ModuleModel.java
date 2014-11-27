/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import stores.Module;
import stores.ModuleFile;
import java.util.Map;

/**
 *
 * @author peterbennington
 */

public class ModuleModel {
 
    Cluster cluster;

    public ModuleModel() {

    }
    
    public Set<Module> getStudentModules(String user){
        Set<Module> studentModules = new HashSet();
        
        Session session = cluster.connect("savethesemester");
        PreparedStatement psModules = session.prepare("select modulecode from modules where username = ?");
        BoundStatement bsModules = new BoundStatement(psModules);
        ResultSet rs = session.execute(bsModules.bind(user));
        
        if (rs.isExhausted()) {
            System.out.println("No modules found for student: " + user);
            return null;
        }
        else {
            for (Row row : rs){
                Module module = getModule(user, row.getString("modulecode"));
                studentModules.add(module);
            }
        }
        
        return studentModules;
    }
    
    public Module getModule(String user, String moduleCode){
        Session session = cluster.connect("savethesemester");
        PreparedStatement psModules = session.prepare("select modulename, startdate, examdate from modules where username = ? AND modulecode = ?");
        BoundStatement bsModules = new BoundStatement(psModules);
        ResultSet rs = session.execute(bsModules.bind(user, moduleCode));
        
        Module module = null;
        
        if (rs.isExhausted()) {
            System.out.println("No module found for " + user + " - " + moduleCode);
            return null;
        }
        else {
            for (Row row : rs){
                module = new Module();
                
                module.setUsername(user);
                module.setModuleCode(moduleCode);
                module.setModuleName(row.getString("modulename"));
                module.setStartDate(row.getDate("startdate"));
                module.setExamDate(row.getDate("examdate"));
                
                Set<ModuleFile> moduleFiles = getModuleFiles(user, moduleCode);
                
                module.setFiles(moduleFiles);
            }
        }
        
        return module;
    }
    
    public Set<ModuleFile> getModuleFiles(String user, String moduleCode){
        Session session = cluster.connect("savethesemester");
        PreparedStatement psModuleFiles = session.prepare("select files from modules where username = ? AND modulecode = ?");
        BoundStatement bsModuleFiles = new BoundStatement(psModuleFiles);
        ResultSet rs = session.execute(bsModuleFiles.bind(user, moduleCode));
        
        Set<ModuleFile> moduleFiles = null;

        if (rs.isExhausted()) {
            System.out.println("No files found for " + user + " - " + moduleCode);
            return null;
        }
        else {            
            for (Row row : rs){
                Map<UUID, UDTValue> files = row.getMap("files", UUID.class, UDTValue.class);
                Iterator iterator = files.entrySet().iterator();
                
                moduleFiles = new HashSet<>();

                while (iterator.hasNext()){
                    ModuleFile moduleFile = new ModuleFile();
                    
                    //UDTValue file = iterator.next();
                    Map.Entry file = (Map.Entry) iterator.next();
                    UDTValue fileInfo = (UDTValue) file.getValue();
                    
                    UUID fileID = (UUID) file.getKey();             
                    String fileName = fileInfo.getString("filename");
                    String fileType = fileInfo.getString("filetype");
                    int numPages = fileInfo.getInt("numpages");
                    boolean completed = fileInfo.getBool("completed");
                    Date dateCompleted = fileInfo.getDate("datecompleted");
                    
                    moduleFile.setFileID(fileID);
                    moduleFile.setFileName(fileName);
                    moduleFile.setFileType(fileType);
                    moduleFile.setNumPages(numPages);
                    moduleFile.setCompleted(completed);
                    moduleFile.setDateCompleted(dateCompleted);
                    
                    moduleFiles.add(moduleFile);
                }
            }
        }
        
        return moduleFiles;
    }
    
    public boolean addModule(String moduleCode, String moduleName, String startDate, String examDate, String username) throws ParseException {         
        Session session = cluster.connect("savethesemester");
        // if statement checks if the modulecode has been taken, in which case display error message
        if (moduleExists(moduleCode)) {
            return false;
        }
        // here the 2 date strings are parsed into the date format
        Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);
        PreparedStatement ps = session.prepare("insert into modules (moduleCode, moduleName, startDate, examDate, dateAdded, username) Values(?,?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        Date dateAdded = new Date();
        session.execute(boundStatement.bind(moduleCode, moduleName, sDate, eDate, dateAdded, username));
        return true;
    }
    
    //this boolean method will check the if the module trying to be added already exists
    private boolean moduleExists(String moduleCode) {
    
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("select modulecode from modules where modulecode =? ALLOW FILTERING");
        BoundStatement boundState = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(boundState.bind(moduleCode));
        if (rs.isExhausted()) {
            System.out.println("This module doesn't exist.");
            return false;
        } else {
                    return true;
                }
        }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public boolean addDeliverable(String moduleCode, String deliverableName, String dueDate, double perWorth, double perAchieved, String username) throws ParseException {
        Session session = cluster.connect("savethesemester");    

        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        UUID deliverableID = uuid.randomUUID();
 
        // here the 2 date strings are parsed into the date format
        Date dDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
        PreparedStatement ps = session.prepare("insert into deliverables (deliverableID, moduleCode, deliverableName, dueDate, percentageWorth, percentageAchieved, dateAdded, username) Values(?,?,?,?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        Date dateAdded = new Date();
        session.execute(boundStatement.bind(deliverableID, moduleCode, deliverableName, dDate, perWorth, perAchieved, dateAdded, username));
        return true;
    }  
       
        private boolean existingFile(String username, UUID id) {
         Session session = cluster.connect("savethesemester");
         PreparedStatement ps = session.prepare("select files from modules where username =?");
         BoundStatement boundState = new BoundStatement(ps);
         ResultSet rs = null;
       
        rs = session.execute(boundState.bind(username));
        for(Row row : rs)
         {
            Set<UDTValue> files = row.getSet("files", UDTValue.class);
            Iterator<UDTValue> iterator = files.iterator();
            while (iterator.hasNext()){
                    UDTValue file = iterator.next();
                    UUID fileID = file.getUUID("fileid");
                    if (fileID == id)
                    {
                        System.out.println("File already exists");
                        return true;
                }
                    else
                    {
                        System.out.println ("File does not exist!");
                        return false;
                    }
                    
                 }
         }

            return false; 
        }

   public boolean addFile(String fileName, String fileType, String numPages, String username, String modulecode ){
         Session session = cluster.connect("savethesemester");
         
         UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
         UUID fileID = uuid.randomUUID();
        
        if (existingFile(fileID))
        if (existingFile(username,fileID))
         {
             System.out.println("The file " + fileName + " has already been uploaded!");
             return false;
         }
        
        int noPages = Integer.valueOf(numPages);
        UserType fileUDT = cluster.getMetadata().getKeyspace("savethesemester").getUserType("file");
        UDTValue newfile = fileUDT.newValue()
                .setUUID("fileid",fileID)
                .setString("filetype", fileType)
                .setInt("numpages", noPages);
        Set <UDTValue> toadd = new HashSet<>();
        toadd.add(newfile);
        PreparedStatement ps = session.prepare("UPDATE modules SET files = files + ? where username = ? AND modulecode = ?");
         System.out.println("File has been added!");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(fileName, fileType, numPages, username));
       
        session.execute(boundStatement.bind(toadd, username, modulecode));
         return true;
     }      
 
    private boolean existingFile(UUID fileID) {
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("select fileid from file where fileid =?");
        
        BoundStatement boundState = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(boundState.bind(fileID));
        if (rs.isExhausted()) {
            System.out.println("This file has not already been uploaded.");
            return false;
        } 
        else 
        {
            return true;
        }
    }
}