package admin;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ticketplex.User;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class UsersWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel listPanel;
	private JLabel lblListaKorisnika;
	private JScrollPane scrollPane;
	private JPanel panel;


	/**
	 * Create the frame.
	 */
	public UsersWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Icon-Admin-48.png"));
		setTitle("Lista korisnika");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		contentPane.setLayout(null);
		lblListaKorisnika = new JLabel("Korisnici:");
		lblListaKorisnika.setHorizontalAlignment(SwingConstants.LEFT);
		lblListaKorisnika.setBounds(10, 22, 194, 14);
		contentPane.add(lblListaKorisnika);
		
		listPanel = new JPanel();
		listPanel.setBounds(10, 59, 414, 191);
		listPanel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, listPanel.getWidth(), listPanel.getHeight());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel = new JPanel();
		panel.setLayout(null);
		scrollPane.setViewportView(panel);
		
	}
	
	void setList(LinkedList<User> users){
		panel.removeAll();
		panel.repaint();
		int i = 0;
		int h = 25;
		for (User u : users) {
			JLabel lblNewLabel;
			JLabel lblOpcije;
			lblNewLabel = new JLabel(u.getUsername());
			lblNewLabel.setBounds(0, h*i, 200, h);
			panel.add(lblNewLabel);
			

			lblOpcije = new JLabel("<HTML><u>Opcije</u></HTML>");
			lblOpcije.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblOpcije.setBounds(330, h*i, 147, h);
			lblOpcije.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e)  
			    {  
					AdminController.showUser(u);;
			    }  
			});
			panel.add(lblOpcije);
			i++;
		}
		panel.setPreferredSize(new Dimension(listPanel.getWidth(), i*h));
		
		listPanel.add(scrollPane);
		contentPane.add(listPanel);
		
	}
	
	public void closeWindow() {
		dispose();
	}

}
