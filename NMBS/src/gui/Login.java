package gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class Login {
	private JLabel username;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("NMBS");
		centreWindow(frame);
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 10, 80, 25);
		panel.add(lblUsername);

		JTextField txtUsername = new JTextField(20);
		txtUsername.setBounds(100, 10, 160, 25);
		panel.add(txtUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 40, 80, 25);
		panel.add(lblPassword);

		JPasswordField txtPassword = new JPasswordField(20);
		txtPassword.setBounds(100, 40, 160, 25);
		panel.add(txtPassword);

		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(100, 81, 80, 25);
		panel.add(btnLogin);
		
	}
}
