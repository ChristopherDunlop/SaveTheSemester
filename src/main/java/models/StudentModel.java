/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import lib.AeSimpleSHA1;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author Administrator
 */
public class StudentModel {
    Cluster cluster;
    public StudentModel(){
        
    }
    
    public boolean RegisterStudent(String username, String Password, String name, String surname){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");        
        PreparedStatement ps = session.prepare("insert into studentprofiles (login,password,first_name,last_name) Values(?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,name,surname));
        //We are assuming this always works.  Also a transaction would be good here !
       
        return true;
    }
    
    public boolean IsValidStudent(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("SaveTheSemester");
        PreparedStatement ps = session.prepare("select password from studentprofiles where login=?");
        System.out.println("This is your user: " + username);
        System.out.println("This is your password: " + Password);
        System.out.println("This is your encoded password: " + EncodedPassword);
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No student found!");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
    return false;  
    }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
       
    public boolean existingStudent(String username)
    {
    	Session session = cluster.connect("instagrim");
    	PreparedStatement ps = session.prepare("select login from studentprofiles where login =?");
    	ResultSet rs = null;
    	BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                 boundStatement.bind( // here you are binding the 'boundStatement'
                         username));
        if (rs.isExhausted()) {
            System.out.println("No user found: " + username);
            return false;
        } else 
        {
        	System.out.println("Student " + username + " already exists!");
        	return false;
        }
    }
        
    
       
}