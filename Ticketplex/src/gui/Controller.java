package gui;

import java.awt.EventQueue;

import admin.AdminController;
import gui.dialogs.ForgotPasswordDialog;
import gui.dialogs.RegistrationDialog;
import gui.dialogs.ReservationDialog;
import gui.dialogs.SettingsDialog;
import gui.dialogs.UserReservationsDialog;
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
	
	public static void setViewMovies(){
		mainWindow.listMovies(ticketplexClient.getAllMovies());
	}

	/**
	 * Handle login information
	 * @param user
	 * @param pass
	 * @return 0-user 1-pass 2-ok
	 */
	public static int handleLogin(String user, String pass) {
		if(user.equals("admin") && pass.equals("admin")){
			System.out.println("Admin login");
			AdminController.startAdminPanel();
			return 2;
		}
		
		mainWindow.setSideUser();
		
		return 0;
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
		resDialog=new ReservationDialog();
		resDialog.setVisible(true);
	}
	
	public static void showUserReservationsDialog(){
		urDialog=new UserReservationsDialog();
		urDialog.setVisible(true);
	}
}
