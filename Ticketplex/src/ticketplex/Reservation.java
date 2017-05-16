package ticketplex;

import java.util.GregorianCalendar;

/**
 * Klasa predstavlja rezervaciju
 * 
 * @author skakac
 * @version 1.0
 */
public class Reservation {
	/**
	 * Predstavlja ID rezervacija
	 */
	private int id;
	/**
	 * Predstavlja ID projekcije za koju je vezan ova rezervacija
	 */
	private int showtime_id;
	/**
	 * Predstavlja ID korisnika koji je napravio ovu rezervaciju
	 */
	private int user_id;
	/**
	 * Predstavlja broj rezervisanih mesta od strane korisnika
	 */
	private int number_of_seats;

	/**
	 * Predstavlja ime filma za koji je vezana rezervacija
	 */
	private String _movie;
	/**
	 * Predstavlja vreme projekcije
	 */
	private long _showtime_timestamp;

	/**
	 * Konstruktor klase
	 * 
	 * @param id
	 * @param showtime_id
	 * @param user_id
	 * @param number_of_seats
	 */
	public Reservation(int id, int showtime_id, int user_id, int number_of_seats) {
		this.id = id;
		this.showtime_id = showtime_id;
		this.user_id = user_id;
		this.number_of_seats = number_of_seats;
	}

	public String getMovieName() {
		return _movie;

	}

	public void setMovieName(String name) {
		this._movie = name;
	}

	public long getShowtimeTimesamp() {
		return _showtime_timestamp;

	}

	public void setShowtimeTimesamp(long timestamp) {
		this._showtime_timestamp = timestamp;
	}

	public int getShowtime_id() {
		return showtime_id;
	}

	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	/**
	 * Funkcija vraća vreme projekcije
	 * 
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getDateAsCalendar() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(this._showtime_timestamp);
		return cal;
	}

}
