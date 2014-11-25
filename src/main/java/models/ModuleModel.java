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


/**
 *
 * @author peterbennington
 */

public class ModuleModel {
 
    Cluster cluster;

    public ModuleModel() {

    }
    
    public boolean addModule(String moduleCode, String moduleName, String startDate, String examDate) {
                
        Session session = cluster.connect("savethesemester");
        
        // if statement checks if the modulecode has been taken, in which case display error message
        if (moduleExists(moduleCode)) {
            return false;
        }

        PreparedStatement ps = session.prepare("insert into modules (moduleCode, moduleName, startDate, examDate) Values(?,?,?,?)");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        moduleCode, moduleName, startDate, examDate));
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
}