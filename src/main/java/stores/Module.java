/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.util.Date;
import java.util.Set;


/**
 *
 * @author Shaun Smith
 */
public class Module {
    String username = null;
    String moduleCode = null;
    String moduleName = null;
    Set<ModuleFile> files = null;
    Date startDate = null;
    Date examDate = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Set<ModuleFile> getFiles() {
        return files;
    }

    public void setFiles(Set<ModuleFile> files) {
        this.files = files;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    
    public int getNumOfFiles(){
        return files.size();
    }
    
    public int getNumFilePages(){
        int numFilePages = 0;
        
        java.util.Iterator<ModuleFile> iterator = files.iterator();
        while (iterator.hasNext()){
            ModuleFile file = iterator.next();
            numFilePages += file.getNumPages();
        }
        
        return numFilePages;
    }
}