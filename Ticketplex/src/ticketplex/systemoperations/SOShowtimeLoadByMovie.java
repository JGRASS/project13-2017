package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import db.Database;
import ticketplex.Showtime;

public class SOShowtimeLoadByMovie {

	public static LinkedList<Showtime> execute(int movie_id) {
		LinkedList<Showtime> showtimes = new LinkedList<Showtime>();		
		
		Connection con = Database.getInstance().getConnection();
	
		String sql = "SELECT showtimes.id as 'id', showtimes.movie_id as 'movie_id', showtimes.timestamp as 'timestamp'";
		sql += " FROM showtimes";
		sql += " WHERE showtimes.movie_id = ?";
	
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, movie_id);
		
	    	ResultSet rs = pstmt.executeQuery();
	    	
	        while (rs.next()) {
	        		        	
	        	Showtime showtime = new Showtime(rs.getInt("id"), rs.getInt("movie_id"), rs.getLong("timestamp"));
	        	//showtime.setNumOfReservations(rs.getInt("num_of_reservations"));
	        	showtimes.add(showtime);
	        }
	
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

		Collections.sort(showtimes, new Comparator<Showtime>() {
	        public int compare(Showtime s1, Showtime s2) {
	        	long comp = s1.getDatetime() - s2.getDatetime();
	        	if(comp == 0) return 0;
	        	if(comp < 0) return -1;
	        	return 1;
	        }
	    });

		
	    return showtimes;

	}

}
