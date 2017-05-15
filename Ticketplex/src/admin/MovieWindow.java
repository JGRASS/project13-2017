package admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Controller;
import ticketplex.Movie;
import ticketplex.Showtime;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel, innerPanel;
	private JLabel lblNazivFilma;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JLabel lblName;
	private JLabel lblYear;
	private JLabel lblDescription;
	private JLabel lblCast;
	private JLabel lblDirector;
	private JLabel lblLength;
	private JLabel lblimdbRating;
	private JLabel lblImdblink;
	private JLabel lblGenre;
	private JLabel lblReservationNumber;



	/**
	 * Create the frame.
	 */
	public MovieWindow(Movie m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 724, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblName());
		contentPane.add(getLblYear());
		contentPane.add(getLblDescription());
		contentPane.add(getLblCast());
		contentPane.add(getLblDirector());
		contentPane.add(getLblLength());
		contentPane.add(getLblimdbRating());
		contentPane.add(getLblImdblink());
		contentPane.add(getLblGenre());
		contentPane.add(getLblReservationNumber());
		
		listPanel = new JPanel();
		listPanel.setBounds(284, 166, 414, 191);
		listPanel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, listPanel.getWidth(), listPanel.getHeight());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		innerPanel = new JPanel();
		scrollPane.setViewportView(innerPanel);
		innerPanel.setLayout(null);
		
		listPanel.add(scrollPane);
		contentPane.add(listPanel);
		
		JButton btnDodajPrikazivanje = new JButton("Dodaj prikazivanje");
		btnDodajPrikazivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminController.addNewShowtime(m);
			}
		});
		btnDodajPrikazivanje.setBounds(525, 142, 173, 23);
		contentPane.add(btnDodajPrikazivanje);
		
		JLabel lblPrikazivanja = new JLabel("Prikazivanja");
		lblPrikazivanja.setBounds(284, 141, 121, 14);
		contentPane.add(lblPrikazivanja);
		
	
	}
	//prosiriiiii//
	void setMovie(Movie m){
		lblName.setText(m.getName());
		lblGenre.setText(m.getGenre());
		lblDescription.setText("<html>"+m.getDescription()+"</html>");
		lblCast.setText(m.getCast());
		lblDirector.setText(m.getDirector());
		lblLength.setText(String.valueOf(m.getLength()));
		lblimdbRating.setText(m.getImdbRating());
		lblImdblink.setText(m.getImdbLink());
		lblReservationNumber.setText(String.valueOf(AdminController.processGetNumberOfReservations(m.getId())));
	}
	void setList(LinkedList<Showtime> showtimes){

		innerPanel.removeAll();
		innerPanel.repaint();
		
		int i = 0;
		int h = 25;
		for (Showtime s : showtimes) {
			SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String datum=fmt.format(s.getDateAsCalendar().getTime());
			
			
			
			JLabel lblNewLabel = new JLabel(datum);
			lblNewLabel.setBounds(0, h*i, 200, h);
			innerPanel.add(lblNewLabel);
			
			JLabel lblDelete = new JLabel("<HTML><u>Izbrisi</u></HTML>");
			lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDelete.setBounds(220, h*i, 60, h);
			lblDelete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
					//System.out.println("Izbrisan film: " + m.getName() + ".");
					AdminController.processRemoveShowtime(s);
			    }  
			});
			innerPanel.add(lblDelete);
			i++;
		}
		innerPanel.setPreferredSize(new Dimension(listPanel.getWidth(), i*h));
	}
	

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Ime");
			lblName.setBounds(20, 21, 153, 14);
		}
		return lblName;
	}
	private JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Year");
			lblYear.setBounds(20, 41, 46, 14);
		}
		return lblYear;
	}
	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Description");
			lblDescription.setVerticalAlignment(SwingConstants.TOP);
			lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
			lblDescription.setBounds(284, 21, 414, 85);
		}
		return lblDescription;
	}
	private JLabel getLblCast() {
		if (lblCast == null) {
			lblCast = new JLabel("Cast");
			lblCast.setBounds(20, 91, 163, 14);
		}
		return lblCast;
	}
	private JLabel getLblDirector() {
		if (lblDirector == null) {
			lblDirector = new JLabel("Director");
			lblDirector.setBounds(20, 116, 153, 14);
		}
		return lblDirector;
	}
	private JLabel getLblLength() {
		if (lblLength == null) {
			lblLength = new JLabel("Length");
			lblLength.setBounds(20, 151, 46, 14);
		}
		return lblLength;
	}
	private JLabel getLblimdbRating() {
		if (lblimdbRating == null) {
			lblimdbRating = new JLabel("ImdbRating");
			lblimdbRating.setBounds(20, 174, 78, 14);
		}
		return lblimdbRating;
	}
	private JLabel getLblImdblink() {
		if (lblImdblink == null) {
			lblImdblink = new JLabel("imdbLink");
			lblImdblink.setBounds(20, 201, 153, 14);
		}
		return lblImdblink;
	}
	private JLabel getLblGenre() {
		if (lblGenre == null) {
			lblGenre = new JLabel("Genre");
			lblGenre.setBounds(20, 66, 153, 14);
		}
		return lblGenre;
	}
	private JLabel getLblReservationNumber() {
		if (lblReservationNumber == null) {
			lblReservationNumber = new JLabel("New label");
			lblReservationNumber.setBounds(20, 226, 46, 14);
		}
		return lblReservationNumber;
	}
}
