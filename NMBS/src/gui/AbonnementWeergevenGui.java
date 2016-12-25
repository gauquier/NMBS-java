package gui;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.*;
import source.*;

import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AbonnementWeergevenGui extends JPanel {
	private SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
	private JButton btnKlantBekijken;
	private JButton btnMedewerkerBekijken;
	private Abonnement doorgegevenAbonnement;
	public AbonnementWeergevenGui(Abonnement abonnement) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		doorgegevenAbonnement = abonnement;

		JLabel lblAbonnement = DefaultComponentFactory.getInstance().createTitle("Abonnement van " + abonnement.getKlant().getNaam());
		lblAbonnement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNaam = new JLabel("Naam: ");
		lblNaam.setForeground(Color.WHITE);
		
		JLabel lblNaamValue = new JLabel("");
		lblNaamValue.setForeground(Color.WHITE);
		lblNaamValue.setText(abonnement.getKlant().getNaam());
		
		
		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setForeground(Color.WHITE);
		
		JLabel lblVertrekValue = new JLabel("");
		lblVertrekValue.setForeground(Color.WHITE);
		lblVertrekValue.setText(abonnement.getDepZone());
		
		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setForeground(Color.WHITE);
		
		JLabel lblAankomstValue = new JLabel("");
		lblAankomstValue.setForeground(Color.WHITE);
		lblAankomstValue.setText(abonnement.getArrZone());
		
		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setForeground(Color.WHITE);
		
		JLabel lblPrijsValue = new JLabel("");
		lblPrijsValue.setForeground(Color.WHITE);
		lblPrijsValue.setText(Double.toString(abonnement.getPrijs())+ " euro");
		
		JLabel lblPeriode = new JLabel("Periode:");
		lblPeriode.setForeground(Color.WHITE);
		
		JLabel lblPeriodeValue = new JLabel("");
		lblPeriodeValue.setForeground(Color.WHITE);
		
		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setForeground(Color.WHITE);
		
		JLabel lblStartdatumValue = new JLabel((String) null);
		lblStartdatumValue.setForeground(Color.WHITE);
		
		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setForeground(Color.WHITE);
		
		JLabel lblEinddatumValue = new JLabel((String) null);
		lblEinddatumValue.setForeground(Color.WHITE);
		
		JLabel lblVerkoopdatum = new JLabel("Verkoopdatum:");
		lblVerkoopdatum.setForeground(Color.WHITE);
		
		JLabel lblVerkoopdatumValue = new JLabel((String) null);
		lblVerkoopdatumValue.setForeground(Color.WHITE);
		
		JLabel lblResterendeTijd = new JLabel("Resterende tijd:");
		lblResterendeTijd.setForeground(Color.WHITE);
		
		JLabel lblResterendeTijdValue = new JLabel((String) null);
		lblResterendeTijdValue.setForeground(Color.WHITE);
		
		JLabel lblLaatstGewijzigdDoor = new JLabel("Laatst gewijzigd door:");
		lblLaatstGewijzigdDoor.setForeground(Color.WHITE);
		
		JLabel lblLaatstGewijzigdDoorValue = new JLabel((String) null);
		lblLaatstGewijzigdDoorValue.setForeground(Color.WHITE);
		
		btnKlantBekijken = new JButton("Klant bekijken");
		btnKlantBekijken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnKlantBekijken.setBackground(Color.ORANGE);
		btnKlantBekijken.addActionListener(new MenuItemHandler());
		
		btnMedewerkerBekijken = new JButton("Medewerker bekijken");
		btnMedewerkerBekijken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnMedewerkerBekijken.setBackground(Color.ORANGE);
		btnMedewerkerBekijken.addActionListener(new MenuItemHandler());
		
		if(abonnement.getP()!=null){
			lblPeriodeValue.setText("Actief");
			lblStartdatumValue.setText(formatDatum.format(abonnement.getP().getStartDate()));
			lblEinddatumValue.setText(formatDatum.format(abonnement.getP().getEndDate()));
			lblVerkoopdatumValue.setText(formatDatum.format(abonnement.getP().getVerkoopdatum()));
			lblResterendeTijdValue.setText(abonnement.getResterendeDagen() + " dagen");
			lblLaatstGewijzigdDoorValue.setText(MedewerkerDAO.getMedewerker(abonnement.getP().getMedewerkerId()).getNaam());
		} else {
			lblPeriodeValue.setText("Inactief");
			lblStartdatumValue.setText("Niet van toepassing");
			lblEinddatumValue.setText("Niet van toepassing");
			lblVerkoopdatumValue.setText("Niet van toepassing");
			lblResterendeTijdValue.setText("Niet van toepassing");
			lblLaatstGewijzigdDoorValue.setText("Niet van toepassing");
			btnMedewerkerBekijken.setVisible(false);
		}
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVerkoopdatum)
								.addComponent(lblResterendeTijd))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVerkoopdatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResterendeTijdValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLaatstGewijzigdDoor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLaatstGewijzigdDoorValue, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAbonnement)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNaam)
								.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAankomst, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPeriode, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStartdatum)
								.addComponent(lblEinddatum, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEinddatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStartdatumValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPeriodeValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAankomstValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVertrekValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNaamValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
									.addGap(29)
									.addComponent(btnKlantBekijken, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnMedewerkerBekijken, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAbonnement)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNaam)
								.addComponent(lblNaamValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVertrek)
								.addComponent(lblVertrekValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnKlantBekijken, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAankomst)
						.addComponent(lblAankomstValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPrijs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPrijsValue, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeriode)
						.addComponent(lblPeriodeValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartdatum)
						.addComponent(lblStartdatumValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEinddatum)
						.addComponent(lblEinddatumValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerkoopdatum)
						.addComponent(lblVerkoopdatumValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblResterendeTijd)
								.addComponent(lblResterendeTijdValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLaatstGewijzigdDoor)
								.addComponent(lblLaatstGewijzigdDoorValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(btnMedewerkerBekijken, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(88))
		);

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			if(e.getSource() == btnKlantBekijken){
				AdminGui.setHuidigeKeuze(new KlantWeergevenGui(doorgegevenAbonnement.getKlant()));
				
			}
			
			if(e.getSource() == btnMedewerkerBekijken){
				AdminGui.setHuidigeKeuze(new GebruikerWeergevenGui(MedewerkerDAO.getMedewerker(doorgegevenAbonnement.getP().getMedewerkerId())));
			}
		}
	}
}