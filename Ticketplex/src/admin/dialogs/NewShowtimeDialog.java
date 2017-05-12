package admin.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.AdminController;
import admin.AdminWindow;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewShowtimeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblImeFilma;
	private JTextField textFieldDate;
	private JLabel lblNewLabel;
	private JTextField textFieldTime;
	private JLabel lblException;
	private  File file;


	/**
	 * Create the dialog.
	 */
	public NewShowtimeDialog() {
		setBounds(100, 100, 472, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblImeFilma());
		contentPanel.add(getTextFieldDate());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getTextFieldTime());
		contentPanel.add(getLblException());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						lblException.setText("");
						try {
							String date = textFieldDate.getText();
							String time = textFieldTime.getText();
							
							DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
							dateFormat.setLenient(false);
							Date d = dateFormat.parse(time + " " + date);
							Calendar cal = GregorianCalendar.getInstance();
							cal.setTime(d);
							System.out.println(cal);
						} catch (NumberFormatException e) {
							lblException.setText("Lose ste uneli datum ili vreme.");
							e.printStackTrace();
						} catch (ParseException e) {
							lblException.setText("Lose ste uneli datum ili vreme.");
						}
						
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JLabel getLblImeFilma() {
		if (lblImeFilma == null) {
			lblImeFilma = new JLabel("Datum prikazivanja [dd/mm/yyyy]");
			lblImeFilma.setBounds(26, 21, 171, 14);
		}
		return lblImeFilma;
	}
	private JTextField getTextFieldDate() {
		if (textFieldDate == null) {
			textFieldDate = new JTextField();
			textFieldDate.setBounds(268, 18, 86, 20);
			textFieldDate.setColumns(10);
		}
		return textFieldDate;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Vreme prikazivanja [hh:mm]");
			lblNewLabel.setBounds(26, 46, 171, 20);
		}
		return lblNewLabel;
	}
	private JTextField getTextFieldTime() {
		if (textFieldTime == null) {
			textFieldTime = new JTextField();
			textFieldTime.setBounds(268, 49, 86, 20);
			textFieldTime.setColumns(10);
		}
		return textFieldTime;
	}
	private JLabel getLblException() {
		if (lblException == null) {
			lblException = new JLabel("");
			lblException.setBounds(26, 243, 292, 14);
		}
		return lblException;
	}
}
