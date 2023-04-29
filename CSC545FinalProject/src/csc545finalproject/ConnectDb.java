/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc545finalproject;



/**
 *
 * @author yangm
 */

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JOptionPane;




public class ConnectDb {
    
    
    
    public class DatabaseRecipe {
        public String food_name;
        public String food_group;
        public int calories;
        public int sugars;
        public int fat;
        public boolean in_stock;
        
        DatabaseRecipe() {}
        @Override
        public String toString() {
            return "DatabaseRecipe for " + food_name;
        }
        
        static ArrayList<DatabaseRecipe> fromDatabase(Connection conn) {
            Gson gson = new Gson();
            var results = new ArrayList<DatabaseRecipe>();
            
            String statement = """
                           select json_object( 'food_name' VALUE foodname,
                           'food_group' VALUE foodgroup,
                           'calories' VALUE foodcalories,
                           'sugars' VALUE foodsugars,
                           'fat' value foodfat,
                           'in_stock' value CASE WHEN foodinfridge IS FALSE THEN 'false' ELSE 'true' END FORMAT JSON ) AS "result" from food""";
        
            try
            {
                var result = (OracleResultSet) conn.prepareStatement(statement).executeQuery();
                while (result.next()) {
                    results.add(
                            gson.fromJson(result.getString("result"), ConnectDb.DatabaseRecipe.class)
                    );
                    
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return results;
        }
        
        
    
    }
    
    public static Connection setupConnection()
    {
        /*
         Specify the database you would like to connect with:
         - protocol (e.g., jdbc)
         - vendor (e.g., oracle)
         - driver (e.g., thin)
         - server (e.g., csitoracle.eku.edu)
         - port number (e.g., default 1521)
         - database instance name (e.g., cscpdb)
         */
        
        
        
        

        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:";  // URL for the database

        /*
         Specify the user account you will use to connect with the database:
         - user name (e.g., myName)
         - password (e.g., myPassword)
         */
        String username = "system";
        String password = "pass";

        try
        {
            // Load jdbc driver.
            Class.forName(jdbcDriver);

            // Connect to the Oracle database
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            return conn;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    static void close(Connection conn)
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch(Throwable whatever)
            {}
        }
    }

    static void close(OraclePreparedStatement st)
    {
        if(st != null)
        {
            try
            {
                st.close();
            }
            catch(Throwable whatever)
            {}
        }
    }

    static void close(OracleResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch(Throwable whatever)
            {}
        }
    }

}