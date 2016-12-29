package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.AbonnementDAO;
import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PeriodeDAO;
import dao.PrijsDAO;
import source.Abonnement;
import source.Login;
import source.Periode;
import source.VerkoopType;


public class AbonnementVerlengenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6560637230362797041L;
	private JTextField txtDuur;
	private JLabel lblNieuweEinddatumValue;
	private JLabel lblNieuwePrijsValue;
	private JButton btnOpslaan;
	private Abonnement doorgegevenAbonnement;
	private Calendar startdatum=Calendar.getInstance();
	private Calendar nieuweEinddatum=Calendar.getInstance();
	private Calendar verkoopdatum=Calendar.getInstance();
	private double nieuwePrijs;
	private SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
	private int verschilInJaren, verschilInMaanden;
	
	public AbonnementVerlengenGui(Abonnement abonnement) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		doorgegevenAbonnement=abonnement;
		if(doorgegevenAbonnement.getP()!=null){
		verschilInJaren = doorgegevenAbonnement.getP().getEndDate().getYear()-doorgegevenAbonnement.getP().getStartDate().getYear();
		verschilInMaanden = verschilInJaren*12+ doorgegevenAbonnement.getP().getEndDate().getMonth() - Calendar.getInstance().getTime().getMonth();
		}
		
		updateDatum();
		updatePrijs();
		
		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement verlengen");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStartdatum.setForeground(Color.WHITE);
		
		txtDuur = new JTextField();
		txtDuur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtDuur.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuur.setText("1");
		txtDuur.setColumns(10);
		txtDuur.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateDatum();
				updatePrijs();
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateDatum();
				updatePrijs();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
		
			}	
			
		});
		
		
		btnOpslaan = new JButton("Opslaan");
		btnOpslaan.setFont(new Font("Dialog", Font.BOLD, 20));
		btnOpslaan.setBackground(Color.ORANGE);
		btnOpslaan.addActionListener(new MenuItemHandler());
		
		

		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEinddatum.setForeground(Color.WHITE);
		
		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDuur.setForeground(Color.WHITE);
		
		JLabel lblDatum1 = new JLabel("Niet van toepassing");
		lblDatum1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum1.setForeground(Color.WHITE);
		lblDatum1.setText(formatDatum.format(startdatum.getTime()));
		
		JLabel lblDatum2 = new JLabel("Niet van toepassing");
		lblDatum2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum2.setForeground(Color.WHITE);
		
		JLabel lblMaanden = new JLabel("maand(en)");
		lblMaanden.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblMaanden.setForeground(Color.WHITE);
		
		JLabel lblNieuweEinddatum = new JLabel("Nieuwe einddatum:");
		lblNieuweEinddatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuweEinddatum.setForeground(Color.WHITE);
		nieuweEinddatum.add(Calendar.MONTH, 1);
		
		
		lblNieuweEinddatumValue = new JLabel(formatDatum.format(nieuweEinddatum.getTime()));
		lblNieuweEinddatumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuweEinddatumValue.setForeground(Color.WHITE);
		
		JLabel lblBedrag = new JLabel("Prijs:");
		lblBedrag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBedrag.setForeground(Color.WHITE);
		
		JLabel lblPrijsValue = new JLabel("Niet van toepassing");
		lblPrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijsValue.setForeground(Color.WHITE);
		
		JLabel lblNieuwePrijs = new JLabel("Nieuwe prijs:");
		lblNieuwePrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuwePrijs.setForeground(Color.WHITE);
		
		lblNieuwePrijsValue = new JLabel("");
		lblNieuwePrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuwePrijsValue.setForeground(Color.WHITE);
		
		if(abonnement.getP()==null){
			startdatum.add(Calendar.DAY_OF_MONTH, 1);
			nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
			lblDatum1.setText(formatDatum.format(startdatum.getTime()));
			lblDatum2.setText("Niet van toepassing");
			lblPrijsValue.setText("Niet van toepassing");
			lblNieuweEinddatumValue.setText(formatDatum.format(nieuweEinddatum.getInstance().getTime()));
			lblNieuwePrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)));
		} else {
			lblDatum1.setText(formatDatum.format(doorgegevenAbonnement.getP().getStartDate()));
			lblDatum2.setText(formatDatum.format(doorgegevenAbonnement.getP().getEndDate()));
			startdatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getStartDate());
			nieuweEinddatum.setTime(doorgegevenAbonnement.getP().getEndDate());
			lblPrijsValue.setText(Double.toString(doorgegevenAbonnement.getPrijs()) + " euro");
		}
		
	
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbonnementAanmaken)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartdatum)
								.addComponent(lblEinddatum, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBedrag, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDuur, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDuur, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblMaanden, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDatum2, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
									.addComponent(lblPrijsValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNieuwePrijs)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNieuwePrijsValue, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNieuweEinddatumValue, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbonnementAanmaken)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartdatum)
						.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEinddatum)
						.addComponent(lblDatum2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBedrag))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDuur)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtDuur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMaanden)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNieuweEinddatumValue, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNieuwePrijsValue, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNieuwePrijs, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(122, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}

	public void close()
	{
		this.setVisible(false);
	}
	
	public void updatePrijs(){
		 Runnable doHighlight = new Runnable() {
		        @Override
		        public void run() {
		        	if(doorgegevenAbonnement.getP()==null){
		        		nieuwePrijs=(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))* Integer.parseInt(txtDuur.getText());
		        	} else {
		        		nieuwePrijs=(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))* (Integer.parseInt(txtDuur.getText()) + verschilInMaanden);
		        		
		        	}
		        	 lblNieuwePrijsValue.setText(Double.toString(nieuwePrijs));
		        }
		    };       
		    SwingUtilities.invokeLater(doHighlight);
		
		
	}
	
	public void updateDatum(){
		 Runnable doHighlight = new Runnable() {
		        @Override
		        public void run() {
		        	if(doorgegevenAbonnement.getP()==null){
		        	nieuweEinddatum = Calendar.getInstance();	
		        	nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
		        	} else {
		        		nieuweEinddatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getEndDate());
		        	}
		        	
		           if(Integer.parseInt(txtDuur.getText())<=0 || Integer.parseInt(txtDuur.getText()) > 12){
						JOptionPane.showMessageDialog(new JFrame(), "De duur kan niet kleiner dan 1 of groter dan 12 zijn.");
						txtDuur.setText(Integer.toString(1));
					}else{
		           nieuweEinddatum.add(Calendar.MONTH, Integer.parseInt(txtDuur.getText()));
		           
		           lblNieuweEinddatumValue.setText(formatDatum.format(nieuweEinddatum.getTime()));
		          
		           
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
	
	private boolean limietVerlengingBereikt(){
		if((Integer.parseInt(txtDuur.getText()) + verschilInMaanden)>=12){
			return true;
		} else {
			return false;
		}
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();
			int huidigeMedewerkerId = MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser())).getMedewerkerId();
		
			if (e.getSource() == btnOpslaan) {
			if(!txtDuur.getText().isEmpty()){
				if(Integer.parseInt(txtDuur.getText())<=0 || Integer.parseInt(txtDuur.getText()) > 12){
					JOptionPane.showMessageDialog(new JFrame(), "De duur kan niet kleiner dan/gelijk aan 0 zijn of groter dan 12 zijn.");
					
				}
				else {
				
				Periode periode;
				
				
				if(doorgegevenAbonnement.getP()==null){
				periode = new Periode(0, startdatum.getTime(), nieuweEinddatum.getTime(), verkoopdatum.getTime() );
				
				AbonnementDAO.updatePrijs(doorgegevenAbonnement, nieuwePrijs);
				PeriodeDAO.addPeriode(periode, doorgegevenAbonnement, huidigeMedewerkerId); 
				JOptionPane.showMessageDialog(new JFrame(), "Het abonnement wordt actief binnen 24 uur.");
				
				
				}
				else {
				
					
				if(limietVerlengingBereikt()){
					int n = OkCancel("U bent van plan de limiet van 12 maanden te overschrijden. Bent u zeker dat u wilt doorgaan?");
					if(n>0){
						return;
					}
				
				} 
				
				periode = new Periode(doorgegevenAbonnement.getP().getPeriodeId(), nieuweEinddatum.getTime());
				
				AbonnementDAO.updatePrijs(doorgegevenAbonnement, nieuwePrijs);
				PeriodeDAO.updatePeriode(periode, huidigeMedewerkerId);
				JOptionPane.showMessageDialog(new JFrame(), "Het abonnement is verlengd.");
				
				
				}
				
				
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
