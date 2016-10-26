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

public class LoginGui {

	JFrame frame;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	public static LoginGui window;

	public LoginGui() {
		initialize();
	}

	public static void start() {
		window = new LoginGui();
		window.frame.setVisible(true);
	}

	public void closeFrame() {
		window.frame.dispose();
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		centreWindow(frame);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(208, 200, 116, 25);
		frame.getContentPane().add(btnLogin);

		txtUsername = new JTextField();
		txtUsername.setBounds(208, 100, 116, 22);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(116, 90, 84, 38);
		frame.getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(116, 140, 84, 38);
		frame.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(208, 150, 116, 22);
		frame.getContentPane().add(txtPassword);
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

						// int rollId = LoginDao.getRoll(username);

						// Login.setUsername(username);
						System.out.println("User " + username + " is aangelogd.");

						closeFrame();
						Controller.adminInterface = new AdminGui();
						Controller.adminInterface.setHome();

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
