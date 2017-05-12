package admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Controller;
import ticketplex.Movie;
import ticketplex.TicketplexClient;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class UsersWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel;
	private JLabel lblNazivFilma;
	private JScrollPane scrollPane;
	private JButton btnNewButton;



	/**
	 * Create the frame.
	 */
	public UsersWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		contentPane.setLayout(null);
		
		setList();
	}
	
	void setList(){
		TicketplexClient t = new TicketplexClient();
		t.loadAllData();
		LinkedList<Movie> movies = t.movies;
		
		lblNazivFilma = new JLabel("Lista prikazivanja filmova:");
		lblNazivFilma.setHorizontalAlignment(SwingConstants.CENTER);
		lblNazivFilma.setBounds(10, 22, 194, 14);
		contentPane.add(lblNazivFilma);
		
		listPanel = new JPanel();
		listPanel.setBounds(10, 59, 414, 191);
		listPanel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, listPanel.getWidth(), listPanel.getHeight());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		scrollPane.setViewportView(panel);
		int i = 0;
		int h = 25;
		for (Movie m : movies) {
			JLabel lblNewLabel;
			JLabel lblOpcije;
			JLabel lblDelete;
			lblNewLabel = new JLabel(m.getName());
			lblNewLabel.setBounds(0, h*i, 200, h);
			panel.add(lblNewLabel);
			
			lblDelete = new JLabel("<HTML><u>Izbrisi</u></HTML>");
			lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDelete.setBounds(220, h*i, 147, h);
			lblDelete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
					System.out.println("Izbrisan film: " + m.getName() + ".");
			    }  
			});
			panel.add(lblDelete);
			lblOpcije = new JLabel("<HTML><u>Opcije</u></HTML>");
			lblOpcije.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblOpcije.setBounds(330, h*i, 147, h);
			lblOpcije.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
					AdminController.showMovie(m);
			    }  
			});
			panel.add(lblOpcije);
			i++;
		}
		panel.setPreferredSize(new Dimension(listPanel.getWidth(), i*h));
		
		listPanel.add(scrollPane);
		contentPane.add(listPanel);
		contentPane.add(getBtnNewButton());
		
	}
	
	private JButton getBtnNewButton() {
		
		if (btnNewButton == null) {
			btnNewButton = new JButton("Dodaj novo prikazivanje");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					AdminController.addNewShowtime();
				}
			});
			btnNewButton.setBounds(232, 18, 172, 23);
		}
		return btnNewButton;
	}

}
