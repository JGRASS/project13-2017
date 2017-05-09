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

public class AdminController {

	static AdminWindow adminWindow;
	static MovieWindow movieWindow;
	static UsersWindow usersWindow;

	public static void startAdminPanel() {
		

		
		adminWindow = new AdminWindow();
		adminWindow.setVisible(true);
	}

	public static void addNewMovie() {
		NewMovieDialog dialog = new NewMovieDialog();
		dialog.setVisible(true);
	}
	
	public static void showMovie(Movie movie){
		movieWindow  = new MovieWindow();
		movieWindow.setVisible(true);
	}
	
	public static void showUsers(){
		usersWindow = new UsersWindow();
		usersWindow.setVisible(true);
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

	public static void addNewShowtime() {
		NewShowtimeDialog dialog = new NewShowtimeDialog();
		dialog.setVisible(true);
	}
}
