package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import source.Login;
import source.Station;
import source.VerlorenVoorwerp;

public class LoginDao {
	private static String username1, password1;
	private static int rollid, loginId;
	private static DBA dba = new DBA();
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static int addLogin(Login login){
		dba.createInsert("Login");
		dba.addValue(login.getUsername());
		dba.addValue(login.getPassword());
		dba.commit();
		return getLoginId(login);
	}
	
	public static int getLoginId(Login login){
		
		dba.createSelect("Login", "loginId");
		dba.addWhere("username", login.getUsername()); 
		dba.addWhere("pass", login.getPassword());
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
	
	public static String getUserName(String user){
		dba.createSelect("Login", "Username");
		dba.addWhere("Username", Login.getUsername()); 
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getString(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return user;
	}
	
	public static int getRoll(int loginId){
		dba.createSelect("Medewerker", "rolId");
		dba.addWhere("loginId", Login.getLoginId()); 
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				loginId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return loginId;
	}
	
	public static int getLoginId(String username){

		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT loginId FROM Login WHERE username = ?");
			stmt.setString(1, username);
			data = stmt.executeQuery();

			while(data.next()){
				loginId=data.getInt(1);
			}
			data.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return loginId;
	}

	public static String getWachtwoord(String pass){
		dba.createSelect("Login", "pass");
		dba.addWhere("Username", Login.getUsername()); 
		ResultSet rs = dba.commit();
		try {
			if(rs.next()){
				return rs.getString(pass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return pass;
	}

	public static void updateWachtwoord(String password){
		dba.createUpdate("Login", "pass", password);
		dba.addWhere("username", Login.getCurrentUser()); 
		ResultSet rs = dba.commit();
	}
	 
	public static void loginWijzigen(Login login) throws Exception{ 
	}
	public static Login loginZoekenOpLoginId(int loginId) throws Exception{
		return null; 
	}
	public static Login loginZoekenOpLoginId(Login login) throws Exception{
		return null;
		
	}

	public static Login loginZoekenOpUsername(Login login) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static Login loginToevoegen(Login login) throws Exception{
		return null;
		
	}
}
