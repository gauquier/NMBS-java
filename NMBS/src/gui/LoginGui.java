package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDao;
import handler.Controller;
import source.Login;
import source.Medewerker;
import source.Rol;
import javax.swing.UIManager;
import java.awt.Color;

public class LoginGui {

	JFrame frmNmbs;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	public static LoginGui window;

	public LoginGui() {
		initialize();
	}

	public static void start() {
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
		frmNmbs = new JFrame();
		frmNmbs.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/jonas/Desktop/NMBS-java/NMBS/lib/logo-nmbs.png"));
		frmNmbs.setTitle("NMBS");
		frmNmbs.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		frmNmbs.setBounds(0, 0, 450, 300);
		frmNmbs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNmbs.getContentPane().setLayout(null);
		frmNmbs.setResizable(false);
		centreWindow(frmNmbs);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(208, 200, 116, 25);
		frmNmbs.getContentPane().add(btnLogin);

		txtUsername = new JTextField();
		txtUsername.setBounds(208, 100, 116, 22);
		frmNmbs.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(116, 90, 84, 38);
		frmNmbs.getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(116, 140, 84, 38);
		frmNmbs.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(208, 150, 116, 22);
		frmNmbs.getContentPane().add(txtPassword);
		btnLogin.addActionListener(new ButtonHandler());
		txtPassword.addActionListener(new ButtonHandler());

	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogin || e.getSource() == txtPassword) {

				String username = txtUsername.getText().trim();
				String password = new String(txtPassword.getPassword());

				String databasePassword = LoginDao.getWachtwoord(username);
				String databaseUsername = LoginDao.getUserName(username);
				if (databasePassword == null || databaseUsername == null) {
					JOptionPane.showMessageDialog(new JFrame(), "User is not allowed.");
					return;
				}
				if (databasePassword != null && databaseUsername != null) {
					if (databasePassword.equals(password) && databaseUsername.equals(username)) {

						int loginId = LoginDao.getLoginId(username);
						int rollId = LoginDao.getRoll(loginId);
						
						System.out.println(rollId);
						System.out.println("User " + username + " is aangelogd.");

						closeFrame();
						// if(Rol.getRolId()== 1){
							Controller.adminInterface = new AdminGui();
							Controller.adminInterface.setHome();
						// }
						/* else if(Rol.getRolId()== 2){
							Controller.medewerkerInterface = new MedewerkerGui();
							Controller.medewerkerInterface.setHome();
						} */
						

					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Username or password wrong.");
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Username or password wrong.");
				}
			}
		}
	}
}
