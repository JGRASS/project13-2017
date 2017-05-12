package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import ticketplex.Reservation;
import ticketplex.TicketplexClient;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class UserReservationsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public UserReservationsDialog() {
		setBounds(100, 100, 430, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setVisible(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, MainWindow.okvir));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(MainWindow.grayLight);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		
		HeaderPanel headerPanel = new HeaderPanel(this);
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 430, 42);
		contentPanel.add(headerPanel);
		
		JLabel lblVaeRezervacije = new JLabel("Va\u0161e rezervacije");
		lblVaeRezervacije.setBounds(10, 0, 430, 42);
		headerPanel.add(lblVaeRezervacije);
		lblVaeRezervacije.setForeground(Color.WHITE);
		lblVaeRezervacije.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPanel scrollPaneParent = new JPanel();
		scrollPaneParent.setBounds(0, 41, 430, 283);
		contentPanel.add(scrollPaneParent);
		scrollPaneParent.setBorder(null);
		scrollPaneParent.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 430, 283);;
		scrollPane.setBorder(null);
		scrollPaneParent.add(scrollPane);
		
		JPanel scrollPaneContent = new JPanel();
		scrollPaneContent.setBackground(MainWindow.grayDark);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		scrollPane.setViewportView(scrollPaneContent);
		scrollPaneContent.setBorder(null);
		scrollPaneContent.setLayout(null);
		
		LinkedList<Reservation> reservations;
		TicketplexClient tc=new TicketplexClient();
		//tc.register("test123", "test123", "sdadsa");
		try {
			tc.login("test123", "test123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reservations = tc.getUserReservations();
		for(int k=0; k<100; k++){
			reservations.add(reservations.getFirst());
			
		}
		
		int i=0; 
		int height=35;
		int heightD=36;
		for(Reservation res: reservations){
			JLabel txtMov=new JLabel(res.getMovieName());
			txtMov.setFont(new Font("Arial", Font.BOLD, 14));
			txtMov.setForeground(new Color(211,211,211));
			txtMov.setBounds(10, i*height, 300, height);
			scrollPaneContent.add(txtMov);
			

			SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy");
			String datum=fmt.format(res.getDateAsCalendar().getTime());
			
			JLabel txtDate=new JLabel(datum);
			txtDate.setFont(new Font("Arial", Font.PLAIN, 14));
			txtDate.setForeground(new Color(211,211,211));
			txtDate.setBounds(100, i*height, 100, height);
			scrollPaneContent.add(txtDate);
			
			SimpleDateFormat fmtH=new SimpleDateFormat("kk:mm");
			String sat=fmtH.format(res.getDateAsCalendar().getTime());
			
			JLabel txtHour=new JLabel(sat);
			txtHour.setFont(new Font("Arial", Font.PLAIN, 14));
			txtHour.setForeground(new Color(211,211,211));
			txtHour.setBounds(220, i*height, 50, height);
			scrollPaneContent.add(txtHour);
			
			JLabel txtSeats=new JLabel(res.getNumber_of_seats()+"");
			txtSeats.setFont(new Font("Arial", Font.PLAIN, 14));
			txtSeats.setForeground(new Color(211,211,211));
			txtSeats.setBounds(300, i*height, 10, height);
			scrollPaneContent.add(txtSeats);
			
			JLabel txtDel=new JLabel("<html><u>odustani</u></html>");
			txtDel.setFont(new Font("Arial",Font.PLAIN, 14));
			txtDel.setForeground(new Color(211,211,211));
			txtDel.setBounds(360, i*height, 300, height);
			txtDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			txtDel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
				}
			});
			scrollPaneContent.add(txtDel);
			
			i++;
			
		}
		scrollPaneContent.setPreferredSize(new Dimension(scrollPane.getWidth(), i*height));

		
		JLabel lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setBounds(10, 345, 46, 14);
		contentPanel.add(lblBack);
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		contentPanel.add(lblBack);

		
	}
}
