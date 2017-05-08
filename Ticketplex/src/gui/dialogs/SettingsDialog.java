package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import gui.MainWindow;
import gui.HeaderPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class SettingsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public static Color okvir = new Color(22, 22, 22); 
	
	Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
	CompoundBorder errorBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.red), emptyBorder);
	private JTextField txtNew;
	private JTextField textOld;
	
	public SettingsDialog() {
		setBounds(0, 0, 255, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		contentPanel.setBackground(MainWindow.grayLight);
		setLocationRelativeTo(null);

		
		HeaderPanel headerPanel = new HeaderPanel(this);
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 450, 30);
		contentPanel.add(headerPanel);
		
		JLabel lblPode = new JLabel("Pode\u0161avanja");
		lblPode.setHorizontalAlignment(SwingConstants.LEFT);
		lblPode.setForeground(Color.WHITE);
		lblPode.setFont(new Font("Arial", Font.BOLD, 20));
		lblPode.setBounds(10, 0, 243, 30);
		headerPanel.add(lblPode);
		
		txtNew = new JTextField();
		txtNew.setText("Nova lozinka");
		txtNew.setForeground(new Color(153, 153, 153));
		txtNew.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNew.setColumns(10);
		txtNew.setBorder(emptyBorder);
		txtNew.setBounds(10, 96, 236, 35);
		contentPanel.add(txtNew);
		
		txtNew.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtNew.getText().equals("Nova lozinka")) txtNew.setText("");
			}
		});
		
		textOld = new JTextField();
		textOld.setText("Stara lozinka");
		textOld.setForeground(new Color(153, 153, 153));
		textOld.setFont(new Font("Arial", Font.PLAIN, 14));
		textOld.setColumns(10);
		textOld.setBorder(emptyBorder);
		textOld.setBounds(10, 50, 236, 35);
		contentPanel.add(textOld);
		
		textOld.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textOld.getText().equals("Stara lozinka")) textOld.setText("");
			}
		});
		
		JButton btnNewPass = new JButton("PROMENI LOZINKU");
		UIManager.put(btnNewPass.getBackground(), MainWindow.grayDark);
		btnNewPass.setForeground(new Color(153, 153, 153));
		btnNewPass.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewPass.setBorder(null);
		btnNewPass.setBackground(new Color(32, 32, 32));
		btnNewPass.setBounds(10, 142, 236, 30);
		contentPanel.add(btnNewPass);
		btnNewPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		btnNewPass.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnNewPass.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnNewPass.setBackground(MainWindow.grayDark);
		    }
		});
		
		
		JLabel label = new JLabel("Uspesno ste se registrovali");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(211, 211, 211));
		label.setFont(new Font("Arial", Font.BOLD, 13));
		
		label.setBounds(0, 205, 255, 25);
		contentPanel.add(label);
		
		JLabel lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setBounds(10, 245, 46, 25);
		contentPanel.add(lblBack);
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
		
		JTextField a = new JTextField();
		a.setBounds(-10, -10, 0, 0);
		a.requestFocus();
		contentPanel.add(a);
	}
}
