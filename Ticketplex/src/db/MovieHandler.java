package db;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import models.Movie;

public class MovieHandler {
	
	public static LinkedList<Movie> getAllMovies(){
		
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
	        				rs.getBytes("img"), rs.getInt("status"), 0);
	        	
	        	movies.add(movie);
	        }
	        System.out.println("Loaded movies.");
	
	    } catch (SQLException e) {
	    	System.out.println(e);
	        System.out.println(e.getMessage());
	    }
	    
	    return movies;
	}
	
	
	public static LinkedList<Movie> getAllMoviesTest(){
		LinkedList<Movie> movies = new LinkedList<>();
		
		String[] posters = new String[]{"it", "logan2", "galaxy", "pirates", "thor", "assassin", "justice", "starwars", "wonder", "kong", "beauty"};
		
		
		movies.add(new Movie(1, "Logan", 2017, "Action, Drama, Sci-Fi", 
				"In the near future, a weary Logan cares for an ailing Professor X somewhere on the Mexican border. However, Logan's attempts to hide from the world and his legacy are upended when a young mutant arrives, pursued by dark forces.",
				"Hugh Jackman, Patrick Stewart, Dafne Keen",
				"James Mangold",
				137,
				"8.4", "http://www.imdb.com/title/tt3315342/",
				MovieHandler.readImageFile("C:/Users/Dusan/Desktop/app/thumbs/logan.jpg"),
				Movie.STATUS_ACTIVE,
				0
				));
		
		movies.add(new Movie(2, "Guardians of the Galaxy Vol. 2", 2017, "Action, Adventure, Sci-Fi",
				"Set to the backdrop of Awesome Mixtape #2, 'Guardians of the Galaxy Vol. 2' continues the team's adventures as they unravel the mystery of Peter Quill's true parentage.",
				"Chris Pratt, Zoe Saldana, Dave Bautista",
				"James Gunn",
				136,
				"8.2", "http://www.imdb.com/title/tt3896198",
				MovieHandler.readImageFile("C:/Users/Dusan/Desktop/app/thumbs/galaxy.jpg"),
				Movie.STATUS_ACTIVE,
				0
				));
		
		
		movies.add(new Movie(3, "It", 2017, "Drama, Horror",
				"Children start to disappear in the town of Derry, Maine. The neighborhood children unite together to face Pennywise, an evil clown whose history of murder and violence dates back for centuries.",
				"Bill Skarsgard, Jaeden Lieberher, Finn Wolfhard",
				"Andres Muschietti",
				137,
				"Rating", "lin",
				MovieHandler.readImageFile("C:/Users/Dusan/Desktop/app/thumbs/it.jpg"),
				Movie.STATUS_ACTIVE,
				0
				));
		
		movies.add(new Movie(4, "Pirates of the Caribbean: Dead Men Tell No Tales", 2017, "Action, Adventure, Fantasy",
				"Captain Jack Sparrow searches for the trident of Poseidon.",
				"Johnny Depp, Geoffrey Rush, Javier Bardem",
				"Joachim Ronning, Espen Sandberg",
				137,
				"", "http://www.imdb.com/title/tt1790809",
				MovieHandler.readImageFile("C:/Users/Dusan/Desktop/app/thumbs/pirates.jpg"),
				Movie.STATUS_ACTIVE,
				0
				));
		
		int last_rand = 3;
		for(int i=0; i<17; i++){
			int rand = random(posters.length-1);
			while(rand == last_rand){
				rand = random(posters.length-1);
			}
			last_rand = rand;
			
			
			movies.add(new Movie(2, "Movie", 2017, "genre",
					"Desc",
					"Cast",
					"Direc",
					137,
					"Rating", "lin",
					MovieHandler.readImageFile("C:/Users/Dusan/Desktop/app/thumbs/"+posters[rand]+".jpg"),
					Movie.STATUS_ACTIVE,
					0
					));
		} 
		
		
		return movies;
		
	}
	
	static int random(int max){
		return (int) (Math.random() * ( max ));
	}
	

	// stackoverflow
	public static byte[] readImageFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
}
