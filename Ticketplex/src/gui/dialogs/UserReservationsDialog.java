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
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class UserReservationsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public UserReservationsDialog() {
		setBounds(100, 100, 450, 370);
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
		headerPanel.setBounds(0, 0, 450, 42);
		contentPanel.add(headerPanel);
		
		JLabel lblVaeRezervacije = new JLabel("Va\u0161e rezervacije");
		lblVaeRezervacije.setBounds(10, 0, 460, 42);
		headerPanel.add(lblVaeRezervacije);
		lblVaeRezervacije.setForeground(Color.WHITE);
		lblVaeRezervacije.setFont(new Font("Arial", Font.BOLD, 20));
		
		JPanel scrollPaneParent = new JPanel();
		scrollPaneParent.setBounds(0, 41, 450, 293);
		contentPanel.add(scrollPaneParent);
		scrollPaneParent.setBorder(null);
		scrollPaneParent.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, scrollPaneParent.getWidth(), scrollPaneParent.getHeight());;
		scrollPane.setBorder(null);
		scrollPaneParent.add(scrollPane);
		
		JPanel scrollPaneContent = new JPanel();
		scrollPaneContent.setBackground(UIManager.getColor("CheckBox.darkShadow"));
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
		int height=20;
		for(Reservation res: reservations){
			JLabel txtRes=new JLabel(res.getMovieName());
			txtRes.setBounds(0, i*height, 300, height);
			scrollPaneContent.add(txtRes);
			i++;
			
		}
		scrollPaneContent.setPreferredSize(new Dimension(scrollPane.getWidth(), i*height));

		
	}
}
