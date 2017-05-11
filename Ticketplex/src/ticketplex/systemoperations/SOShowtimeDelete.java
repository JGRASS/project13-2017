package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOShowtimeDelete {
	public static boolean execute(int showtime_id) {

		Connection con = Database.getInstance().getConnection();

		String sql = "DELETE FROM showtimes WHERE id=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, showtime_id);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}
}
