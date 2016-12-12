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
	private JTextField txtPrijs;
	private JTextField txtDuur;

	public AbonnementToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		btnAanmaken = new JButton("Aanmaken");
		btnAanmaken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAanmaken.setBackground(Color.ORANGE);
		btnAanmaken.addActionListener(new MenuItemHandler());

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement aanmaken");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		
		JLabel lblKlant = new JLabel("Klant:");
		lblKlant.setForeground(Color.WHITE);
		
		txtKlant = new JTextField();
		txtKlant.setColumns(10);
		
		btnZoeken = new JButton("Zoeken");
		
		arrayLijst= new ArrayList<Klant>();
		
		arrayLijst = KlantDAO.getAllKlanten();
		
		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();
		
		
		for(Klant m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		list = new JList<Klant>(dlm);
		
		JLabel lblGevondenResultaten = new JLabel("Gevonden resultaten:");
		lblGevondenResultaten.setForeground(Color.WHITE);
		
		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setForeground(Color.WHITE);
		
		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setForeground(Color.WHITE);
		
		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setForeground(Color.WHITE);
		
		JLabel lblPrijs = new JLabel("Prijs:");
		lblPrijs.setForeground(Color.WHITE);
		
		txtPrijs = new JTextField();
		txtPrijs.setColumns(10);
		txtPrijs.setText(new Double(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)).toString());
		
		txtDuur = new JTextField();
		txtDuur.setColumns(10);
		
		JLabel lblMaanden = new JLabel("maanden");
		lblMaanden.setForeground(Color.WHITE);
		
		JLabel lblEuro = new JLabel("euro");
		lblEuro.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbonnementAanmaken)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrijs, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDuur))
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtKlant, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtPrijs, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(txtDuur, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEuro, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMaanden, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))))
									.addGap(230)
									.addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAankomst, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblKlant, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(58))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblGevondenResultaten)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(159)
									.addComponent(btnZoeken))
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))))
					.addGap(270))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblAbonnementAanmaken)
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKlant)
								.addComponent(txtKlant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGevondenResultaten)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(lblVertrek)
							.addGap(18)
							.addComponent(lblAankomst)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addGap(262)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDuur, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtDuur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaanden))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrijs)
								.addComponent(txtPrijs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEuro))))
					.addGap(31))
		);

		setLayout(groupLayout);
	}


	
	
	
	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

		
			if (e.getSource() == btnAanmaken) {
				
				double prijs = new Double(txtPrijs.getText());
				
				VerkoopType v=VerkoopType.ABONNEMENT;
				
				Abonnement abonnement= new Abonnement(0.00,prijs,v,list.getSelectedValue(), "brussel", "brussel");
	
						AbonnementDAO.addAbonnement(abonnement);
						JOptionPane.showMessageDialog(new JFrame(), "Abonnement is aangemaakt!");
						AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
				}

				

	
			}
		}
}

