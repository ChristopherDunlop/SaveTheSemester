/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import static com.datastax.driver.core.DataType.timestamp;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.security.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Tom
 */
public class Student {
    String username = null;
    String firstName = null;
    String lastName = null;
    String uni = null;
    String bio =null;
    String course = null;
    Date date = null;

    public Date getDate() {
        return date;
    }

    public Date setDate(Date date) {
        this.date = date;
        return null;
    }
    
    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
