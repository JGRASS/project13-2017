package gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gui.HeaderPanel;
import gui.MainWindow;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AlertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public AlertDialog() {
		
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		this.requestFocus();
		setBounds(0, 0, 255, 171);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, MainWindow.okvir));
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
		
		JLabel lblAlert = new JLabel("MORATE BITI ULOGOVANI!");
		lblAlert.setBounds(36, 56, 183, 14);
		lblAlert.setFont(new Font("Arial", Font.BOLD, 14));
		lblAlert.setForeground(new Color(211,211,211));
		contentPanel.add(lblAlert);
		
		JButton btnBack = new JButton("NAZAD");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(52, 101, 148, 30);
		UIManager.put(btnBack.getBackground(), MainWindow.grayDark);
		btnBack.setForeground(new Color(153,153,153));
		btnBack.setBackground(MainWindow.grayDark);
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setBorder(null);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnBack.setBackground(new Color(25, 25, 25));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnBack.setBackground(MainWindow.grayDark);
		    }
		});
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		
		contentPanel.add(btnBack);
		
		
		
		}
}
