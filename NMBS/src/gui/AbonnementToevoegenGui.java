package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.*;
import source.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JSlider;

public class AbonnementToevoegenGui extends JPanel {
	private JButton btnAanmaken;
	private JButton btnZoeken;
	private Klant klant;
	private ArrayList<Klant> arrayLijst;
	private ArrayList<Object> objecten;
	private JList<Klant> list;
	private JTextField txtKlant;
	public String navigation;
	private AutoComboBox comboNaar = new AutoComboBox();
	private AutoComboBox comboVan = new AutoComboBox();

	public AbonnementToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		btnAanmaken = new JButton("Aanmaken");
		btnAanmaken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAanmaken.setBackground(Color.ORANGE);
		btnAanmaken.addActionListener(new MenuItemHandler());

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement aanmaken");
		lblAbonnementAanmaken.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblKlant = new JLabel("Klant:");
		lblKlant.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblKlant.setForeground(Color.WHITE);

		txtKlant = new JTextField();
		txtKlant.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtKlant.setColumns(10);

		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		arrayLijst = new ArrayList<Klant>();

		arrayLijst = KlantDAO.getAllKlanten();

		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();

		for (Klant m : arrayLijst) {
			dlm.addElement(m);
		}

		list = new JList<Klant>(dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblGevondenResultaten = new JLabel("Gevonden resultaten:");
		lblGevondenResultaten.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGevondenResultaten.setForeground(Color.WHITE);

		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVertrek.setForeground(Color.WHITE);

		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAankomst.setForeground(Color.WHITE);

		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijs.setForeground(Color.WHITE);

		JLabel lblPrijsValue = new JLabel("");
		lblPrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijsValue.setForeground(Color.WHITE);
		lblPrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)) + " euro/maand");

		comboVan = new AutoComboBox();
		comboVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboNaar = new AutoComboBox();
		comboNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		ArrayList<Station> stations = StationDAO.getAll();
		ArrayList<String> stationNamen = new ArrayList<String>();
		for (int i = 0; i < stations.size(); i++) {
			stationNamen.add(stations.get(i).getNaam());
		}
		comboNaar.setKeyWord(stationNamen);
		comboVan.setKeyWord(stationNamen);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(30)
								.addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING).addComponent(
												lblAbonnementAanmaken)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblGevondenResultaten)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblKlant, GroupLayout.PREFERRED_SIZE, 58,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(txtKlant, GroupLayout.PREFERRED_SIZE, 202,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnZoeken))
														.addComponent(list, GroupLayout.PREFERRED_SIZE, 309,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 76,
																GroupLayout.PREFERRED_SIZE)
														.addGap(53).addComponent(lblPrijsValue,
																GroupLayout.PREFERRED_SIZE, 250,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblAankomst).addComponent(lblVertrek,
																GroupLayout.PREFERRED_SIZE, 76,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(comboVan, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(comboNaar, GroupLayout.DEFAULT_SIZE, 205,
																Short.MAX_VALUE))))
								.addPreferredGap(ComponentPlacement.RELATED, 210, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(433).addComponent(btnAanmaken,
								GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(19).addComponent(lblAbonnementAanmaken).addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblKlant)
						.addComponent(txtKlant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZoeken))
				.addGap(29).addComponent(lblGevondenResultaten).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(list, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE).addGap(3)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVertrek).addComponent(
						comboVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAankomst).addComponent(
						comboNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrijs)
						.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				.addGap(8)));

		setLayout(groupLayout);
	}

	public Boolean unknownIndex() {
		if (list.getSelectedValue() == null || list.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Er is geen klant aangeduid.");
			return false;
		} else {
			return true;
		}
	}

	public void close() {
		this.setVisible(false);
	}

	private boolean klantHeeftAlAbonnement(Klant klant) {
		ArrayList<Abonnement> arrayLijst = new ArrayList<Abonnement>();
		arrayLijst = AbonnementDAO.getAllAbonnementen();

		for (int i = 0; i < arrayLijst.size(); i++) {
			if (arrayLijst.get(i).getKlant().getKlantId() == klant.getKlantId()) {
				return true;
			}
		}
		return false;
	}

	public int OkCancel(String message) {
		int n = JOptionPane.showConfirmDialog(null, message, "Bevestiging", JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}
		return 1;

	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == btnAanmaken) {

				if (!unknownIndex()) {
					return;
				} else {
					if (comboVan.getSelectedItem() == null || comboNaar.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Gelieve alle velden in te vullen");
						return;
					} else {
						if (comboVan.getSelectedItem() == comboNaar.getSelectedItem()) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Het vertrekpunt kan niet hetzelfde zijn als het aankomstpunt.");
							return;
						} else {
							if (klantHeeftAlAbonnement(list.getSelectedValue())) {
								int n = OkCancel(
										"Deze klant heeft reeds een abonnement. Bent u zeker dat u dit abonnement wil aanmaken?");
								if (n > 0) {
									return;
								}
							} else {
								VerkoopType v = VerkoopType.ABONNEMENT;

								Abonnement abonnement = new Abonnement(0.00, 0, v, list.getSelectedValue(),
										(String) comboVan.getSelectedItem(), (String) comboNaar.getSelectedItem());

								abonnement.setAbonnementId(AbonnementDAO.addAbonnement(abonnement));
								JOptionPane.showMessageDialog(new JFrame(), "Abonnement is aangemaakt!");
								navigation = "AbonnementVerlengen";
								AdminGui.setHuidigeKeuze(new AbonnementVerlengenGui(abonnement));
							}
						}
					}
				}
			}
		}
	}
}