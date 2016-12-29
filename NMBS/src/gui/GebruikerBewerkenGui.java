package gui;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import source.Klant;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GebruikerBewerkenGui extends JPanel {
	private JTextField txtZoekveld;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst, arrayLijst2;
	private DefaultListModel<Medewerker> dlm;
	private JButton btnVerwijderen;
	private JButton btnPasswordReset;
	public String navigation;
	private JLabel label;
	
	
	public GebruikerBewerkenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance().createTitle("Gebruikers beheren");
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		arrayLijst= new ArrayList<Medewerker>();
		
		arrayLijst = MedewerkerDAO.getAllMedewerkers();
		
		
		dlm = new DefaultListModel<Medewerker>();
		
		
		for(Medewerker m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		
		txtZoekveld = new JTextField();
		txtZoekveld.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtZoekveld.setColumns(10);
		txtZoekveld.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLijst();
			}	
			
		});
		
		btnBewerken = new JButton("Bewerken");
		btnBewerken.setFont(new Font("Dialog", Font.BOLD, 20));
		btnBewerken.setBackground(Color.ORANGE);
		btnBewerken.addActionListener(new MenuItemHandler());
		
		btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.setFont(new Font("Dialog", Font.BOLD, 20));
		btnVerwijderen.setBackground(Color.ORANGE);
		btnVerwijderen.addActionListener(new MenuItemHandler());
		
		btnPasswordReset = new JButton("Password Reset");
		btnPasswordReset.setFont(new Font("Dialog", Font.BOLD, 20));
		btnPasswordReset.setBackground(Color.ORANGE);
		btnPasswordReset.addActionListener(new MenuItemHandler());
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		label = new JLabel("Zoeken op naam:");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGebruikerBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(lblGebruikerBewerken)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(12)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(btnBewerken)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPasswordReset)))
					.addContainerGap(162, Short.MAX_VALUE))
		);
		
		list = new JList<Medewerker>(dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            AdminGui.setHuidigeKeuze(new GebruikerWeergevenGui(list.getSelectedValue()));
		        }
		    }
		});
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
	
	public void updateLijst(){
		ArrayList <Medewerker> t = new ArrayList<Medewerker>();
		if(!txtZoekveld.getText().isEmpty()){
			
			for(int i=0;i<arrayLijst.size();i++){
				if(arrayLijst.get(i).getNaam().toLowerCase().contains(txtZoekveld.getText().toLowerCase())){
					t.add(arrayLijst.get(i));
				}
				
			}
		} else {
			t=arrayLijst;
		}
		
		
	
		dlm.clear();
		for(Medewerker m: t){
			dlm.addElement(m);
		}
		
		list.setModel(dlm);
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBewerken) {
				
				if(!unknownIndex()){
					return;
				} else {
					navigation= "gebruikerBijwerken";
					AdminGui.setHuidigeKeuze(new GebruikerBijwerkenGui(list.getSelectedValue()));
				}
			}
		
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
		
					return;
				} else {
				int n = OkCancel("Ben je zeker dat je " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " wil verwijderen?");	
				
				
				if(n==0){
				MedewerkerDAO.removeMedewerker(list.getSelectedValue().getMedewerkerId());
				((DefaultListModel<Medewerker>)list.getModel()).remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is succesvol verwijdert.");
				} else if (n==1){
					return;
				}
			}
			}
			if(e.getSource() == btnPasswordReset){
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

