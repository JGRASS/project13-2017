package ticketplex;

public class Reservation {
	private int id;
	private int showtime_id;
	private int user_id;

	private String _movie;
	private long _showtime_timestamp;


	public Reservation(int id, int showtime_id, int user_id) {
		this.id = id;
		this.showtime_id = showtime_id;
		this.user_id = user_id;
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
	
	
	
	
}
