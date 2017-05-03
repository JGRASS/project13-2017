package admin;

public class AdminController {

	static AdminWindow adminWindow;
	
	public static void startAdminPanel(){
		adminWindow = new AdminWindow();
		adminWindow.setVisible(true);
	}
}
