package ticketplex;

public class Reservation {
	private int id;
	private int showtime_id;
	private int user_id;
	

	public Reservation(int id, int showtime_id, int user_id) {
		this.id = id;
		this.showtime_id = showtime_id;
		this.user_id = user_id;
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
