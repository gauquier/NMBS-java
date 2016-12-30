package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDateChooser;

import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import source.Login;
import source.Station;
import source.VerlorenVoorwerp;

public class VerlorenVoorwerpenToevoegenGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5501780870042021655L;
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();

	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.VerlorenVoorwerpenToevoegenGui");

	private JTextArea txtrBeschrijving;
	private JButton btnToevoegen;
	private JComboBox<Station> stationLijst;
	private JDateChooser dateChooser;

	public VerlorenVoorwerpenToevoegenGui() {

		this.setDoubleBuffered(false);
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblBeschrijving = new JLabel(bundle.getString("lblBeschrijving"));
		lblBeschrijving.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBeschrijving.setForeground(Color.WHITE);
		this.txtrBeschrijving = new JTextArea();
		this.txtrBeschrijving.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblStation = new JLabel(bundle.getString("lblStation"));
		lblStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStation.setForeground(Color.WHITE);

		JLabel lblDatum = new JLabel(bundle.getString("lblDatum"));
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum.setForeground(Color.WHITE);

		this.btnToevoegen = new JButton(bundle.getString("btnToevoegen"));
		this.btnToevoegen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnToevoegen.setForeground(Color.BLACK);
		this.btnToevoegen.addActionListener(new MenuItemHandler());

		JLabel label = DefaultComponentFactory.getInstance().createTitle(bundle.getString("label"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);

		this.stationLijst = new JComboBox<Station>();
		this.stationLijst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ArrayList<Station> lijst = StationDAO.getAll();
		for (Station station : lijst) {
			this.stationLijst.addItem(station);
		}

		this.dateChooser = new JDateChooser();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBeschrijving)
								.addComponent(lblStation)
								.addComponent(lblDatum))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtrBeschrijving, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
								.addComponent(stationLijst, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label)))
					.addContainerGap(110, Short.MAX_VALUE))
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
						.addComponent(lblDatum)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(btnToevoegen)
					.addContainerGap())
		);
		this.setLayout(groupLayout);

	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String station;
			String beschrijving;
			Date date = VerlorenVoorwerpenToevoegenGui.this.dateChooser.getDate();
			/*
			 * LocalDate datum =
			 * date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			 * LocalDate today = LocalDate.now(ZoneId.of("Europe/Brussels"));
			 */
			boolean gevonden = false;
			VerlorenVoorwerp vv;

			if (e.getSource() == VerlorenVoorwerpenToevoegenGui.this.btnToevoegen) {
				if (!VerlorenVoorwerpenToevoegenGui.this.txtrBeschrijving.getText().isEmpty()
						&& VerlorenVoorwerpenToevoegenGui.this.dateChooser
								.getDate() != null /*
													 * && !datum.isAfter(today)
													 * mss niet langer dan een
													 * jaar terug
													 */ ) {
					station = VerlorenVoorwerpenToevoegenGui.this.stationLijst.getSelectedItem().toString();
					beschrijving = VerlorenVoorwerpenToevoegenGui.this.txtrBeschrijving.getText();
					date = VerlorenVoorwerpenToevoegenGui.this.dateChooser.getDate();

					vv = new VerlorenVoorwerp(-1, beschrijving, date, gevonden);
					VerlorenVoorwerpDAO.insertVerlorenVoorwerp(vv, StationDAO.checkStation(station));
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("itemAdded"));
					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
					}else{
						MedewerkerGui.setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
					}


				}

				else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}

		}

	}
}
