package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.KlantDAO;
import source.Klant;



public class KlantenBeheerGui extends JPanel {
	private static ResourceBundle bundle;
	
	private JTextField textField;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Klant> list;
	private ArrayList<Klant> arrayLijst;
	private ArrayList<Object> objecten;
	private JButton btnVerwijderen;
	public String navigation;
	
	
	public KlantenBeheerGui() {
		bundle = ResourceBundle.getBundle("localization.KlantBewerkenGui");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblKlantBewerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblKlantBewerken"));
		lblKlantBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		arrayLijst= new ArrayList<Klant>();
		
		arrayLijst = KlantDAO.getAllKlanten();
		
		
		
		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();
		
		
		for(Klant m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		list = new JList<Klant>(dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	
		            AdminGui.setHuidigeKeuze(new KlantWeergevenGui(list.getSelectedValue()));
		        }
		    }
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setColumns(10);
		
		btnZoeken = new JButton(bundle.getString("btnZoeken"));
		btnZoeken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnZoeken.setBackground(Color.ORANGE);
		
		btnBewerken = new JButton(bundle.getString("btnBewerken"));
		btnBewerken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBewerken.setBackground(Color.ORANGE);
		btnBewerken.addActionListener(new MenuItemHandler());
		
		btnVerwijderen = new JButton(bundle.getString("btnVerwijderen"));
		btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 20));

		btnVerwijderen.setBackground(Color.ORANGE);
		btnVerwijderen.addActionListener(new MenuItemHandler());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblKlantBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField))
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVerwijderen))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblKlantBewerken)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZoeken)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBewerken)
							.addGap(12)
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
		JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noSelectedCustomer"));
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
				
					navigation= "gebruikerBijwerken";
					AdminGui.setHuidigeKeuze(new KlantBijwerkenGui(list.getSelectedValue()));
					
			}
				}
			
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
					return;
				} else {
				int n = OkCancel(bundle.getString("betUZekerDatU") + " " + list.getSelectedValue().getVoornaam() + " " + list.getSelectedValue().getAchternaam() + " " + bundle.getString("wiltVerwijderen"));	
					
				if(n==0){
				KlantDAO.removeKlant(list.getSelectedValue().getKlantId());
				((DefaultListModel<Klant>)list.getModel()).remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerDeleted"));
				} else if (n==1){
					return;
				}
				
			}
			}
			
			
		}
	}
}
