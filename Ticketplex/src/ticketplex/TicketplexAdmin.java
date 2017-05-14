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
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception {
		if(name == null || name.isEmpty())
			throw new Exception("Ime je obavezno.");
		if(year <= 0)
			throw new Exception("Godina je obavezna.");
		if(genre == null || genre.isEmpty())
			throw new Exception("Zanr je obavezan.");
		if(description == null || description.isEmpty())
			throw new Exception("Opis je obavezan.");
		if(cast == null || cast.isEmpty())
			throw new Exception("Uloge su obavezne.");
		if(director == null || director.isEmpty())
			throw new Exception("Reziser je obavezno.");
		if(length <= 0)
			throw new Exception("Duzina filma mora biti pozitivan broj.");
		
		if(imdbRating == null || imdbRating.isEmpty())
			throw new Exception("IMDB ocena je obavezna");
		try {
			if(Float.parseFloat(imdbRating) < 0 || Float.parseFloat(imdbRating) > 10)
				throw new Exception("IMDB ocena mora biti izmedju 0 i 10");
		} catch (NumberFormatException e) {
			throw new Exception("IMDB ocena mora biti broj");
		}
		if(imdbLink == null || imdbLink.isEmpty())
			throw new Exception("Imdb link je obavezan.");
		if(img == null)
			throw new Exception("Poster je obavezan.");
		
		
		SOMovieInsert.execute(name, year, genre, description, cast, director, length, imdbRating, imdbLink, img, Movie.STATUS_ACTIVE);
		
	}

	@Override
	public void setMovieStatus(int movie_id, int status) throws Exception {
		if(status != Movie.STATUS_ACTIVE && status != Movie.STATUS_INACTIVE){
			throw new Exception("Nevazeci status");
		}
		
		SOMovieUpdateStatus.execute(movie_id, status);
		
	}
	
	@Override
	public void removeMovie(int movie_id) {
		SOMovieDelete.execute(movie_id);
		
	}
	@Override
	public void addMovieShowtime(int movie_id, long timestamp) throws Exception {
		GregorianCalendar now = new GregorianCalendar();
		now.add(GregorianCalendar.MONTH, -1);
		
		if(timestamp < now.getTimeInMillis()){
			throw new Exception("Vreme prikazivanja mora biti u buducnosti");
		}
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
