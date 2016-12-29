package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Prijs;
import source.VerkoopType; 
//enum VerkoopType {
//	PrijsDAOTest;
//
//	public static VerkoopType VerkoopTypeCasting(String cast){
//		
//		switch(cast){
//		case "PrijsDAOTest" : return VerkoopType.PrijsDAOTest; 
//		}
//		return null;
//	}
//}
public class PrijsDAOTest {
	private String testClassName = "PrijsDAOT";
	Prijs prijs;

	@Before
	public void initialize() {
		prijs = new Prijs(0, testClassName, 9119);
	}

	@After
	public void terminate() {
		prijsVerwijderen();
	}

	@Test
	public void testUpdatePrijsByVerkoopType() {  
			prijs = Help.prijsToevoegen(prijs);
			prijs.setPrijs(8118);
			PrijsDAO.updatePrijsByVerkoopType(prijs.getVerkoopType(), prijs.getPrijs());
			Prijs prijsTmp = Help.prijsOphalen(prijs);
			assertEquals(prijs.getPrijs(), prijsTmp.getPrijs(), 0); 
	}
	@Test(expected=Exception.class)
	public void testUpdatePrijsByVerkoopTypeVoorOnbestaandeVerkoopType() {   
			PrijsDAO.updatePrijsByVerkoopType(prijs.getVerkoopType(), prijs.getPrijs()); 
	}
	@Test
	public void testGetPrijsByVerkoopType() { 
			prijs.setVerkoopType(VerkoopType.STANDAARD.toString());  
			double verkoopPrijs=PrijsDAO.getPrijsByVerkoopType(VerkoopType.STANDAARD);
			Prijs prijsTmp = Help.prijsOphalen(prijs); 
			assertEquals(prijsTmp.getPrijs(), verkoopPrijs, 0); 
	} 
	@Test
	public void testGetAllPrijzen() {   
			int countBefore=PrijsDAO.getAllPrijzen().size();
			Help.prijsToevoegen(prijs); 
			int countAfter=PrijsDAO.getAllPrijzen().size();
			assertEquals(countBefore + 1, countAfter ); 
	}

	private void prijsVerwijderen() {
		String prijsVerwijderen="DELETE FROM Prijs WHERE verkoopType LIKE '%"+testClassName+"%'";
		Help.executeQuery(prijsVerwijderen, true);
	}

}
