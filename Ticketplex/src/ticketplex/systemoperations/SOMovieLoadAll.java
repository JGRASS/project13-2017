package ticketplex.systemoperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.Database;
import ticketplex.Movie;

public class SOMovieLoadAll {
	public static LinkedList<Movie> execute(){
		LinkedList<Movie> movies = new LinkedList<Movie>();		
		
		Connection con = Database.getInstance().getConnection();
	
		String select = "SELECT * FROM movies";
	
	    try (Statement stmt = con.createStatement()) {
		
	    	ResultSet rs = stmt.executeQuery(select);
	    	
	        while (rs.next()) {
	        		        	
	        	Movie movie = new Movie(rs.getInt("id"), rs.getString("name"), rs.getInt("year"), rs.getString("genre"),
	        				rs.getString("description"), 
	        				rs.getString("cast"), rs.getString("director"), 
	        				rs.getInt("length"), 
	        				rs.getString("imdbRating"), rs.getString("imdbLink"), 
	        				rs.getBytes("img"), rs.getInt("status"));
	        	
	        	movies.add(movie);
	        }
	        System.out.println("Loaded movies.");
	
	    } catch (SQLException e) {
	    	System.out.println(e);
	        System.out.println(e.getMessage());
	    }
	    
	    return movies;
	}
	
}
