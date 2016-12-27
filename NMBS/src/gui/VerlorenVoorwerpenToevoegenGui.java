package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import source.Station;
import source.VerlorenVoorwerp;
import sun.util.resources.LocaleData;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import javafx.scene.control.ComboBox;

import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import source.Pdf;
public class VerlorenVoorwerpenToevoegenGui extends JPanel {
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.VerlorenVoorwerpenToevoegenGui");
	
	private JTextArea txtrBeschrijving;
	private JButton btnToevoegen;
	private JComboBox<Station> stationLijst;
	private JDateChooser dateChooser;

	public VerlorenVoorwerpenToevoegenGui() {
		
		setDoubleBuffered(false);
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblBeschrijving = new JLabel(bundle.getString("lblBeschrijving"));
		lblBeschrijving.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBeschrijving.setForeground(Color.WHITE);
		txtrBeschrijving = new JTextArea();
		txtrBeschrijving.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblStation = new JLabel(bundle.getString("lblStation"));
		lblStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStation.setForeground(Color.WHITE);

		JLabel lblDatum = new JLabel(bundle.getString("lblDatum"));
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum.setForeground(Color.WHITE);

		btnToevoegen = new JButton(bundle.getString("btnToevoegen"));
		btnToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnToevoegen.setForeground(Color.BLACK);
		btnToevoegen.addActionListener(new MenuItemHandler());

		JLabel label = DefaultComponentFactory.getInstance().createTitle(bundle.getString("label"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);

		stationLijst = new JComboBox<Station>();
		stationLijst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ArrayList<Station> lijst = StationDAO.getAll();
		for (Station station : lijst) {
			stationLijst.addItem(station);
		}

		dateChooser = new JDateChooser();
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBeschrijving)
								.addComponent(lblStation)
								.addComponent(lblDatum))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
							.addGap(139))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBeschrijving))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatum))
					.addGap(31)
					.addComponent(btnToevoegen)
					.addGap(185))
		);
		setLayout(groupLayout);

	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String station;
			String beschrijving;
			Date date = dateChooser.getDate();
			/*LocalDate datum = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate today = LocalDate.now(ZoneId.of("Europe/Brussels"));*/
			boolean gevonden = false;
			VerlorenVoorwerp vv;

			if (e.getSource() == btnToevoegen) {
				if (!txtrBeschrijving.getText().isEmpty() && dateChooser.getDate() != null /*&& !datum.isAfter(today) mss niet langer dan een jaar terug*/ ) 
					{
						station = stationLijst.getSelectedItem().toString();
						beschrijving = txtrBeschrijving.getText();
						date = dateChooser.getDate();
						
						vv = new VerlorenVoorwerp(-1, beschrijving, date, gevonden);
						VerlorenVoorwerpDAO.insertVerlorenVoorwerp(vv, StationDAO.checkStation(station));
						JOptionPane.showMessageDialog(new JFrame(), "Verloren voorwerp toegevoegd!");
						AdminGui.setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());//reset functie van maken
					}

				else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}

		}

	}
}
