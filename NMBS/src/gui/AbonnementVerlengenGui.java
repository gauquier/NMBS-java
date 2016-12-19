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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import dao.*;
import source.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class AbonnementVerlengenGui extends JPanel {
	private Klant klant;
	private JTextField txtDuur;
	private JLabel lblDatum3;
	private JButton btnOpslaan;
	private Abonnement doorgegevenAbonnement;
	private Calendar startdatum=Calendar.getInstance();
	private Calendar nieuweEinddatum=Calendar.getInstance();
	private Calendar verkoopdatum=Calendar.getInstance();
	private SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
	
	public AbonnementVerlengenGui(Abonnement abonnement) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		startdatum.add(Calendar.DAY_OF_MONTH, 1);
		nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
		
		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement verlengen");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		doorgegevenAbonnement=abonnement;
		
		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setForeground(Color.WHITE);
		
		txtDuur = new JTextField();
		txtDuur.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuur.setText("1");
		txtDuur.setColumns(10);
		txtDuur.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateDatum();
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateDatum();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
		
			}	
			
		});
		
		
		btnOpslaan = new JButton("Opslaan");
		btnOpslaan.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnOpslaan.setBackground(Color.ORANGE);
		btnOpslaan.addActionListener(new MenuItemHandler());
		
		

		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setForeground(Color.WHITE);
		
		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setForeground(Color.WHITE);
		
		JLabel lblDatum1 = new JLabel("Niet van toepassing");
		lblDatum1.setForeground(Color.WHITE);
		lblDatum1.setText(formatDatum.format(startdatum.getTime()));
		
		JLabel lblDatum2 = new JLabel("Niet van toepassing");
		lblDatum2.setForeground(Color.WHITE);
		if(abonnement.getP()==null){
			lblDatum2.setText("Niet van toepassing");

		} else {
			lblDatum2.setText(formatDatum.format(PeriodeDAO.getPeriode(abonnement).getEndDate()));
			//startdatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getStartDate());
			//nieuweEinddatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getEndDate());
		}
		
	
		JLabel lblMaanden = new JLabel("maand(en)");
		lblMaanden.setForeground(Color.WHITE);
		
		JLabel lblNieuweEinddatum = new JLabel("Nieuwe einddatum:");
		lblNieuweEinddatum.setForeground(Color.WHITE);
		
		
		nieuweEinddatum.add(Calendar.MONTH, 1);
		lblDatum3 = new JLabel(formatDatum.format(nieuweEinddatum.getTime()));
		lblDatum3.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(354, Short.MAX_VALUE)
					.addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbonnementAanmaken)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblEinddatum, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDuur, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblStartdatum, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGap(52)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(txtDuur, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblMaanden, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDatum2, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE))))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblDatum3, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(215, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbonnementAanmaken)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(306)
							.addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartdatum)
								.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEinddatum)
								.addComponent(lblDatum2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(71)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDuur)
								.addComponent(txtDuur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaanden))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDatum3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}

	public void close()
	{
		this.setVisible(false);
	}
	
	public void updateDatum(){
		 Runnable doHighlight = new Runnable() {
		        @Override
		        public void run() {
		           nieuweEinddatum = Calendar.getInstance();
		           if(Integer.parseInt(txtDuur.getText())<=0 || Integer.parseInt(txtDuur.getText()) > 12){
						JOptionPane.showMessageDialog(new JFrame(), "De duur kan niet kleiner dan 1 of groter dan 12 zijn.");
						txtDuur.setText(Integer.toString(1));
					}else{
		           nieuweEinddatum.add(Calendar.MONTH, Integer.parseInt(txtDuur.getText()));
		           nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
		           lblDatum3.setText(formatDatum.format(nieuweEinddatum.getTime()));
		          
		           
					}
		        }
		    };       
		    SwingUtilities.invokeLater(doHighlight);
		
		
	}
	
	private int OkCancel(String message){
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
	
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

		
			if (e.getSource() == btnOpslaan) {
			if(!txtDuur.getText().isEmpty()){
				if(Integer.parseInt(txtDuur.getText())<=0 || Integer.parseInt(txtDuur.getText()) > 12){
					JOptionPane.showMessageDialog(new JFrame(), "De duur kan niet kleiner dan/gelijk aan 0 zijn of groter dan 12 zijn.");
					
				}
				else {
					
				
				//System.out.println(startdatum.getTime());
				//System.out.println(nieuweEinddatum.getTime());
				Periode periode = new Periode(0, startdatum.getTime(), nieuweEinddatum.getTime(), verkoopdatum.getTime() );
				PeriodeDAO.addPeriode(periode, doorgegevenAbonnement, 1); // 1 moet vervangen worden door currentloginId
				
				JOptionPane.showMessageDialog(new JFrame(), "Periode is toegevoegd!");
				AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
				
				}
				}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
			}		
		

		
			}
		
		}
}
}
