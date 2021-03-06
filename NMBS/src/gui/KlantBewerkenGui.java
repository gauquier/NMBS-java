package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.AbonnementDAO;
import dao.KlantDAO;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Abonnement;
import source.Klant;
import source.Login;

public class KlantBewerkenGui extends JPanel {
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.KlantBewerkenGui");
	
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();
	private JTextField txtZoeken;
	private JButton btnBewerken;
	private JList<Klant> list;
	private ArrayList<Klant> arrayLijst;
	private DefaultListModel<Klant> dlm;
	private JButton btnVerwijderen;
	public String navigation;
	public String newline = System.getProperty("line.separator");

	
	
	public KlantBewerkenGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblKlantBewerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblKlantBewerken"));//tekst: "Klanten beheren"
		lblKlantBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Klant>();

		this.arrayLijst = KlantDAO.getAllKlanten();

		this.dlm = new DefaultListModel<Klant>();

		for (Klant m : this.arrayLijst) {
			this.dlm.addElement(m);
		}

		this.list = new JList<Klant>(this.dlm);
		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setViewportView(this.list);

		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(new KlantWeergevenGui(KlantBewerkenGui.this.list.getSelectedValue()));
					} else {
						MedewerkerGui.setHuidigeKeuze(new KlantWeergevenGui(KlantBewerkenGui.this.list.getSelectedValue()));
					}
					
				}
			}
		});

		this.txtZoeken = new JTextField();
		this.txtZoeken.setColumns(10);
		this.txtZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtZoeken.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

				KlantBewerkenGui.this.updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				KlantBewerkenGui.this.updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				KlantBewerkenGui.this.updateLijst();
			}

		});

		this.btnBewerken = new JButton(bundle.getString("btnBewerken"));
		this.btnBewerken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnBewerken.setBackground(Color.ORANGE);
		this.btnBewerken.addActionListener(new MenuItemHandler());

		this.btnVerwijderen = new JButton(bundle.getString("btnVerwijderen"));
		this.btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnVerwijderen.setBackground(Color.ORANGE);
		this.btnVerwijderen.addActionListener(new MenuItemHandler());

		JLabel lblZoekenOpNaam = new JLabel(bundle.getString("lblZoekenOpNaam"));
		lblZoekenOpNaam.setForeground(Color.WHITE);
		lblZoekenOpNaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(37)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblKlantBewerken)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblZoekenOpNaam, GroupLayout.PREFERRED_SIZE, 175,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE).addComponent(
										this.txtZoeken, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(this.list, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(this.btnVerwijderen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(this.btnBewerken, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblKlantBewerken)
								.addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.txtZoeken, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblZoekenOpNaam))
								.addGap(12).addComponent(this.list, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(97).addComponent(this.btnBewerken)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnVerwijderen)))
						.addContainerGap()));
		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	public int OkCancel(String message) {
		int n = JOptionPane.showConfirmDialog(null, message, bundle.getString("confirmation"), JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}
		return 1;

	}

	public Boolean unknownIndex() {
		if (this.list.getSelectedValue() == null || this.list.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noSelectedCustomer"));
			return false;
		} else {
			return true;
		}
	}

	private void verwijderBijhorendeAbonnementen(Klant klant) {
		ArrayList<Abonnement> arrayLijst = new ArrayList<Abonnement>();
		arrayLijst = AbonnementDAO.getAllAbonnementen();

		for (int i = 0; i < arrayLijst.size(); i++) {
			if (arrayLijst.get(i).getKlant().getKlantId() == klant.getKlantId()) {
				AbonnementDAO.removeAbonnement(arrayLijst.get(i).getAbonnementId());
			}
		}
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

	public void updateLijst() {
		ArrayList<Klant> t = new ArrayList<Klant>();
		if (!this.txtZoeken.getText().isEmpty()) {

			for (int i = 0; i < this.arrayLijst.size(); i++) {
				if (this.arrayLijst.get(i).getNaam().toLowerCase().contains(this.txtZoeken.getText().toLowerCase())) {
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

			if (e.getSource() == KlantBewerkenGui.this.btnBewerken) {

				if (!KlantBewerkenGui.this.unknownIndex()) {
					return;
				} else {

					KlantBewerkenGui.this.navigation = "gebruikerBijwerken";
					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(new KlantBijwerkenGui(KlantBewerkenGui.this.list.getSelectedValue()));
					}else {
						MedewerkerGui.setHuidigeKeuze(new KlantBijwerkenGui(KlantBewerkenGui.this.list.getSelectedValue()));
					}
					

				}
			}

			if (e.getSource() == KlantBewerkenGui.this.btnVerwijderen) {
				if (!KlantBewerkenGui.this.unknownIndex()) {
					return;
				} else {
					int n;

					if (KlantBewerkenGui.this.klantHeeftAlAbonnement(KlantBewerkenGui.this.list.getSelectedValue())) {
						n = KlantBewerkenGui.this.OkCancel(
								bundle.getString("klantHeeftAlAbonnement")
										+ KlantBewerkenGui.this.newline + " " + bundle.getString("betUZekerDatU") + " "
										+ KlantBewerkenGui.this.list.getSelectedValue().getVoornaam() + " "
										+ KlantBewerkenGui.this.list.getSelectedValue().getAchternaam()
										+ " " + bundle.getString("wiltVerwijderen"));
						if (n == 0) {
							KlantBewerkenGui.this
									.verwijderBijhorendeAbonnementen(KlantBewerkenGui.this.list.getSelectedValue());
						}
					} else {
						n = KlantBewerkenGui.this.OkCancel(bundle.getString("betUZekerDatU") + " "
								+ KlantBewerkenGui.this.list.getSelectedValue().getVoornaam() + " "
								+ KlantBewerkenGui.this.list.getSelectedValue().getAchternaam() + " " + bundle.getString("wiltVerwijderen"));
					}

					if (n == 0) {

						KlantDAO.removeKlant(KlantBewerkenGui.this.list.getSelectedValue().getKlantId());
						((DefaultListModel<Klant>) KlantBewerkenGui.this.list.getModel())
								.remove(KlantBewerkenGui.this.list.getSelectedIndex());
						arrayLijst = KlantDAO.getAllKlanten();
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerDeleted"));
					} else if (n == 1) {
						return;
					}

				}
			}

		}
	}
}
