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
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import lib.CassandraHosts;
import stores.Student;


import java.util.Set;
import stores.Student;


/**
 *
 * @author Administrator
 */
public class StudentModel {
    Cluster cluster;
    
    public StudentModel(){
        
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    public Student getStudent(String user){
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("SELECT username, firstname, lastname, modules FROM students WHERE username = ?");
        BoundStatement boundStatement = new BoundStatement(ps);
        ResultSet rs = null;
        rs = session.execute(boundStatement.bind(user));
        session.close();
        
        Student student = null;
        
        if (rs.isExhausted()) {
            System.out.println("No students returned for username: " + user);
            return null;
        }
        else {
            for (Row row : rs) {
                student = new Student();
                
                String username = row.getString("username");
                String firstname = row.getString("firstname");
                String lastname = row.getString("lastname");
                Set<String> modules = row.getSet("modules", String.class);
                
                student.setUsername(username);
                student.setFirstName(firstname);
                student.setLastName(lastname);
                student.setModules(modules);
            }
        }
        
        return student;
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
        Session session = cluster.connect("savethesemester");        
        PreparedStatement ps = session.prepare("insert into students (username, firstname, lastname, password) Values(?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,name,surname,EncodedPassword));
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
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("select password from students where username=?");
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

       
    public boolean existingStudent(String username)
    {
    	Session session = cluster.connect("savethesemester");
    	PreparedStatement ps = session.prepare("select username from students where  username=?");
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

        
       public Student getStudentInfo(String user) {
      
       Session session = cluster.connect("savethesemester");
       
       ResultSet rs;
       PreparedStatement ps = session.prepare("select username, firstname, lastname, modules from students where username = ?");
       rs = null;
       BoundStatement boundStatement = new BoundStatement(ps);
       rs = session.execute(boundStatement.bind(user));
        Student student = null;
       if (rs.isExhausted()) {
            System.out.println("No user found");
            return null;
        } else {
            for (Row row : rs) {
                student = new Student();
                String username = row.getString("username");
                String firstName = row.getString ("firstname");
                String lastName = row.getString ("lastname");
                Set <String> modules = row.getSet("modules", String.class);
                
                student.setUsername(username);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setModules (modules);
                
            }
        }
       
        return student;
    }
}
