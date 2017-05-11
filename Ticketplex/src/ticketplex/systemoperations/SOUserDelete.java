package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOUserDelete {
	public static boolean execute(int user_id) {

		Connection con = Database.getInstance().getConnection();

		String sql = "DELETE FROM users WHERE id=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, user_id);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}
}
