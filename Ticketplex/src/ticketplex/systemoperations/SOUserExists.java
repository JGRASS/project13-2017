package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;

public class SOUserExists {
	
	public static boolean execute(String username){
		
		Connection con = Database.getInstance().getConnection();
		
		String sql = "SELECT * FROM users WHERE username=?";
		
		 try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setString(1, username);
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
