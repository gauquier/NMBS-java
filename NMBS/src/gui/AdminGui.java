package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import handler.Controller;

public class AdminGui extends JFrame {
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenu home, verkoop, routeInfo, stationInfo, verlorenVoorwerpen, instellingen;
    JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen;

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
		home = new JMenu("Home");
		verkoop = new JMenu("Verkoop");
		routeInfo = new JMenu("Route info");
		stationInfo = new JMenu("Station info");
		instellingen = new JMenu("Instellingen");
		
		abonnementBeheer = new JMenuItem("Abbonement beheer");
		abonnementVerkoop = new JMenuItem("Abbonement verkoop");
		verkoop.add(abonnementVerkoop);
		verkoop.add(abonnementBeheer);
		
		uitloggen = new JMenuItem("Uitloggen");
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(routeInfo);
		menubar.add(stationInfo);
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
	
}