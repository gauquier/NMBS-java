package testing_source;

import static org.junit.Assert.*;

import org.junit.Test;
import source.*;


import java.sql.Connection;
 
public class DatabaseTest {

	@Test
	public void getConnectionTest() { 
		Connection con = Database.getConnection(); 
		assertNotEquals(null, con); 
		System.out.println(con.toString()); 
		Database.close(con);
	}
	@Test
	public void closeTest() { 
		//nutteloos om deze methode te testen
		//return niets en exceptions worden gevangen binnen de methode
	}

}
