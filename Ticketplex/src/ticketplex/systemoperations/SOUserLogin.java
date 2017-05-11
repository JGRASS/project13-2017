package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;
import ticketplex.User;

public class SOUserLogin {
	public static User execute(String username, String password) {

		Connection con = Database.getInstance().getConnection();

		String sql = "SELECT * FROM users WHERE username=? AND password=? LIMIT 1";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			String hash = SOGenerateSHA2.execute(password);
			pstmt.setString(1, username);
			pstmt.setString(2, hash);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("Email"));

				return user;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;

	}
}
