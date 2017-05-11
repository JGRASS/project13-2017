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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ticketplex.Movie;
import ticketplex.User;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	
	public static Color grayDark = new Color(32, 32, 32); 
	public static Color grayLight = new Color(49, 49, 49); 
	public static Color okvir = new Color(22, 22, 22);

	Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
	CompoundBorder errorBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.red), emptyBorder);
	

	

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);		
		setBounds(100, 100, 900, 520);
		setLocationRelativeTo(null);
		
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
		//setSideUser();
		
		
			
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
		lblBreadcrumbs.setBounds(10, 0, 549, 30);
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
		sidePanel.repaint();
		JLabel lblName = new JLabel("TICKETPLEX");
		lblName.setBackground(new Color(175, 238, 238));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(10, 21, 190, 36);
		
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
				
				if(user == "" || user.equals("Username") || user.length() < 4){
					error[0] = true;
				}
				if(pass == "" || pass.equals("Password") || pass.length() < 4){
					error[1] = true;
				}
				
				if(!error[0] && !error[1]){
					System.out.println("test");
					int feedback = Controller.handleLogin(user, pass);
					if(feedback <= 1) error[feedback] = true;		
					
				}
				
				if(error[0]){
					tfUsername.setBorder(errorBorder);
				}else if(error[1]){
					tfPassword.setBorder(errorBorder);
				}
				
			}
		});
		
		
		JLabel lblForgot = new JLabel("<html><u>Zaboravio sam lozinku</u></html>");
		lblForgot.setForeground(new Color(153, 153, 153));
		lblForgot.setFont(new Font("Arial", Font.PLAIN, 12)); 
		lblForgot.setBounds(10, 177, 153, 14);
		lblForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblForgot.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)  
		    {  
				Controller.showfpDialog();
		    } 
			
			
			
		});
		
		
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
	
	
	//USER
	
	public void  setSideUser(User user){
		sidePanel.removeAll();
		sidePanel.repaint();
		JLabel lblName = new JLabel("TICKETPLEX");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(10, 21, 190, 36);
		sidePanel.add(lblName);
		
		
		JLabel lblUser=new JLabel("Username");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setForeground(new Color(211, 211, 211));
		lblUser.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblUser.setBounds(10, 77
				, 190, 36);
		sidePanel.add(lblUser);
		
		JButton btnReservations = new JButton("MOJE REZERVACIJE");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UIManager.put(btnReservations.getBackground(), MainWindow.grayDark);
		btnReservations.setForeground(new Color(153, 153, 153));
		btnReservations.setFont(new Font("Arial", Font.BOLD, 14));
		btnReservations.setBorder(null);
		btnReservations.setBackground(new Color(32, 32, 32));
		btnReservations.setBounds(10, 139, 190, 27);
		btnReservations.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sidePanel.add(btnReservations); 
		
		btnReservations.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnReservations.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnReservations.setBackground(MainWindow.grayDark);
		    }
		});
		
		JButton btnSettings = new JButton("PODE\u0160AVANJA");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		UIManager.put(btnSettings.getBackground(), MainWindow.grayDark);
		btnSettings.setForeground(new Color(153, 153, 153));
		btnSettings.setFont(new Font("Arial", Font.BOLD, 14));
		btnSettings.setBorder(null);
		btnSettings.setBackground(new Color(32, 32, 32));
		btnSettings.setBounds(10, 171, 190, 27);
		btnSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sidePanel.add(btnSettings); 
		
		btnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnSettings.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnSettings.setBackground(MainWindow.grayDark);
		    }
		});
		
		btnSettings.addMouseListener(new MouseAdapter() {
@Override
			public void mouseClicked(MouseEvent e) {
				Controller.showSettingsDialog();
			}
		});
		
		JButton btnLogOut = new JButton("LOGOUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UIManager.put(btnLogOut.getBackground(), MainWindow.grayDark);
		btnLogOut.setForeground(new Color(153, 153, 153));
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(new Color(32, 32, 32));
		btnLogOut.setBounds(10, 203, 190, 27);
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sidePanel.add(btnLogOut); 
		
		btnLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnLogOut.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnLogOut.setBackground(MainWindow.grayDark);
		    }
		});
		
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 setSideGuest();
			}
		});
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

	public void showfpDialog(){
		System.out.println("forgot clicked");
	}
	
	public void setMovie(Movie mov){
		setBreadcrumbs(new String[] {"Repertoar",mov.getName()});
		mainPanel.removeAll();
		mainPanel.repaint();
		
		JLabel lblMovie = new JLabel("");
		lblMovie.setBounds(21,25,140,210);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(mov.getImg()).getImage().getScaledInstance(lblMovie.getWidth(), lblMovie.getHeight(), Image.SCALE_SMOOTH));
		lblMovie.setIcon(imageIcon);
		lblMovie.setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, okvir));
		mainPanel.add(lblMovie);
		
		JLabel lblTitle = new JLabel(mov.getName());
		lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
		lblTitle.setForeground(new Color(255,255,255));
		lblTitle.setBounds(180, 25, 600, 30);
		mainPanel.add(lblTitle);
		
		JLabel lblYear=new JLabel(""+mov.getYear());
		lblYear.setFont(new Font("Arial", Font.BOLD, 16));
		lblYear.setForeground(new Color(211,211,211));
		lblYear.setBounds(180, 65, 50, 20);
		mainPanel.add(lblYear);
		
		JLabel lblGenre=new JLabel(mov.getGenre());
		lblGenre.setFont(new Font("Arial", Font.BOLD, 16));
		lblGenre.setForeground(new Color(211,211,211));
		lblGenre.setBounds(180, 83, 250, 20);
		mainPanel.add(lblGenre);
		
		String str = "James Edward Franco (born April 19, 1978)[2] is an American actor and filmmaker known for his work in both comedic and dramatic films and TV shows. For his role in 127 Hours (2010), Franco was nominated for an Academy Award for Best Actor. He is known for his roles in live-action films such as Milk (2008), Pineapple Express (2008), Rise of the Planet of the Apes (2011), Spring Breakers (2012), Oz the Great and Powerful (2013), This Is the End (2013), Goat (2016), and Sam Raimi's Spider-Man trilogy, while also voicing characters in the animated films The Little Prince (2015) and Sausage Party (2016).Franco is also known for his work on television; his first prominent acting role was the lead character Daniel Desario on the short-lived comedy-drama Freaks and Geeks, which developed a cult following. He also portrayed the title character in the TV biographical film James Dean (2001), for which he won a Golden Globe Award. He had recurring roles on the soap opera General Hospital and sitcom Angie Tribeca and starred in the 2016 limited series 11.22.63. He will star in the David Simon-created HBO drama The Deuce in 2017.Franco volunteers for the Art of Elysium charity, and has also taught film classes at New York University, the University of Southern California, UCLA, Studio 4, and Palo Alto High School.[3][4][5][6][7]";
		
		
		
		JLabel lblDescription=new JLabel("");
		lblDescription.setText("<html>"+ str +"</html>");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDescription.setForeground(new Color(211,211,211));
		lblDescription.setBounds(180, 110, 450, 80);
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		
		mainPanel.add(lblDescription);
		
		/*JScrollPane scrDesc= new JScrollPane(lblDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrDesc.setBounds(180, 110, 500, 90);
		scrDesc.setForeground(new Color(211,211,211));
		mainPanel.add(scrDesc);*/
		
		JLabel lblDuration=new JLabel("Trajanje:");
		lblDuration.setFont(new Font("Arial", Font.BOLD, 14));
		lblDuration.setForeground(new Color(211,211,211));
		lblDuration.setBounds(180,200, 100, 20);
		mainPanel.add(lblDuration);
		
		JLabel lblDirector=new JLabel("Režiser:");
		lblDirector.setFont(new Font("Arial", Font.BOLD, 14));
		lblDirector.setForeground(new Color(211,211,211));
		lblDirector.setBounds(180,218, 100, 20);
		mainPanel.add(lblDirector);
		
		JLabel lblCast=new JLabel("Glumci:");
		lblCast.setFont(new Font("Arial", Font.BOLD, 14));
		lblCast.setForeground(new Color(211,211,211));
		lblCast.setBounds(180,236, 100, 20);
		mainPanel.add(lblCast);
		
		JLabel lblDur=new JLabel(""+mov.getLength());
		lblDur.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDur.setForeground(new Color(211,211,211));
		lblDur.setBounds(250,201, 100, 20);
		mainPanel.add(lblDur);
		
		JLabel lblDir=new JLabel(mov.getDirector());
		lblDir.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDir.setForeground(new Color(211,211,211));
		lblDir.setBounds(250,219, 200, 20);
		mainPanel.add(lblDir);
		
		JLabel lblC=new JLabel("<html>"+mov.getCast()+"</html>");
		lblC.setFont(new Font("Arial", Font.PLAIN, 14));
		lblC.setForeground(new Color(211,211,211));
		lblC.setVerticalAlignment(SwingConstants.TOP);
		lblC.setBounds(250,237, 300, 20);
		mainPanel.add(lblC);
		
		JButton btnReserve=new JButton("Rezerviši kartu");
		btnReserve.setHorizontalAlignment(SwingConstants.CENTER);
		btnReserve.setFont(new Font("Arial", Font.BOLD, 20));
		btnReserve.setBounds(180, 270, 200, 50);
		btnReserve.setBackground(new Color(239,202,16));
		btnReserve.setForeground(new Color(0,0,0));
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setBorder(null);
		btnReserve.setFocusPainted(false);
		btnReserve.setBorderPainted(false);
		
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.showReservationDialog();
			}
		});
		mainPanel.add(btnReserve);
		
		JLabel lblRating=new JLabel(mov.getImdbRating());
		lblRating.setBounds(60,220,60,60);
		lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblRating.setFont(new Font("Arial", Font.BOLD, 19));
		lblRating.setForeground(new Color(211,211,211));
		mainPanel.add(lblRating);
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
			
			
			lblMovie.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Controller.showMovie(movie);
				}
			});
		}
		
		

		
		listPanel.setPreferredSize(new Dimension(listPanel.getWidth(), (i / per_line) * (title_height + title_padding) + 10));
		
		mainPanel.add(scrollPane);
	}
}
