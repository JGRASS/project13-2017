package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOUserRegister {

	public static boolean execute(String username, String password, String email){
		
		Connection con = Database.getInstance().getConnection();
		
		String sql = "INSERT INTO users(username, password, email) VALUES(?,?,?)";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            String hash = SOGenerateSHA2.execute(password);
			pstmt.setString(1, username);
            pstmt.setString(2, hash);
            pstmt.setString(3, email);
            return pstmt.executeUpdate() > 0 ? true : false;
            
           
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	
		return false;
	}

}
