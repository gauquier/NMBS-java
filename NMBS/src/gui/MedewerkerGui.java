package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import handler.Controller;

import dao.Connection;

import java.awt.Toolkit;

import javax.swing.UIManager;

import com.apple.eawt.Application;

public class MedewerkerGui  extends JFrame {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.MedewerkerGui");
	
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home , routeInfo, stationInfo;
    JMenu verkoop, verlorenVoorwerpen, instellingen;
    JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen, ticketVerkoop, verlorenVoorwerpToevoegen, verlorenVoorwerpZoeken, wachtwoordVeranderen;
    
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
		Controller.medewerkerInterface.getContentPane().add(getHuidigeKeuze());
		}
		else {
		Controller.medewerkerInterface.dispose();
		Controller.medewerkerInterface.setVisible(false);
		LoginGui.start();
		}
	}
	
	public void setHome(){
		navigation= "home";
		setHuidigeKeuze(new HomeGui(false));
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}
	
	MedewerkerGui(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		Application.getApplication().setDockIconImage(new ImageIcon("NMBS/lib/logo.png").getImage());
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setBackground(new Color(0, 191, 255));
		this.setResizable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setMinimumSize(new Dimension(850, 550));
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
		home.setBackground(Color.ORANGE);
		home.setFont(new Font("Segoe UI", Font.BOLD, 14));
		home.setOpaque(true);
		home.addActionListener(new MenuItemHandler());
		
		verkoop = new JMenu(bundle.getString("verkoop"));
		verkoop.setBackground(Color.ORANGE);
		verkoop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verkoop.setOpaque(true);
		
		routeInfo = new JMenuItem(bundle.getString("routeInfo"));
		routeInfo.setBackground(Color.ORANGE);
		routeInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		routeInfo.setOpaque(true);
		routeInfo.addActionListener(new MenuItemHandler());
		
		stationInfo = new JMenuItem(bundle.getString("stationInfo"));
		stationInfo.setBackground(Color.ORANGE);
		stationInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		stationInfo.setOpaque(true);
		stationInfo.addActionListener(new MenuItemHandler());
		
		verlorenVoorwerpen = new JMenu(bundle.getString("verlorenVoorwerpen"));
		verlorenVoorwerpen.setBackground(Color.ORANGE);
		verlorenVoorwerpen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verlorenVoorwerpen.setOpaque(true);
		
		verlorenVoorwerpToevoegen = new JMenuItem(bundle.getString("verlorenVoorwerpToevoegen"));
		verlorenVoorwerpToevoegen.setBackground(Color.ORANGE);
		verlorenVoorwerpToevoegen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verlorenVoorwerpToevoegen.setOpaque(true);
		verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());
		
		verlorenVoorwerpZoeken = new JMenuItem(bundle.getString("verlorenVoorwerpZoeken"));
		verlorenVoorwerpZoeken.setBackground(Color.ORANGE);
		verlorenVoorwerpZoeken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verlorenVoorwerpZoeken.setOpaque(true);
		verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());
		
		verlorenVoorwerpen.add(verlorenVoorwerpToevoegen);
		verlorenVoorwerpen.add(verlorenVoorwerpZoeken);
		
		instellingen = new JMenu("instellingen");
		instellingen.setBackground(Color.ORANGE);
		instellingen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		instellingen.setOpaque(true);
		
		wachtwoordVeranderen = new JMenuItem(bundle.getString("wachtwoordVeranderen"));
		wachtwoordVeranderen.setBackground(Color.ORANGE);
		wachtwoordVeranderen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		wachtwoordVeranderen.setOpaque(true);
		wachtwoordVeranderen.addActionListener(new MenuItemHandler());
		instellingen.add(wachtwoordVeranderen);
		
		abonnementBeheer = new JMenuItem(bundle.getString("abonnementBeheer"));
		abonnementBeheer.setBackground(Color.ORANGE);
		abonnementBeheer.setFont(new Font("Segoe UI", Font.BOLD, 14));
		abonnementBeheer.setOpaque(true);
		abonnementVerkoop = new JMenuItem(bundle.getString("abonnementVerkoop"));
		abonnementVerkoop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		abonnementVerkoop.setBackground(Color.ORANGE);
		abonnementVerkoop.setOpaque(true);
		verkoop.add(abonnementVerkoop);
		verkoop.add(abonnementBeheer);
		
		ticketVerkoop = new JMenuItem(bundle.getString("ticketVerkoop"));
		ticketVerkoop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		ticketVerkoop.setBackground(Color.ORANGE);
		ticketVerkoop.setOpaque(true);
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem(bundle.getString("uitloggen"));
		uitloggen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		uitloggen.setBackground(Color.ORANGE);
		uitloggen.setOpaque(true);
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(routeInfo);
		menubar.add(stationInfo);
		menubar.add(verlorenVoorwerpen);
		menubar.add(instellingen);
		setJMenuBar(menubar);
	}
	
	public static void Home()
	{
		 Thread t1 = new Thread(new Runnable() {
			    public void run()
			    {
			    Controller.medewerkerInterface.setHome();     
			    }});  
			    t1.start();
	}
	
	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==uitloggen)
			{
				Connection.close();
				setHuidigeKeuze(null);
				LoginGui.start();
			}
			else if (e.getSource() == home){
				setHuidigeKeuze(new HomeGui(false));
			}
			else if (e.getSource() == ticketVerkoop){
				setHuidigeKeuze(new TicketVerkoopGui(false));
			}
			else if (e.getSource() == routeInfo){
				setHuidigeKeuze(new RouteZoekenGui());
			}
			else if (e.getSource() == stationInfo){
				setHuidigeKeuze(new StationInfoGui());
			}
			else if (e.getSource() == verlorenVoorwerpZoeken){
				setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());
			}
			else if (e.getSource() == verlorenVoorwerpToevoegen){
				setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
			}
			else if (e.getSource() == wachtwoordVeranderen){
				setHuidigeKeuze(new WachtwoordVeranderenGui());
			}
		}
	}
}
