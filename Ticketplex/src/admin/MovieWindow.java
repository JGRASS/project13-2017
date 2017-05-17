package admin;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ticketplex.Movie;
import ticketplex.Showtime;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieWindow extends JFrame {

	/**
	 * 
	 */
	public int movie_id = 0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel, innerPanel;
	private JScrollPane scrollPane;
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
	
	private JButton btnDodajPrikazivanje, btnIzmeniDetalje;



	/**
	 * Create the frame.
	 */
	public MovieWindow(Movie m) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Icon-Admin-48.png"));
		movie_id = m.getId();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 396);
		setLocationRelativeTo(null);
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
		
		btnDodajPrikazivanje = new JButton("Dodaj prikazivanje");
		btnDodajPrikazivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnDodajPrikazivanje.setBounds(549, 131, 148, 23);
		contentPane.add(btnDodajPrikazivanje);
		
		JLabel lblPrikazivanja = new JLabel("Prikazivanja");
		lblPrikazivanja.setBounds(284, 141, 121, 14);
		contentPane.add(lblPrikazivanja);
		
		btnIzmeniDetalje = new JButton("Izmeni detalje");

		btnIzmeniDetalje.setBounds(549, 107, 148, 23);
		contentPane.add(btnIzmeniDetalje);
		
	
	}
	//prosiriiiii//
	void setMovie(Movie m){
		movie_id = m.getId();
		setTitle("Film: "+m.getName());
		lblName.setText("Ime: "+m.getName());
		lblYear.setText("Godina: "+String.valueOf(m.getYear()));
		lblGenre.setText("Žanr: "+m.getGenre());
		lblDescription.setText("<html>"+m.getDescription()+"</html>");
		lblCast.setText("Uloge: "+m.getCast());
		lblDirector.setText("Režiser:  "+ m.getDirector());
		lblLength.setText("Trajanje: "+String.valueOf(m.getLength())+" min");
		lblimdbRating.setText("Ocena: "+m.getImdbRating());
		lblImdblink.setText("Link: "+m.getImdbLink());
		lblReservationNumber.setText("Broj rezervacija: "+String.valueOf(AdminController.processGetNumberOfReservations(m.getId())));
		

		for( ActionListener al : btnDodajPrikazivanje.getActionListeners() ) {
			btnDodajPrikazivanje.removeActionListener( al );
		}
		btnDodajPrikazivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminController.addNewShowtime(m);
			}
		});
		
		for( ActionListener al : btnIzmeniDetalje.getActionListeners() ) {
			btnIzmeniDetalje.removeActionListener( al );
		}
		btnIzmeniDetalje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminController.showEditMovieDialog(m.getId());
			}
		});
	}
	void setList(LinkedList<Showtime> showtimes){

		innerPanel.removeAll();
		innerPanel.repaint();
		
		int i = 0;
		int h = 25;
		for (Showtime s : showtimes) {
			SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String datum=fmt.format(s.getDateAsCalendar().getTime());
			
			
			
			JLabel lblDate = new JLabel(datum);
			lblDate.setBounds(10, h*i, 100, h);
			innerPanel.add(lblDate);
			
			JLabel lblRes = new JLabel(s.getNumOfReservations()+" rezervacija");
			lblRes.setBounds(150, h*i, 100, h);
			innerPanel.add(lblRes);
			
			JLabel lblSeats = new JLabel(s.getNumOfSeats()+"/"+Showtime.seats+" mesta");
			lblSeats.setBounds(250, h*i, 100, h);
			innerPanel.add(lblSeats);
			
			JLabel lblDelete = new JLabel("<HTML><u>Izbrisi</u></HTML>");
			lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDelete.setBounds(370, h*i, 60, h);
			lblDelete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
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
			lblName.setBounds(10, 21, 240, 14);
		}
		return lblName;
	}
	private JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Year");
			lblYear.setBounds(10, 59, 240, 14);
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
			lblCast.setBounds(10, 135, 240, 14);
		}
		return lblCast;
	}
	private JLabel getLblDirector() {
		if (lblDirector == null) {
			lblDirector = new JLabel("Director");
			lblDirector.setBounds(10, 173, 240, 14);
		}
		return lblDirector;
	}
	private JLabel getLblLength() {
		if (lblLength == null) {
			lblLength = new JLabel("Length");
			lblLength.setBounds(10, 211, 240, 14);
		}
		return lblLength;
	}
	private JLabel getLblimdbRating() {
		if (lblimdbRating == null) {
			lblimdbRating = new JLabel("ImdbRating");
			lblimdbRating.setBounds(10, 249, 240, 14);
		}
		return lblimdbRating;
	}
	private JLabel getLblImdblink() {
		if (lblImdblink == null) {
			lblImdblink = new JLabel("imdbLink");
			lblImdblink.setBounds(10, 287, 240, 14);
		}
		return lblImdblink;
	}
	private JLabel getLblGenre() {
		if (lblGenre == null) {
			lblGenre = new JLabel("Genre");
			lblGenre.setBounds(10, 97, 240, 14);
		}
		return lblGenre;
	}
	private JLabel getLblReservationNumber() {
		if (lblReservationNumber == null) {
			lblReservationNumber = new JLabel("New label");
			lblReservationNumber.setBounds(10, 325, 240, 14);
		}
		return lblReservationNumber;
	}
	
	public void closeWindow() {
		dispose();
	}
}
