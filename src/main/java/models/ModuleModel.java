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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import stores.Module;
import stores.ModuleFile;


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
                Module module = getModule(row.getString("modulecode"));
                studentModules.add(module);
            }
        }
        
        return studentModules;
    }
    
    public Module getModule(String moduleCode){
        Session session = cluster.connect("savethesemester");
        PreparedStatement psModules = session.prepare("select username, modulename, files, startdate, examdate from modules where modulecode = ? ALLOW FILTERING");
        BoundStatement bsModules = new BoundStatement(psModules);
        ResultSet rs = session.execute(bsModules.bind(moduleCode));
        
        Module module = null;
        
        if (rs.isExhausted()) {
            System.out.println("No module found for module code: " + moduleCode);
            return null;
        }
        else {
            for (Row row : rs){
                module = new Module();
                
                module.setUsername(row.getString("username"));
                module.setModuleCode(moduleCode);
                module.setModuleName(row.getString("modulename"));
                module.setStartDate(row.getDate("startdate"));
                module.setExamDate(row.getDate("examdate"));
                
                Set<UDTValue> files = row.getSet("files", UDTValue.class);
                Iterator<UDTValue> iterator = files.iterator();
                
                Set<ModuleFile> moduleFiles = new HashSet<>();

                while (iterator.hasNext()){
                    ModuleFile moduleFile = new ModuleFile();
                    
                    UDTValue file = iterator.next();
                    UUID fileID = file.getUUID("fileid");
                    String fileName = file.getString("filename");
                    String fileType = file.getString("filetype");
                    int numPages = file.getInt("numpages");
                    boolean completed = file.getBool("completed");
                    Date dateCompleted = file.getDate("datecompleted");
                    
                    moduleFile.setFileID(fileID);
                    moduleFile.setFileName(fileName);
                    moduleFile.setFileType(fileType);
                    moduleFile.setNumPages(numPages);
                    moduleFile.setCompleted(completed);
                    moduleFile.setDateCompleted(dateCompleted);
                    
                    moduleFiles.add(moduleFile);
                }
                
                module.setFiles(moduleFiles);
            }
        }
        
        return module;
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
        PreparedStatement ps = session.prepare("select modulecode from modules where modulecode =?");
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
        // if statement checks if deliverableID has been taken, in which case display error message
        if (deliverableExists(deliverableID)) {
            return false;
        }
        // here the 2 date strings are parsed into the date format
        Date dDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
        PreparedStatement ps = session.prepare("insert into deliverables (deliverableID, moduleCode, deliverableName, dueDate, percentageWorth, percentageAchieved, dateAdded, username) Values(?,?,?,?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        Date dateAdded = new Date();
        session.execute(boundStatement.bind(deliverableID, moduleCode, deliverableName, dDate, perWorth, perAchieved, dateAdded, username));
        return true;
    }  

    //this boolean method will check the if the module trying to be added already exists
    private boolean deliverableExists(UUID deliverableID) {
    
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("select deliverableid from deliverables where deliverableid =?");
        BoundStatement boundState = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(boundState.bind(deliverableID));
        if (rs.isExhausted()) {
            System.out.println("This deliverable doesn't exist.");
            return false;
        } else {
                    return true;
                }
        }
       
        public boolean addFile(String fileName, String fileType, String numPages, String username ){
        Session session = cluster.connect("savethesemester");
        
        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        UUID fileID = uuid.randomUUID();
        
        if (existingFile(fileID))
        {
            System.out.println("The file " + fileName + " has already been uploaded!");
            return false;
        }
        
        System.out.println("File has been added!");
        PreparedStatement ps = session.prepare("insert into modules (username, modulecode, modulename, files, startdate, examdate, dateadded) Values(?,?,?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(fileName, fileType, numPages, username));
       
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
