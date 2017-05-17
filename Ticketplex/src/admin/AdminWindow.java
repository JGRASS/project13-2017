package admin;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ticketplex.Movie;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel;
	private JScrollPane scrollPane;
	private JPanel innerPanel;

	private JLabel lblNazivFilma;
	private JButton btnNewButton;
	private JMenuBar menuBar;
	private JMenu mnOpcije;
	private JMenuItem mntmKorisnici;

	/**
	 * Create the frame.
	 */
	public AdminWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Icon-Admin-48.png"));
		setTitle("Admin panel [Ticketplex]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 329);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnOpcije = new JMenu("Opcije");
		menuBar.add(mnOpcije);

		mntmKorisnici = new JMenuItem("Korisnici");
		mntmKorisnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminController.showUsers();
			}
		});
		mnOpcije.add(mntmKorisnici);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNazivFilma = new JLabel("Lista filmova");
		lblNazivFilma.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazivFilma.setBounds(10, 22, 183, 14);
		contentPane.add(lblNazivFilma);

		listPanel = new JPanel();
		listPanel.setBounds(10, 59, 414, 191);
		listPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, listPanel.getWidth(), listPanel.getHeight());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		innerPanel = new JPanel();
		innerPanel.setLayout(null);
		scrollPane.setViewportView(innerPanel);

		listPanel.add(scrollPane);
		contentPane.add(listPanel);
		contentPane.add(getBtnNewButton());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				AdminController.closeAdminPanel();
				
			}
		});

	}

	void setList(LinkedList<Movie> movies) {
		innerPanel.removeAll();
		innerPanel.repaint();

		int i = 0;
		int h = 25;
		for (Movie m : movies) {
			JLabel lblNewLabel;
			JLabel lblOpcije;
			JLabel lblDelete;
			lblNewLabel = new JLabel(m.getName());
			lblNewLabel.setBounds(0, h * i, 200, h);
			innerPanel.add(lblNewLabel);

			lblDelete = new JLabel("<HTML><u>Izbrisi</u></HTML>");
			lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDelete.setBounds(220, h * i, 60, h);
			lblDelete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					AdminController.processRemoveMovie(m);
				}
			});
			innerPanel.add(lblDelete);
			lblOpcije = new JLabel("<HTML><u>Opcije</u></HTML>");
			lblOpcije.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblOpcije.setBounds(330, h * i, 147, h);
			lblOpcije.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					AdminController.showMovie(m.getId());
				}
			});
			innerPanel.add(lblOpcije);
			i++;
		}
		innerPanel.setPreferredSize(new Dimension(listPanel.getWidth(), i * h));

	}

	private JButton getBtnNewButton() {

		if (btnNewButton == null) {
			btnNewButton = new JButton("Dodaj novi film");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					AdminController.addNewMovie();
				}
			});
			btnNewButton.setBounds(279, 18, 125, 23);
		}
		return btnNewButton;
	}
	
	public void closeWindow() {
		dispose();
	}

	
}
