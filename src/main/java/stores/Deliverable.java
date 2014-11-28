/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Christopher
 */
public class Deliverable {
   UUID deliverableID = null;
   String username = null;
   String moduleCode = null;
   String deliverableName = null;
   Date dueDate = null;
   Double percentageWorth = null;
   Double percentageAchieved = null;

    public UUID getDeliverableID() {
        return deliverableID;
    }

    public void setDeliverableID(UUID deliverableID) {
        this.deliverableID = deliverableID;
    }

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

    public String getDeliverableName() {
        return deliverableName;
    }

    public void setDeliverableName(String deliverableName) {
        this.deliverableName = deliverableName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getPercentageWorth() {
        return percentageWorth;
    }

    public void setPercentageWorth(Double percentageWorth) {
        this.percentageWorth = percentageWorth;
    }

    public Double getPercentageAchieved() {
        return percentageAchieved;
    }

    public void setPercentageAchieved(Double percentageAchieved) {
        this.percentageAchieved = percentageAchieved;
    }

    public Double getFinalGradePercentage(){
        Double fgp;
        fgp = (percentageAchieved / 100) * percentageWorth;
        return fgp;
    }
    
}