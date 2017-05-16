package admin;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ticketplex.Reservation;
import ticketplex.User;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class UserWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel, innerPanel;
	private JScrollPane scrollPane;
	private JLabel lblUsername;
	private JLabel lblEmail;
	private JButton btnIzbrisiKorisnika;



	/**
	 * Create the frame.
	 */
	public UserWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Icon-Admin-48.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 435, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblUsername());
		contentPane.add(getLblEmail());
		
		listPanel = new JPanel();
		listPanel.setBounds(20, 91, 385, 191);
		listPanel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, listPanel.getWidth(), listPanel.getHeight());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		innerPanel = new JPanel();
		scrollPane.setViewportView(innerPanel);
		innerPanel.setLayout(null);
		
		listPanel.add(scrollPane);
		contentPane.add(listPanel);
		
		JLabel lblRezervacije = new JLabel("Rezervacije:");
		lblRezervacije.setBounds(20, 66, 121, 14);
		contentPane.add(lblRezervacije);
		contentPane.add(getBtnIzbrisiKorisnika());
		
	
	}
	
	void setUser(User u){
		setTitle("Korisnik: "+u.getUsername());
		lblUsername.setText(u.getUsername());
		lblEmail.setText(u.getEmail());
		
		for( ActionListener al : btnIzbrisiKorisnika.getActionListeners() ) {
			btnIzbrisiKorisnika.removeActionListener( al );
		}
		btnIzbrisiKorisnika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdminController.processRemoveUser(u.getId());
			}
		});
	}
	void setList(LinkedList<Reservation> reservations){

		innerPanel.removeAll();
		innerPanel.repaint();
		
		int i = 0;
		int h = 25;
		for (Reservation r : reservations) {
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String datum = fmt.format(r.getDateAsCalendar().getTime());
			
			
			JLabel lblMovieName = new JLabel(r.getMovieName());
			lblMovieName.setBounds(0, h*i, 120, h);
			innerPanel.add(lblMovieName);
			
			JLabel lblDate = new JLabel(datum);
			lblDate.setBounds(130, h*i, 100, h);
			innerPanel.add(lblDate);
			
			JLabel lblSeats = new JLabel("sedista: "+r.getNumber_of_seats());
			lblSeats.setBounds(250, h*i, 100, h);
			innerPanel.add(lblSeats);
			
			JLabel lblDelete = new JLabel("<HTML><u>Izbrisi</u></HTML>");
			lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDelete.setBounds(330, h*i, 60, h);
			lblDelete.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
					
					AdminController.processRemoveReservation(r);
			    }  
			});
			innerPanel.add(lblDelete);
			i++;
		}
		innerPanel.setPreferredSize(new Dimension(listPanel.getWidth(), i*h));
	}
	

	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Ime");
			lblUsername.setBounds(20, 21, 153, 14);
		}
		return lblUsername;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
			lblEmail.setBounds(20, 41, 171, 14);
		}
		return lblEmail;
	}
	private JButton getBtnIzbrisiKorisnika() {
		if (btnIzbrisiKorisnika == null) {
			btnIzbrisiKorisnika = new JButton("Izbrisi korisnika");
			
			btnIzbrisiKorisnika.setBounds(273, 17, 132, 23);
		}
		return btnIzbrisiKorisnika;
	}
}
