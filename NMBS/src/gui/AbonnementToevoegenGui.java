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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JSlider;

public class AbonnementToevoegenGui extends JPanel {
	private JButton btnAanmaken;
	private Klant klant;
	private ArrayList<Klant> arrayLijst;
	private DefaultListModel<Klant> dlm;
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
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblKlant = new JLabel("Klant:");
		lblKlant.setForeground(Color.WHITE);

		txtKlant = new JTextField();
		txtKlant.setColumns(10);
		txtKlant.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

				updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLijst();
			}

		});

		arrayLijst = new ArrayList<Klant>();

		arrayLijst = KlantDAO.getAllKlanten();

		dlm = new DefaultListModel<Klant>();

		for (Klant m : arrayLijst) {
			dlm.addElement(m);
		}

		list = new JList<Klant>(dlm);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setViewportView(list);

		JLabel lblGevondenResultaten = new JLabel("Gevonden resultaten:");
		lblGevondenResultaten.setForeground(Color.WHITE);

		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setForeground(Color.WHITE);

		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setForeground(Color.WHITE);

		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setForeground(Color.WHITE);

		JLabel lblPrijsValue = new JLabel("");
		lblPrijsValue.setForeground(Color.WHITE);
		lblPrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)) + " euro/maand");

		comboVan = new AutoComboBox();
		comboNaar = new AutoComboBox();

		ArrayList<Station> stations = StationDAO.getAll();
		ArrayList<String> stationNamen = new ArrayList<String>();
		for (int i = 0; i < stations.size(); i++) {
			stationNamen.add(stations.get(i).getNaam());
		}
		comboNaar.setKeyWord(stationNamen);
		comboVan.setKeyWord(stationNamen);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(30).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING, false).addComponent(
										lblAbonnementAanmaken)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblKlant, GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE)
														.addGap(58))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblGevondenResultaten)
														.addPreferredGap(ComponentPlacement.UNRELATED)))
										.addComponent(list, GroupLayout.PREFERRED_SIZE, 112,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(
										groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 76,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(34)
																.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE,
																		167, GroupLayout.PREFERRED_SIZE)
																.addGap(202).addComponent(btnAanmaken,
																		GroupLayout.PREFERRED_SIZE, 111,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout
																.createParallelGroup(Alignment.LEADING, false)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblAankomst,
																				GroupLayout.PREFERRED_SIZE, 76,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(comboNaar,
																				GroupLayout.PREFERRED_SIZE, 141,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblVertrek,
																				GroupLayout.PREFERRED_SIZE, 76,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(comboVan,
																				GroupLayout.PREFERRED_SIZE, 141,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGap(110).addComponent(txtKlant,
																				GroupLayout.PREFERRED_SIZE, 141,
																				GroupLayout.PREFERRED_SIZE))))))
						.addContainerGap(34, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(19).addComponent(lblAbonnementAanmaken).addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblKlant).addComponent(
						txtKlant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblGevondenResultaten)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVertrek))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAankomst))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrijs)))
				.addContainerGap()));

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

	public void updateLijst() {
		ArrayList<Klant> t = new ArrayList<Klant>();
		if (!txtKlant.getText().isEmpty()) {

			for (int i = 0; i < arrayLijst.size(); i++) {
				if (arrayLijst.get(i).getNaam().toLowerCase().contains(txtKlant.getText().toLowerCase())) {
					t.add(arrayLijst.get(i));
				}

			}
		} else {
			t = arrayLijst;
		}

		dlm.clear();
		for (Klant m : t) {
			dlm.addElement(m);
		}

		list.setModel(dlm);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == btnAanmaken) {

				if (!unknownIndex()) {
					return;
				} else {
					if (comboVan.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Er is vertrekpunt gekozen.");
						return;
					} else if (comboNaar.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Er is aankomstpunt gekozen.");
						return;
					}

					else {
						if (comboVan.getSelectedItem() == comboNaar.getSelectedItem()) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Het vertrekpunt kan niet hetzelfde zijn als het aankomstpunt.");
							return;
						}

						if (klantHeeftAlAbonnement(list.getSelectedValue())) {
							int n = OkCancel(
									"Deze klant heeft reeds een abonnement. Bent u zeker dat u dit abonnement wil aanmaken?");
							if (n > 0) {
								return;
							}
						}

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
