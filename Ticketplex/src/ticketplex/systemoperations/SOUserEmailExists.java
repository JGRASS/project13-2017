package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;

public class SOUserEmailExists {
	
	public static boolean execute(String email){
		
		Connection con = Database.getInstance().getConnection();
		
		String sql = "SELECT * FROM users WHERE email=?";
		
		 try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setString(1, email);
	            ResultSet rs = pstmt.executeQuery();
	            
	            int count = 0;
	            
	            while (rs.next()) {
	                ++count;
	            }
	            
	            return count == 0 ? false : true;
	            
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		return false;
		
	}
}
