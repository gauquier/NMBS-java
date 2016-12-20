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

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import handler.Controller;
import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GebruikerBewerkenGui extends JPanel {
	private JTextField txtZoekveld;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst, arrayLijst2;
	private ArrayList<Object> objecten;
	private JButton btnVerwijderen;
	private JButton btnPasswordReset;
	public String navigation;
	
	
	public GebruikerBewerkenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance().createTitle("Gebruikers beheren");
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		arrayLijst= new ArrayList<Medewerker>();
		
		arrayLijst = MedewerkerDAO.getAllMedewerkers();
		
		
		
		DefaultListModel<Medewerker> dlm = new DefaultListModel<Medewerker>();
		
		
		for(Medewerker m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		txtZoekveld = new JTextField();
		txtZoekveld.setColumns(10);
		txtZoekveld.addKeyListener(new KeyListener()
		  {
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {
					Thread userRefresh = new Thread(new Runnable(){
						@Override
						public void run() {
							if(txtZoekveld.getText().isEmpty()){
								dlm.clear();
								for(Medewerker m : arrayLijst)
								{
									dlm.addElement(m);
								}
								list = new JList<Medewerker>(dlm);
							}
							else if(txtZoekveld.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
								dlm.clear();
								for(Medewerker m : arrayLijst)
								{
									dlm.addElement(m);
								}
								list = new JList<Medewerker>(dlm);
							}
							else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE){
								dlm.clear();
								for(Medewerker m : arrayLijst)
								{
									dlm.addElement(m);
								}
								list = new JList<Medewerker>(dlm);
							}
							else
							{
								
								dlm.clear();
								arrayLijst2 = MedewerkerDAO.getAllMedewerkersFromSearch(txtZoekveld.getText());
		    					for(Medewerker m : arrayLijst2)
		    					{
		    						dlm.addElement(m);
		    					}
		    					list = new JList<Medewerker>(dlm);
							}
						}
					});
					userRefresh.start();
				}
	  		  });	

		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnZoeken.setBackground(Color.ORANGE);
		
		btnBewerken = new JButton("Bewerken");
		btnBewerken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBewerken.setBackground(Color.ORANGE);
		btnBewerken.addActionListener(new MenuItemHandler());
		
		btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerwijderen.setBackground(Color.ORANGE);
		btnVerwijderen.addActionListener(new MenuItemHandler());
		
		btnPasswordReset = new JButton("Password Reset");
		btnPasswordReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnPasswordReset.setBackground(Color.ORANGE);
		btnPasswordReset.addActionListener(new MenuItemHandler());
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addComponent(lblGebruikerBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addGap(61)
							.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 102, Short.MAX_VALUE)))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(46)
								.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblGebruikerBewerken)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZoeken)
						.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.addGap(92))
		);
		
		list = new JList<Medewerker>(dlm);
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
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
	
	public void close() {
		this.setVisible(false);
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

			if (e.getSource() == btnBewerken) {
				
				if(!unknownIndex()){
					return;
				} else {
					System.out.println(list.getSelectedValue().getId());
					navigation= "gebruikerToevoegen";
					AdminGui.setHuidigeKeuze(new GebruikerBijwerkenGui(list.getSelectedValue()));
				}
			}
		
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
					return;
				} else {
				int n = OkCancel("Ben je zeker dat je " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " wil verwijderen?");	
				
				if(n==0){
				MedewerkerDAO.removeMedewerker(list.getSelectedValue().getId());
				((DefaultListModel<Medewerker>)list.getModel()).remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is succesvol verwijdert.");
				} else if (n==1){
					return;
				}
			}
			}
			if(e.getSource() == btnPasswordReset){
				System.out.println("test");
				if(!unknownIndex()){
					return;
			 } else {
					
					String wachtwoord =  new String("reset1");
					
					int n = OkCancel("Ben je zeker dat je een password reset wil uitvoeren op " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + "?");
					
					if(n==0){
					try {
						LoginDao.updateWachtwoordWhere(list.getSelectedValue().getLogin().getLoginId(), DualHash.hashString(wachtwoord));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(new JFrame(), "Het wachtwoord van " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " is gereset naar 'reset1'.");
					} else if (n==1){
						return;
					}
				
				}
				
			}
			
		}
	}
}

