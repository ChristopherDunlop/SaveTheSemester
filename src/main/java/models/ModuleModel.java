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
import com.datastax.driver.core.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lib.Convertors;


/**
 *
 * @author peterbennington
 */

public class ModuleModel {
 
    Cluster cluster;

    public ModuleModel() {

    }
    
    public boolean addModule(String moduleCode, String moduleName, String startDate, String examDate) throws ParseException {         
        Session session = cluster.connect("savethesemester");
        // if statement checks if the modulecode has been taken, in which case display error message
        if (moduleExists(moduleCode)) {
            return false;
        }
        // here the 2 date strings are parsed into the date format
        Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDate);
        PreparedStatement ps = session.prepare("insert into modules (moduleCode, moduleName, startDate, examDate, dateAdded) Values(?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        Date dateAdded = new Date();
        session.execute(boundStatement.bind(moduleCode, moduleName, sDate, eDate, dateAdded));
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

    }
