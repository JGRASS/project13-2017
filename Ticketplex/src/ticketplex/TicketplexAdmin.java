package ticketplex;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import ticketplex.interfaces.TicketplexAdminInterface;
import ticketplex.systemoperations.SOReservationLoadByMovie;
import ticketplex.systemoperations.SOReservationLoadByUser;
import ticketplex.systemoperations.SOReservationRemove;
import ticketplex.systemoperations.SOShowtimeLoadByMovie;
import ticketplex.systemoperations.SOShowtimeDelete;
import ticketplex.systemoperations.SOShowtimeInsert;
import ticketplex.systemoperations.SOUserDelete;
import ticketplex.systemoperations.SOUserLoadAll;
import ticketplex.systemoperations.SOMovieDelete;
import ticketplex.systemoperations.SOMovieInsert;
import ticketplex.systemoperations.SOMovieLoadAll;
import ticketplex.systemoperations.SOMovieUpdateStatus;

public class TicketplexAdmin implements TicketplexAdminInterface{
	public static void main(String[] args) {
		TicketplexAdmin ta = new TicketplexAdmin();
		
		LinkedList<Movie> mvs = ta.getAllMovies();
		LinkedList<Showtime> sts = ta.getAllMovieShowings(mvs.getFirst().getId());
		
		for(Showtime s : sts){
			System.out.println(s);
		}
		
		for(int i=0; i<100; i++){
			Random rnd = new Random();
			int n = 100000 + rnd.nextInt(900000);
			System.out.println(n);
		}
	}
	
	@Override
	public LinkedList<Movie> getAllMovies() {
		return SOMovieLoadAll.execute();
	}

	@Override
	public LinkedList<Showtime> getAllMovieShowings(int movie_id) {
		return SOShowtimeLoadByMovie.execute(movie_id);
	}

	@Override
	public LinkedList<Reservation> getAllMovieReservations(int movie_id) {
		return SOReservationLoadByMovie.execute(movie_id);
	}
	
	@Override
	public int getMovieNumOfReservations(int movie_id){
		LinkedList<Showtime> showtimes = getAllMovieShowings(movie_id);
		int res = 0;
		for(Showtime showtime : showtimes){
			res += showtime.getNumOfReservations();
		}
		
		return res;		
	}
	
	@Override
	public void addMovie(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) {
		
		SOMovieInsert.execute(name, year, genre, description, cast, director, length, imdbRating, imdbLink, img, Movie.STATUS_ACTIVE);
		
	}

	@Override
	public void setMovieStatus(int movie_id, int status) {
		if(status != Movie.STATUS_ACTIVE && status != Movie.STATUS_INACTIVE){
			throw new RuntimeException("Nevazeci status");
		}
		
		SOMovieUpdateStatus.execute(movie_id, status);
		
	}
	
	@Override
	public void removeMovie(int movie_id) {
		SOMovieDelete.execute(movie_id);
		
	}
	@Override
	public void addMovieShowtime(int movie_id, long timestamp) {
		SOShowtimeInsert.execute(movie_id, timestamp);
		
	}
	@Override
	public void removeMovieShowtime(int showtime_id) {
		SOShowtimeDelete.execute(showtime_id);
		
	}
	@Override
	public LinkedList<User> getAllUsers() {
		return SOUserLoadAll.execute();
	}
	@Override
	public void removeUser(int user_id) {
		SOUserDelete.execute(user_id);
		
	}
	@Override
	public LinkedList<Reservation> getUserReservations(int user_id) {
		return SOReservationLoadByUser.execute(user_id);
	}
	@Override
	public void removeReservation(int reservation_id) {
		SOReservationRemove.execute(reservation_id);
		
	}






}
