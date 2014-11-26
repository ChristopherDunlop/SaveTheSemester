/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.util.UUID;

/**
 *
 * @author Tom
 */
public class ModuleFile {
    UUID fileID = null;
    String fileName = null;
    String fileType = null;
    int numPages = 0;

    public UUID getFileID() {
        return fileID;
    }

    public void setFileID(UUID fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
}
