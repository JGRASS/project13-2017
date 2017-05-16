package gui.dialogs;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import ticketplex.Movie;
import ticketplex.Showtime;
import gui.Controller;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ReservationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblError;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ReservationDialog(Movie mov, LinkedList<Showtime> showtimes) {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		this.requestFocus();
		setBounds(0, 0, 255, 329);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, MainWindow.okvir));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(MainWindow.grayLight);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		HeaderPanel headerPanel = new HeaderPanel(this);
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 450, 30);
		contentPanel.add(headerPanel);
		
		JLabel lblRes = new JLabel("Rezervacija: "+mov.getName());
		lblRes.setForeground(Color.WHITE);
		lblRes.setFont(new Font("Arial", Font.BOLD, 18));
		lblRes.setBounds(10, 0, 244, 30);
		headerPanel.add(lblRes);

		JLabel lblShowtime=new JLabel("Odaberite projekciju");
		lblShowtime.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowtime.setForeground(new Color(211,211,211));
		lblShowtime.setFont(new Font("Arial", Font.BOLD, 14));
		lblShowtime.setBounds(0,41,255,30);
		contentPanel.add(lblShowtime);
		
		JLabel lblSeats=new JLabel("Broj rezervacija");
		lblSeats.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeats.setForeground(new Color(211,211,211));
		lblSeats.setFont(new Font("Arial", Font.BOLD, 14));
		lblSeats.setBounds(0,120,255,30);
		contentPanel.add(lblSeats);
		
		JComboBox<Showtime> boxTime = new JComboBox<Showtime>();

		boxTime.setBounds(27, 82, 195, 20);
		
		for(Showtime s : showtimes){
			boxTime.addItem(s);
		}
		boxTime.setBorder(null);
		boxTime.setBackground(new Color(249,249,249));
		contentPanel.add(boxTime);
		
		JButton btnReserve = new JButton("REZERVI\u0160I");
		btnReserve.setBounds(10, 227, 236, 30);
		UIManager.put(btnReserve.getBackground(), MainWindow.grayDark);
		btnReserve.setForeground(new Color(153,153,153));
		btnReserve.setBackground(MainWindow.grayDark);
		btnReserve.setFont(new Font("Arial", Font.BOLD, 14));
		btnReserve.setBorder(null);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		contentPanel.add(btnReserve);
		
		JLabel lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setBounds(10, 294, 46, 25);
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		contentPanel.add(lblBack);
		
		JSlider seatsSlider = new JSlider();
		seatsSlider.setForeground(UIManager.getColor("Button.background"));
		seatsSlider.setSnapToTicks(true);
		seatsSlider.setPaintLabels(true);
		seatsSlider.setMajorTickSpacing(1);
		seatsSlider.setMaximum(5);
		seatsSlider.setMinimum(1);
		seatsSlider.setBounds(22, 161, 200, 45);
		seatsSlider.setBorder(null);
		seatsSlider.setBackground(MainWindow.grayLight);
		@SuppressWarnings("rawtypes")
		Dictionary dictionary = seatsSlider.getLabelTable();
	      if (dictionary != null) {
	         @SuppressWarnings("rawtypes")
			Enumeration keys = dictionary.keys();
	         while (keys.hasMoreElements()) {
	            JLabel label = (JLabel) dictionary.get(keys.nextElement());
	            label.setOpaque(true);
	            label.setForeground(new Color(211,211,211));
	            label.setBackground(MainWindow.grayLight);
	         }
	      }
		contentPanel.add(seatsSlider);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(211, 211, 211));
		lblError.setFont(new Font("Arial", Font.BOLD, 13));
		lblError.setBounds(0, 230, 255, 25);
		contentPanel.add(lblError);
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
		
		btnReserve.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				Showtime showtime=(Showtime) boxTime.getSelectedItem();
				Controller.processNewReservation(showtime.getId(),seatsSlider.getValue());
				btnReserve.setVisible(false);
				
			}
			
			
		    public void mouseEntered(MouseEvent evt) {
		        btnReserve.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(MouseEvent evt) {
		        btnReserve.setBackground(MainWindow.grayDark);
		    }
		});
		
}
	public void showMsg(String msg) {
		lblError.setText(msg);
	}
	
	
}
