package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOMovieUpdateStatus {
	public static boolean execute(int movie_id, int status) {

		Connection con = Database.getInstance().getConnection();

		String sql = "UPDATE movies SET status = ? WHERE id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, status);
			pstmt.setInt(2, movie_id);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
