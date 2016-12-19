package gui;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import dao.*;
import source.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


public class AbonnementBeheerGui extends JPanel {
	private JTextField textField;
	private JButton btnZoeken;
	private JButton btnVerlengen;
	private JList<Abonnement> list;
	private ArrayList<Abonnement> arrayLijst;
	private ArrayList<Object> objecten;
	private JButton btnStopZetten;
	public String navigation;
	private JButton btnNieuwAbonnement;
	
	
	public AbonnementBeheerGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblAbonnementenBeheren = DefaultComponentFactory.getInstance().createTitle("Abonnementen beheren");
		lblAbonnementenBeheren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		
		arrayLijst= new ArrayList<Abonnement>();
		
		arrayLijst = AbonnementDAO.getAllAbonnementen();
		
		DefaultListModel<Abonnement> dlm = new DefaultListModel<Abonnement>();
		
		
		for(Abonnement a : arrayLijst)
		{
			dlm.addElement(a);
		}
		
		list = new JList<Abonnement>(dlm);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnZoeken.setBackground(Color.ORANGE);
		
		btnVerlengen = new JButton("Verlengen");
		btnVerlengen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerlengen.setBackground(Color.ORANGE);
		btnVerlengen.addActionListener(new MenuItemHandler());
		
		btnStopZetten = new JButton("Stop zetten");
		btnStopZetten.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnStopZetten.setBackground(Color.ORANGE);
		btnStopZetten.addActionListener(new MenuItemHandler());
		
		btnNieuwAbonnement = new JButton("Aanmaken");
		btnNieuwAbonnement.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNieuwAbonnement.setBackground(Color.ORANGE);
		btnNieuwAbonnement.addActionListener(new MenuItemHandler());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbonnementenBeheren)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnZoeken)
									.addGap(121)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnStopZetten, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
									.addComponent(btnVerlengen, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
								.addComponent(btnNieuwAbonnement, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbonnementenBeheren)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZoeken)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNieuwAbonnement, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerlengen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStopZetten))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	public void close()
	{
		this.setVisible(false);
	}
	
	public int OkCancel(String message){
		int n = JOptionPane.showConfirmDialog(
                null, message,
                "Bevestiging",
                JOptionPane.YES_NO_OPTION);
		
		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}  
		return 1;
		
	}
	
	
	
	public Boolean unknownIndex(){
	if(list.getSelectedValue()==null || list.getSelectedIndex()<0){
		JOptionPane.showMessageDialog(new JFrame(), "Er is geen gebruiker aangeduid.");
		return false;
	}
	else {
		return true;
	}
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == btnNieuwAbonnement){
				AdminGui.setHuidigeKeuze(new AbonnementToevoegenGui());
			}
			
			if (e.getSource() == btnVerlengen) {
				AdminGui.setHuidigeKeuze(new AbonnementVerlengenGui(list.getSelectedValue()));
				
				}
			
			if (e.getSource() == btnStopZetten) {
				
				
			}
			
			
			
		}
	}
}
