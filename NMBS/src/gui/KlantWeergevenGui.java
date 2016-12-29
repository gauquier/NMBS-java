package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import source.Klant;

public class KlantWeergevenGui extends JPanel {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2392843966171201473L;

	public KlantWeergevenGui(Klant klant) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));


		JLabel lblKlant = DefaultComponentFactory.getInstance().createTitle("");
		lblKlant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKlant.setText(klant.getNaam());
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setForeground(Color.WHITE);
		
		JLabel lblEmailValue = new JLabel("");
		lblEmailValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmailValue.setForeground(Color.WHITE);
		lblEmailValue.setText(klant.getEmail());
		
		
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAdres.setForeground(Color.WHITE);
		
		JLabel lblAdresValue = new JLabel("");
		lblAdresValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAdresValue.setForeground(Color.WHITE);
		lblAdresValue.setText(klant.getAdres().toString());
		
		JLabel lblBus = new JLabel("Bus:");
		lblBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBus.setForeground(Color.WHITE);
		
		
		JLabel lblBusValue = new JLabel("");
		lblBusValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBusValue.setForeground(Color.WHITE);
		if(klant.getAdres().getBus()==null||klant.getAdres().getBus()==""||klant.getAdres().getBus()==" "){
			lblBusValue.setText("Niet van toepassing");
		} else {
			lblBusValue.setText(klant.getAdres().getBus());
		}
		
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblInfo.setForeground(Color.WHITE);
		
		JLabel lblInfoValue = new JLabel("");
		lblInfoValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblInfoValue.setForeground(Color.WHITE);
		lblInfoValue.setText(klant.getInfo());
		
		
		
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKlant, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblBus, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdres, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBusValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdresValue, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailValue, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInfoValue, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblKlant, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(lblEmailValue, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdres)
						.addComponent(lblAdresValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(lblBusValue, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInfoValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(282))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInfo)
							.addContainerGap())))
		);

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}
}