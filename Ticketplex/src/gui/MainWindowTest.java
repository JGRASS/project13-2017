package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindowTest extends JFrame {
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowTest frame = new MainWindowTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel containerPane;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private HeaderPanel headerPanel;
	private JLabel lblBreadcrumbs;
	
	
	private static Color grayDark = new Color(32, 32, 32); 
	private static Color grayLight = new Color(49, 49, 49); 
	

	

	public MainWindowTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);		
		setBounds(100, 100, 900, 520);
		
		containerPane = new JPanel();
		containerPane.setBackground(grayLight);	
		containerPane.setLayout(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(
                1, 1, 1, 1, Color.black));
		setContentPane(containerPane);
		
		
		initHeader();
		initMain();
		initSide();
		

		
	}
	
	public void initHeader(){
		headerPanel = new HeaderPanel(this);
		headerPanel.setBounds(0, 0, 900, 30);
		headerPanel.setLayout(null);
		
		
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Calibri", Font.BOLD, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(849, 0, 51, 30);
		lblX.setForeground(new Color(80, 80, 80));
		
		lblX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  
		    {  
				System.exit(0);
		    }  
		});
			
		
		lblBreadcrumbs = new JLabel("");
		lblBreadcrumbs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBreadcrumbs.setHorizontalAlignment(SwingConstants.LEFT);
		lblBreadcrumbs.setBounds(10, 0, 274, 30);
		lblBreadcrumbs.setForeground(new Color(80, 80, 80));	
		setBreadcrumbs(new String[] {"Repertoar"});	
		
		
		headerPanel.add(lblX);
		headerPanel.add(lblBreadcrumbs);
		containerPane.add(headerPanel);
	}
	
	public void initMain(){
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 30, 690, 490);
		mainPanel.setBackground(grayDark);
		containerPane.add(mainPanel);
		

	}
	
	public void initSide(){		
		sidePanel = new JPanel();
		sidePanel.setBounds(690, 30, 210, 490);
		sidePanel.setBackground(grayLight);
		containerPane.add(sidePanel);
	}
	
	public void setBreadcrumbs(String[] paths){
		String full = "<html>";
		full += "<b><font color=#5a5a5a>Ticketplex </font></b>";
		for(String path : paths){
			full += "<font color=#4f4f4f size=-2> ></font>";
			full += "<font color=#4f4f4f> "+path+"</font>";
		}
		full += "</html>";
		
		lblBreadcrumbs.setText(full);
		return;
	}	
}
