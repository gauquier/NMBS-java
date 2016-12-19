package source;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Adres;

public class AdresTest {
	private Adres adres1;
	private Adres adres1_copy;
	private Adres adres2;
	
	private int adres1_id=1;
	private int adres1_copy_id=2;
	private int adres2_id=1;
	
	@Before
	public void initialize(){
		adres1= new Adres("AdresTest1_straatnaam", 170, "AdresTest1_woonplaats", 1070, "6");
		adres1_copy=new Adres("AdresTest1_straatnaam", 170, "AdresTest1_woonplaats", 1070, "6");
		adres2= new Adres("AdresTest2_straatnaam", 170, "AdresTest2_woonplaats", 1070, "6"); 
		adres1.setAdresId(adres1_id);
		adres1_copy.setAdresId(adres1_copy_id); 
		adres2.setAdresId(adres2_id); 
	} 
	@Test
	public void testAdres1GelijkAanAdres1_copy() { 
		assertEquals(adres1, adres1_copy); 
	}
	@Test
	public void testAdres1NietGelijkAanAdres2() { 
		assertNotEquals(adres1, adres2); 
	}

}
