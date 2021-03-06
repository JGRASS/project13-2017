package ticketplex.interfaces;

import java.util.LinkedList;

import ticketplex.Movie;
import ticketplex.Reservation;
import ticketplex.Showtime;
import ticketplex.User;

public interface TicketplexAdminInterface {
	
	public LinkedList<Movie> getAllMovies();
	public Movie getMovie(int movie_id);
	public LinkedList<Showtime> getAllMovieShowings(int movie_id);
	public LinkedList<Reservation> getAllMovieReservations(int movie_id);
	

	public int getMovieNumOfReservations(int movie_id);
	public void addMovie(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception;
	public void editMovie(int movie_id, String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception;
	public void setMovieStatus(int movie_id, int status) throws Exception;
	public void removeMovie(int movie_id);
	
	public void addMovieShowtime(int movie_id, long timestamp) throws Exception;
	public void removeMovieShowtime(int showtime_id);
	
	
	public LinkedList<User> getAllUsers();	
	public void removeUser(int user_id);
	
	public LinkedList<Reservation> getUserReservations(int user_id);
	public void removeReservation(int reservation_id, int user_id);
	
}
