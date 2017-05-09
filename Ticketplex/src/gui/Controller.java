package gui;

import java.awt.EventQueue;

import admin.AdminController;
import gui.dialogs.RegistrationDialog;
import ticketplex.TicketplexClient;

public class Controller {
	
	static TicketplexClient ticketplexClient;
	static MainWindow mainWindow;
	static RegistrationDialog regDialog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticketplexClient = new TicketplexClient();
					mainWindow = new MainWindow();
					mainWindow.setVisible(true);
					AdminController.startAdminPanel();
					init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void init(){
		ticketplexClient.loadAllData();
		
		setViewMovies();
	}
	
	public static void setViewMovies(){
		mainWindow.listMovies(ticketplexClient.movies);
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
		
		return 0;
	}

	public static void showRegisterDialog() {
		regDialog = new RegistrationDialog();
		regDialog.setVisible(true);
		
	}
	
	
	
	
	
}
