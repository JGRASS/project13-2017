package ticketplex;

import java.util.GregorianCalendar;

public class Reservation {
	private int id;
	private int showtime_id;
	private int user_id;
	private int number_of_seats;
	

	private String _movie;
	private long _showtime_timestamp;


	public Reservation(int id, int showtime_id, int user_id, int number_of_seats) {
		this.id = id;
		this.showtime_id = showtime_id;
		this.user_id = user_id;
		this.number_of_seats=number_of_seats;
	}

	public String getMovieName(){
		return _movie;
		
	}
	public void setMovieName(String name){
		this._movie = name;
	}
	
	public long getShowtimeTimesamp(){
		return _showtime_timestamp;
		
	}
	public void setShowtimeTimesamp(long timestamp){
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
	

	public GregorianCalendar getDateAsCalendar(){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(this._showtime_timestamp);
		return cal;
	}

	
	
	
}
