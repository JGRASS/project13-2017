package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;

public class SOShowtimeNumOfReservationsSeats {
	public static int execute(int showtime_id) {

		Connection con = Database.getInstance().getConnection();

		String sql = "SELECT SUM(reservations.number_of_seats) as 'num_of_seats'";
		sql += " FROM showtimes JOIN reservations ON reservations.showtime_id = showtimes.id";
		sql += " WHERE showtimes.id = ?";
		sql += " GROUP BY showtimes.id";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, showtime_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				return rs.getInt("num_of_seats");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;

	}
}
