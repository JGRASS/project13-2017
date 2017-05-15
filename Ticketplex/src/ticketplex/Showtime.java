package ticketplex;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Showtime {
	static int seats = 120;
	
	private int id;
	private int movie_id;
	private long timestamp;
	private int _num_of_reservations;

	public Showtime(int id, int movie_id, int timestamp) {
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
	
	public GregorianCalendar getDateAsCalendar(){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(this.timestamp*1000);
		return cal;
	}
	
	public int getNumOfReservations(){
		return _num_of_reservations;
		
	}
	public void setNumOfReservations(int num){
		this._num_of_reservations = num;
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
