package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel containerPane;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private HeaderPanel headerPanel;
	private JLabel lblBreadcrumbs;
	
	
	private static Color grayDark = new Color(32, 32, 32); 
	private static Color grayLight = new Color(49, 49, 49); 

	

	

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);		
		setBounds(100, 100, 900, 520);
		
		containerPane = new JPanel();
		containerPane.setBackground(grayLight);	
		containerPane.setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, Color.black));
		setContentPane(containerPane);
		
		
		initHeader();
		initMain();
		initSide();

		setSideGuest();
		

		
	}
	
	public void initHeader(){
		headerPanel = new HeaderPanel(this);
		headerPanel.setBounds(0, 0, 900, 30);
		headerPanel.setLayout(null);
		
		
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Calibri", Font.BOLD, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(849, 0, 51, 30);
		lblX.setForeground(new Color(80, 80, 80));
		
		lblX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
		    {  
				System.exit(0);
		    }  
		});
			
		
		lblBreadcrumbs = new JLabel("");
		lblBreadcrumbs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBreadcrumbs.setHorizontalAlignment(SwingConstants.LEFT);
		lblBreadcrumbs.setBounds(10, 0, 274, 30);
		lblBreadcrumbs.setForeground(new Color(80, 80, 80));	
		setBreadcrumbs(new String[] {"Repertoar"});	
		
		
		headerPanel.add(lblX);
		headerPanel.add(lblBreadcrumbs);
		containerPane.add(headerPanel);
	}
	
	public void initMain(){
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 30, 690, 490);
		mainPanel.setBackground(grayDark);
		containerPane.add(mainPanel);
		mainPanel.setLayout(null);
		

	}
	
	public void initSide(){		
		sidePanel = new JPanel();
		sidePanel.setBounds(690, 30, 210, 490);
		sidePanel.setBackground(grayLight);
		containerPane.add(sidePanel);
		sidePanel.setLayout(null);

	}
	
	
	public void setSideGuest(){		
		sidePanel.removeAll();
		JLabel lblName = new JLabel("Ticketplex");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(10, 21, 141, 14);
		
		
		
		JTextField tfUsername = new JTextField();
		tfUsername.setForeground(new Color(153, 153, 153));
		tfUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		tfUsername.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		tfUsername.setText("Username");
		tfUsername.setBounds(10, 63, 190, 27);
		
		tfUsername.setColumns(10);
		
		JTextField tfPassword = new JTextField();
		tfPassword.setForeground(new Color(153, 153, 153));
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPassword.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		tfPassword.setText("Password");
		tfPassword.setColumns(10);
		tfPassword.setBounds(10, 101, 190, 27);
		
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(10, 139, 190, 27);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setForeground(new Color(153, 153, 153));
		btnLogin.setBackground(grayDark);
		btnLogin.setBorder(null);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblForgot = new JLabel("<html><u>Zaboravio sam lozinku</u></html>");
		lblForgot.setForeground(new Color(153, 153, 153));
		lblForgot.setFont(new Font("Arial", Font.PLAIN, 12));
		lblForgot.setBounds(10, 177, 153, 14);
		lblForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblRegister = new JLabel("<html><u>Registracija naloga</u></html>");
		lblRegister.setForeground(new Color(153, 153, 153));
		lblRegister.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRegister.setBounds(10, 199, 153, 14);
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
		    {  
				showRegisterDialog();
		    }  
		});
		
		
		sidePanel.add(lblName);
		sidePanel.add(tfUsername);
		sidePanel.add(tfPassword);
		sidePanel.add(btnLogin);
				
		sidePanel.add(lblForgot);
		sidePanel.add(lblRegister);
		
	}
	
	public void setBreadcrumbs(String[] paths){
		String full = "<html>";
		full += "<b><font color=#5a5a5a>Ticketplex </font></b>";
		for(String path : paths){
			full += "<font color=#4f4f4f size=-2> ></font>";
			full += "<font color=#4f4f4f> "+path+"</font>";
		}
		full += "</html>";
		
		lblBreadcrumbs.setText(full);
		return;
	}	
	
	public void showRegisterDialog(){
		System.out.println("register clicked");
	}
}
