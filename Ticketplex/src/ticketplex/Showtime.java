package ticketplex;

public class Showtime {
	
	private int id;
	private int movie_id;
	private int screen_id;
	private int datetime;

	public Showtime(int id, int movie_id, int screen_id, int datetime) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.screen_id = screen_id;
		this.datetime = datetime;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public int getDatetime() {
		return datetime;
	}

	public void setDatetime(int datetime) {
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}
	
	
	
	
}
