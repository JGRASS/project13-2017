package admin;

import admin.dialogs.NewMovieDialog;

public class AdminController {

	static AdminWindow adminWindow;
	
	public static void startAdminPanel(){
		adminWindow = new AdminWindow();
		adminWindow.setVisible(true);
	}
	public static void addNewMovie(){
		NewMovieDialog dialog = new NewMovieDialog();
		dialog.setVisible(true);
	}
}
