package admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import admin.dialogs.NewMovieDialog;
import admin.dialogs.NewShowtimeDialog;
import ticketplex.Movie;
import ticketplex.Showtime;
import ticketplex.TicketplexAdmin;

public class AdminController {

	static AdminWindow adminWindow;
	static MovieWindow movieWindow;
	static UsersWindow usersWindow;
	static TicketplexAdmin ticketplexAdmin;
	static NewMovieDialog newMovieDialog;
	static NewShowtimeDialog newShowtimeDialog;
	
	
	public static void startAdminPanel() {
		ticketplexAdmin = new TicketplexAdmin();
		
		adminWindow = new AdminWindow();
		adminWindow.setVisible(true);
		adminWindow.setList(ticketplexAdmin.getAllMovies());
	}

	public static void addNewMovie() {
		if(newMovieDialog == null)
			newMovieDialog = new NewMovieDialog();
		newMovieDialog.setVisible(true);
	}
	
	public static void addNewShowtime(Movie movie) {
		if(newShowtimeDialog == null)
			newShowtimeDialog = new NewShowtimeDialog(movie);
		newShowtimeDialog.setVisible(true);
	}
	
	public static void showMovie(Movie movie){
		movieWindow  = new MovieWindow(movie);
		movieWindow.setMovie(movie);
		movieWindow.setList(ticketplexAdmin.getAllMovieShowings(movie.getId()));
		movieWindow.setVisible(true);
	}
	
	
	public static void showUsers(){
		usersWindow = new UsersWindow();
		usersWindow.setVisible(true);
	}
	
	
	
	
	public static void processRemoveMovie(Movie m){
		ticketplexAdmin.removeMovie(m.getId());
		adminWindow.setList(ticketplexAdmin.getAllMovies());
	}
	
	public static void processAddNewMovie(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception{
		ticketplexAdmin.addMovie(name, year, genre, description, cast, director, length, imdbRating, imdbLink, img);
		newMovieDialog.closeDialog();
		adminWindow.setList(ticketplexAdmin.getAllMovies());
	}
	
	public static int processGetNumberOfReservations(int movie_id){
		return ticketplexAdmin.getMovieNumOfReservations(movie_id);
	}
	
	
	public static void processAddNewShowtime(Movie movie, long timestamp){
		//todo
	}
	
	public static void processRemoveShowtime(Showtime s) {
		ticketplexAdmin.removeMovieShowtime(s.getId());
		movieWindow.setList(ticketplexAdmin.getAllMovieShowings(s.getMovie_id()));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// stackoverflow
	public static byte[] readImageFile(String file) {
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}


	
}
