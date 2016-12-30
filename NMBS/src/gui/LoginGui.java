package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Hashing.DualHash;
import dao.CheckIfConnected;
import dao.Connection;
import dao.LoginDao;
import handler.Controller;
import source.Login;

public class LoginGui {

	private JFrame frmNmbs;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	private static LoginGui window;
	private static Login login;
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.LoginGui");

	public LoginGui() {
		this.initialize();
	}

	public static void start() {
		// v system look and feel (i.p.v. niet-zo-mooie java look and feel)
		// Source:
		// https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		// ^ code system look and feel
		window = new LoginGui();
		window.frmNmbs.setVisible(true);
	}

	public void closeFrame() {
		window.frmNmbs.dispose();
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	private void initialize() {
		

		this.frmNmbs = new JFrame();
		this.frmNmbs.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		this.frmNmbs.setTitle("NMBS");
		this.frmNmbs.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		this.frmNmbs.setBounds(0, 0, 450, 300);
		this.frmNmbs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmNmbs.getContentPane().setLayout(null);
		this.frmNmbs.setResizable(false);
		LoginGui.centreWindow(this.frmNmbs);

		this.btnLogin = new JButton(bundle.getString("login"));
		this.btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.btnLogin.setBounds(234, 196, 149, 38);
		this.frmNmbs.getContentPane().add(this.btnLogin);

		this.txtUsername = new JTextField();
		this.txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtUsername.setBounds(234, 96, 149, 26);
		this.frmNmbs.getContentPane().add(this.txtUsername);
		this.txtUsername.setColumns(10);

		JLabel lblUser = new JLabel(bundle.getString("user"));
		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(41, 90, 181, 38);
		this.frmNmbs.getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel(bundle.getString("pass"));
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(41, 140, 181, 38);
		this.frmNmbs.getContentPane().add(lblPassword);

		this.txtPassword = new JPasswordField();
		this.txtPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPassword.setBounds(234, 146, 149, 26);
		this.frmNmbs.getContentPane().add(this.txtPassword);
		this.btnLogin.addActionListener(new ButtonHandler());
		this.txtPassword.addActionListener(new ButtonHandler());

	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!(CheckIfConnected.checkIfConnected()))// als er geen
														// internetverbinding is
			{
				LoginGui.this.closeFrame();
				JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noInternet"));

				JOptionPane.showMessageDialog(new JFrame(), bundle.getString("offlineStart"));

				Controller.offlineInterface = new OfflineGui();
				Controller.offlineInterface.setHome();

				return;
			}

			if (!Connection.checkDBConnection()) {
				JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noDB"));

				JOptionPane.showMessageDialog(new JFrame(), bundle.getString("offlineStart"));

				Controller.offlineInterface = new OfflineGui();
				Controller.offlineInterface.setHome();

				return;
			}
			if (e.getSource() == LoginGui.this.btnLogin || e.getSource() == LoginGui.this.txtPassword) {

				String username = LoginGui.this.txtUsername.getText().trim();
				String password = new String(LoginGui.this.txtPassword.getPassword());

				String databaseUsername = LoginDao.getUserName(username);

				if (databaseUsername == null) {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userNoAllow"));
					return;
				}
				if (databaseUsername != null) {
					if (databaseUsername.equals(username)) {
						String databasePassword = LoginDao.getWachtwoord(username);
						if (databasePassword != null) {
							try {
								if (databasePassword.equals(DualHash.hashString(password))) {
									int loginId = LoginDao.getLoginId(username);
									if (LoginDao.getActief(loginId) == 1) {
										login = new Login(loginId, username, "");
										Login.setCurrentUser(username);

										LoginGui.this.closeFrame();
										KiesStationGui.start();
									} else {
										JOptionPane.showMessageDialog(new JFrame(),
												bundle.getString("employeeNoAllow"));
									}

								} else {
									JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCredentials"));
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCredentials"));
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCredentials"));
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCredentials"));
				}
			}
		}
	}
}
