package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeGui extends JPanel {
	private JPanel jPanel, jPanel2;

	ImageIcon photo;
	WritableRaster raster;
	DataBufferByte data;
	File image;
	
	public HomeGui(){
		initialize();
	}
	
	
	private void initialize() {
		
		jPanel = new JPanel();		
		jPanel2 = new JPanel();		
		
		jPanel2.setLayout(new BorderLayout());
		
		
		jPanel2.add(jPanel);
		add(jPanel);
		
		GroupLayout layout = new GroupLayout(jPanel);
		jPanel.setLayout(layout);
		
	}
}
