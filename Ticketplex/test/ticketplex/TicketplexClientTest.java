package ticketplex;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TicketplexClientTest {

	static TicketplexClient tc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tc = new TicketplexClient();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		tc = null;
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		tc.logout();
	}

	@Test
	public void testIsGuest() {
		assertEquals(true, tc.isGuest());
	}
	
	@Test
	public void testIsGuestUser() {
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		assertEquals(false, tc.isGuest());
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testRegisterLoggedin() throws Exception{
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		tc.register("username", "password", "test@gmail.com");
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testRegisterUsernameNull() throws Exception{
		tc.register(null, "password", "test@gmail.com");
	}
	@Test(expected=java.lang.Exception.class)
	public void testRegisterUsernameEmpty() throws Exception{
		tc.register("", "password", "test@gmail.com");
	}
	@Test(expected=java.lang.Exception.class)
	public void testRegisterUsernameShort() throws Exception{
		tc.register("asd", "password", "test@gmail.com");
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testRegisterPasswordNull() throws Exception{
		tc.register("username", null, "test@gmail.com");
	}
	@Test(expected=java.lang.Exception.class)
	public void testRegisterPasswordEmpty() throws Exception{
		tc.register("username", "", "test@gmail.com");
	}
	@Test(expected=java.lang.Exception.class)
	public void testRegisterPasswordShort() throws Exception{
		tc.register("username", "asd", "test@gmail.com");
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testRegisterEmailInvalid() throws Exception{
		tc.register("username", "password", "testgmail.com");
	}
	@Test(expected=java.lang.Exception.class)
	public void testRegisterEmailInvalid2() throws Exception{
		tc.register("username", "password", "test@gmail");
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testLoginUser() throws Exception{
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		tc.login("username", "password");
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testLoginUserEmpty() throws Exception{
		tc.login("", "password");
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testLoginPasswordEmpty() throws Exception{
		tc.login("username", "");
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testChangePasswordUser() throws Exception{
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		
		tc.changePassword("old_pass", "new_pass");
	}	
	@Test(expected=java.lang.Exception.class)
	public void testChangePasswordNewPasswordEmpty() throws Exception{	
		tc.changePassword("old_pass", "");
	}	
	@Test(expected=java.lang.Exception.class)
	public void testChangePasswordNewPasswordShort() throws Exception{	
		tc.changePassword("old_pass", "asd");
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testGetShowtimeSpaceIDNegative(){
		tc.getShowtimeSpace(-1);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testMakeReservationGuest() throws Exception{
		tc.makeReservation(1, 3);
	}	
	@Test(expected=java.lang.Exception.class)
	public void testMakeReservationIDNegative() throws Exception{		
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		
		tc.makeReservation(-1, 3);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testGetUserReservationGuest() throws Exception{
		
		tc.getUserReservations();
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testDeleteReservationGuest() throws Exception{
		tc.deleteReservation(1);
	}
	@Test(expected=java.lang.Exception.class)
	public void testDeleteReservationIDNegative() throws Exception{
		User user = new User(1, "username", "password", "test@gmail.com");
		tc.user = user;
		
		tc.deleteReservation(-1);
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testGetAllMovieShowings(){		
		tc.getAllMovieShowings(-1);
	}
	
	

}
