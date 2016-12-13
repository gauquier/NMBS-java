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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import javafx.scene.control.ComboBox;

import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

public class VerlorenVoorwerpenToevoegenGui extends JPanel {

	private JTextArea txtrBeschrijving;
	private JButton btnToevoegen;
	private JComboBox<String> stationLijst;
	private JDateChooser dateChooser;

	private VerlorenVoorwerp verlorenVoorwerp;
	private Station station;

	private StationDAO stationDAO = new StationDAO();
	private VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();

	public VerlorenVoorwerpenToevoegenGui() {
		setDoubleBuffered(false);
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblBeschrijving = new JLabel("Beschrijving");
		lblBeschrijving.setForeground(Color.WHITE);
		txtrBeschrijving = new JTextArea();

		JLabel lblStation = new JLabel("Station");
		lblStation.setForeground(Color.WHITE);

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);

		btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.setForeground(Color.BLACK);
		btnToevoegen.addActionListener(new MenuItemHandler());

		JLabel label = DefaultComponentFactory.getInstance().createTitle("Verloren voorwerp toevoegen");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setForeground(Color.ORANGE);

		stationLijst = new JComboBox();
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			stationLijst.addItem(station.getNaam());
		}

		dateChooser = new JDateChooser();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
								.addComponent(txtrBeschrijving, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
								.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(stationLijst, 0, 201, Short.MAX_VALUE))
							.addGap(254))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBeschrijving))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(67)
							.addComponent(btnToevoegen))
						.addComponent(lblDatum))
					.addGap(52))
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
			Date date;
			boolean gevonden = false;
			VerlorenVoorwerp vv;

			if (e.getSource() == btnToevoegen) {
				if (!txtrBeschrijving.getText().isEmpty()) {
					{
						station = stationLijst.getSelectedItem().toString();
						beschrijving = txtrBeschrijving.getText();
						date = dateChooser.getDate();
						vv = new VerlorenVoorwerp(-1, beschrijving, date, gevonden);
						verlorenVoorwerpDAO.insertVerlorenVoorwerp(vv, stationDAO.checkStation(station));
					}
				}

				else {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill in all required fields!");
				}
			}

		}

	}
}
