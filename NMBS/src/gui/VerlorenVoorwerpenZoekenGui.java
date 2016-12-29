
package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import source.Station;
import source.VerlorenVoorwerp;


public class VerlorenVoorwerpenZoekenGui extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3449634391108490909L;

	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.VerlorenVoorwerpenZoekenGui");
	
	private JComboBox<Station> stationLijst;
	private JButton btnBewerken;
	private JList<VerlorenVoorwerp> list;
	
	private ArrayList<VerlorenVoorwerp> arrayLijst;

	public VerlorenVoorwerpenZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel(bundle.getString("lblStation"));
		lblStation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStation.setForeground(Color.WHITE);
		
		JLabel lblVerlorenVoorwerpenZoeken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblVerlorenVoorwerpenZoeken"));
		lblVerlorenVoorwerpenZoeken.setForeground(Color.WHITE);
		lblVerlorenVoorwerpenZoeken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		stationLijst = new JComboBox<Station>();
		stationLijst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ArrayList<Station> lijst = StationDAO.getAll();
		for (Station station : lijst) {
			stationLijst.addItem(station);
		}
		
		
		stationLijst.setSelectedIndex(0);//veranderen naar current user station id
		arrayLijst = VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(1);//veranderen naar current user station id
		final DefaultListModel<VerlorenVoorwerp> dlm = new DefaultListModel<VerlorenVoorwerp>();
		for(VerlorenVoorwerp v : arrayLijst){
			dlm.addElement(v);
		}
		
		list = new JList<VerlorenVoorwerp>(dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		list.setBackground(SystemColor.menu);
		
		stationLijst.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				arrayLijst.clear();
				dlm.removeAllElements();
				list.removeAll();
				
				arrayLijst= new ArrayList<VerlorenVoorwerp>();
				arrayLijst = VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(StationDAO.checkStation(stationLijst.getSelectedItem().toString()));//veranderen naar current user station id
				
				
				for(VerlorenVoorwerp v : arrayLijst){
					dlm.addElement(v);
				}
				
				list = new JList<VerlorenVoorwerp>(dlm);
				
			}
		});
		
		btnBewerken = new JButton(bundle.getString("btnBewerken"));
		btnBewerken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBewerken.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVerlorenVoorwerpenZoeken, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblStation)
								.addGap(32)
								.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblVerlorenVoorwerpenZoeken)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation)
						.addComponent(btnBewerken))
					.addGap(30)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void close()
	{
		this.setVisible(false);
	}
	

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBewerken) {

				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Er is geen verloren voorwerp aangeduid.");
				} else {
					 
					VerlorenVoorwerpDAO.verlorenVoowerpUpdate(VerlorenVoorwerpDAO.getId(list.getSelectedValue()));
					JOptionPane.showMessageDialog(new JFrame(), "Verloren verwijdert");
					AdminGui.setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());//reset zou beter zijn
					

				}
			}

		}
	}
}
