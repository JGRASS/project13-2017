package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOReservationRemove {
	public static boolean execute(int reservation_id, int user_id) {

		Connection con = Database.getInstance().getConnection();

		String sql = "DELETE FROM reservations WHERE id=? AND user_id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, reservation_id);
			pstmt.setInt(2, user_id);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}
}
