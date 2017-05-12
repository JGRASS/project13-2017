package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOReservationInsert {
	public static boolean execute(int showtime_id, int user_id, int number_of_seats) {

		Connection con = Database.getInstance().getConnection();

		String sql = "INSERT INTO reservations(showtime_id, user_id, number_of_seats) VALUES(?,?,?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, showtime_id);
			pstmt.setInt(2, user_id);
			pstmt.setInt(3, number_of_seats);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
