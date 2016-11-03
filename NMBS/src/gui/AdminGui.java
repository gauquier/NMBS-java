package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class AdminGui extends JFrame {
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home , routeInfo, stationInfo;
    JMenu verkoop, verlorenVoorwerpen, instellingen, gebruikers;
    JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen, gebruikersToevoegen, gebruikersVerwijderen;

	public JPanel getHuidigeKeuze() {
		return HuidigeKeuze;
	}
	
	public void setHuidigeKeuze(JPanel huidigeKeuze) {
		
		if (HuidigeKeuze!=null){HuidigeKeuze.setVisible(false);}
		this.vorigeKeuze= this.HuidigeKeuze;
		HuidigeKeuze = huidigeKeuze;
		if (HuidigeKeuze!=null)
		{
		HuidigeKeuze.setVisible(true);
		Controller.adminInterface.getContentPane().add(getHuidigeKeuze());
		}
		else {
		Controller.adminInterface.dispose();
		Controller.adminInterface.setVisible(false);
		LoginGui.start();
		}
		}

	public void setHome(){
		navigation= "home";
		setHuidigeKeuze(new HomeGui());
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}
	public AdminGui() {
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
		home = new JMenuItem("Home");
		home.addActionListener(new MenuItemHandler());
		verkoop = new JMenu("Verkoop");
		routeInfo = new JMenuItem("Route info");
		stationInfo = new JMenuItem("Station info");
		gebruikers = new JMenu("Gebruikers");
		instellingen = new JMenu("Instellingen");
		
		abonnementBeheer = new JMenuItem("Abbonement beheer");
		abonnementVerkoop = new JMenuItem("Abbonement verkoop");
		verkoop.add(abonnementVerkoop);
		verkoop.add(abonnementBeheer);
		
		gebruikersToevoegen = new JMenuItem("Gebruiker toevoegen");
		gebruikersToevoegen.addActionListener(new MenuItemHandler());
		gebruikersVerwijderen = new JMenuItem("Gebruiker verwijderen");
		gebruikersVerwijderen.addActionListener(new MenuItemHandler());
		gebruikers.add(gebruikersToevoegen);
		gebruikers.add(gebruikersVerwijderen);
		
		uitloggen = new JMenuItem("Uitloggen");
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(routeInfo);
		menubar.add(stationInfo);
		menubar.add(gebruikers);
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
			if (e.getSource() == gebruikersToevoegen) {	
				navigation= "gebruikerToevoegen";
				setHuidigeKeuze(new GebruikerToevoegenGui());
			}
			else if (e.getSource() == gebruikersVerwijderen) {	
				navigation= "gebruikersVerwijderen";
				setHuidigeKeuze(new GebruikerVerwijderenGui());
			}
			else if (e.getSource()==uitloggen)
			{
				setHuidigeKeuze(null);
				LoginGui.start();
			}
			else if (e.getSource() == home){
				setHuidigeKeuze(new HomeGui());
			}
		}
	}
	
}