package source;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import dao.TicketDao;


public class Pdf {

	public void TicketGenerator(int id) {
		
		//TicketDao tDao = new TicketDao();
		Ticket ticket = TicketDao.getTicket(id);
		
		Document document = new Document();
		Rectangle rectangle = new Rectangle(PageSize.A4);
		document.setPageSize(rectangle);
		
		Font[] fonts = {
				new Font(Font.FontFamily.COURIER,12,Font.BOLD),
				new Font(Font.FontFamily.COURIER,10),
				new Font(Font.FontFamily.COURIER,13)
		};
	
		try {
			PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));
			
			document.open();
			
			String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/SNCB_logo.svg/1000px-SNCB_logo.svg.png";
			Image image = Image.getInstance(new URL(imageUrl));
		    image.scaleAbsolute(50f, 30f);
		    
			
		    document.add(image);
			document.add(new Paragraph("Ticket nummer:  " + id + "\n\n",fonts[0]));
			document.add(new Paragraph("GELDIG VOOR EEN REIS\n",fonts[1]));
			if(ticket.getTerugDatum() == null){
				document.add(new Paragraph("HEENREIS",fonts[1]));
			}
			else{
				document.add(new Paragraph("HEEN- EN TERUGREIS",fonts[1]));
			}
			document.add(new Paragraph(ticket.getKlasse() + "e klas\n\n",fonts[1]));
			String depStation = ticket.getDepZone();
			document.add(new Paragraph("Van station:    " + depStation.toUpperCase() + "\n",fonts[0]));
			String arrStation = ticket.getArrZone();
			document.add(new Paragraph("Tot station:    " + arrStation.toUpperCase() + "\n\n",fonts[0]));
			
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date datum = ticket.getHeenDatum();    
			String heenDatum = df.format(datum);
			document.add(new Paragraph("Heen op:        " + heenDatum + "\n",fonts[0]));
			if(ticket.getTerugDatum() != null){
				datum = ticket.getTerugDatum();   
				String terugdatum = df.format(datum);
				document.add(new Paragraph("Terug op:       " + terugdatum,fonts[0]));
			}
			
			
			DateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			datum = Calendar.getInstance().getTime(); 
			String reportDate = dfh.format(datum);
			document.add(new Paragraph("\n\n" +  "Verkoopdatum:       " + reportDate + "\n",fonts[1]));
			
			document.add(new Paragraph("Reizigers: " + ticket.getAantal(), fonts[1]));
			String imageUrl2 = "http://optional.is/required/wp-content/uploads/2009/06/barcode-pdf417.png";
			Image image2 = Image.getInstance(new URL(imageUrl2));
			 image2.scaleAbsolute(200f, 80f);
		    document.add(image2);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("../NMBS-java/ticket.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
		
		
	}
	
	public void AboGenerator(Abonnement abo) {
		//code abo
	}
	/*
	public static void main(String[] args) {
		Abonnement abo = new Abonnement(15264, 12354, 1562, 2, "Bruxelles-Nord", "Bruxelles-Zuid", 50.0, null, 0.0, true);
		Document document = new Document();
		Rectangle rectangle = new Rectangle(PageSize.A4);
		document.setPageSize(rectangle);
		
		Font[] fonts = {
				new Font(Font.FontFamily.COURIER,12,Font.BOLD),
				new Font(Font.FontFamily.COURIER,10),
				new Font(Font.FontFamily.COURIER,13),
				new Font(Font.FontFamily.COURIER,18)
		};
	
		try {
			PdfWriter.getInstance(document, new FileOutputStream("abonnement.pdf"));
			
			document.open();
			
			String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/SNCB_logo.svg/1000px-SNCB_logo.svg.png";
			Image image = Image.getInstance(new URL(imageUrl));
		    image.scaleAbsolute(50f, 30f);
		    document.add(image);
		    
			document.add(new Paragraph(abo.getAbonnementId() + "",fonts[3]));
			document.add(new Paragraph(2 + "e klas\n\n",fonts[2]));
			/*String depStation = ticket.getDepZone();
			document.add(new Paragraph("Van station:    " + depStation.toUpperCase() + "\n",fonts[0]));
			String arrStation = ticket.getArrZone();
			document.add(new Paragraph("Tot station:    " + arrStation.toUpperCase() + "\n\n",fonts[0]));
			
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date datum = ticket.getHeenDatum();    
			String heenDatum = df.format(datum);
			document.add(new Paragraph("Heen op:        " + heenDatum + "\n",fonts[0]));
			if(ticket.getTerugDatum() != null){
				datum = ticket.getTerugDatum();   
				String terugdatum = df.format(datum);
				document.add(new Paragraph("Terug op:       " + terugdatum,fonts[0]));
			}
			
			
			DateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			datum = Calendar.getInstance().getTime(); 
			String reportDate = dfh.format(datum);
			document.add(new Paragraph("\n\n" +  "Verkoopdatum:       " + reportDate + "\n",fonts[1]));
			
			document.add(new Paragraph("Reizigers: " + ticket.getAantal(), fonts[1]));
			String imageUrl2 = "http://optional.is/required/wp-content/uploads/2009/06/barcode-pdf417.png";
			Image image2 = Image.getInstance(new URL(imageUrl2));
			 image2.scaleAbsolute(200f, 80f);
		    document.add(image2);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("../NMBS-java/abonnement.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
		
		
	}
	*/
	
	
	
}
