package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class SOMovieUpdate {
	public static boolean execute(int movie_id, String name, int year, String genre, String description, String cast,
			String director, int length, String imdbRating, String imdbLink, byte[] img, int status) {

		Connection con = Database.getInstance().getConnection();
		String sql = "UPDATE movies "
				+ "SET name=?, year=?, genre=?, description=?, cast=?, director=?, length=?, imdbRating=?, imdbLink=?, status=?, img=?"
				+ "WHERE id=?";
		
		if (img == null) {
			sql = "UPDATE movies "
					+ "SET name=?, year=?, genre=?, description=?, cast=?, director=?, length=?, imdbRating=?, imdbLink=?, status=?"
					+ "WHERE id=?";
		} 

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, year);
			pstmt.setString(3, genre);
			pstmt.setString(4, description);
			pstmt.setString(5, cast);
			pstmt.setString(6, director);
			pstmt.setInt(7, length);
			pstmt.setString(8, imdbRating);
			pstmt.setString(9, imdbLink);
			pstmt.setInt(10, status);
			if(img == null){
				pstmt.setInt(11, movie_id);
			}else{
				pstmt.setBytes(11, img);
				pstmt.setInt(12, movie_id);
			}
			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}
}
