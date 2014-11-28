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
import java.security.Timestamp;
import java.util.Date;


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
    
 public boolean RegisterStudent(String username, String Password, String name, String surname){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try 
        {
            EncodedPassword= sha1handler.SHA1(Password);
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException et)
        {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("savethesemester");        
        PreparedStatement ps = session.prepare("insert into students (username, firstname, lastname, password, dateAdded) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        Date dateAdded = new Date();
        session.execute(boundStatement.bind(username,name,surname,EncodedPassword, dateAdded));
       
        return true;
    }
    
    public boolean IsValidStudent(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try 
        {
            EncodedPassword= sha1handler.SHA1(Password);
        }
        catch (UnsupportedEncodingException | NoSuchAlgorithmException et)
        {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("savethesemester");
        PreparedStatement ps = session.prepare("select password from students where username=?");
        System.out.println("The Student is: " + username);
        System.out.println("This is your encoded password: " + EncodedPassword);
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute(boundStatement.bind(username));
        if (rs.isExhausted()) 
        {
            System.out.println("No student found!");
            return false;
        } 
        else 
        {
            for (Row row : rs) 
            {   
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
        rs = session.execute(boundStatement.bind(username));
        if (rs.isExhausted()) 
        {
            System.out.println("No user found: " + username);
            return false;
        } else 
        {
        	System.out.println("Student " + username + " already exists!");
        	return true;
        }
    }

        
       public Student getStudentInfo(String user) {
      
       Session session = cluster.connect("savethesemester");
       
       ResultSet rs;
       PreparedStatement ps = session.prepare("select username, firstname, lastname, university, course, dateadded , bio from students where username = ?");
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
                String university = row.getString ("university");
                String course = row.getString("course");
                String bio = row.getString("bio");
                Date date = row.getDate("dateadded");                
                student.setUsername(username);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setUni(university);
                student.setCourse(course);
                student.setBio(bio);
                student.setDate(date);
                System.out.println("student info" + username + firstName + lastName + university + course + bio + date);
            }
        }
        return student;
    }       
   
public boolean editProfile(String username, String firstname, String lastname, String uni, String course, String bio)
{
    Session session = cluster.connect("savethesemester");
    PreparedStatement ps = session.prepare("UPDATE students SET firstname = ?, lastname = ?, university = ?, course = ?, bio = ? WHERE username = ?");
    BoundStatement bs = new BoundStatement(ps);
    session.execute(bs.bind(firstname, lastname, uni, course, bio, username));
    return true;
}
}