
package gui;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.util.ArrayList;
import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import source.Station;
import source.VerlorenVoorwerp;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.SystemColor;


public class VerlorenVoorwerpenZoekenGui extends JPanel {
	private JComboBox stationLijst;
	private JButton btnZoeken;
	private StationDAO stationDAO = new StationDAO();
	private VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();
	private JList<VerlorenVoorwerp> list;
	private ArrayList<VerlorenVoorwerp> arrayLijst;
	private ArrayList<Object> objecten;
	private DefaultListModel<VerlorenVoorwerp> dlm;
	
	
	public VerlorenVoorwerpenZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStation.setForeground(Color.WHITE);
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnZoeken.addActionListener(new MenuItemHandler());
		
		JLabel lblVerlorenVoorwerpenZoeken = DefaultComponentFactory.getInstance().createTitle("Verloren voorwerpen zoeken");
		lblVerlorenVoorwerpenZoeken.setForeground(Color.BLACK);
		lblVerlorenVoorwerpenZoeken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		stationLijst = new JComboBox();
		stationLijst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			stationLijst.addItem(station);
		}
		
		
		stationLijst.setSelectedIndex(0);;//veranderen naar current user station id
		arrayLijst= new ArrayList<VerlorenVoorwerp>();
		arrayLijst = verlorenVoorwerpDAO.getVerlorenVoorwerpByStation(1);//veranderen naar current user station id
		DefaultListModel<VerlorenVoorwerp> dlm = new DefaultListModel<VerlorenVoorwerp>();
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
				
				arrayLijst= new ArrayList<VerlorenVoorwerp>();
				arrayLijst = verlorenVoorwerpDAO.getVerlorenVoorwerpByStation(stationDAO.checkStation(stationLijst.getSelectedItem().toString()));//veranderen naar current user station id
				
				
				for(VerlorenVoorwerp v : arrayLijst){
					dlm.addElement(v);
				}
				
				list = new JList<VerlorenVoorwerp>(dlm);
				
			}
		});
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStation)
							.addGap(30)
							.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnZoeken)))
					.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblVerlorenVoorwerpenZoeken, GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblVerlorenVoorwerpenZoeken)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation)
						.addComponent(btnZoeken, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(184, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void close()
	{
		this.setVisible(false);
	}
	
	
	
	
	private class MenuItemHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnZoeken) 
			{
			
						
			}
			
		}

	}
}
