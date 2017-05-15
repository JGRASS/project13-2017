package gui;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;




import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static Color colorTop = new Color(34, 34, 34);
	private static Color colorBottom = new Color(42, 42, 42);
	
	private Point initialClick;
	@SuppressWarnings("unused")
	private JFrame parent;
	private JDialog dialog;

	/* 
	 * Moving window by draging component 
	 * http://stackoverflow.com/a/13171534
	*/
	public HeaderPanel(final JFrame parent) {
		this.parent = parent;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				getComponentAt(initialClick);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				// get location of Window
				int thisX = parent.getLocation().x;
				int thisY = parent.getLocation().y;

				// Determine how much the mouse moved since the initial click
				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

				// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				parent.setLocation(X, Y);
			}
		});
	}
	
	 public HeaderPanel(JDialog dialog) {
		 this.dialog = dialog;
		 addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					initialClick = e.getPoint();
					getComponentAt(initialClick);
				}
			});

			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {

					// get location of Window
					int thisX = dialog.getLocation().x;
					int thisY = dialog.getLocation().y;

					// Determine how much the mouse moved since the initial click
					int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
					int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

					// Move window to this position
					int X = thisX + xMoved;
					int Y = thisY + yMoved;
					dialog.setLocation(X, Y);
				}
			});
	}
	 
	

	@Override
     protected void paintComponent(Graphics grphcs) {
         super.paintComponent(grphcs);
         Graphics2D g2d = (Graphics2D) grphcs;
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_ON);
         GradientPaint gp = new GradientPaint(0, 0,
        		 colorTop, 0, getHeight(),
        		 colorBottom);
         g2d.setPaint(gp);
         g2d.fillRect(0, 0, getWidth(), getHeight()); 

     }
}