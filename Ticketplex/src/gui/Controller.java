package gui;

import java.awt.EventQueue;

import admin.AdminController;
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticketplexClient = new TicketplexClient();
					mainWindow = new MainWindow();
					mainWindow.setVisible(true);
					showUserReservationsDialog();
					
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
		regDialog = new RegistrationDialog();
		regDialog.setVisible(true);		
	}
	
	public static void showfpDialog() {
		fpDialog = new ForgotPasswordDialog();
		fpDialog.setVisible(true);
		
	}
	
	public static void showSettingsDialog(){
		settDialog=new SettingsDialog();
		settDialog.setVisible(true);
	}	
	
	
	public static void showReservationDialog(){
		if(ticketplexClient.isGuest()){
			//show alert
			return;
		}
		resDialog=new ReservationDialog();
		resDialog.setVisible(true);
	}
	
	public static void showUserReservationsDialog(){
		urDialog=new UserReservationsDialog();
		urDialog.setVisible(true);
	}

	
	/**
	 * Handle login information
	 * @param user
	 * @param pass
	 * @return 0-user 1-pass 2-ok
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
}
