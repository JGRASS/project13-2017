package gui;

import java.awt.EventQueue;
import java.util.LinkedList;

import admin.AdminController;
import db.MovieHandler;
import gui.dialogs.RegistrationDialog;
import models.Movie;

public class Controller {
	
	static MainWindow mainWindow;
	static RegistrationDialog regDialog;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		LinkedList<Movie> movies = MovieHandler.getAllMovies();
		

		
		mainWindow.listMovies(movies);
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
