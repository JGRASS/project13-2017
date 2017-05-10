package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class UserReservationsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public UserReservationsDialog() {
		setBounds(100, 100, 450, 300);
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
		
		HeaderPanel headerPanel = new HeaderPanel((JDialog) null);
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 450, 30);
		contentPanel.add(headerPanel);
		
		JLabel lblVaeRezervacije = new JLabel("Va\u0161e rezervacije");
		lblVaeRezervacije.setForeground(Color.WHITE);
		lblVaeRezervacije.setFont(new Font("Arial", Font.BOLD, 20));
		lblVaeRezervacije.setBounds(10, 0, 460, 30);
		headerPanel.add(lblVaeRezervacije);
	}
}
