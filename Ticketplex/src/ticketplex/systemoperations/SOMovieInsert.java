package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOMovieInsert {
	public static boolean execute(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img, int status) {

		Connection con = Database.getInstance().getConnection();

		String sql = "INSERT INTO movies(name, year, genre, description, cast, director, length, img, imdbRating, imdbLink, status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, year);
			pstmt.setString(3, genre);
			pstmt.setString(4, description);
			pstmt.setString(5, cast);
			pstmt.setString(6, director);
			pstmt.setInt(7, length);
			pstmt.setBytes(8, img);
			pstmt.setString(9, imdbRating);
			pstmt.setString(10, imdbLink);
			pstmt.setInt(11, status);
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
