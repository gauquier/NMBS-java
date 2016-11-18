package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import source.VerlorenVoorwerp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.VerlorenVoorwerpDAO;

public class VerlorenVoorwerpenToevoegenGui extends JPanel{
	
	private JTextField txtDatum;
	private JButton btnToevoegen;
	JTextArea txtrBeschrijving;
	VerlorenVoorwerp verlorenVoorwerp;
	private JTextField txtStation;
	
	public VerlorenVoorwerpenToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblBeschrijving = new JLabel("Beschrijving");
		lblBeschrijving.setForeground(Color.WHITE);
		
		JTextArea txtrBeschrijving = new JTextArea();
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		
		JButton btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addActionListener(new MenuItemHandler());
		
		JLabel lblStation = new JLabel("Station");	
		lblStation.setForeground(Color.WHITE);
		
		txtStation = new JTextField();
		txtStation.setColumns(10);
		
		JLabel label = DefaultComponentFactory.getInstance().createTitle("Verloren voorwerp toevoegen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblBeschrijving)
							.addComponent(lblDatum)
							.addComponent(lblStation)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnToevoegen)
						.addComponent(txtStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(label)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBeschrijving)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatum))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStation))
					.addGap(18)
					.addComponent(btnToevoegen)
					.addGap(28))
		);
		setLayout(groupLayout);
	}
	
	public void close()
	{
		this.setVisible(false);
	}
	
	private class MenuItemHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnToevoegen){
				if(!txtrBeschrijving.getText().isEmpty() && !txtDatum.getText().isEmpty()){
					boolean gevonden = false;
					String startDateString = txtDatum.getText();
		            DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		            Date startDate = null;
					try {
						startDate = df.parse(startDateString);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					verlorenVoorwerp = new VerlorenVoorwerp(txtStation.getText().trim(), txtrBeschrijving.getText().trim(), (java.sql.Date) startDate ,gevonden);
					VerlorenVoorwerpDAO.insertVerlorenVoorwerp(verlorenVoorwerp);
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
		}		    	
    }
}
