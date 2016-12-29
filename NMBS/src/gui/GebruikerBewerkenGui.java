package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Medewerker;

public class GebruikerBewerkenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082995962712377672L;
	private JTextField txtZoekveld;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst, arrayLijst2;
	private JButton btnVerwijderen;
	private JButton btnPasswordReset;
	public String navigation;
	
	
	public GebruikerBewerkenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance().createTitle("Gebruikers beheren");
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		arrayLijst= new ArrayList<Medewerker>();
		
		arrayLijst = MedewerkerDAO.getAllMedewerkers();
		
		
		
		final DefaultListModel<Medewerker> dlm = new DefaultListModel<Medewerker>();
		
		
		for(Medewerker m : arrayLijst)
		{
			dlm.addElement(m);
		}
		
		
		txtZoekveld = new JTextField();
		txtZoekveld.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtZoekveld.setColumns(10);
		txtZoekveld.addKeyListener(new KeyListener()
		  {
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(final KeyEvent e) {
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
		btnZoeken.setFont(new Font("Dialog", Font.BOLD, 20));
		btnZoeken.setBackground(Color.ORANGE);
		
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addComponent(lblGebruikerBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnZoeken)
							.addGap(18)
							.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblGebruikerBewerken)
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnZoeken)
								.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBewerken)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnVerwijderen))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(187)
							.addComponent(btnPasswordReset)))
					.addContainerGap(148, Short.MAX_VALUE))
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

