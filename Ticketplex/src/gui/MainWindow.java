package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ticketplex.Movie;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

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
		
		mainPanel.requestFocus();

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
	
	//SIDE PANEL
	public void setSideGuest(){		
		sidePanel.removeAll();
		JLabel lblName = new JLabel("Ticketplex");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(10, 21, 141, 14);
		
		Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder errortBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.red), emptyBorder);
		
		JTextField tfUsername = new JTextField();
		tfUsername.setForeground(new Color(153, 153, 153));
		tfUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		tfUsername.setBorder(emptyBorder);
		tfUsername.setText("Username");		
		tfUsername.setColumns(10);
		tfUsername.setBounds(10, 63, 190, 27);
		tfUsername.addFocusListener(new FocusListener() {  
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfUsername.getText().equals("Username"))
					tfUsername.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent arg0){}  
		});
		
		JTextField tfPassword = new JPasswordField();
		tfPassword.setForeground(new Color(153, 153, 153));
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPassword.setBorder(emptyBorder);
		tfPassword.setText("Password");
		tfPassword.setColumns(10);
		tfPassword.setBounds(10, 101, 190, 27);
		tfPassword.addFocusListener(new FocusListener() {  
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfPassword.getText().equals("Password"))
					tfPassword.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent arg0){}  
		});
		
		
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(10, 139, 190, 27);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setForeground(new Color(153, 153, 153));
		btnLogin.setBackground(grayDark);
		btnLogin.setBorder(null);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tfUsername.getText();
				String pass = tfPassword.getText();
				boolean[] error = new boolean[2];
				tfUsername.setBorder(emptyBorder); tfPassword.setBorder(emptyBorder);
				
				if(user == "" || user == "Username" || user.length() < 4){
					error[0] = true;
				}
				if(pass == "" || pass == "Password" || pass.length() < 4){
					error[1] = true;
				}
				
				if(!error[0] && !error[1]){
					int feedback = Controller.handleLogin(user, pass);
					if(feedback <= 1) error[feedback] = true;
					
				}
				
				if(error[0]){
					tfUsername.setBorder(errortBorder);
				}else if(error[1]){
					tfPassword.setBorder(errortBorder);
				}
				
			}
		});
		
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
				Controller.showRegisterDialog();
		    }  
		});
		
		
		sidePanel.add(lblName);
		sidePanel.add(tfUsername);
		sidePanel.add(tfPassword);
		sidePanel.add(btnLogin);
				
		sidePanel.add(lblForgot);
		sidePanel.add(lblRegister);
		
	}
	
	//HEADER
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
	
	public void listMovies(LinkedList<Movie> movies){
		mainPanel.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0,0, mainPanel.getWidth(), mainPanel.getHeight());
		scrollPane.setBackground(mainPanel.getBackground());
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(null);
		listPanel.setBounds(0,0, mainPanel.getWidth(), mainPanel.getHeight());
		listPanel.setBackground(mainPanel.getBackground());
		


		scrollPane.setViewportView(listPanel);
		
		
		
		
		int title_width = 120, title_height = 200;
		int title_padding = 15, per_line = 5;
		int i = 0;
		
		for(Movie movie : movies){
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(movie.getImg()).getImage().getScaledInstance(title_width, title_height, Image.SCALE_SMOOTH));
			int x = 13 + (title_width + title_padding) * (i % per_line);
			int y = 10 + (title_height + title_padding) * (i / per_line);
			
			JLabel lblMovie = new JLabel("");
			lblMovie.setBounds(x, y, title_width, title_height);
			lblMovie.setIcon(imageIcon);
			lblMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			listPanel.add(lblMovie);
			i++;
		}
		
		listPanel.setPreferredSize(new Dimension(listPanel.getWidth(), (i / per_line) * (title_height + title_padding) + 10));
		
		mainPanel.add(scrollPane);
	}
	
}
