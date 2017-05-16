package ticketplex;


import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *Klasa predstavlja projekciju filma
 * @author skakac
 *
 */
public class Showtime {
	/**
	 * Označava broj sedišta
	 */
	public static int seats = 120;
	/**
	 * Označava id projekcije
	 */
	private int id;
	/**
	 * Označava id filma koji se prikazuje
	 */
	private int movie_id;
	/**
	 * Označava vreme projekcije
	 */
	private long timestamp;
	/**
	 *  Označava broj rezervacija napravljenih za ovu projekciju
	 */
	private int _num_of_reservations;
	/**
	 *  Označava broj zauzetih sedišta
	 */
	private int _num_of_seats;

	/**
	 * Konstruktor klase Showtime
	 * @param id
	 * @param movie_id
	 * @param timestamp
	 */
	public Showtime(int id, int movie_id, long timestamp) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.timestamp = timestamp;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}


	public long getDatetime() {
		return timestamp;
	}
		
	public void setDatetime(int datetime) {
		this.timestamp = datetime;
	}
	/**
	 * Funkcija vraća vreme projekcije
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getDateAsCalendar(){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(this.timestamp);
		return cal;
	}
	
	public int getNumOfReservations(){
		return _num_of_reservations;
		
	}
	public void setNumOfReservations(int num){
		this._num_of_reservations = num;

	}

	public int getNumOfSeats() {
		return _num_of_seats;
	}

	public void setNumOfSeats(int _num_of_seats) {
		this._num_of_seats = _num_of_seats;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String datum=fmt.format(getDateAsCalendar().getTime());
		
		return datum;
	}

	
	
	
	
}
