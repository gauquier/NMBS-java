package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class HomeGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2564170957766548127L;

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
		setBackground(new Color(0, 191, 255));

		jPanel2.add(jPanel);
		add(jPanel);
		
		GroupLayout layout = new GroupLayout(jPanel);
		jPanel.setLayout(layout);		
	}
}
