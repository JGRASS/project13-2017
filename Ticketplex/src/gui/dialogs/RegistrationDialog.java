package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import gui.HeaderPanel;
import gui.MainWindow;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtPassword;

	
	Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
	CompoundBorder errorBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.red), emptyBorder);
	
	public static Color okvir = new Color(22, 22, 22); 
	private JLabel lblBack;
	
	

	/**
	 * Create the dialog.
	 */
	public RegistrationDialog() {
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		this.requestFocus();
		setBounds(0, 0, 255, 265);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, okvir));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(MainWindow.grayLight);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		HeaderPanel headerPanel = new HeaderPanel(this);
		headerPanel.setBounds(0, 0, 450, 30);
		contentPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registracija");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 127, 30);
		headerPanel.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBorder(emptyBorder);
		txtUsername.setForeground(new Color(153, 153, 153));
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
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
		
		txtEmail = new JTextField();
		txtEmail.setBorder(emptyBorder);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setForeground(new Color(153, 153, 153));
		txtEmail.setText("Email");
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 96, 236, 35);
		contentPanel.add(txtEmail);
		
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtEmail.getText().equals("Email")) txtEmail.setText("");
			}
		});
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(emptyBorder);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setForeground(new Color(153, 153, 153));
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(10, 142, 236, 35);
		contentPanel.add(txtPassword);
		
		JButton btnRegister = new JButton("REGISTRUJ SE");
		UIManager.put(btnRegister.getBackground(), MainWindow.grayDark);
		btnRegister.setForeground(new Color(153,153,153));
		btnRegister.setBackground(MainWindow.grayDark);
		btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegister.setBounds(10, 188, 236, 30);
		btnRegister.setBorder(null);
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnRegister.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnRegister.setBackground(MainWindow.grayDark);
		    }
		});
		
		contentPanel.add(btnRegister);
		
		lblBack = new JLabel("<html><u>Nazad</u></html>");
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblBack.setForeground(new Color(153, 153, 153));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		lblBack.setBounds(10, 229, 46, 25);
		contentPanel.add(lblBack);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtPassword.getText().equals("Password")) txtPassword.setText("");
			}
		});
		
		
		
		
		
	}
}
