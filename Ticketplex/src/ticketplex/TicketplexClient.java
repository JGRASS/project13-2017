package ticketplex;

import java.util.LinkedList;

import ticketplex.interfaces.TicketplexClientInterface;
import ticketplex.systemoperations.SOLoadMovies;

public class TicketplexClient implements TicketplexClientInterface{

	public User user = null;
	public LinkedList<Movie> movies = new LinkedList<Movie>();
	public LinkedList<Showtime> showtimes = new LinkedList<Showtime>();
	public LinkedList<Reservation> reservations = new LinkedList<Reservation>();
	

	@Override
	public void loadAllData() {
		movies = SOLoadMovies.execute();
		//to-do
		//showtimes = SOLoadShowtimes.execute();
		//reservations = SOLoadReservations.execute();
		
	}

	@Override
	public void saveUserData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public User getUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(String name, String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String restartPassword(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(User user, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerReservation(User user, Showtime showtime, int seats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Reservation> getUserReservations(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}


}
