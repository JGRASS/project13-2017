package gui;

import java.awt.EventQueue;

import admin.AdminController;
import gui.dialogs.AlertDialog;
import gui.dialogs.ForgotPasswordDialog;
import gui.dialogs.RegistrationDialog;
import gui.dialogs.ReservationDialog;
import gui.dialogs.SettingsDialog;
import gui.dialogs.UserReservationsDialog;
import ticketplex.Movie;
import ticketplex.TicketplexClient;

public class Controller {
	
	static TicketplexClient ticketplexClient;
	static MainWindow mainWindow;
	static RegistrationDialog regDialog;
	static ForgotPasswordDialog fpDialog;
	static SettingsDialog settDialog;
	static ReservationDialog resDialog;
	static UserReservationsDialog urDialog;
	static AlertDialog alertDialog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticketplexClient = new TicketplexClient();
					mainWindow = new MainWindow();
					mainWindow.setVisible(true);
					init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void init(){	
		setViewMovies();
	}
	
	
	
	public static void restartMainWindow(){
		ticketplexClient = null;
		ticketplexClient = new TicketplexClient();
		mainWindow.dispose();
		mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		init();
	}
	public static void hideGuestGUI(){
		if(regDialog != null) regDialog.setVisible(false);
		if(fpDialog != null) fpDialog.setVisible(false);
	}
	
	public static void hideUserGUI(){
		if(urDialog != null) urDialog.setVisible(false);
		if(settDialog != null) settDialog.setVisible(false);		
		if(resDialog != null) resDialog.setVisible(false);
	}
	
	public static void setViewMovies(){
		mainWindow.listMovies(ticketplexClient.getAllMovies());
	}
	
	public static void showMovie(Movie movie){
		mainWindow.setMovie(movie);

	}

	
	public static void showRegisterDialog() {
		if(regDialog == null)
			regDialog = new RegistrationDialog();
		regDialog.setVisible(true);		
	}
	
	public static void showfpDialog() {
		if(fpDialog == null)
			fpDialog = new ForgotPasswordDialog();
		fpDialog.setVisible(true);
		
	}
	
	public static void showSettingsDialog(){
		if(settDialog == null)
			settDialog=new SettingsDialog();
		settDialog.setVisible(true);
	}	
	
	public static void showAlertDialog(){
		if(alertDialog == null)
			alertDialog=new AlertDialog();
		alertDialog.setVisible(true);
	}
	
	
	public static void showReservationDialog(Movie movie){
		if(ticketplexClient.isGuest()){
			showAlertDialog();
			return;
		}
		resDialog=new ReservationDialog(movie, ticketplexClient.getAllMovieShowings(movie.getId()));
		resDialog.setVisible(true);
	}
	
	public static void showUserReservationsDialog(){
		if(urDialog == null)
			urDialog = new UserReservationsDialog();
		urDialog.setVisible(true);
		try {
			urDialog.renderList(ticketplexClient.getUserReservations());
		} catch (Exception e) {
			showAlertDialog();
		}
	}

	
	/**
	 * Funkcija obradjuje zahtev za login
	 * @param user username korisnika
	 * @param pass lozinka korisnika
	 * @return <ul> <li> 0 - problem sa username-om</li> <li>1 - problem sa lozinkom</li> <li>2 - uspesna prijava</li></ul>
	 */
	public static int handleLogin(String user, String pass) {
		if(user.equals("admin") && pass.equals("admin")){
			AdminController.startAdminPanel();
			mainWindow.setVisible(false);
			hideGuestGUI();
			hideUserGUI();
			return 2;
		}
		
		try {			
			ticketplexClient.login(user, pass);
			hideGuestGUI();
			mainWindow.setSideUser(ticketplexClient.user);
		} catch (Exception e) {
			if(e.getMessage().equals("error password"))
				return 1;
			return 0;
		}
		return 2;
	}
	
	public static void processLogout(){
		ticketplexClient.logout();
		hideUserGUI();
	}

	public static void processRegister(String username, String password, String email){
		try {
			ticketplexClient.register(username, password, email);
			handleLogin(username, password);
		} catch (Exception e) {
			regDialog.showMsg(e.getMessage());
		}
	}
	
	public static void processResetPassword(String username){
		try {
			String newpass = ticketplexClient.resetPassword(username);
			fpDialog.showMsg("Vasa nova lozinka je: "+newpass);
		} catch (Exception e) {
			fpDialog.showMsg(e.getMessage());
		}
	}

	public static boolean processNewReservation(int showtime_id, int number_of_seats) {
		try {
			ticketplexClient.makeReservation(showtime_id, number_of_seats);
			resDialog.showMsg("Uspe�no ste rezervisali projekciju.");
			return true;
		} catch (Exception e) {
			resDialog.showMsg(e.getMessage());
		}
		return false;
		
	}

	public static void processSetNewPassword(String old_password,String new_password) {
		try {
			ticketplexClient.changePassword(old_password, new_password);
			settDialog.showMsg("Uspe�no ste promenili lozinku.");
		} catch (Exception e) {
			settDialog.showMsg(e.getMessage());
		}
		
	}

	public static void processRemoveReservation(int reservation_id) {
		try {
			ticketplexClient.deleteReservation(reservation_id);
		} catch (Exception e) {
			showAlertDialog();
		}
		
	}

}
