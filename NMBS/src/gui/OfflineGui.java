package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import handler.Controller;
import handler.VerkoopController;
import source.Klant;
import source.Medewerker;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

/*import dao.AdresDAO;
import dao.KlantDAO;
import dao.MedewerkerDAO;*/

import java.awt.Toolkit;
import java.awt.Font;

public class OfflineGui extends JFrame {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.OfflineGui");
	
	private Container c = getContentPane();	
	
	private static JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home;
    JMenu verkoop, instellingen;
    JMenuItem uitloggen, ticketVerkoop;

	public static JPanel getHuidigeKeuze() {
		return HuidigeKeuze;
	}
	
	public static void setHuidigeKeuze(JPanel huidigeKeuze) {
		
		if (HuidigeKeuze!=null){HuidigeKeuze.setVisible(false);}
		vorigeKeuze= HuidigeKeuze;
		HuidigeKeuze = huidigeKeuze;
		if (HuidigeKeuze!=null)
		{
		HuidigeKeuze.setVisible(true);
		Controller.offlineInterface.getContentPane().add(getHuidigeKeuze());
		}
		else {
		Controller.offlineInterface.dispose();
		Controller.offlineInterface.setVisible(false);
		LoginGui.start();
		}
	}

	public void setHome(){
		navigation= "home";
		setHuidigeKeuze(new HomeGui(true));
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}
	public OfflineGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/jonas/Desktop/NMBS-java/NMBS/lib/logo-nmbs.png"));
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setResizable(true);
		menuGUI();		
	}
	private void menuGUI() {
		createMenu();
		setTitle("NMBS");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenu() {
		menubar = new JMenuBar();
		menubar.setForeground(Color.BLACK);
		menubar.setBackground(Color.ORANGE);
		home = new JMenuItem(bundle.getString("home"));

		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setFont(new Font("Segoe UI", Font.BOLD, 14));
		home.setHorizontalTextPosition(SwingConstants.CENTER);;
		home.setBackground(Color.ORANGE);
		home.setOpaque(true);
		home.addActionListener(new MenuItemHandler());
		
		verkoop = new JMenu(bundle.getString("verkoop"));
		verkoop.setHorizontalAlignment(SwingConstants.CENTER);
		verkoop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verkoop.setBackground(Color.ORANGE);
		verkoop.setOpaque(true);
		
		instellingen = new JMenu(bundle.getString("instellingen"));
		instellingen.setHorizontalAlignment(SwingConstants.CENTER);
		instellingen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		instellingen.setBackground(Color.ORANGE);
		instellingen.setOpaque(true);
		
		ticketVerkoop = new JMenuItem(bundle.getString("ticketVerkoop"));
		ticketVerkoop.setBackground(Color.WHITE);
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem(bundle.getString("uitloggen"));
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(instellingen);
		setJMenuBar(menubar);
	}
	
	public static void Home()
	{
		 Thread t1 = new Thread(new Runnable() {
			    public void run()
			    {
			    Controller.adminInterface.setHome();     
			    }});  
			    t1.start();
	}
	
	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==uitloggen)
			{
				setHuidigeKeuze(null);
			}
			else if (e.getSource() == home){

				setHuidigeKeuze(new HomeGui(true));
			}
			else if (e.getSource() == ticketVerkoop){
				setHuidigeKeuze(new TicketVerkoopGui(true));
			}
		
		}
	}
	
}