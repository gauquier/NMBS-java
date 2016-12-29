package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.MedewerkerDAO;
import source.Abonnement;

public class AbonnementWeergevenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5924070135078488735L;
	private SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
	private JButton btnKlantBekijken;
	private JButton btnMedewerkerBekijken;
	private Abonnement doorgegevenAbonnement;

	public AbonnementWeergevenGui(Abonnement abonnement) {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.doorgegevenAbonnement = abonnement;

		JLabel lblAbonnement = DefaultComponentFactory.getInstance()
				.createTitle("Abonnement van " + abonnement.getKlant().getNaam());
		lblAbonnement.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNaam = new JLabel("Naam: ");
		lblNaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNaam.setForeground(Color.WHITE);

		JLabel lblNaamValue = new JLabel("");
		lblNaamValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNaamValue.setForeground(Color.WHITE);
		lblNaamValue.setText(abonnement.getKlant().getNaam());

		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVertrek.setForeground(Color.WHITE);

		JLabel lblVertrekValue = new JLabel("");
		lblVertrekValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVertrekValue.setForeground(Color.WHITE);
		lblVertrekValue.setText(abonnement.getDepZone());

		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAankomst.setForeground(Color.WHITE);

		JLabel lblAankomstValue = new JLabel("");
		lblAankomstValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAankomstValue.setForeground(Color.WHITE);
		lblAankomstValue.setText(abonnement.getArrZone());

		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijs.setForeground(Color.WHITE);

		JLabel lblPrijsValue = new JLabel("");
		lblPrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijsValue.setForeground(Color.WHITE);
		lblPrijsValue.setText(Double.toString(abonnement.getPrijs()) + " euro");

		JLabel lblPeriode = new JLabel("Periode:");
		lblPeriode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPeriode.setForeground(Color.WHITE);

		JLabel lblPeriodeValue = new JLabel("");
		lblPeriodeValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPeriodeValue.setForeground(Color.WHITE);

		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStartdatum.setForeground(Color.WHITE);

		JLabel lblStartdatumValue = new JLabel((String) null);
		lblStartdatumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStartdatumValue.setForeground(Color.WHITE);

		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEinddatum.setForeground(Color.WHITE);

		JLabel lblEinddatumValue = new JLabel((String) null);
		lblEinddatumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEinddatumValue.setForeground(Color.WHITE);

		JLabel lblVerkoopdatum = new JLabel("Verkoopdatum:");
		lblVerkoopdatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVerkoopdatum.setForeground(Color.WHITE);

		JLabel lblVerkoopdatumValue = new JLabel((String) null);
		lblVerkoopdatumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVerkoopdatumValue.setForeground(Color.WHITE);

		JLabel lblResterendeTijd = new JLabel("Resterende tijd:");
		lblResterendeTijd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblResterendeTijd.setForeground(Color.WHITE);

		JLabel lblResterendeTijdValue = new JLabel((String) null);
		lblResterendeTijdValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblResterendeTijdValue.setForeground(Color.WHITE);

		JLabel lblLaatstGewijzigdDoor = new JLabel("Laatst gewijzigd door:");
		lblLaatstGewijzigdDoor.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLaatstGewijzigdDoor.setForeground(Color.WHITE);

		JLabel lblLaatstGewijzigdDoorValue = new JLabel((String) null);
		lblLaatstGewijzigdDoorValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLaatstGewijzigdDoorValue.setForeground(Color.WHITE);

		this.btnKlantBekijken = new JButton("Klant bekijken");
		this.btnKlantBekijken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnKlantBekijken.setBackground(Color.ORANGE);
		this.btnKlantBekijken.addActionListener(new MenuItemHandler());

		this.btnMedewerkerBekijken = new JButton("Medewerker bekijken");
		this.btnMedewerkerBekijken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnMedewerkerBekijken.setBackground(Color.ORANGE);
		this.btnMedewerkerBekijken.addActionListener(new MenuItemHandler());

		if (abonnement.getP() != null) {
			lblPeriodeValue.setText("Actief");
			lblStartdatumValue.setText(this.formatDatum.format(abonnement.getP().getStartDate()));
			lblEinddatumValue.setText(this.formatDatum.format(abonnement.getP().getEndDate()));
			lblVerkoopdatumValue.setText(this.formatDatum.format(abonnement.getP().getVerkoopdatum()));
			lblResterendeTijdValue.setText(abonnement.getResterendeDagen() + " dagen");
			lblLaatstGewijzigdDoorValue
					.setText(MedewerkerDAO.getMedewerker(abonnement.getP().getMedewerkerId()).getNaam());
		} else {
			lblPeriodeValue.setText("Inactief");
			lblStartdatumValue.setText("Niet van toepassing");
			lblEinddatumValue.setText("Niet van toepassing");
			lblVerkoopdatumValue.setText("Niet van toepassing");
			lblResterendeTijdValue.setText("Niet van toepassing");
			lblLaatstGewijzigdDoorValue.setText("Niet van toepassing");
			this.btnMedewerkerBekijken.setVisible(false);
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnMedewerkerBekijken, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(btnKlantBekijken, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblResterendeTijd)
							.addGap(18)
							.addComponent(lblResterendeTijdValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNaam)
										.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVertrek, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
										.addComponent(lblAankomst, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblEinddatum, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblVerkoopdatum, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(5)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblAankomstValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblVertrekValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNaamValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblEinddatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPeriodeValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblStartdatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(lblVerkoopdatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
								.addGap(168))
							.addComponent(lblAbonnement)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblLaatstGewijzigdDoor)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblLaatstGewijzigdDoorValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(lblPeriode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblStartdatum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(430, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAbonnement)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaam)
						.addComponent(lblNaamValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVertrek)
						.addComponent(lblVertrekValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAankomst)
						.addComponent(lblAankomstValue))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPrijsValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPrijs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPeriode)
						.addComponent(lblPeriodeValue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartdatum)
						.addComponent(lblStartdatumValue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEinddatum, Alignment.TRAILING)
						.addComponent(lblEinddatumValue, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVerkoopdatum)
							.addGap(33))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVerkoopdatumValue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(29)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblResterendeTijdValue, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblResterendeTijd)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLaatstGewijzigdDoor)
						.addComponent(lblLaatstGewijzigdDoorValue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMedewerkerBekijken, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnKlantBekijken, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(140, Short.MAX_VALUE))
		);

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == AbonnementWeergevenGui.this.btnKlantBekijken) {
				AdminGui.setHuidigeKeuze(
						new KlantWeergevenGui(AbonnementWeergevenGui.this.doorgegevenAbonnement.getKlant()));

			}

			if (e.getSource() == AbonnementWeergevenGui.this.btnMedewerkerBekijken) {
				AdminGui.setHuidigeKeuze(new GebruikerWeergevenGui(MedewerkerDAO
						.getMedewerker(AbonnementWeergevenGui.this.doorgegevenAbonnement.getP().getMedewerkerId())));
			}
		}
	}
}