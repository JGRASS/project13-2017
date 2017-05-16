package gui.dialogs;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import ticketplex.Reservation;
import gui.Controller;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UserReservationsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel scrollPaneParent, scrollPaneContent;
	private JScrollPane scrollPane;

	
	public UserReservationsDialog() {
		setBounds(100, 100, 500, 370);
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
		headerPanel.setBounds(0, 0, 500, 42);
		contentPanel.add(headerPanel);
		
		JLabel lblVaeRezervacije = new JLabel("Va\u0161e rezervacije");
		lblVaeRezervacije.setBounds(10, 0, 500, 42);
		headerPanel.add(lblVaeRezervacije);
		lblVaeRezervacije.setForeground(Color.WHITE);
		lblVaeRezervacije.setFont(new Font("Arial", Font.BOLD, 20));
		
		scrollPaneParent = new JPanel();
		scrollPaneParent.setBounds(0, 41, 500, 283);
		contentPanel.add(scrollPaneParent);
		scrollPaneParent.setBorder(null);
		scrollPaneParent.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 283);;
		scrollPane.setBorder(null);
		scrollPaneParent.add(scrollPane);
		
		scrollPaneContent = new JPanel();
		scrollPaneContent.setBackground(MainWindow.grayDark);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		scrollPane.setViewportView(scrollPaneContent);
		scrollPaneContent.setBorder(null);
		scrollPaneContent.setLayout(null);

		
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


	public void renderList(LinkedList<Reservation> reservations) {
		
		scrollPaneContent.removeAll();
		scrollPaneContent.repaint();
		
		int i=0; 
		int height=35;
		//int heightD=36;
		for(Reservation res: reservations){
			JLabel txtMov=new JLabel(res.getMovieName());
			txtMov.setFont(new Font("Arial", Font.BOLD, 14));
			txtMov.setForeground(new Color(211,211,211));
			txtMov.setBounds(10, i*height, 140, height);
			scrollPaneContent.add(txtMov);
			

			SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy");
			String datum=fmt.format(res.getDateAsCalendar().getTime());
			
			JLabel txtDate=new JLabel(datum);
			txtDate.setFont(new Font("Arial", Font.PLAIN, 14));
			txtDate.setForeground(new Color(211,211,211));
			txtDate.setBounds(170, i*height, 100, height);
			scrollPaneContent.add(txtDate);
			
			SimpleDateFormat fmtH=new SimpleDateFormat("kk:mm");
			String sat=fmtH.format(res.getDateAsCalendar().getTime());
			
			JLabel txtHour=new JLabel(sat);
			txtHour.setFont(new Font("Arial", Font.PLAIN, 14));
			txtHour.setForeground(new Color(211,211,211));
			txtHour.setBounds(290, i*height, 50, height);
			scrollPaneContent.add(txtHour);
			
			JLabel txtSeats=new JLabel(res.getNumber_of_seats()+"");
			txtSeats.setFont(new Font("Arial", Font.PLAIN, 14));
			txtSeats.setForeground(new Color(211,211,211));
			txtSeats.setBounds(370, i*height, 10, height);
			scrollPaneContent.add(txtSeats);
			
			JLabel lblDel=new JLabel("<html><u>odustani</u></html>");
			lblDel.setFont(new Font("Arial",Font.PLAIN, 14));
			lblDel.setForeground(new Color(211,211,211));
			lblDel.setBounds(430, i*height, 300, height);
			lblDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			JLabel lblRemoved=new JLabel("REZERVACIJA OTKAZANA");
			lblRemoved.setFont(new Font("Arial", Font.BOLD, 16));
			lblRemoved.setForeground(new Color(255,255,255));
			lblRemoved.setBounds(0, i*height, 500, height);
			lblRemoved.setVisible(false);
			lblRemoved.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPaneContent.add(lblRemoved);
			
			lblDel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Controller.processRemoveReservation(res.getId());
					txtMov.setVisible(false);
					txtHour.setVisible(false);
					txtDate.setVisible(false);
					txtSeats.setVisible(false);
					lblDel.setVisible(false);
					lblRemoved.setVisible(true);
				}
			});
			scrollPaneContent.add(lblDel);
			
			i++;
			
		}
		scrollPaneContent.setPreferredSize(new Dimension(scrollPane.getWidth(), i*height));
		 
		
	}
}
