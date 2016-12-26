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

public class GebruikerWeergevenGui extends JPanel {

	private Abonnement doorgegevenAbonnement;
	
	public GebruikerWeergevenGui(Medewerker medewerker) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));


		JLabel lblGebruiker = DefaultComponentFactory.getInstance().createTitle("<dynamic>");
		lblGebruiker.setFont(new Font("Tahoma", Font.PLAIN, 14));
		if(!medewerker.isActief()){
			lblGebruiker.setText(medewerker.getNaam() + " (Inactief)");
		}
		else {
			lblGebruiker.setText(medewerker.getNaam());
		}
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		
		JLabel lblEmailValue = new JLabel("");
		lblEmailValue.setForeground(Color.WHITE);
		lblEmailValue.setText(medewerker.getEmail());
		
		
		JLabel lblVertrek = new JLabel("Adres:");
		lblVertrek.setForeground(Color.WHITE);
		
		JLabel lblAdresValue = new JLabel("");
		lblAdresValue.setForeground(Color.WHITE);
		lblAdresValue.setText(medewerker.getAdres().toString());
		
		JLabel lblBus = new JLabel("Bus:");
		lblBus.setForeground(Color.WHITE);
		
		
		JLabel lblBusValue = new JLabel("");
		lblBusValue.setForeground(Color.WHITE);
		if(medewerker.getAdres().getBus()==null||medewerker.getAdres().getBus()==""||medewerker.getAdres().getBus()==" "){
			lblBusValue.setText("Niet van toepassing");
		} else {
			lblBusValue.setText(medewerker.getAdres().getBus());
		}
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		
		JLabel lblUsernameValue = new JLabel("");
		lblUsernameValue.setForeground(Color.WHITE);
		lblUsernameValue.setText(medewerker.getLogin().getUsername());

		
		JLabel lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		
		JLabel lblTypeValue = new JLabel("<dynamic>");
		lblTypeValue.setForeground(Color.WHITE);
		lblTypeValue.setText(medewerker.getRol().getRol());
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGebruiker)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBus, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTypeValue, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdresValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblBusValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblUsernameValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))))
					.addContainerGap(225, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblGebruiker)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblEmailValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVertrek)
						.addComponent(lblAdresValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(lblBusValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUsernameValue, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(lblTypeValue))
					.addContainerGap(163, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}
}