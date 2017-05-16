package ticketplex.interfaces;

import java.util.LinkedList;

import ticketplex.Movie;
import ticketplex.Reservation;
import ticketplex.Showtime;

public interface TicketplexClientInterface {

	public void register(String username, String password, String email) throws Exception;
	public void login(String username, String password) throws Exception;
	public void logout();
	
	public boolean isGuest();
	public String resetPassword(String username) throws Exception;
	public void changePassword(String old_password, String new_password) throws Exception;
	
	public LinkedList<Movie> getAllMovies();
	public Movie loadMovie(int movie_id);
	
	public LinkedList<Showtime> getAllMovieShowings(int movie_id);
	public int getShowtimeSpace(int showtime_id);
	public void makeReservation(int showtime_id, int number_of_seats);
	
	public LinkedList<Reservation> getUserReservations();
	public void deleteReservation(int reservation_id);
	
	
	
}
