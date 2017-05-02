package db;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	private static Database dbIsntance;
    private static Connection conn;

    public static Database getInstance(){
    	return (dbIsntance == null) ? new Database() : dbIsntance;
    }

    public  Connection getConnection(){

        if(conn == null){
            try {
            	// db parameters
                String url = "jdbc:sqlite:db/database.db";
                // create a connection to the database
                conn = DriverManager.getConnection(url);
            } catch (SQLException ex) {
            	System.out.println(ex.getMessage());
            }
        }

        return conn;
    }
    
	
}
