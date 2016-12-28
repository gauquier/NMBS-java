package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.LoginDao;
import dao.StationDAO;
import handler.Controller;
import source.AutoComboBox;
import source.Login;
import source.Station;

public class KiesStationGui {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.KiesStationGui");
	
	JFrame frmStation;
	public static KiesStationGui window;
	private JButton btnSelecteer;
	private AutoComboBox cmbbStation;
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
		frmStation.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		frmStation.setBounds(0, 0, 450, 300);
		frmStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStation.getContentPane().setLayout(null);
		frmStation.setResizable(false);
		centreWindow(frmStation);
		
		cmbbStation = new AutoComboBox();
		cmbbStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		cmbbStation.addItem("Hoofdbureau");
		StationDAO stationDAO=new StationDAO(); 
		for(Station station:stationDAO.getStationsLazyLoading()){
			cmbbStation.addItem(station.getNaam());
		}
		
		JLabel lblKiesUwHuidig = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblKiesUwHuidig"));
		lblKiesUwHuidig.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		
		JLabel lblSelecteerUwHuidig = new JLabel(bundle.getString("lblSelecteerUwHuidig"));
		lblSelecteerUwHuidig.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSelecteerUwHuidig.setForeground(Color.WHITE);
		
		btnSelecteer = new JButton(bundle.getString("btnSelecteer"));
		btnSelecteer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnSelecteer.addActionListener(new MenuItemHandler());
		GroupLayout groupLayout = new GroupLayout(frmStation.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(lblKiesUwHuidig))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(161)
							.addComponent(btnSelecteer)))
					.addContainerGap(91, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(93)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cmbbStation, Alignment.LEADING, 0, 276, Short.MAX_VALUE)
						.addComponent(lblSelecteerUwHuidig, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
					.addGap(81))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblKiesUwHuidig)
					.addGap(55)
					.addComponent(lblSelecteerUwHuidig)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSelecteer)
					.addContainerGap(50, Short.MAX_VALUE))
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
