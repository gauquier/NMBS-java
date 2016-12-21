package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import com.apple.eawt.Application;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.LoginDao;
import dao.StationDAO;
import handler.Controller;
import source.Login;
import source.Station;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class KiesStationGui {
	
	JFrame frmStation;
	public static KiesStationGui window;
	private JButton btnSelecteer;
	private JComboBox cmbbStation;
	private Station station;
	Login login;
	
	public KiesStationGui() {
		initialize();
	}
	
	public static void start() {
		window = new KiesStationGui();
		window.frmStation.setVisible(true);
	}
	
	public void closeFrame() {
		window.frmStation.dispose();
	}
	
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {		
		frmStation = new JFrame();
		frmStation.setTitle("NMBS");
		frmStation.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		Application.getApplication().setDockIconImage(new ImageIcon("NMBS/lib/logo.png").getImage());
		frmStation.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		frmStation.setBounds(0, 0, 450, 300);
		frmStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStation.getContentPane().setLayout(null);
		frmStation.setResizable(false);
		centreWindow(frmStation);
		
		cmbbStation = new JComboBox();
		cmbbStation.addItem("Hoofdbureau");
		StationDAO stationDAO=new StationDAO(); 
		for(Station station:stationDAO.getStationsLazyLoading()){
			cmbbStation.addItem(station.getNaam());
		}
		
		JLabel lblKiesUwHuidig = DefaultComponentFactory.getInstance().createTitle("Kies uw huidig station");
		
		JLabel lblSelecteerUwHuidig = new JLabel("Selecteer uw huidig station");
		lblSelecteerUwHuidig.setForeground(Color.WHITE);
		
		btnSelecteer = new JButton("Selecteer");
		btnSelecteer.addActionListener(new MenuItemHandler());
		GroupLayout groupLayout = new GroupLayout(frmStation.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblKiesUwHuidig)
					.addContainerGap(299, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(lblSelecteerUwHuidig)
					.addContainerGap(140, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(175)
					.addComponent(btnSelecteer)
					.addContainerGap(175, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblKiesUwHuidig)
					.addGap(55)
					.addComponent(lblSelecteerUwHuidig)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSelecteer)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		frmStation.getContentPane().setLayout(groupLayout);
		

	}
	
	private class MenuItemHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSelecteer){
				String username = Login.getCurrentUser();
				int loginId = LoginDao.getLoginId(username);
				int rollId = LoginDao.getRoll(loginId);
				String currentStation = (String) cmbbStation.getSelectedItem();
				station = new Station(currentStation);
				if(rollId == 1){
					closeFrame();
					Controller.adminInterface = new AdminGui();
					Controller.adminInterface.setHome();
				 }
				 else if(rollId == 2){
					closeFrame();
					Controller.medewerkerInterface = new MedewerkerGui();
					Controller.medewerkerInterface.setHome();
				} 
			}
		}
	}
}
