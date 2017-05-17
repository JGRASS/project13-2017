package ticketplex;

import java.util.LinkedList;
import java.util.Random;

import ticketplex.interfaces.TicketplexClientInterface;
import ticketplex.systemoperations.SOUserEmailExists;
import ticketplex.systemoperations.SOGenerateSHA2;
import ticketplex.systemoperations.SOMovieLoadAll;
import ticketplex.systemoperations.SOReservationInsert;
import ticketplex.systemoperations.SOReservationLoadByUser;
import ticketplex.systemoperations.SOReservationRemove;
import ticketplex.systemoperations.SOShowtimeLoadByMovie;
import ticketplex.systemoperations.SOShowtimeNumOfReservationsSeats;
import ticketplex.systemoperations.SOUserExists;
import ticketplex.systemoperations.SOUserLogin;
import ticketplex.systemoperations.SOUserRegister;
import ticketplex.systemoperations.SOUserSetPassword;

/**
 * Sistemski kontroler zadužen za sve funkcije korisničkog dela programa
 * 
 * @author Kristina
 * @version 1.0
 */
public class TicketplexClient implements TicketplexClientInterface {

	/**
	 * Označava trenutnog korisnika u aplikaciji. Podrazumeva se da korisnik
	 * nije ulogovan.
	 */
	public User user = null;

	/**
	 * Funkcija proverava da li je korisnik ulogovan
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isGuest() {
		return (user == null) ? true : false;
	}

	/**
	 * Funkcija ubacuje novog korisnika u bazu
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @throws Exception ako: 
	 * <ul>
	 * <li>je korisnik već ulogovan</li>
	 * <li>je polje za username prazno ili nepopunjeno</li>
	 * <li>je unet username kraći od 4 znaka</li>
	 * <li>je polje za lozinku prazno ili nepopunjeno</li>
	 * <li>je lozinka kraća od 4 znaka</li>
	 * <li>je polje za email prazno</li>
	 * <li>korisnik sa unetim username-om već postoji</li>
	 * <li>korisnik sa unetim email-om već postoji</li>
	 * <li>nije unet validan email</li>
	 * <li>je došlo do greške u izvršavanju procesa registracije</li>
	 * </ul>
	 */
	@Override
	public void register(String username, String password, String email) throws Exception {
		if (!isGuest()) {
			throw new Exception("Već ste ulogovani!");
		}
		if (username.isEmpty() || username.equals("Username"))
			throw new Exception("Username prazan");

		if (username.length() < 4)
			throw new Exception("Unesite duzi username");

		if (password.isEmpty() || password.equals("Password"))
			throw new Exception("Lozinka prazana");

		if (password.length() < 4)
			throw new Exception("Unesite duzu lozinku");

		if (email.isEmpty())
			throw new Exception("Popunite email!");

		if (SOUserExists.execute(username))
			throw new Exception("Username već postoji!");

		if (SOUserEmailExists.execute(email))
			throw new Exception("Email već postoji!");

		if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
			throw new Exception("Email mora biti validan!");

		if (!SOUserRegister.execute(username, password, email))
			throw new Exception("Doslo je do greške");

	}

	/**
	 * Funkcija služi za prijavljivanje korisnika
	 * 
	 * @param username
	 * @param password
	 * @throws Exception ako je:
	 * <ul>
	 * <li>korisnik već ulogovan</li>
	 * <li>polje za username i/ili polje lozinku je prazno</li>
	 * <li>uneta pogrešna lozinka</li>
	 * <li>unet pogrešan username</li>
	 * </ul>
	 */
	@Override
	public void login(String username, String password) throws Exception {
		if (!isGuest()) {
			throw new Exception("Već ste ulogovani!");
		}
		if (username.isEmpty() || password.isEmpty()) {
			throw new Exception("Unesite username i lozinku!");
		}

		this.user = SOUserLogin.execute(username, password);
		if (this.user == null) {
			if (SOUserExists.execute(username)) {
				throw new Exception("error password");
			}
			throw new Exception("error username");
		}

	}

	/**
	 * Funkcija služi za odjavljivanje korisnika
	 */

	@Override
	public void logout() {
		this.user = null;
	}

	/**
	 * Funkcija služi za resetovanje lozinke. Generiše nasumično lozinku i
	 * dodeljuje je korisniku.
	 * 
	 * @param username korisnika za koji se generiše šifra
	 * @throws Exception ako uneti username ne postoji
	 * @return String nova lozinka
	 */

	@Override
	public String resetPassword(String username) throws Exception {
		Random rnd = new Random();
		String npwd = String.valueOf(1000 + rnd.nextInt(9000));
		
		if (username.isEmpty() || username.equals("Username")) {
			throw new Exception("Unesite username!");
		}
		
		if (!SOUserSetPassword.execute(username, npwd))
			throw new Exception("Ne postoji takav korisnik!");

		return npwd;
	}

	/**
	 * Funkcija služi da korisnik sam promeni svoju lozinku
	 * 
	 * @param old_password
	 * @param new_password
	 * @throws Exception ako:
	 * <ul>
	 * <li>korisnik nije ulogovan</li>
	 * <li>je uneta stara lozinka pogrešna</li>
	 * <li>je polje za novu lozinku prazno ili nepopunjeno</li>
	 * <li>je nova lozinka kraća od 4 znaka</li>
	 * </ul>
	 */

	@Override
	public void changePassword(String old_password, String new_password) throws Exception {
		if (isGuest())
			throw new Exception("Morate biti ulogovani!");

		if (!SOGenerateSHA2.execute(old_password).equals(this.user.getPassword()))
			throw new Exception("Pogrešna stara lozinka!");

		if (old_password.isEmpty() || old_password.equals("Stara lozinka"))
			throw new Exception("Unesite staru lozinku");
		
		if (new_password.isEmpty() || new_password.equals("Nova lozinka"))
			throw new Exception("Unesite novu lozinku");

		if (new_password.length() < 4)
			throw new Exception("Unesite duzu lozinku");

		SOUserSetPassword.execute(this.user.getUsername(), new_password);
		this.user.setPassword(SOGenerateSHA2.execute(new_password));

	}

	/**
	 * Funkcija vraća sve filmove iz baze
	 * 
	 * @return LinkedList<Movie>
	 */

	@Override
	public LinkedList<Movie> getAllMovies() {
		LinkedList<Movie> movies = SOMovieLoadAll.execute();
		for (int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getStatus() == Movie.STATUS_INACTIVE)
				movies.remove(i);

		}
		return movies;
	}

	/**
	 * Funkcija koja vraća određeni film
	 * @param movie_id
	 * @return film 
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */

	@Override
	public Movie loadMovie(int movie_id) {
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
	 * Funkcija koja vraća listu svih projekcija datog filma
	 * @param movie_id
	 * @return lista projekcija
	 * @throws RuntimeException Ako je ID filma nepozitivan broj
	 */
	@Override
	public LinkedList<Showtime> getAllMovieShowings(int movie_id) {
		if(movie_id <= 0)
			throw new RuntimeException("ID filma mora biti pozitivan broj");
		
		return SOShowtimeLoadByMovie.execute(movie_id);
	}

	/**
	 * Funkcija koja proverava koliko je ostalo slobodnih mesta za projekciju
	 * @param showtime_id
	 * @throws RuntimeException Ako je ID prikazivanja nepozitivan broj
	 * @return broj slobodnih mesta 
	 */
	@Override
	public int getShowtimeSpace(int showtime_id) {
		if(showtime_id <= 0)
			throw new RuntimeException("ID prikazivanja mora biti pozitivan broj");
		
		int used = SOShowtimeNumOfReservationsSeats.execute(showtime_id);
		return Showtime.seats - used;
	}
	/**
	 * Funkcija koja služi za rezervisanje projekcije
	 * @param showtime_id
	 * @param number_of_seats
	 * @throws RuntimeException Ako je ID prikazivanja nepozitivan broj
	 * @throws Exception ako korisnik nije ulogovan
	 * @throws Exception ako nema dovoljno slobodnih mesta
	 */
	@Override
	public void makeReservation(int showtime_id, int number_of_seats) throws Exception {
		if (isGuest())
			throw new Exception("Morate biti ulogovani");
		
		if(showtime_id <= 0)
			throw new RuntimeException("ID prikazivanja mora biti pozitivan broj");
		
		int space = getShowtimeSpace(showtime_id);
		if(space <= 0)
			throw new Exception("Sva mesta su zauzeta");
		
		if(space < number_of_seats){
			if(space == 1) throw new Exception("Ostalo je samo jedno slobodno mesto");
			if(space < 5) throw new Exception("Ostalo je samo "+space+" slobodna mesta");
			throw new Exception("Ostalo je samo "+space+" slobodnih mesta");
		}
			

		SOReservationInsert.execute(showtime_id, this.user.getId(), number_of_seats);
	}
	/**
	 * Funkcija vraća rezervacije ulogovanog korisnika.
	 * @return rezervacije
	 * @throws Exception ako korisnik nije ulogovan
	 */
	@Override
	public LinkedList<Reservation> getUserReservations() throws Exception {
		if (isGuest())
			throw new Exception("Morate biti ulogovani!");

		return SOReservationLoadByUser.execute(this.user.getId());
	}
	
	/**
	 * Funkcija briše rezervaciju ulogovanog korisnika.
	 * @param reservation_id ID rezervacije
	 * @throws RuntimeException Ako je ID rezervacije nepozitivan broj
	 * @throws Exception ako korisnik nije ulogovan
	 */
	@Override
	public void deleteReservation(int reservation_id) throws Exception {
		if (isGuest())
			throw new Exception("Morate biti ulogovani");
		
		if(reservation_id <= 0)
			throw new RuntimeException("ID rezervacije mora biti pozitivan broj");

		SOReservationRemove.execute(reservation_id, this.user.getId());
	}

}
