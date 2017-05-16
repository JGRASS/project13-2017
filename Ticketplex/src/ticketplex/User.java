package ticketplex;
/**
 * Klasa predstavlja korisnika koji je registrovan
 * @author skakac
 *
 */
public class User {
	/**
	 * Označava id korisnika
	 */
	private int id;
	/**
	 * Označava jedinstveni username korisnika
	 */
	private String username;
	/**
	 * Označava šifru korisnika
	 */
	private String password;
	/**
	 *  Označava email korisnika
	 */
	private String email;

	/**
	 * Konstruktor klase User
	 * @param id
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}
	
	
	
	
	
}
