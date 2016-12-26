
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
import java.util.ResourceBundle;

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
import javax.swing.LayoutStyle.ComponentPlacement;


public class VerlorenVoorwerpenZoekenGui extends JPanel {
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.VerlorenVoorwerpenZoekenGui");
	
	private JComboBox stationLijst;
	private StationDAO stationDAO = new StationDAO();
	private JButton btnBewerken;
	private JList<VerlorenVoorwerp> list;
	
	private VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();
	private ArrayList<VerlorenVoorwerp> arrayLijst;
	private ArrayList<Object> objecten;
	private DefaultListModel<VerlorenVoorwerp> dlm;
	
	
	
	public VerlorenVoorwerpenZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel(bundle.getString("lblStation"));
		lblStation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStation.setForeground(Color.WHITE);
		
		JLabel lblVerlorenVoorwerpenZoeken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblVerlorenVoorwerpenZoeken"));
		lblVerlorenVoorwerpenZoeken.setForeground(Color.ORANGE);
		lblVerlorenVoorwerpenZoeken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		stationLijst = new JComboBox();
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			stationLijst.addItem(station);
		}
		
		
		stationLijst.setSelectedIndex(0);//veranderen naar current user station id
		arrayLijst= new ArrayList<VerlorenVoorwerp>();
		arrayLijst = verlorenVoorwerpDAO.getVerlorenVoorwerpByStation(1);//veranderen naar current user station id
		final DefaultListModel<VerlorenVoorwerp> dlm = new DefaultListModel<VerlorenVoorwerp>();
		for(VerlorenVoorwerp v : arrayLijst){
			dlm.addElement(v);
		}
		
		list = new JList<VerlorenVoorwerp>(dlm);
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
		
		btnBewerken = new JButton(bundle.getString("btnBewerken"));
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(251)
					.addComponent(lblVerlorenVoorwerpenZoeken, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addGap(255))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStation)
							.addGap(30)
							.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblVerlorenVoorwerpenZoeken)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation)
						.addComponent(btnBewerken))
					.addGap(36)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(340, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void close()
	{
		this.setVisible(false);
	}
}
