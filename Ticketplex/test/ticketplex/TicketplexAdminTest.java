package ticketplex;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TicketplexAdminTest {

	static TicketplexAdmin ta;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ta = new TicketplexAdmin();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ta = null;
	}


	@Test
	public void testGetMovie() {
		Movie movie = ta.getAllMovies().getFirst();
		assertEquals(movie.getName(), ta.getMovie(movie.getId()).getName());
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testGetMovieNegative() {
		 ta.getMovie(-1);
	}
	
	@Test
	public void testGetMovieOutOfList() throws Exception {
		Movie movie = ta.getAllMovies().getLast();
		assertEquals(null, ta.getMovie(movie.getId()+1));
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieNameNull() throws Exception{
		ta.addMovie(null, 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieNameEmpty() throws Exception{
		ta.addMovie("", 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieYearNegative() throws Exception{
		ta.addMovie("Name", -2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}	
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieGenreNull() throws Exception{
		ta.addMovie("Name", 2017, null, "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieGenreEmpty() throws Exception{
		ta.addMovie("Name", 2017, "", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieDescNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", null, "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieDescEmpty() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "", "uloge", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieCastNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", null, "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieCastEmpty() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "", "reziser", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieDirNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", null, 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieDirEmpty() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "", 123, "8.5", "http://google.com", new byte[]{0,1});
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieLengthNegative() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", -123, "8.5", "http://google.com", new byte[]{0,1});
	}

	@Test(expected=java.lang.Exception.class)
	public void testAddMovieRatingString() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "asd", "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieRatingOutOfRange() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "18.5", "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieRatingNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, null, "http://google.com", new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieRatingEmpty() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "", "http://google.com", new byte[]{0,1});
	}
	
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieImdbNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", null, new byte[]{0,1});
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieImdbEmpty() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "", new byte[]{0,1});
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieImgNull() throws Exception{
		ta.addMovie("Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", null);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testEditMovieIDNegative() throws Exception{
		ta.editMovie(-1, "Name", 2017, "zanr", "Opis", "uloge", "reziser", 123, "8.5", "http://google.com", null);
	}
	
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testSetMovieStatusIDNegative() throws Exception{
		ta.setMovieStatus(-1, Movie.STATUS_ACTIVE);
	}
	@Test(expected=java.lang.Exception.class)
	public void testSetMovieStatusStatusInvalid() throws Exception{
		ta.setMovieStatus(2, 33);
	}
	
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testRemoveMovie(){
		ta.removeMovie(-1);
	}
	
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieShowtimeIDNegative() throws Exception{
		ta.addMovieShowtime(-1, (new GregorianCalendar()).getTimeInMillis());
	}
	@Test(expected=java.lang.Exception.class)
	public void testAddMovieShowtimeDatePast() throws Exception{
		GregorianCalendar date = new GregorianCalendar();
		date.add(GregorianCalendar.YEAR, -1);
		ta.addMovieShowtime(1, date.getTimeInMillis());
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testRemoveShowtimeNegative(){
		ta.removeMovieShowtime(-1);
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testRemoveUserNegative(){
		ta.removeUser(-1);
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testGetUserResNegative(){
		ta.getUserReservations(-1);
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testRemoveReservationNegative(){
		ta.removeReservation(-1, 1);
	}
	@Test(expected=java.lang.RuntimeException.class)
	public void testRemoveReservationUserIDNegative(){
		ta.removeReservation(1, -1);
	}
	
	
	

}
