package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOUserSetPassword {
	public static boolean execute(String username, String new_password) {

		Connection con = Database.getInstance().getConnection();

		String sql = "UPDATE users SET password = ? WHERE username = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			String hash = SOGenerateSHA2.execute(new_password);
			pstmt.setString(1, hash);
			pstmt.setString(2, username);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
