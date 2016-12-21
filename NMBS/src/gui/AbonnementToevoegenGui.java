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

public class AbonnementToevoegenGui extends JPanel {
	private JButton btnAanmaken;
	private JButton btnZoeken;
	private Klant klant;
	private ArrayList<Klant> arrayLijst;
	private ArrayList<Object> objecten;
	private JList<Klant> list;
	private JList<Klant> list_1;
	private JTextField txtKlant;

	public AbonnementToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		btnAanmaken = new JButton("Aanmaken");
		btnAanmaken.setFont(new Font("Dialog", Font.BOLD, 20));
		btnAanmaken.setBackground(Color.ORANGE);
		btnAanmaken.addActionListener(new MenuItemHandler());

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement aanmaken");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		
		JLabel lblKlant = new JLabel("Klant:");
		lblKlant.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblKlant.setForeground(Color.WHITE);
		
		txtKlant = new JTextField();
		txtKlant.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtKlant.setColumns(10);
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		list = new JList<Klant>();
		
		arrayLijst= new ArrayList<Klant>();
		
		arrayLijst = KlantDAO.getAllKlanten();
		
		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();
		
		
		for(Klant m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		list_1 = new JList<Klant>(dlm);
		list_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblGevondenResultaten = new JLabel("Gevonden resultaten:");
		lblGevondenResultaten.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGevondenResultaten.setForeground(Color.WHITE);
		
		JLabel lblVertrek = new JLabel("Vertrek:");
		lblVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVertrek.setForeground(Color.WHITE);
		
		JLabel lblAankomst = new JLabel("Aankomst:");
		lblAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAankomst.setForeground(Color.WHITE);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblType.setForeground(Color.WHITE);
		
		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDuur.setForeground(Color.WHITE);
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
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDuur, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAankomst))
							.addGap(529))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGevondenResultaten)
								.addComponent(lblKlant, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(txtKlant, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnZoeken))
								.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
							.addGap(145)))
					.addGap(144))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(654, Short.MAX_VALUE)
					.addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblAbonnementAanmaken)
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtKlant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnZoeken)
							.addComponent(lblKlant))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGevondenResultaten)
								.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))))
					.addGap(13)
					.addComponent(lblVertrek)
					.addGap(18)
					.addComponent(lblAankomst)
					.addGap(6)
					.addComponent(lblType)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDuur)
					.addGap(4)
					.addComponent(btnAanmaken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
				
				
				VerkoopType v=null;
				
				Abonnement abonnement= new Abonnement(5.00,10.00,v,list.getSelectedValue(),"brussel", "brussel");
				
		
						
						AbonnementDAO.addAbonnement(abonnement);
						JOptionPane.showMessageDialog(new JFrame(), "Abonnement is aangemaakt!");
						//AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
				}

				

	
			}
		}
}

