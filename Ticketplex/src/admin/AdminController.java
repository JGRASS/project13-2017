package admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import admin.dialogs.EditMovieDialog;
import admin.dialogs.NewMovieDialog;
import admin.dialogs.NewShowtimeDialog;
import gui.Controller;
import ticketplex.Movie;
import ticketplex.Reservation;
import ticketplex.Showtime;
import ticketplex.TicketplexAdmin;
import ticketplex.User;

public class AdminController {

	static AdminWindow adminWindow;
	static MovieWindow movieWindow;
	static UsersWindow usersWindow;
	static TicketplexAdmin ticketplexAdmin;
	static EditMovieDialog editMovieDialog;
	static NewMovieDialog newMovieDialog;
	static NewShowtimeDialog newShowtimeDialog;
	static UserWindow userWindow;
	
	
	
	public static void startAdminPanel() {
		ticketplexAdmin = new TicketplexAdmin();
		
		adminWindow = new AdminWindow();
		adminWindow.setVisible(true);
		adminWindow.setList(ticketplexAdmin.getAllMovies());
	}
	
	public static void closeAdminPanel(){
		adminWindow.setVisible(false);
		adminWindow.dispose();
		adminWindow = null;
		hideMovieWindow();
		hideUsers();
		hideEditMovie();
		hideNewMovie();
		hideNewShowtime();
		hideUser();
		Controller.restartMainWindow();
	}

	public static void addNewMovie() {
		hideNewMovie();		
		if(newMovieDialog == null)
			newMovieDialog = new NewMovieDialog();
		newMovieDialog.setVisible(true);
	}
	public static void hideNewMovie(){
		if(newMovieDialog != null){
			newMovieDialog.setVisible(false);
			newMovieDialog.dispose();
			newMovieDialog = null;
		}
	}
	
	public static void addNewShowtime(Movie movie) {
		hideNewShowtime();		
		if(newShowtimeDialog == null)
			newShowtimeDialog = new NewShowtimeDialog(movie);
		newShowtimeDialog.setVisible(true);
	}
	public static void hideNewShowtime(){
		if(newShowtimeDialog != null){
			newShowtimeDialog.setVisible(false);
			newShowtimeDialog.dispose();
			newShowtimeDialog = null;
		}
	}
	
	public static void showMovie(int movie_id){
		hideMovieWindow();
		if(movieWindow == null)
			movieWindow  = new MovieWindow(ticketplexAdmin.getMovie(movie_id));
		movieWindow.setMovie(ticketplexAdmin.getMovie(movie_id));
		movieWindow.setList(ticketplexAdmin.getAllMovieShowings(movie_id));
		movieWindow.setVisible(true);
	}
	
	public static void hideMovieWindow(int movie_id){
		if(movieWindow == null || movieWindow.movie_id != movie_id) return;
		movieWindow.setVisible(false);
		hideMovieWindow();
		
		if(editMovieDialog != null){
			editMovieDialog.setVisible(false);
			hideEditMovie();
		}		
		
	}
	public static void hideMovieWindow(){
		if(movieWindow != null){
			movieWindow.setVisible(false);
			movieWindow.dispose();
			movieWindow = null;
		}
	}
	

	public static void showEditMovieDialog(int movie_id) {
		hideEditMovie();
		
		if(editMovieDialog == null)
			editMovieDialog = new EditMovieDialog();
		
		editMovieDialog.setMovie(ticketplexAdmin.getMovie(movie_id));
		editMovieDialog.setVisible(true);
		
	}
	public static void hideEditMovie(){
		if(editMovieDialog != null){
			editMovieDialog.setVisible(false);
			editMovieDialog.dispose();
			editMovieDialog = null;
		}
	}
	
	public static void showUsers(){
		hideUsers();
		if(usersWindow == null)
			usersWindow = new UsersWindow();
		usersWindow.setVisible(true);
		usersWindow.setList(ticketplexAdmin.getAllUsers());
	}
	public static void hideUsers(){
		if(usersWindow != null){
			usersWindow.setVisible(false);
			usersWindow.dispose();
			usersWindow = null;
		}
	}
	
	
	
	public static void processRemoveMovie(Movie m){
		ticketplexAdmin.removeMovie(m.getId());
		adminWindow.setList(ticketplexAdmin.getAllMovies());
		hideMovieWindow(m.getId());
		
	}
	
	public static void processAddNewMovie(String name, int year, String genre, String description, String cast, String director,
			int length, String imdbRating, String imdbLink, byte[] img) throws Exception{
		ticketplexAdmin.addMovie(name, year, genre, description, cast, director, length, imdbRating, imdbLink, img);
		newMovieDialog.closeDialog();
		adminWindow.setList(ticketplexAdmin.getAllMovies());
	}
	

	public static void processEditNewMovie(int movie_id, String name, int year, String genre, String description,
			String cast, String director, int length, String imdbRating, String imdbLink, byte[] img) throws Exception {
		ticketplexAdmin.editMovie(movie_id, name, year, genre, description, cast, director, length, imdbRating, imdbLink, img);
		editMovieDialog.closeDialog();
		movieWindow.setMovie(ticketplexAdmin.getMovie(movie_id));
		adminWindow.setList(ticketplexAdmin.getAllMovies());
		
	}
	
	public static int processGetNumberOfReservations(int movie_id){
		return ticketplexAdmin.getMovieNumOfReservations(movie_id);
	}
	
	
	public static void processAddNewShowtime(Movie movie, long timestamp) throws Exception{
		ticketplexAdmin.addMovieShowtime(movie.getId(), timestamp);
		movieWindow.setList(ticketplexAdmin.getAllMovieShowings(movie.getId()));
	}
	
	public static void processRemoveShowtime(Showtime s) {
		ticketplexAdmin.removeMovieShowtime(s.getId());
		movieWindow.setList(ticketplexAdmin.getAllMovieShowings(s.getMovie_id()));
		
	}
		

	public static void showUser(User u) {
		if(userWindow == null)
			userWindow = new UserWindow();		
		userWindow.setVisible(true);
		userWindow.setUser(u);
		userWindow.setList(ticketplexAdmin.getUserReservations(u.getId()));
	}
	public static void hideUser(){
		if(userWindow != null){
			userWindow.setVisible(false);
			userWindow.dispose();
			userWindow = null;
		}
	}

	public static void processRemoveReservation(Reservation r) {
		ticketplexAdmin.removeReservation(r.getId(), r.getUser_id());
		userWindow.setList(ticketplexAdmin.getUserReservations(r.getUser_id()));
		
	}

	public static void processRemoveUser(int id) {
		ticketplexAdmin.removeUser(id);
		hideUser();
		usersWindow.setList(ticketplexAdmin.getAllUsers());
		
		
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
