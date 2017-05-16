package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ticketplex.Movie;
import ticketplex.User;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private JScrollPane scrollPane;
	private JPanel listPanel;

	public static Color grayDark = new Color(32, 32, 32);
	public static Color grayLight = new Color(49, 49, 49);
	public static Color okvir = new Color(22, 22, 22);

	public static Color white = new Color(255, 255, 255);

	int title_width = 120, title_height = 200;


	Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
	CompoundBorder errorBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.red), emptyBorder);

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 900, 540);
		setLocationRelativeTo(null);

		containerPane = new JPanel();
		containerPane.setBackground(grayLight);
		containerPane.setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		setContentPane(containerPane);

		initHeader();
		initMain();
		initSide();

		mainPanel.requestFocus();

		setSideGuest();
		// setSideUser();

	}

	public void initHeader() {
		headerPanel = new HeaderPanel(this);
		headerPanel.setBounds(0, 0, 900, 30);
		headerPanel.setLayout(null);

		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Calibri", Font.BOLD, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(849, 0, 51, 30);
		lblX.setForeground(new Color(80, 80, 80));

		lblX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblBreadcrumbs = new JLabel("");
		lblBreadcrumbs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBreadcrumbs.setHorizontalAlignment(SwingConstants.LEFT);
		lblBreadcrumbs.setBounds(10, 0, 549, 30);
		lblBreadcrumbs.setForeground(new Color(80, 80, 80));

		headerPanel.add(lblX);
		headerPanel.add(lblBreadcrumbs);
		containerPane.add(headerPanel);
	}

	public void initMain() {
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 30, 690, 510);
		mainPanel.setBackground(grayDark);
		containerPane.add(mainPanel);
		mainPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
		scrollPane.setBackground(mainPanel.getBackground());
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);

		listPanel = new JPanel();
		listPanel.setLayout(null);
		listPanel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
		listPanel.setBackground(mainPanel.getBackground());

		scrollPane.setViewportView(listPanel);

	}

	public void initSide() {
		sidePanel = new JPanel();
		sidePanel.setBounds(690, 30, 210, 490);
		sidePanel.setBackground(grayLight);
		containerPane.add(sidePanel);
		sidePanel.setLayout(null);

	}

	// SIDE PANEL
	public void setSideGuest() {
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
				if (tfUsername.getText().equals("Username"))
					tfUsername.setText("");

			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
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
				if (tfPassword.getText().equals("Password"))
					tfPassword.setText("");

			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
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
				handleLogin(tfUsername, tfPassword);
			}
		});
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					handleLogin(tfUsername, tfPassword);
			}
		});

		btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLogin.setBackground(new Color(25, 25, 25));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnLogin.setBackground(MainWindow.grayDark);
			}
		});

		JLabel lblForgot = new JLabel("<html><u>Zaboravio sam lozinku</u></html>");
		lblForgot.setForeground(new Color(153, 153, 153));
		lblForgot.setFont(new Font("Arial", Font.PLAIN, 12));
		lblForgot.setBounds(10, 177, 153, 14);
		lblForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblForgot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Controller.showfpDialog();
			}

		});

		JLabel lblRegister = new JLabel("<html><u>Registracija naloga</u></html>");
		lblRegister.setForeground(new Color(153, 153, 153));
		lblRegister.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRegister.setBounds(10, 199, 153, 14);
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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

	private void handleLogin(JTextField tfUsername, JTextField tfPassword) {
		String user = tfUsername.getText();
		String pass = tfPassword.getText();
		boolean[] error = new boolean[2];
		tfUsername.setBorder(emptyBorder);
		tfPassword.setBorder(emptyBorder);

		if (user == "" || user.equals("Username") || user.length() < 4) {
			error[0] = true;
		}
		if (pass == "" || pass.equals("Password") || pass.length() < 4) {
			error[1] = true;
		}

		if (!error[0] && !error[1]) {
			System.out.println("test");
			int feedback = Controller.handleLogin(user, pass);
			if (feedback <= 1)
				error[feedback] = true;

		}

		if (error[0]) {
			tfUsername.setBorder(errorBorder);
		} else if (error[1]) {
			tfPassword.setBorder(errorBorder);
		}
	}

	// USER

	public void setSideUser(User user) {
		sidePanel.removeAll();
		sidePanel.repaint();
		JLabel lblName = new JLabel("TICKETPLEX");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(10, 21, 190, 36);
		sidePanel.add(lblName);

		JLabel lblUser = new JLabel(user.getUsername());
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setForeground(new Color(211, 211, 211));
		lblUser.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblUser.setBounds(10, 77, 190, 36);
		sidePanel.add(lblUser);

		JButton btnReservations = new JButton("MOJE REZERVACIJE");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.showUserReservationsDialog();
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
				Controller.processLogout();
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

	// HEADER
	public void setBreadcrumbs(String[] paths) {
		String full = "<html>";
		full += "<b><font color=#5a5a5a>Ticketplex </font></b>";
		for (String path : paths) {
			full += "<font color=#4f4f4f size=-2> ></font>";
			full += "<font color=#4f4f4f> " + path + "</font>";
		}
		full += "</html>";

		lblBreadcrumbs.setText(full);
		return;
	}

	public void showRegisterDialog() {
		System.out.println("register clicked");
	}

	public void showfpDialog() {
		System.out.println("forgot clicked");
	}

	public void setMovie(Movie mov) {
		setBreadcrumbs(new String[] { "Repertoar", mov.getName() });
		mainPanel.removeAll();
		mainPanel.repaint();

		JLabel lblMovie = new JLabel("");
		lblMovie.setBounds(21, 25, (int) (title_width * 1.2), (int) (title_height * 1.1));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(mov.getImg()).getImage()
				.getScaledInstance(lblMovie.getWidth(), lblMovie.getHeight(), Image.SCALE_SMOOTH));
		lblMovie.setIcon(imageIcon);
		lblMovie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, okvir));
		mainPanel.add(lblMovie);

		JLabel lblTitle = new JLabel(mov.getName());
		lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(180, 25, 600, 30);
		mainPanel.add(lblTitle);

		JLabel lblYear = new JLabel("" + mov.getYear());
		lblYear.setFont(new Font("Arial", Font.BOLD, 16));
		lblYear.setForeground(new Color(211, 211, 211));
		lblYear.setBounds(180, 65, 50, 20);
		mainPanel.add(lblYear);

		JLabel lblGenre = new JLabel(mov.getGenre());
		lblGenre.setFont(new Font("Arial", Font.BOLD, 16));
		lblGenre.setForeground(new Color(211, 211, 211));
		lblGenre.setBounds(180, 83, 250, 20);
		mainPanel.add(lblGenre);

		//String str = "I just the took to calling it the Bat. And yes Mr. Wayne, it does come in black. There is a prison in a more ancient part of the world. A pit where men are thrown to suffer and die. But sometimes a man rises from the darkness. Sometimes, the pit sends something back. We have purged your fear. You are ready to Iead these men. You are ready to become a member of the League of Shadows. But first, you must demonstrate your commitment to justice. I will go back to Gotham and I will fight men Iike this but I will not become an executioner.";
		
		JTextArea taDescription = new JTextArea();
		taDescription.setText(mov.getDescription()/*str*/);
		taDescription.setLineWrap(true);
		taDescription.setWrapStyleWord(true);
		taDescription.setEditable(false);
		taDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		taDescription.setForeground(new Color(211, 211, 211));
		taDescription.setBackground(mainPanel.getBackground());
		taDescription.setBounds(180, 110, 450, 85);
		int outside_height = (int)taDescription.getPreferredScrollableViewportSize().getHeight() <  taDescription.getHeight() ? (int)taDescription.getPreferredScrollableViewportSize().getHeight() :taDescription.getHeight();
		
		JScrollPane spDescription = new JScrollPane(taDescription);
		spDescription.setBounds(taDescription.getX(), taDescription.getY(), taDescription.getWidth(), outside_height);
		spDescription.setBorder(null);
		spDescription.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		spDescription.getVerticalScrollBar().setUnitIncrement(30);
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	spDescription.getViewport().setViewPosition( new Point(0, 0) );
		    }
		});
		mainPanel.add(spDescription);
		
		int Y_endofDesc = (taDescription.getY() + outside_height);
		
		JLabel lblDuration = new JLabel("Trajanje:");
		lblDuration.setFont(new Font("Arial", Font.BOLD, 14));
		lblDuration.setForeground(new Color(211, 211, 211));
		lblDuration.setBounds(180, Y_endofDesc+5, 100, 20);
		mainPanel.add(lblDuration);

		JLabel lblDirector = new JLabel("Režiser:");
		lblDirector.setFont(new Font("Arial", Font.BOLD, 14));
		lblDirector.setForeground(new Color(211, 211, 211));
		lblDirector.setBounds(180, Y_endofDesc+5+18, 100, 20);
		mainPanel.add(lblDirector);

		JLabel lblCast = new JLabel("Glumci:");
		lblCast.setFont(new Font("Arial", Font.BOLD, 14));
		lblCast.setForeground(new Color(211, 211, 211));
		lblCast.setBounds(180, Y_endofDesc+5+36, 100, 20);
		mainPanel.add(lblCast);

		JLabel lblDur = new JLabel("" + mov.getLength() +" min");
		lblDur.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDur.setForeground(new Color(211, 211, 211));
		lblDur.setBounds(250, Y_endofDesc+5+1, 100, 20);
		mainPanel.add(lblDur);

		JLabel lblDir = new JLabel(mov.getDirector());
		lblDir.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDir.setForeground(new Color(211, 211, 211));
		lblDir.setBounds(250, Y_endofDesc+5+19, 200, 20);
		mainPanel.add(lblDir);

		JLabel lblC = new JLabel("<html>" + mov.getCast() + "</html>");
		lblC.setFont(new Font("Arial", Font.PLAIN, 14));
		lblC.setForeground(new Color(211, 211, 211));
		lblC.setVerticalAlignment(SwingConstants.TOP);
		lblC.setBounds(250, Y_endofDesc+5+37, 300, 20);
		mainPanel.add(lblC);

		JButton btnReserve = new JButton("Rezerviši kartu");
		btnReserve.setHorizontalAlignment(SwingConstants.CENTER);
		btnReserve.setFont(new Font("Arial", Font.BOLD, 20));
		btnReserve.setBounds(180, Y_endofDesc+5+70, 200, 50);
		btnReserve.setBackground(new Color(239, 202, 16));
		btnReserve.setForeground(new Color(0, 0, 0));
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setBorder(null);
		btnReserve.setFocusPainted(false);
		btnReserve.setBorderPainted(false);

		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.showReservationDialog(mov);
			}
		});
		mainPanel.add(btnReserve);

		JLabel lblImdbIcon = new JLabel();
		lblImdbIcon.setBounds(25, 237, 60, 60);
		lblImdbIcon.setIcon(new ImageIcon("res/IMDb-48.png"));
		lblImdbIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPanel.add(lblImdbIcon);

		ImageIcon starIcon = new ImageIcon(
				new ImageIcon("res/Star-48.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		JLabel lblImdbStar = new JLabel();
		lblImdbStar.setBounds(140, 239, 60, 60);
		lblImdbStar.setIcon(starIcon);
		mainPanel.add(lblImdbStar);

		JLabel lblRating = new JLabel(mov.getImdbRating());
		lblRating.setBounds(90, 240, 60, 60);
		lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblRating.setFont(new Font("Arial", Font.BOLD, 19));
		lblRating.setForeground(new Color(211, 211, 211));
		mainPanel.add(lblRating);

		lblImdbIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(mov.getImdbLink()));
					} catch (IOException | URISyntaxException e1) {

					}
				}
			}
		});

		JLabel lblBack = new JLabel("< Povratak na repertoar");
		lblBack.setBounds(10, 475, 230, 30);
		lblBack.setFont(new Font("Arial", Font.BOLD, 14));
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPanel.add(lblBack);

		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Controller.setViewMovies();
			}
		});

		lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblBack.setForeground(new Color(163, 163, 163));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblBack.setForeground(new Color(255, 255, 255));
			}
		});

	}

	public void listMovies(LinkedList<Movie> movies) {
		mainPanel.removeAll();
		mainPanel.repaint();

		setBreadcrumbs(new String[] { "Repertoar" });
		int title_padding = 50, per_line = 4;
		int i = 0;

		for (Movie movie : movies) {
			int x = 9 + (title_width + title_padding) * (i % per_line);
			int y = 11 + (title_height + title_padding) * (i / per_line);

			JLabel lblMovie = new JLabel("");
			lblMovie.setBounds(x, y, (int)(title_width*1.35), (int)(title_height*1.2));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(movie.getImg()).getImage().getScaledInstance(lblMovie.getWidth(),
					lblMovie.getHeight(), Image.SCALE_SMOOTH));
			lblMovie.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0,0,0)));
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

		int lines = (int) Math.ceil((i * 1.0) / per_line);
		listPanel.setPreferredSize(new Dimension(listPanel.getWidth(), (lines) * (title_height + title_padding) + 10));

		mainPanel.add(scrollPane);

	}

}
