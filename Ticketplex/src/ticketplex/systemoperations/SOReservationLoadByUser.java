package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import db.Database;
import ticketplex.Reservation;

public class SOReservationLoadByUser {
	public static LinkedList<Reservation> execute(int user_id) {
		LinkedList<Reservation> reservations = new LinkedList<Reservation>();

		Connection con = Database.getInstance().getConnection();

		String sql = "SELECT reservations.id as 'id', reservations.number_of_seats as 'number_of_seats', reservations.showtime_id as 'showtime_id', reservations.user_id as 'user_id', movies.name as 'movie_name', showtimes.timestamp as 'showtime_timesamp'";
		sql += " FROM reservations JOIN showtimes ON showtimes.id = reservations.showtime_id JOIN movies ON movies.id = showtimes.movie_id";
		sql += " WHERE user_id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, user_id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Reservation reservation = new Reservation(rs.getInt("id"), rs.getInt("showtime_id"),
						rs.getInt("user_id"), rs.getInt("number_of_seats"));
				reservation.setMovieName(rs.getString("movie_name"));
				reservation.setShowtimeTimesamp(rs.getLong("showtime_timesamp"));
				reservations.add(reservation);
			}
			

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return reservations;

	}
}
