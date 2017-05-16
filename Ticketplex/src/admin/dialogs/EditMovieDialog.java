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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import admin.AdminController;
import ticketplex.Movie;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class EditMovieDialog extends JDialog {

	private int movie_id;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblImeFilma;
	private JTextField textFieldName;
	private JLabel lblNewLabel;
	private JTextField textFieldYear;
	private JLabel lblNewLabel_1;
	private JTextArea textFieldDescription;
	private JLabel lblNewDescription;
	private JTextField textFieldGenre;
	private JLabel lblNewLabel_3;
	private JTextField textFieldCast;
	private JLabel lblReziser;
	private JTextField textFieldDirector;
	private JLabel lblNewLabel_2;
	private JTextField textFieldLength;
	private JLabel lblNewLabel_4;
	private JTextField textFieldImdbRating;
	private JLabel lblNewLabel_5;
	private JTextField textFieldImdbLink;
	private JLabel lblNewLabel_6;
	private JButton btnImg;
	private JLabel lblImage;
	private JLabel lblException;
	private File file;

	/**
	 * Create the dialog.
	 */
	public EditMovieDialog() {
		
		setResizable(false);
		setBounds(100, 100, 608, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblImeFilma());
		contentPanel.add(getTextFieldName());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getTextFieldYear());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getTextFieldDescription());
		contentPanel.add(getLblNewDescription());
		contentPanel.add(getTextFieldGenre());
		contentPanel.add(getLblNewLabel_3());
		contentPanel.add(getTextFieldCast());
		contentPanel.add(getLblReziser());
		contentPanel.add(getTextFieldDirector());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getTextField_4());
		contentPanel.add(getLblNewLabel_4());
		contentPanel.add(getTextField_5());
		contentPanel.add(getLblNewLabel_5());
		contentPanel.add(getTextField_6());
		contentPanel.add(getLblNewLabel_6());
		contentPanel.add(getBtnImg());
		contentPanel.add(getLblImage());
		contentPanel.add(getLblException());
		



		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		for( ActionListener al : okButton.getActionListeners() ) {
			okButton.removeActionListener( al );
		}
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblException.setText("");
				try {
					String name = textFieldName.getText();
					int year = Integer.parseInt(textFieldYear.getText());
					String genre = textFieldGenre.getText();
					String description = textFieldDescription.getText();
					String cast = textFieldCast.getText();
					String director = textFieldDirector.getText();
					int length = Integer.parseInt(textFieldLength.getText());
					String imdbRating = textFieldImdbRating.getText();
					String imdbLink = textFieldImdbLink.getText();
					byte[] img = null;
					if (file != null)
						img = AdminController.readImageFile(file.getAbsolutePath());
					
					AdminController.processEditNewMovie(movie_id, name, year, genre, description, cast, director, length,
							imdbRating, imdbLink, img);
				} catch (NumberFormatException e) {
					lblException.setText("Godina i trajanje filma moraju biti pozitivni brojevi");
				} catch (Exception e) {
					lblException.setText(e.getMessage());
				}

			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeDialog();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}
	
	public void setMovie(Movie m){
		setTitle("Izmeni film: "+m.getName());
		
		textFieldName.setText(m.getName());
		textFieldYear.setText(m.getYear()+"");
		textFieldDescription.setText(m.getDescription());
		textFieldGenre.setText(m.getGenre());
		textFieldCast.setText(m.getCast());
		textFieldDirector.setText(m.getDirector());
		textFieldLength.setText(m.getLength()+"");
		textFieldImdbRating.setText(m.getImdbRating());
		textFieldImdbLink.setText(m.getImdbLink());
		movie_id = m.getId();
		
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(m.getImg()).getImage()
				.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
		lblImage.setIcon(imageIcon);
	}

	private JLabel getLblImeFilma() {
		if (lblImeFilma == null) {
			lblImeFilma = new JLabel("Ime filma:");
			lblImeFilma.setBounds(26, 21, 76, 14);
		}
		return lblImeFilma;
	}

	private JTextField getTextFieldName() {
		if (textFieldName == null) {
			textFieldName = new JTextField();
			textFieldName.setBounds(122, 21, 141, 20);
			textFieldName.setColumns(10);
		}
		return textFieldName;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Godina:");
			lblNewLabel.setBounds(26, 46, 46, 20);
		}
		return lblNewLabel;
	}

	private JTextField getTextFieldYear() {
		if (textFieldYear == null) {
			textFieldYear = new JTextField();
			textFieldYear.setBounds(122, 52, 141, 20);
			textFieldYear.setColumns(10);
		}
		return textFieldYear;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Zanr:");
			lblNewLabel_1.setBounds(26, 80, 46, 14);
		}
		return lblNewLabel_1;
	}

	private JTextArea getTextFieldDescription() {
		if (textFieldDescription == null) {
			textFieldDescription = new JTextArea();
			textFieldDescription.setBounds(284, 43, 298, 110);
			textFieldDescription.setLineWrap(true);
			textFieldDescription.setWrapStyleWord(true);
		}
		return textFieldDescription;
	}

	private JLabel getLblNewDescription() {
		if (lblNewDescription == null) {
			lblNewDescription = new JLabel("Opis:");
			lblNewDescription.setBounds(285, 21, 46, 14);
		}
		return lblNewDescription;
	}

	private JTextField getTextFieldGenre() {
		if (textFieldGenre == null) {
			textFieldGenre = new JTextField();
			textFieldGenre.setBounds(122, 80, 141, 20);
			textFieldGenre.setColumns(10);
		}
		return textFieldGenre;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Uloge:");
			lblNewLabel_3.setBounds(26, 108, 46, 14);
		}
		return lblNewLabel_3;
	}

	private JTextField getTextFieldCast() {
		if (textFieldCast == null) {
			textFieldCast = new JTextField();
			textFieldCast.setBounds(122, 108, 141, 20);
			textFieldCast.setColumns(10);
		}
		return textFieldCast;
	}

	private JLabel getLblReziser() {
		if (lblReziser == null) {
			lblReziser = new JLabel("Reziser:");
			lblReziser.setBounds(26, 139, 46, 14);
		}
		return lblReziser;
	}

	private JTextField getTextFieldDirector() {
		if (textFieldDirector == null) {
			textFieldDirector = new JTextField();
			textFieldDirector.setBounds(122, 136, 141, 20);
			textFieldDirector.setColumns(10);
		}
		return textFieldDirector;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Trajanje filma:");
			lblNewLabel_2.setBounds(26, 171, 86, 14);
		}
		return lblNewLabel_2;
	}

	private JTextField getTextField_4() {
		if (textFieldLength == null) {
			textFieldLength = new JTextField();
			textFieldLength.setBounds(122, 168, 141, 20);
			textFieldLength.setColumns(10);
		}
		return textFieldLength;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("IMDb ocena:");
			lblNewLabel_4.setBounds(26, 209, 76, 14);
		}
		return lblNewLabel_4;
	}

	private JTextField getTextField_5() {
		if (textFieldImdbRating == null) {
			textFieldImdbRating = new JTextField();
			textFieldImdbRating.setBounds(122, 206, 141, 20);
			textFieldImdbRating.setColumns(10);
		}
		return textFieldImdbRating;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("IMDb link:");
			lblNewLabel_5.setBounds(26, 240, 58, 14);
		}
		return lblNewLabel_5;
	}

	private JTextField getTextField_6() {
		if (textFieldImdbLink == null) {
			textFieldImdbLink = new JTextField();
			textFieldImdbLink.setBounds(122, 237, 141, 20);
			textFieldImdbLink.setColumns(10);
		}
		return textFieldImdbLink;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Slika:");
			lblNewLabel_6.setBounds(284, 171, 46, 14);
		}
		return lblNewLabel_6;
	}

	private JButton getBtnImg() {
		if (btnImg == null) {
			btnImg = new JButton("Izaberi");
			btnImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JFileChooser fc = new JFileChooser();
					int result = fc.showOpenDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
						try {
							ImageIcon imageIcon = new ImageIcon(new ImageIcon(ImageIO.read(file)).getImage()
									.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
							lblImage.setIcon(imageIcon);
						} catch (IOException e) {
							lblException.setText(e.getMessage());
						}
					}
				}
			});
			btnImg.setBounds(284, 189, 76, 23);
		}
		return btnImg;
	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(395, 181, 60, 100);
		}
		return lblImage;
	}

	private JLabel getLblException() {
		if (lblException == null) {
			lblException = new JLabel("");
			lblException.setBounds(26, 284, 292, 14);
		}
		return lblException;
	}

	public void closeDialog() {
		dispose();
	}
}
