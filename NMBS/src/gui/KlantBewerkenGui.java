package gui;

import dao.PersoonDao;
import source.Abonnement;
import source.Adres;
import dao.AbonnementDAO;
import dao.KlantDAO;
import source.Klant;
import source.Persoon;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;



import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;


public class KlantBewerkenGui extends JPanel {
	private JTextField textField;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Klant> list;
	private ArrayList<Klant> arrayLijst;
	private ArrayList<Object> objecten;
	private JButton btnVerwijderen;
	public String navigation;
	public String newline = System.getProperty("line.separator");
	
	public KlantBewerkenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblKlantBewerken = DefaultComponentFactory.getInstance().createTitle("Klanten beheren");
		lblKlantBewerken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		arrayLijst= new ArrayList<Klant>();
		
		arrayLijst = KlantDAO.getAllKlanten();
		
		
		
		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();
		
		
		for(Klant m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		list = new JList<Klant>(dlm);
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	
		            AdminGui.setHuidigeKeuze(new KlantWeergevenGui(list.getSelectedValue()));
		        }
		    }
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKlantBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVerwijderen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBewerken, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblKlantBewerken)
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnZoeken)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(btnBewerken)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen)))
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
		JOptionPane.showMessageDialog(new JFrame(), "Er is geen klant aangeduid.");
		return false;
	}
	else {
		return true;
	}
	}
	
	private void verwijderBijhorendeAbonnementen(Klant klant){
		ArrayList<Abonnement> arrayLijst= new ArrayList<Abonnement>();
		arrayLijst = AbonnementDAO.getAllAbonnementen();
		
		for(int i=0;i<arrayLijst.size();i++){
			if (arrayLijst.get(i).getKlant().getKlantId()== klant.getKlantId()){
				AbonnementDAO.removeAbonnement(arrayLijst.get(i).getAbonnementId());
			}
		}
	}
	
	private boolean klantHeeftAlAbonnement(Klant klant){
		ArrayList<Abonnement> arrayLijst= new ArrayList<Abonnement>();
		arrayLijst = AbonnementDAO.getAllAbonnementen();
		
		for(int i=0;i<arrayLijst.size();i++){
			if (arrayLijst.get(i).getKlant().getKlantId()== klant.getKlantId()){
				return true;
			}
		}
		return false;
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
		
			
			if (e.getSource() == btnBewerken) {
				
				if(!unknownIndex()){
					return;
				} else {
				
					navigation= "gebruikerBijwerken";
					AdminGui.setHuidigeKeuze(new KlantBijwerkenGui(list.getSelectedValue()));
					
			}
				}
			
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
					return;
				} else {
				int n;	
					
				if(klantHeeftAlAbonnement(list.getSelectedValue())){
					 n = OkCancel("Let op! U bent van plan om een klant te verwijderen waaraan een abonnement gekoppeld is." + newline + " Ben je zeker dat je " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " wil verwijderen?" );	
					 if(n==0){
						 verwijderBijhorendeAbonnementen(list.getSelectedValue());
					 }
				}
				else {	
			   n = OkCancel("Ben je zeker dat je " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " wil verwijderen?");	
				}
				
				
				if(n==0){
						
				KlantDAO.removeKlant(list.getSelectedValue().getKlantId());
				((DefaultListModel<Klant>)list.getModel()).remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(new JFrame(), "Klant is succesvol verwijdert.");
				} else if (n==1){
					return;
				}
				
			}
			}
			
			
		}
	}
}
