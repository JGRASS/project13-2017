package ticketplex.interfaces;

import java.util.LinkedList;

import ticketplex.Movie;
import ticketplex.Reservation;
import ticketplex.Showtime;
import ticketplex.User;

public interface TicketplexAdminInterface {
	
	public void loadAllData();
	public void saveAllData();
	
	public LinkedList<Showtime> getAllMovieShowings(Movie movie);
	public LinkedList<Reservation> getAllMovieReservations(Movie movie);
	public LinkedList<User> getAllUsers();
	
	public void addMovie(Movie movie);
	public void removeMovie(Movie movie);
	
	public void addMovieShowtime(Showtime showtime);
	public void removeMovieShowtime(Showtime showtime);
	
	public void removeUser(User user);
	
	public LinkedList<Reservation> getUserReservations(User user);
	public void removeReservation(Reservation reservation);
	
}
