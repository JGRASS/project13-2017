package gui.dialogs;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Controller;
import gui.HeaderPanel;
import gui.MainWindow;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ForgotPasswordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Color okvir = new Color(22, 22, 22); 
	
	private JTextField txtUsername;
	private JLabel lblError;
	
	

	/**
	 * Create the dialog.
	 */
	public ForgotPasswordDialog() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 255, 181);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, okvir));
		contentPanel.requestFocus();
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
		
		JLabel lblZaboravljenaLozinka = new JLabel("Zaboravljena lozinka");
		lblZaboravljenaLozinka.setHorizontalAlignment(SwingConstants.LEFT);
		lblZaboravljenaLozinka.setForeground(Color.WHITE);
		lblZaboravljenaLozinka.setFont(new Font("Arial", Font.BOLD, 20));
		lblZaboravljenaLozinka.setBounds(10, 0, 243, 30);
		headerPanel.add(lblZaboravljenaLozinka);
		
		
		
		JButton btnNewPass = new JButton("NOVA LOZINKA");
		UIManager.put(btnNewPass.getBackground(), MainWindow.grayDark);
		btnNewPass.setForeground(new Color(153, 153, 153));
		btnNewPass.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewPass.setBorder(null);
		btnNewPass.setBackground(new Color(32, 32, 32));
		btnNewPass.setBounds(10, 95, 236, 30);
		btnNewPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPanel.add(btnNewPass);
		
		JTextField a = new JTextField();
		a.setBounds(-10, -10, 0, 0);
		a.requestFocus();
		contentPanel.add(a);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(153, 153, 153));
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUsername.setBorder(MainWindow.emptyBorder);
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 50, 236, 35);
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtUsername.getText().equals("Username")) txtUsername.setText("");
			}
		});
		
		btnNewPass.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setBorder(MainWindow.emptyBorder);
				Controller.processResetPassword(txtUsername.getText());
				
				//btnNewPass.setVisible(false);
			}
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnNewPass.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnNewPass.setBackground(MainWindow.grayDark);
		    }
		});
		
		JLabel lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
		lblBack.setBounds(10, 149, 46, 25);
		contentPanel.add(lblBack);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Arial", Font.BOLD, 13));
		lblError.setForeground(new Color(211, 211, 211));
		lblError.setBounds(0, 130, 255, 25);
		contentPanel.add(lblError);
		
		
		
		
		
		
	}



	public void showMsg(String msg) {
		lblError.setText(msg);
		if(msg.equals("Ne postoji takav korisnik!")){
			txtUsername.setBorder(MainWindow.errorBorder);
		}
		
	}
}
