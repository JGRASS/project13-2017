package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	private static Database dbIsntance;
    private static Connection conn;

    public static Database getInstance(){
    	if(dbIsntance == null){
    		dbIsntance = new Database();
    	}
    	return dbIsntance;
    	
    }

    public  Connection getConnection(){

        if(conn == null){
            try {
                String url = "jdbc:sqlite:db/database.db";
                conn = DriverManager.getConnection(url);
            } catch (SQLException ex) {
            	System.out.println(ex.getMessage());
            }
        }

        return conn;
    }
    
	
}
