package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import ticketplex.Movie;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class ReservationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ReservationDialog(Movie mov) {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		this.requestFocus();
		setBounds(0, 0, 255, 360);
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
		
		JLabel lblDate=new JLabel("Datum");
		lblDate.setForeground(new Color(211,211,211));
		lblDate.setFont(new Font("Arial", Font.BOLD, 14));
		lblDate.setBounds(53,40,60,30);

		contentPanel.add(lblDate);

		JLabel lblTime=new JLabel("Vreme");
		lblTime.setForeground(new Color(211,211,211));
		lblTime.setFont(new Font("Arial", Font.BOLD, 14));
		lblTime.setBounds(53,96,60,30);
		contentPanel.add(lblTime);
		
		JLabel lblSeats=new JLabel("Broj rezervacija");
		lblSeats.setForeground(new Color(211,211,211));
		lblSeats.setFont(new Font("Arial", Font.BOLD, 14));
		lblSeats.setBounds(53,152,144,30);
		contentPanel.add(lblSeats);
		
		/*JSlider slider = new JSlider();
		slider.setBounds(31, 193, 200, 26);
		slider.setBackground(MainWindow.grayLight);
		slider.a
		
		contentPanel.add(slider);*/
		
		JComboBox boxDate = new JComboBox();
		boxDate.setBounds(63, 69, 121, 20);
		boxDate.addItem("Test1");
		boxDate.addItem("Test2");
		boxDate.setBorder(null);
		boxDate.setBackground(new Color(249,249,249));
		
		/*bvoxDate.setRenderer(new DefaultListCellRenderer() {
		    public void paint(Graphics g) {
		        boxDate.setBackground(Color.YELLOW);
		        boxDate.setForeground(Color.RED);
		        super.paint(g);
		    }
		});*/
	
		contentPanel.add(boxDate);
		
		JComboBox boxTime = new JComboBox();
		boxTime.setBounds(63, 124, 121, 20);
		boxTime.addItem("Test1");
		boxTime.addItem("Test2");
		boxTime.setBorder(null);
		boxTime.setBackground(new Color(249,249,249));
		contentPanel.add(boxTime);
		
		JButton btnReserve = new JButton("REZERVI\u0160I");
		btnReserve.setBounds(10, 238, 236, 30);
		UIManager.put(btnReserve.getBackground(), MainWindow.grayDark);
		btnReserve.setForeground(new Color(153,153,153));
		btnReserve.setBackground(MainWindow.grayDark);
		btnReserve.setFont(new Font("Arial", Font.BOLD, 14));
		btnReserve.setBorder(null);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		btnReserve.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnReserve.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnReserve.setBackground(MainWindow.grayDark);
		    }
		});
		
		contentPanel.add(btnReserve);
		
		JLabel lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setBounds(10, 324, 46, 25);
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		contentPanel.add(lblBack);
		
		JSlider slider = new JSlider();
		slider.setForeground(UIManager.getColor("Button.background"));
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setBounds(27, 182, 200, 45);
		slider.setBorder(null);
		slider.setBackground(MainWindow.grayLight);
		Dictionary dictionary = slider.getLabelTable();
	      if (dictionary != null) {
	         Enumeration keys = dictionary.keys();
	         while (keys.hasMoreElements()) {
	            JLabel label = (JLabel) dictionary.get(keys.nextElement());
	            // uncomment these following lines to get a background for your labels
	            label.setOpaque(true);
	            label.setForeground(new Color(211,211,211));
	            label.setBackground(MainWindow.grayLight);
	         }
	      }
		contentPanel.add(slider);
		
		JLabel lblUspesnoSteRezervisali = new JLabel("Uspe\u0161no ste rezervisali mesta");
		lblUspesnoSteRezervisali.setHorizontalAlignment(SwingConstants.CENTER);
		lblUspesnoSteRezervisali.setForeground(new Color(211, 211, 211));
		lblUspesnoSteRezervisali.setFont(new Font("Arial", Font.BOLD, 13));
		lblUspesnoSteRezervisali.setBounds(0, 288, 255, 25);
		contentPanel.add(lblUspesnoSteRezervisali);
		
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
}
	
	
}
