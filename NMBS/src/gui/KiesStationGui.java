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
import javax.swing.JOptionPane;
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

	private JFrame frmStation;
	private static KiesStationGui window;
	private JButton btnSelecteer;
	private AutoComboBox cmbbStation;
	private Station station;

	public KiesStationGui() {
		this.initialize();
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
		this.frmStation = new JFrame();
		this.frmStation.setTitle("NMBS");
		this.frmStation.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		this.frmStation.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.frmStation.setBounds(0, 0, 450, 300);
		this.frmStation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmStation.getContentPane().setLayout(null);
		this.frmStation.setResizable(false);
		KiesStationGui.centreWindow(this.frmStation);

		this.cmbbStation = new AutoComboBox();
		this.cmbbStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.cmbbStation.addItem("Hoofdbureau");
		StationDAO stationDAO = new StationDAO();
		for (Station station : stationDAO.getStationsLazyLoading()) {
			this.cmbbStation.addItem(station.getNaam());
		}

		JLabel lblKiesUwHuidig = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblKiesUwHuidig"));
		lblKiesUwHuidig.setFont(new Font("Lucida Grande", Font.BOLD, 20));

		JLabel lblSelecteerUwHuidig = new JLabel(bundle.getString("lblSelecteerUwHuidig"));
		lblSelecteerUwHuidig.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSelecteerUwHuidig.setForeground(Color.WHITE);

		this.btnSelecteer = new JButton(bundle.getString("btnSelecteer"));
		this.btnSelecteer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.btnSelecteer.addActionListener(new MenuItemHandler());
		GroupLayout groupLayout = new GroupLayout(this.frmStation.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(29).addComponent(lblKiesUwHuidig))
								.addGroup(groupLayout.createSequentialGroup().addGap(161)
										.addComponent(this.btnSelecteer)))
						.addContainerGap(91, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(93)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.cmbbStation, Alignment.LEADING, 0, 276, Short.MAX_VALUE)
								.addComponent(lblSelecteerUwHuidig, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 276,
										Short.MAX_VALUE))
						.addGap(81)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(lblKiesUwHuidig)
								.addGap(55).addComponent(lblSelecteerUwHuidig)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(this.cmbbStation, GroupLayout.PREFERRED_SIZE, 39,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(this.btnSelecteer).addContainerGap(50, Short.MAX_VALUE)));
		this.frmStation.getContentPane().setLayout(groupLayout);

	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == KiesStationGui.this.btnSelecteer) {
					if(StationDAO.checkStation((String) KiesStationGui.this.cmbbStation.getSelectedItem()) != 0){
						
					String username = Login.getCurrentUser();
					int loginId = LoginDao.getLoginId(username);
					int rollId = LoginDao.getRoll(loginId);
					String currentStation = (String) KiesStationGui.this.cmbbStation.getSelectedItem();
					KiesStationGui.this.station = new Station(currentStation);
					if (rollId == 1) {
						KiesStationGui.this.closeFrame();
						Controller.adminInterface = new AdminGui();
						Controller.adminInterface.setHome();
					} else if (rollId == 2) {
						KiesStationGui.this.closeFrame();
						Controller.medewerkerInterface = new MedewerkerGui();
						Controller.medewerkerInterface.setHome();
					}
				} else{
					JOptionPane.showMessageDialog(new JFrame(), "Dit station bestaat niet.");
					cmbbStation.setSelectedIndex(-1);
				}
			}
		}
	}
}
