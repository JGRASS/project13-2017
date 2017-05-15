package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOMovieDelete {
	public static boolean execute(int movie_id) {

		Connection con = Database.getInstance().getConnection();

		
		
		String sql_res = "DELETE FROM reservations WHERE reservations.id in (SELECT reservations.id FROM showtimes INNER JOIN reservations ON (reservations.showtime_id = showtimes.id) WHERE showtimes.movie_id = ?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql_res)) {
			pstmt.setInt(1, movie_id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String sql_show = "DELETE FROM showtimes  WHERE movie_id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql_show)) {
			pstmt.setInt(1, movie_id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

		String sql = "DELETE FROM movies WHERE id=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, movie_id);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}
}
