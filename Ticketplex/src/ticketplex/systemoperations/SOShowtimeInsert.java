package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOShowtimeInsert {
	public static boolean execute(int movie_id, long timestamp) {
		Connection con = Database.getInstance().getConnection();

		String sql = "INSERT INTO showtimes(movie_id, timestamp) VALUES(?,?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, movie_id);
			pstmt.setLong(2, timestamp);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
