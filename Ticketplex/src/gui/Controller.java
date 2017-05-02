package gui;

import java.awt.EventQueue;
import java.util.LinkedList;

import db.MovieHandler;
import models.Movie;

public class Controller {
	
	static MainWindow mainWindow;
	
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
		LinkedList<Movie> movies = MovieHandler.getAllMoviesTest();//MovieHandler.getAllMovies();
		

		
		mainWindow.listMovies(movies);
	}
	
	
	
	
	
}
