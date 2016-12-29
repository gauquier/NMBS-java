package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.AbonnementDAO;
import dao.KlantDAO;
import dao.PrijsDAO;
import dao.StationDAO;
import source.Abonnement;
import source.AutoComboBox;
import source.Klant;
import source.Station;
import source.VerkoopType;

public class AbonnementToevoegenGui extends JPanel {
	private JButton btnAanmaken;
	private ArrayList<Klant> arrayLijst;
	private DefaultListModel<Klant> dlm;
	private JList<Klant> list;
	private JTextField txtKlant;
	public String navigation;
	private AutoComboBox comboNaar = new AutoComboBox();
	private AutoComboBox comboVan = new AutoComboBox();
	private Border border = BorderFactory.createEmptyBorder();
	private Border borderError = BorderFactory.createLineBorder(Color.RED, 3);
	
	public AbonnementToevoegenGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		this.btnAanmaken = new JButton("Aanmaken");
		this.btnAanmaken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.btnAanmaken.setBackground(Color.ORANGE);
		this.btnAanmaken.addActionListener(new MenuItemHandler());
		btnAanmaken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement aanmaken");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblKlant = new JLabel("Klant:");
		lblKlant.setForeground(Color.WHITE);
		lblKlant.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.txtKlant = new JTextField();
		this.txtKlant.setColumns(10);
		this.txtKlant.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

				AbonnementToevoegenGui.this.updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				AbonnementToevoegenGui.this.updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				AbonnementToevoegenGui.this.updateLijst();
			}

		});

		this.arrayLijst = new ArrayList<Klant>();

		this.arrayLijst = KlantDAO.getAllKlanten();

		this.dlm = new DefaultListModel<Klant>();

		for (Klant m : this.arrayLijst) {
			this.dlm.addElement(m);
		}

		this.list = new JList<Klant>(this.dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		
		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setViewportView(this.list);
		scrollPane.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblGevondenResultaten = new JLabel("Gevonden resultaten:");
		lblGevondenResultaten.setForeground(Color.WHITE);
		lblGevondenResultaten.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setForeground(Color.WHITE);
		lblVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setForeground(Color.WHITE);
		lblAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setForeground(Color.WHITE);
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblPrijsValue = new JLabel("");
		lblPrijsValue.setForeground(Color.WHITE);
		lblPrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)) + " euro/maand");
		lblPrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.comboVan = new AutoComboBox();
		this.comboNaar = new AutoComboBox();

		ArrayList<Station> stations = StationDAO.getAll();
		ArrayList<String> stationNamen = new ArrayList<String>();
		for (int i = 0; i < stations.size(); i++) {
			stationNamen.add(stations.get(i).getNaam());
		}
		this.comboNaar.setKeyWord(stationNamen);
		this.comboVan.setKeyWord(stationNamen);

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
										.addComponent(this.list, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 120,
																GroupLayout.PREFERRED_SIZE)
														.addGap(34)
														.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 167,
																GroupLayout.PREFERRED_SIZE)
														.addGap(202).addComponent(this.btnAanmaken,
																GroupLayout.PREFERRED_SIZE, 160,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblAankomst, GroupLayout.PREFERRED_SIZE,
																		120, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(this.comboNaar,
																		GroupLayout.PREFERRED_SIZE, 141,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE,
																		120, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(this.comboVan, GroupLayout.PREFERRED_SIZE,
																		141, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup().addGap(150)
																.addComponent(this.txtKlant, GroupLayout.PREFERRED_SIZE,
																		141, GroupLayout.PREFERRED_SIZE))))))
						.addContainerGap(34, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(0).addComponent(lblAbonnementAanmaken).addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblKlant).addComponent(
						this.txtKlant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblGevondenResultaten)
						.addComponent(this.list, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.comboVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVertrek))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(this.comboNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAankomst))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.btnAanmaken, GroupLayout.PREFERRED_SIZE, 43,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrijs)))
				.addContainerGap()));

		this.setLayout(groupLayout);
	}

	public Boolean unknownIndex() {
		if (this.list.getSelectedValue() == null || this.list.getSelectedIndex() < 0) {
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
		if (!this.txtKlant.getText().isEmpty()) {

			for (int i = 0; i < this.arrayLijst.size(); i++) {
				if (this.arrayLijst.get(i).getNaam().toLowerCase().contains(this.txtKlant.getText().toLowerCase())) {
					t.add(this.arrayLijst.get(i));
				}

			}
		} else {
			t = this.arrayLijst;
		}

		this.dlm.clear();
		for (Klant m : t) {
			this.dlm.addElement(m);
		}

		this.list.setModel(this.dlm);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == AbonnementToevoegenGui.this.btnAanmaken) {

				if (!AbonnementToevoegenGui.this.unknownIndex()) {
					return;
				} else {
					if (AbonnementToevoegenGui.this.comboVan.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Er is geen vertrekpunt gekozen.");
						return;
					} else if (AbonnementToevoegenGui.this.comboNaar.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Er is geen aankomstpunt gekozen.");
						return;
					}
					else{
						if(StationDAO.checkStation((String) comboVan.getSelectedItem()) != 0){
							comboVan.setBorder(borderError);
						}else{
							comboVan.setBorder(border);
						}
						if(StationDAO.checkStation((String) comboNaar.getSelectedItem()) != 0){
							comboNaar.setBorder(borderError);
						}else{
							comboNaar.setBorder(border);
						}
					if(StationDAO.checkStation((String) comboVan.getSelectedItem()) != 0 || StationDAO.checkStation((String) comboNaar.getSelectedItem()) != 0){
						JOptionPane.showMessageDialog(new JFrame(), "Dit station bestaat niet.");
					}
					else {
						if (AbonnementToevoegenGui.this.comboVan
								.getSelectedItem() == AbonnementToevoegenGui.this.comboNaar.getSelectedItem()) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Het vertrekpunt kan niet hetzelfde zijn als het aankomstpunt.");
							return;
						}

						if (AbonnementToevoegenGui.this
								.klantHeeftAlAbonnement(AbonnementToevoegenGui.this.list.getSelectedValue())) {
							int n = AbonnementToevoegenGui.this.OkCancel(
									"Deze klant heeft reeds een abonnement. Bent u zeker dat u dit abonnement wil aanmaken?");
							if (n > 0) {
								return;
							}
						}
					}
						VerkoopType v = VerkoopType.ABONNEMENT;

						Abonnement abonnement = new Abonnement(0.00, 0, v,
								AbonnementToevoegenGui.this.list.getSelectedValue(),
								(String) AbonnementToevoegenGui.this.comboVan.getSelectedItem(),
								(String) AbonnementToevoegenGui.this.comboNaar.getSelectedItem());

						abonnement.setAbonnementId(AbonnementDAO.addAbonnement(abonnement));
						JOptionPane.showMessageDialog(new JFrame(), "Abonnement is aangemaakt!");
						AbonnementToevoegenGui.this.navigation = "AbonnementVerlengen";
						AdminGui.setHuidigeKeuze(new AbonnementVerlengenGui(abonnement));
					}
				}

			}

		}
	}
}
