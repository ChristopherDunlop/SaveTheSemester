/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;

/**
 *
 * @author Christopher
 */
public final class Keyspaces {
    
    public Keyspaces() {

    }

public static void SetUpKeySpaces(Cluster c) {
        try {
            String createKeyspace ="CREATE KEYSPACE IF NOT EXISTS savethesemester WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";

            String createFileTable = "CREATE TYPE IF NOT EXISTS savethesemester.file("
                    + " FileID UUID,"
                    + " FileName text,"
                    + " FileType text,"
                    + " NumPages int"
                    + ")";

            String createModulesTable = "CREATE TABLE IF NOT EXISTS savethesemester.modules("
                   + " ModuleCode text PRIMARY KEY,"
                   + " ModuleName text,"
                   + " StartDate timestamp,"
                   + " ExamDate timestamp,"
                   + " Files set<frozen<file>>"
                   + ")";

            String createStudentsTable = "CREATE TABLE IF NOT EXISTS savethesemester.students("
                    + " Username text PRIMARY KEY,"
                    + " Password varchar,"
                    + " FirstName text,"
                    + " LastName text,"
                    + " Modules set<text>"
                    + ")";

            String createDeliverablesTable = "CREATE TABLE IF NOT EXISTS savethesemester.deliverables("
                    + " DeliverableID UUID PRIMARY KEY,"
                    + " ModuleCode text,"
                    + " Username text,"
                    + " PercentageWorth float,"
                    + "PercentageAchieved float"
                    + ")";
            
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createKeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created STS ");
            } catch (Exception et) {
                System.out.println("Can't create STS " + et);
            }

            //now add some column families 
            System.out.println("" + createFileTable);

            try {
                SimpleStatement cqlQuery = new SimpleStatement(createFileTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create FILE table " + et);
            }
            System.out.println("" + createModulesTable);

            try {
                SimpleStatement cqlQuery = new SimpleStatement(createModulesTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user MODULES table " + et);
            }
            System.out.println("" + createStudentsTable);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(createStudentsTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create STUDENTS table " + et);
            }
            System.out.println("" + createDeliverablesTable);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(createDeliverablesTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create DELIVERABLES table " + et);
            }
            session.close();

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}