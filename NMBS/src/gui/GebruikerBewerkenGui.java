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

import dao.MedewerkerDAO;
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

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class GebruikerBewerkenGui extends JPanel {
	private JTextField txtZoekveld;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst, arrayLijst2;
	private ArrayList<Object> objecten;
	private JButton btnVerwijderen;
	
	public GebruikerBewerkenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance().createTitle("Gebruiker bewerken");
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		arrayLijst= new ArrayList<Medewerker>();
		
		arrayLijst = MedewerkerDAO.getAllMedewerkers();
		
		
		DefaultListModel<Medewerker> dlm = new DefaultListModel<Medewerker>();
		for(Medewerker m : arrayLijst)
		{
			dlm.addElement(m);
		}
		list = new JList<Medewerker>(dlm);
		
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
							if(!txtZoekveld.getText().isEmpty()){
								dlm.clear();
								
								arrayLijst2 = MedewerkerDAO.getAllMedewerkersFromSearch(txtZoekveld.getText());
		    					System.out.println(arrayLijst2);
		    					for(Medewerker m : arrayLijst2)
		    					{
		    						dlm.addElement(m);
		    					}

		    					System.out.println(dlm);
		    					list = new JList<Medewerker>(dlm);
							}
							else
							{
								for(Medewerker m : arrayLijst)
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
		btnBewerken = new JButton("Bewerken");
		btnBewerken.addActionListener(new MenuItemHandler());
		
		btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new MenuItemHandler());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnBewerken, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnVerwijderen, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addGap(18)
							.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblGebruikerBewerken))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblGebruikerBewerken)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZoeken)
						.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBewerken)
							.addGap(18)
							.addComponent(btnVerwijderen))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	public void close()
	{
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
				//MedewerkerDAO.removeMedewerker(list.getSelectedIndex());
				
				}
			}
			
			if (e.getSource() == btnVerwijderen) {
				if(!unknownIndex()){
					return;
				} else {
				MedewerkerDAO.removeMedewerker(list.getSelectedValue().getId());
				((DefaultListModel<Medewerker>)list.getModel()).remove(list.getSelectedIndex());
				JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is succesvol verwijdert.");
				}
			}
			
		}
	}
}