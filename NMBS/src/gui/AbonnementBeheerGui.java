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

import dao.*;
import source.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class AbonnementBeheerGui extends JPanel {
	private JTextField txtZoekveld;
	private JButton btnVerlengen;
	private JButton btnVerwijderen;
	private JList<Abonnement> list;
	private ArrayList<Abonnement> arrayLijst;
	private DefaultListModel<Abonnement> dlm;
	private JButton btnAnnuleren;
	public String navigation;
	private JButton btnNieuwAbonnement;
	public String newline = System.getProperty("line.separator");
	private JLabel label;
	
	public AbonnementBeheerGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblAbonnementenBeheren = DefaultComponentFactory.getInstance().createTitle("Abonnementen beheren");
		lblAbonnementenBeheren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		
		arrayLijst= new ArrayList<Abonnement>();
		
		arrayLijst = AbonnementDAO.getAllAbonnementen();
		
		dlm = new DefaultListModel<Abonnement>();
		
		
		for(Abonnement a : arrayLijst)
		{
			dlm.addElement(a);
		}
		
		list = new JList<Abonnement>(dlm);
	
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	
		            AdminGui.setHuidigeKeuze(new AbonnementWeergevenGui(list.getSelectedValue()));
		        }
		    }
		});
		
		txtZoekveld = new JTextField();
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
		
		btnVerlengen = new JButton("Verlengen");
		btnVerlengen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerlengen.setBackground(Color.ORANGE);
		btnVerlengen.addActionListener(new MenuItemHandler());
		
		btnAnnuleren = new JButton("Annuleren");
		btnAnnuleren.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAnnuleren.setBackground(Color.ORANGE);
		btnAnnuleren.addActionListener(new MenuItemHandler());
		
		btnNieuwAbonnement = new JButton("Aanmaken");
		btnNieuwAbonnement.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNieuwAbonnement.setBackground(Color.ORANGE);
		btnNieuwAbonnement.addActionListener(new MenuItemHandler());
		
		btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new MenuItemHandler());
		btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerwijderen.setBackground(Color.ORANGE);
		
		label = new JLabel("Zoeken op naam:");
		label.setForeground(Color.WHITE);
		
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
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(72)
									.addComponent(txtZoekveld, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVerwijderen, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(btnAnnuleren, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(btnVerlengen, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(btnNieuwAbonnement, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbonnementenBeheren)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNieuwAbonnement, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerlengen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAnnuleren)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
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
	
	public void updateLijst(){
		ArrayList <Abonnement> t = new ArrayList<Abonnement>();
		if(!txtZoekveld.getText().isEmpty()){
			
			for(int i=0;i<arrayLijst.size();i++){
				if(arrayLijst.get(i).getKlant().getNaam().toLowerCase().contains(txtZoekveld.getText().toLowerCase())){
					t.add(arrayLijst.get(i));
				}
				
			}
		} else {
			t=arrayLijst;
		}
		
	
		dlm.clear();
		for(Abonnement m: t){
			dlm.addElement(m);
		}
		
		list.setModel(dlm);
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
		int huidigeMedewerkerId=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser())).getMedewerkerId();
			
			if(e.getSource() == btnNieuwAbonnement){
				AdminGui.setHuidigeKeuze(new AbonnementToevoegenGui());
			}
			
			if (e.getSource() == btnVerlengen) {
				if(!unknownIndex()){
					return;}
				else {
					
				AdminGui.setHuidigeKeuze(new AbonnementVerlengenGui(list.getSelectedValue()));
				
				}
				
				}
			
			if (e.getSource() == btnAnnuleren) {
				if(!unknownIndex()){
					return;
					} else {
						
					if(list.getSelectedValue().getP()==null){
						JOptionPane.showMessageDialog(new JFrame(), "Dit abonnement heeft geen actieve periode.");
					} else {
						if(list.getSelectedValue().getResterendeDagen()<=31){
							JOptionPane.showMessageDialog(new JFrame(), "Abonnementen kunnen niet geannuleerd worden binnen 31 dagen voor vervalling.");
							return;
						}
						
						int n = OkCancel("Let op! U bent van plan om het abonnement van " + list.getSelectedValue().getKlant().getVoornaam() + " " + list.getSelectedValue().getKlant().getAchternaam()+ " te annuleren." + newline 
								+ "Dit abonnement heeft nog een actieve periode van " + list.getSelectedValue().getResterendeDagen() + " dagen. Bent u zeker dat u wilt doorgaan?");	
						
						if(n==0){
							double terugTeBetalen = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)/Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))*list.getSelectedValue().getResterendeDagen(); 
										
							list.getSelectedValue().getP().setEndDate(Calendar.getInstance().getTime());
							AbonnementDAO.updatePrijs(list.getSelectedValue(), 0);
							PeriodeDAO.updatePeriode(list.getSelectedValue().getP(), huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(), "Het abonnement van " + list.getSelectedValue().getKlant().getVoornaam() + " " + list.getSelectedValue().getKlant().getAchternaam() + " wordt binnen de 24 uur geannuleerd. Het terug te betalen bedrag bedraagt " + terugTeBetalen + " euro.");
							AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
						}
							
					}	
					}
				
			}
			
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
					return;
					} else {
						if(list.getSelectedValue().getP()==null){
							int n = OkCancel("Ben je zeker dat je het abonnement van " + list.getSelectedValue().getKlant().getVoornaam() + " " + list.getSelectedValue().getKlant().getAchternaam() + " wil verwijderen?");	
						if(n==0){
						AbonnementDAO.removeAbonnement(list.getSelectedValue().getAbonnementId());
						((DefaultListModel<Abonnement>)list.getModel()).remove(list.getSelectedIndex());
						JOptionPane.showMessageDialog(new JFrame(), "Abonnement is succesvol verwijdert.");
						} else if (n==1){
							return;
						}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Abonnement kan niet verwijdert worden omdat dit gekoppeld is met een actieve periode.");
							return;
						}
						
						
						
					}
				
			}
		
					
			
		}
	}
}
