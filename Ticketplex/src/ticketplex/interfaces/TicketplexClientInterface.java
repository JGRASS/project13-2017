package ticketplex.interfaces;

import java.util.LinkedList;

import ticketplex.Reservation;
import ticketplex.Showtime;
import ticketplex.User;

public interface TicketplexClientInterface {
	
	public void loadAllData();
	public void saveUserData();
	
	public User getUser(String name, String password);
	
	public void registerUser(String name, String email, String password);
	public String restartPassword(String name); 
	public String changePassword(User user, String password);
	
	
	public void registerReservation(User user, Showtime showtime, int seats);	
	public LinkedList<Reservation> getUserReservations(User user);
	public void removeReservation(Reservation reservation);
}
