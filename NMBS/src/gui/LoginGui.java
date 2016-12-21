package gui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.CheckIfConnected;
import dao.Connection;
import dao.LoginDao;
import handler.Controller;
import source.Login;
import source.Medewerker;
import source.Rol;
import javax.swing.UIManager;
import com.apple.eawt.Application;

import Hashing.DualHash;

import java.awt.Color;
import java.awt.Font;

public class LoginGui {

	JFrame frmNmbs;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	public static LoginGui window;
	public static Login login;
	private static ResourceBundle bundle;

	public LoginGui() {
		initialize();
	}
	
	public static Login getLogin(){
		return login;
	}
	
	public static void start() {
		//v system look and feel (i.p.v. niet-zo-mooie java look and feel) Source: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		//^ code system look and feel
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
		bundle = ResourceBundle.getBundle("localization.LoginGui");
		
		frmNmbs = new JFrame();
		frmNmbs.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		Application.getApplication().setDockIconImage(new ImageIcon("NMBS/lib/logo.png").getImage());
		frmNmbs.setTitle("NMBS");
		frmNmbs.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		frmNmbs.setBounds(0, 0, 450, 300);
		frmNmbs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNmbs.getContentPane().setLayout(null);
		frmNmbs.setResizable(false);
		centreWindow(frmNmbs);

		btnLogin = new JButton(bundle.getString("login"));
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnLogin.setBounds(234, 196, 149, 38);
		frmNmbs.getContentPane().add(btnLogin);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtUsername.setBounds(234, 96, 149, 26);
		frmNmbs.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblUser = new JLabel(bundle.getString("user"));
		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(41, 90, 181, 38);
		frmNmbs.getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel(bundle.getString("pass"));
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(41, 140, 181, 38);
		frmNmbs.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPassword.setBounds(234, 146, 149, 26);
		frmNmbs.getContentPane().add(txtPassword);
		btnLogin.addActionListener(new ButtonHandler());
		txtPassword.addActionListener(new ButtonHandler());

	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!(CheckIfConnected.checkIfConnected()))//als er geen internetverbinding is
			{
				closeFrame();
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
			if (e.getSource() == btnLogin || e.getSource() == txtPassword) {

				String username = txtUsername.getText().trim();
				String password = new String(txtPassword.getPassword());
				
				String databaseUsername = LoginDao.getUserName(username);
				
				if (databaseUsername == null) {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userNoAllow"));
					return;
				}
				if (databaseUsername != null) {
					if (databaseUsername.equals(username)) {
						String databasePassword = LoginDao.getWachtwoord(username);
						if(databasePassword!= null){
							try {
								if(databasePassword.equals(DualHash.hashString(password))){
									int loginId = LoginDao.getLoginId(username);
									if(LoginDao.getActief(loginId) == 1){
										login = new Login(loginId, username, "");
										Login.setCurrentUser(username);

										closeFrame();
										KiesStationGui.start();
									}else{
										JOptionPane.showMessageDialog(new JFrame(), bundle.getString("employeeNoAllow"));
									}
									
								}else {
									JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCredentials"));
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
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
