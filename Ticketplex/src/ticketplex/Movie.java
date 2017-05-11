package ticketplex;

public class Movie {
	
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE   = 1;
	
	private int id;
	private String name;
	private int year;
	private String genre;
	private String description;
	private String cast, director;
	private int length;
	private String imdbRating;
	private String imdbLink;
	private byte[] img;
	
	private int status;
	
	/**
	 * @param id
	 * @param name
	 * @param year
	 * @param genre
	 * @param description
	 * @param cast
	 * @param director
	 * @param length
	 * @param imdbRating
	 * @param imdbLink
	 * @param img
	 * @param status
	 */
	public Movie(int id, String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img, int status) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.description = description;
		this.cast = cast;
		this.director = director;
		this.length = length;
		this.imdbRating = imdbRating;
		this.imdbLink = imdbLink;
		this.img = img;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getImdbLink() {
		return imdbLink;
	}
	public void setImdbLink(String imdbLink) {
		this.imdbLink = imdbLink;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
	
	
	
	
	
	
	
	
}
