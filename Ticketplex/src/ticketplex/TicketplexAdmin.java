package ticketplex;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import ticketplex.interfaces.TicketplexAdminInterface;
import ticketplex.systemoperations.SOReservationLoadByMovie;
import ticketplex.systemoperations.SOReservationLoadByUser;
import ticketplex.systemoperations.SOReservationRemove;
import ticketplex.systemoperations.SOShowtimeLoadByMovie;
import ticketplex.systemoperations.SOShowtimeNumOfReservations;
import ticketplex.systemoperations.SOShowtimeNumOfReservationsSeats;
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
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public Movie getMovie(int movie_id) {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
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
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public LinkedList<Showtime> getAllMovieShowings(int movie_id) {	
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
		LinkedList<Showtime> showtimes = SOShowtimeLoadByMovie.execute(movie_id);
		for (Showtime showtime : showtimes) {
			showtime.setNumOfReservations(SOShowtimeNumOfReservations.execute(showtime.getId()));
			showtime.setNumOfSeats(SOShowtimeNumOfReservationsSeats.execute(showtime.getId()));
		}

		return showtimes;
	}
	
	/**
	 * Funkcija vraca listu svih rezervacija za odredjeni film
	 * @param movie_id id filma
	 * @return lista rezervacija
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public LinkedList<Reservation> getAllMovieReservations(int movie_id) {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
		return SOReservationLoadByMovie.execute(movie_id);
	}
	
	/**
	 * Funkcija vraca ukupan broj rezervacija za odredjeni film
	 * @param movie_id id filma
	 * @return ukupan broj rezervacija
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public int getMovieNumOfReservations(int movie_id) {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
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
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
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
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
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
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 * @throws Exception Ako status nije jedan od predefinisanih iz klase Movie
	 */
	@Override
	public void setMovieStatus(int movie_id, int status) throws Exception {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
		if (status != Movie.STATUS_ACTIVE && status != Movie.STATUS_INACTIVE) {
			throw new Exception("Nevazeci status");
		}

		SOMovieUpdateStatus.execute(movie_id, status);

	}
	/**
	 * Funkcija brise film
	 * @param movie_id id filma koje se brise
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public void removeMovie(int movie_id) {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
		SOMovieDelete.execute(movie_id);

	}
	
	/**
	 * Funkcija dodaje prikazivanje za odredjeni film
	 * @param movie_id id filma
	 * @param timestamp vreme prikazivanja
	 * @throws Exception Ako je id filma negativan, ili vreme prikazivanja u proslosti
	 */
	@Override
	public void addMovieShowtime(int movie_id, long timestamp) throws Exception {
		if (movie_id <= 0) {
			throw new Exception("ID filma ne sme biti nepozitivan broj");
		}
		
		GregorianCalendar now = new GregorianCalendar();
		now.add(GregorianCalendar.MONTH, -1);
		
		if (timestamp < now.getTimeInMillis()) {
			throw new Exception("Vreme prikazivanja mora biti u buducnosti");
		}
		SOShowtimeInsert.execute(movie_id, timestamp);

	}
	
	/**
	 * Funkcija brise prikazivanje filma
	 * @oaram showtime_id id prikazivanja
	 * @throws RuntimeException Ako je ID prikazivanja nepozitivan broj
	 */
	@Override
	public void removeMovieShowtime(int showtime_id) {
		if(showtime_id <= 0)
			throw new RuntimeException("ID prikazivanja mora biti pozitivan broj");
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
	 * @throws RuntimeException Ako je ID korisnika nepozitivan broj
	 */
	@Override
	public void removeUser(int user_id) {
		if(user_id <= 0)
			throw new RuntimeException("ID korisnika mora biti pozitivan broj");
		SOUserDelete.execute(user_id);

	}
	
	/**
	 * Funkcija vraca listu svih rezervacija jednog korisnika
	 * @param user_id id korisnika
	 * @return lista rezervacija
	 * @throws RuntimeException Ako je ID korisnika nepozitivan broj
	 */
	@Override
	public LinkedList<Reservation> getUserReservations(int user_id) {
		if(user_id <= 0)
			throw new RuntimeException("ID korisnika mora biti pozitivan broj");
		
		return SOReservationLoadByUser.execute(user_id);
	}

	/**
	 * Funkcija brise odredjenu rezervaciju jednog korisnika
	 * @param reservation_id id rezervacije
	 * @param user_id id korisnika kome pripada rezervacija
	 * @throws RuntimeException Ako je ID rezervacije nepozitivan broj
	 */
	@Override
	public void removeReservation(int reservation_id, int user_id) {
		if(reservation_id <= 0)
			throw new RuntimeException("ID rezervacije mora biti pozitivan broj");
		if(user_id <= 0)
			throw new RuntimeException("ID korisnika mora biti pozitivan broj");
		
		SOReservationRemove.execute(reservation_id, user_id);

	}

}
