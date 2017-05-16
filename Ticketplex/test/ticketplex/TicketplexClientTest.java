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
	
	//testLoginUserEmpty
	//testLoginPasswordEmpty
	
	//testChangePasswordUser
	//testChangePasswordNewPasswordEmpty
	//testChangePasswordNewPasswordShort
	
	//testGetShowtimeSpaceIDNegative
	//testMakeReservationUser
	//testMakeReservationIDNegative
	
	//testGetUserReservationUser
	//testDeleteReservationIDNegative
	

}
