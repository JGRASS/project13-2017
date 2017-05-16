package ticketplex;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import ticketplex.interfaces.TicketplexAdminInterface;
import ticketplex.systemoperations.SOReservationLoadByMovie;
import ticketplex.systemoperations.SOReservationLoadByUser;
import ticketplex.systemoperations.SOReservationRemove;
import ticketplex.systemoperations.SOShowtimeLoadByMovie;
import ticketplex.systemoperations.SOShowtimeNumOfReservations;
import ticketplex.systemoperations.SOShowtimeDelete;
import ticketplex.systemoperations.SOShowtimeInsert;
import ticketplex.systemoperations.SOUserDelete;
import ticketplex.systemoperations.SOUserLoadAll;
import ticketplex.systemoperations.SOMovieDelete;
import ticketplex.systemoperations.SOMovieInsert;
import ticketplex.systemoperations.SOMovieLoadAll;
import ticketplex.systemoperations.SOMovieUpdate;
import ticketplex.systemoperations.SOMovieUpdateStatus;
/**
 * Sistemski kontroler za admin deo programa
 * @author Milica
 * @version 1.0
 *
 */
public class TicketplexAdmin implements TicketplexAdminInterface {

	/**
	 * Funkcija koja vraca listu svih filmova iza baze
	 * @return lista svih filmova
	 */
	@Override
	public LinkedList<Movie> getAllMovies() {
		return SOMovieLoadAll.execute();
	}

	/**
	 * Funkcija vraca objekta trazenog filma
	 * @param movie_id id filma
	 * @return objekat movie ili null u slucaju da nije pronadjen film
	 */
	@Override
	public Movie getMovie(int movie_id) {
		LinkedList<Movie> movies = getAllMovies();
		for (Movie movie : movies) {
			if (movie.getId() == movie_id)
				return movie;
		}
		return null;
	}
	
	/**
	 * Funkcija vraca listu svih prikazivanja odredjednog filma
	 * @param movie_id id filma
	 * @return lista prikazivanja
	 */
	@Override
	public LinkedList<Showtime> getAllMovieShowings(int movie_id) {		
		LinkedList<Showtime> showtimes = SOShowtimeLoadByMovie.execute(movie_id);
		for (Showtime showtime : showtimes) {
			showtime.setNumOfReservations(SOShowtimeNumOfReservations.execute(showtime.getId()));
		}

		return showtimes;
	}
	
	/**
	 * Funkcija vraca listu svih rezervacija za odredjeni film
	 * @param movie_id id filma
	 * @return lista rezervacija
	 */
	@Override
	public LinkedList<Reservation> getAllMovieReservations(int movie_id) {
		return SOReservationLoadByMovie.execute(movie_id);
	}
	
	/**
	 * Funkcija vraca ukupan broj rezervacija za odredjeni film
	 * @param movie_id id filma
	 * @return ukupan broj rezervacija
	 */
	@Override
	public int getMovieNumOfReservations(int movie_id) {
		LinkedList<Showtime> showtimes = getAllMovieShowings(movie_id);
		int res = 0;
		for (Showtime showtime : showtimes) {
			res += showtime.getNumOfReservations();
		}

		return res;
	}
	
	/**
	 * Funkcija dodaje film u bazu
	 * @param name ime filma
	 * @param year godina izlaska filma
	 * @param genre zanr filma
	 * @param description kratak opis filma
	 * @param cast uloge
	 * @param director reziser
	 * @param length trajanje filma u minutima
	 * @param imdbRating ocena filma
	 * @param imdbLink imdb link filma
	 * @param img slika/poster filma
	 * @throws Exception U sledeci slucajevima
	 * 	<ul>
	 * 	<li>Prazan bilo koji parametar</li>
	 * 	<li>Godina negativan broj</li>
	 * 	<li>Trajanje filma negativan broj</li>
	 * 	<li>IMDB ocena nije izmedju 0 i 10</li>
	 * 	</ul>
	 */
	@Override
	public void addMovie(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception {
		if (name == null || name.isEmpty())
			throw new Exception("Ime je obavezno.");
		if (year <= 0)
			throw new Exception("Godina je obavezna.");
		if (genre == null || genre.isEmpty())
			throw new Exception("Zanr je obavezan.");
		if (description == null || description.isEmpty())
			throw new Exception("Opis je obavezan.");
		if (cast == null || cast.isEmpty())
			throw new Exception("Uloge su obavezne.");
		if (director == null || director.isEmpty())
			throw new Exception("Reziser je obavezno.");
		if (length <= 0)
			throw new Exception("Duzina filma mora biti pozitivan broj.");

		if (imdbRating == null || imdbRating.isEmpty())
			throw new Exception("IMDB ocena je obavezna");
		try {
			if (Float.parseFloat(imdbRating) < 0 || Float.parseFloat(imdbRating) > 10)
				throw new Exception("IMDB ocena mora biti izmedju 0 i 10");
		} catch (NumberFormatException e) {
			throw new Exception("IMDB ocena mora biti broj");
		}
		if (imdbLink == null || imdbLink.isEmpty())
			throw new Exception("Imdb link je obavezan.");
		if (img == null)
			throw new Exception("Poster je obavezan.");

		SOMovieInsert.execute(name, year, genre, description, cast, director, length, imdbRating, imdbLink, img,
				Movie.STATUS_ACTIVE);

	}
	
	/**
	 * Funkcija menja parametre filma
	 * @oaran movie_id id filma koji se menja
	 * @param name ime filma
	 * @param year godina izlaska filma
	 * @param genre zanr filma
	 * @param description kratak opis filma
	 * @param cast uloge
	 * @param director reziser
	 * @param length trajanje filma u minutima
	 * @param imdbRating ocena filma
	 * @param imdbLink imdb link filma
	 * @param img slika/poster filma
	 * @throws Exception U sledeci slucajevima
	 * 	<ul>
	 * 	<li>Prazan bilo koji parametar osim slike</li>
	 * 	<li>Godina negativan broj</li>
	 * 	<li>Trajanje filma negativan broj</li>
	 * 	<li>IMDB ocena nije izmedju 0 i 10</li>
	 * 	</ul>
	 */
	@Override
	public void editMovie(int movie_id, String name, int year, String genre, String description, String cast,
			String director, int length, String imdbRating, String imdbLink, byte[] img) throws Exception {
		if (movie_id <= 0)
			throw new Exception("Film je obavezan.");
		
		if (name == null || name.isEmpty())
			throw new Exception("Ime je obavezno.");
		if (year <= 0)
			throw new Exception("Godina je obavezna.");
		if (genre == null || genre.isEmpty())
			throw new Exception("Zanr je obavezan.");
		if (description == null || description.isEmpty())
			throw new Exception("Opis je obavezan.");
		if (cast == null || cast.isEmpty())
			throw new Exception("Uloge su obavezne.");
		if (director == null || director.isEmpty())
			throw new Exception("Reziser je obavezno.");
		if (length <= 0)
			throw new Exception("Duzina filma mora biti pozitivan broj.");

		if (imdbRating == null || imdbRating.isEmpty())
			throw new Exception("IMDB ocena je obavezna");
		try {
			if (Float.parseFloat(imdbRating) < 0 || Float.parseFloat(imdbRating) > 10)
				throw new Exception("IMDB ocena mora biti izmedju 0 i 10");
		} catch (NumberFormatException e) {
			throw new Exception("IMDB ocena mora biti broj");
		}
		if (imdbLink == null || imdbLink.isEmpty())
			throw new Exception("Imdb link je obavezan.");

		
		SOMovieUpdate.execute(movie_id, name, year, genre, description, cast, director, length, imdbRating, imdbLink, img,
				Movie.STATUS_ACTIVE);
	}
	
	/**
	 * Funkcija menja status filma (active/inactive)
	 * @oaram movie_id id filma
	 * @param status vrednost statusa
	 */
	@Override
	public void setMovieStatus(int movie_id, int status) throws Exception {
		if (status != Movie.STATUS_ACTIVE && status != Movie.STATUS_INACTIVE) {
			throw new Exception("Nevazeci status");
		}

		SOMovieUpdateStatus.execute(movie_id, status);

	}
	/**
	 * Funkcija brise film
	 * @param movie_id id filma koje se brise
	 */
	@Override
	public void removeMovie(int movie_id) {
		SOMovieDelete.execute(movie_id);

	}
	
	/**
	 * Funkcija dodaje prikazivanje za odredjeni film
	 * @param movie_id id filma
	 * @param timestamp vreme prikazivanja
	 */
	@Override
	public void addMovieShowtime(int movie_id, long timestamp) throws Exception {
		GregorianCalendar now = new GregorianCalendar();
		now.add(GregorianCalendar.MONTH, -1);

		if (movie_id <= 0) {
			throw new Exception("Movie id ne sme biti null");
		}
		if (timestamp < now.getTimeInMillis()) {
			throw new Exception("Vreme prikazivanja mora biti u buducnosti");
		}
		SOShowtimeInsert.execute(movie_id, timestamp);

	}
	
	/**
	 * Funkcija brise prikazivanje filma
	 * @oaram showtime_id id prikazivanja
	 */
	@Override
	public void removeMovieShowtime(int showtime_id) {
		SOShowtimeDelete.execute(showtime_id);

	}
	
	/**
	 * Funkcija vraca listu svih registrovanih korisnika
	 * @return lista korisnika
	 */
	@Override
	public LinkedList<User> getAllUsers() {
		return SOUserLoadAll.execute();
	}
	
	/**
	 * Funkcija brise odredjenog korisnika
	 * @param user_id id korisnika
	 */
	@Override
	public void removeUser(int user_id) {
		SOUserDelete.execute(user_id);

	}
	
	/**
	 * Funkcija vraca listu svih rezervacija jednog korisnika
	 * @param user_id id korisnika
	 * @return lista rezervacija
	 */
	@Override
	public LinkedList<Reservation> getUserReservations(int user_id) {
		return SOReservationLoadByUser.execute(user_id);
	}

	/**
	 * Funkcija brise odredjenu rezervaciju jednog korisnika
	 * @param reservation_id id rezervacije
	 */
	@Override
	public void removeReservation(int reservation_id) {
		SOReservationRemove.execute(reservation_id);

	}

}
