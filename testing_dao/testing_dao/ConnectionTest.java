package testing_dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dao.*;
import source.Database;
public class ConnectionTest {
	private java.sql.Connection connection;
	@Before
	public void initialize(){
		connection=Connection.getDBConnection(); 
	}
	@Test
	public void getDBConnectionTest() { 
		assertNotEquals(null, connection); 
	}
	@After
	public void terminate(){
		Database.close(connection); 
	}

}
