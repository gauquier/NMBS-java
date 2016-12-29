package gui;



import java.awt.Color;

import java.awt.Font;

import java.awt.SystemColor;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.util.ArrayList;



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



	private JComboBox stationLijst;

	private StationDAO stationDAO = new StationDAO();

	private JButton btnBewerken;

	private JList<VerlorenVoorwerp> list;



	private VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();

	private ArrayList<VerlorenVoorwerp> arrayLijst;

	private DefaultListModel<VerlorenVoorwerp> dlm;



	public VerlorenVoorwerpenZoekenGui() {

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));



		JLabel lblStation = new JLabel("Station:");

		lblStation.setFont(new Font("Dialog", Font.PLAIN, 20));

		lblStation.setForeground(Color.WHITE);



		JLabel lblVerlorenVoorwerpenZoeken = DefaultComponentFactory.getInstance()

				.createTitle("Verloren voorwerpen zoeken");

		lblVerlorenVoorwerpenZoeken.setForeground(Color.BLACK);

		lblVerlorenVoorwerpenZoeken.setFont(new Font("Tahoma", Font.PLAIN, 20));



		this.stationLijst = new JComboBox();
		stationLijst.setFont(new Font("Dialog", Font.PLAIN, 20));

		ArrayList<Station> lijst = StationDAO.getAll();

		for (Station station : lijst) {

			this.stationLijst.addItem(station);

		}



		this.stationLijst.setSelectedIndex(0);

		this.arrayLijst = new ArrayList<VerlorenVoorwerp>();

		this.arrayLijst = VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(1);

		this.dlm = new DefaultListModel<VerlorenVoorwerp>();



		for (VerlorenVoorwerp v : this.arrayLijst) {

			this.dlm.addElement(v);

		}



		this.list = new JList<VerlorenVoorwerp>(this.dlm);

		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.list.setBackground(SystemColor.menu);



		this.stationLijst.addItemListener(new ItemListener() {



			@Override

			public void itemStateChanged(ItemEvent e) {



				ArrayList<VerlorenVoorwerp> t = new ArrayList<VerlorenVoorwerp>();



				t = VerlorenVoorwerpDAO.getVerlorenVoorwerpByStation(StationDAO

						.checkStation(VerlorenVoorwerpenZoekenGui.this.stationLijst.getSelectedItem().toString()));



				VerlorenVoorwerpenZoekenGui.this.dlm.clear();



				for (VerlorenVoorwerp m : t) {

					VerlorenVoorwerpenZoekenGui.this.dlm.addElement(m);

				}



				VerlorenVoorwerpenZoekenGui.this.list.setModel(VerlorenVoorwerpenZoekenGui.this.dlm);

			}

		});



		this.btnBewerken = new JButton("gevonden");
		btnBewerken.setFont(new Font("Dialog", Font.BOLD, 20));

		this.btnBewerken.addActionListener(new MenuItemHandler());



		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblStation)
							.addGap(32)
							.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBewerken, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVerlorenVoorwerpenZoeken, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
					.addGap(32))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVerlorenVoorwerpenZoeken)
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(stationLijst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation)
						.addComponent(btnBewerken))
					.addGap(30)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		this.setLayout(groupLayout);

	}



	public void close() {

		this.setVisible(false);

	}



	private class MenuItemHandler implements ActionListener {



		@Override

		public void actionPerformed(ActionEvent e) {



			if (e.getSource() == VerlorenVoorwerpenZoekenGui.this.btnBewerken) {



				if (VerlorenVoorwerpenZoekenGui.this.list.getSelectedValue() == null) {

					JOptionPane.showMessageDialog(new JFrame(), "Geen verloren voorwerp aangeduid.");

				} else {



					VerlorenVoorwerpDAO.verlorenVoowerpUpdate(

							VerlorenVoorwerpDAO.getId(VerlorenVoorwerpenZoekenGui.this.list.getSelectedValue()));

					JOptionPane.showMessageDialog(new JFrame(), "Verloren voorwerp gevonden");

					AdminGui.setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());

				}

			}



		}

	}

}