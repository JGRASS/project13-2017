package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa zaduzena za sqlite bazu
 * @author skakac
 * @version 1.0
 */
public class Database {
	/**
	 * Istanca baze
	 */
	private static Database dbIsntance;
    /**
     * Konekcija sa bazom
     */
	private static Connection conn;
    
    /**
     * Funkcija vraca objekat klase Database
     * @return objekat Database
     */
    public static Database getInstance(){
    	if(dbIsntance == null){
    		dbIsntance = new Database();
    	}
    	return dbIsntance;
    	
    }
    /**
     * Funkcija vraca sql konekciju sa bazom
     * @return
     */
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
