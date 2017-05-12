package ticketplex;

import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ticketplex.interfaces.TicketplexClientInterface;
import ticketplex.systemoperations.SOUserEmailExists;
import ticketplex.systemoperations.SOGenerateSHA2;
import ticketplex.systemoperations.SOMovieLoadAll;
import ticketplex.systemoperations.SOReservationInsert;
import ticketplex.systemoperations.SOReservationLoadByUser;
import ticketplex.systemoperations.SOReservationRemove;
import ticketplex.systemoperations.SOShowtimeLoadByMovie;
import ticketplex.systemoperations.SOShowtimeNumOfReservations;
import ticketplex.systemoperations.SOUserExists;
import ticketplex.systemoperations.SOUserLogin;
import ticketplex.systemoperations.SOUserRegister;
import ticketplex.systemoperations.SOUserSetPassword;

public class TicketplexClient implements TicketplexClientInterface{

	public User user = null;
	
	@Override
	public boolean isGuest() {
		return (user == null) ? true : false;
	}
	
	@Override
	public void register(String username, String password, String email) throws Exception {
		if(!isGuest()){
			throw new Exception("Vec ste ulogovani");
		}
		if(username.isEmpty() || username.equals("Username"))
			throw new Exception("Username prazan");

		if(password.isEmpty() || password.equals("Password"))
			throw new Exception("Password prazan");

		if(email.isEmpty())
			throw new Exception("Email prazan");
		
		if(SOUserExists.execute(username))
			throw new Exception("Username zauzet");
		
		if(SOUserEmailExists.execute(email))
			throw new Exception("Email zauzet");
		
		if(!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
			throw new Exception("Email mora biti validan");
		
		if(!SOUserRegister.execute(username, password, email))
			throw new Exception("Doslo je do greske");
		
	}

	@Override
	public void login(String username, String password) throws Exception {
		if(!isGuest()){
			throw new Exception("Vec ste ulogovani");
		}
		if(username.isEmpty() || password.isEmpty()){
			throw new Exception("Unesi username i lozinku");
		}
		
		this.user = SOUserLogin.execute(username, password);
		if(this.user == null){
			if(SOUserExists.execute(username)){
				throw new Exception("error password");
			}
			throw new Exception("error username");
		}
			


	}

	@Override
	public void logout() {
		this.user = null;
	}
	

	@Override
	public String resetPassword(String username) {
		Random rnd = new Random();
		String npwd = String.valueOf(1000 + rnd.nextInt(9000));
		
		if(!SOUserSetPassword.execute(username, npwd))
			throw new RuntimeException("Ne postoji takav korisnik");
		
		
		return npwd;
	}

	@Override
	public void changePassword(String old_password, String new_password) {
		if(isGuest())
			throw new RuntimeException("Morate biti ulogovani");
		
		if(!SOGenerateSHA2.execute(old_password).equals(this.user.getPassword()))
			throw new RuntimeException("Pogresna lozinka");		
		
		SOUserSetPassword.execute(this.user.getUsername(), new_password);
		
	}

	@Override
	public LinkedList<Movie> getAllMovies() {
		LinkedList<Movie> movies = SOMovieLoadAll.execute();
		for(int i=0; i<movies.size(); i++){
			if(movies.get(i).getStatus() == Movie.STATUS_INACTIVE)
				movies.remove(i);
		}
		return movies;
	}

	@Override
	public Movie loadMovie(int movie_id) {
		LinkedList<Movie> movies = getAllMovies();
		for(Movie movie : movies){
			if(movie.getId() == movie_id)
				return movie;
		}
		return null;
	}

	@Override
	public LinkedList<Showtime> getAllMovieShowings(int movie_id) {
		return SOShowtimeLoadByMovie.execute(movie_id);
	}

	@Override
	public int getShowtimeSpace(int showtime_id) {
		int used = SOShowtimeNumOfReservations.execute(showtime_id);
		
		return Showtime.seats - used;
	}

	@Override
	public void makeReservation(int showtime_id, int number_of_seats) {
		if(isGuest())
			throw new RuntimeException("Morate biti ulogovani");
		
		SOReservationInsert.execute(showtime_id, this.user.getId(),number_of_seats);
	}

	@Override
	public LinkedList<Reservation> getUserReservations() {
		if(isGuest())
			throw new RuntimeException("Morate biti ulogovani");
		
		return SOReservationLoadByUser.execute(this.user.getId());
	}

	@Override
	public void deleteReservation(int reservation_id) {
		if(isGuest())
			throw new RuntimeException("Morate biti ulogovani");
		
		SOReservationRemove.execute(reservation_id);
	}






	


}
