package dao;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
//import logins.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.PooledConnection;

public class Connection {
	//FTP
	private static String server = "dtsw.ehb.be";
	private static int port = 21;
	private static String user = "";
	private static String pass = "";
	//SVN
	private static String username = "SP2NMBS";
	private static String password = "JZde2p";
	private static String connectionString = "jdbc:mysql://dt5.ehb.be/SP2NMBS";

	private static PooledConnection pool;


	public static java.sql.Connection getDBConnection(){
		try {        	
			return DriverManager.getConnection(connectionString, username, password);
		}catch (SQLException e){
			e.printStackTrace();            
		}
		return null;
	}

	public PooledConnection getPool() {
		return pool;		
	}

	public void setPool(PooledConnection pool) {
		this.pool = pool;
	}
}
