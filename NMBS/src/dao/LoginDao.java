package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import source.Login;

public class LoginDao {
	private static String username1, password1;
	private static int rollid, loginId;
	
	private static java.sql.Connection connection;
	private static Statement command;
	private static ResultSet data;
	private static PreparedStatement stmt = null;
	
	public static int addLogin(Login login){
		int id = 0;
		DataSource ds = null;
		
		try {
			connection = Connection.getDBConnection();
	        command = connection.createStatement();
	        
	        stmt = connection.prepareStatement("INSERT INTO Login (username, pass) VALUES(?,?,?);");
	        stmt.setString(1, login.getUsername());
	        stmt.setString(2,login.getPassword());
	        stmt.executeUpdate();
	        data = command.executeQuery("SELECT MAX(loginId) FROM Login");
	        if (data.next()) {
	        	id=data.getInt(1);
	        }
	        data.close();
	    }catch (SQLException e){
	        e.printStackTrace();
	    }catch(Exception e) {
	        e.printStackTrace();
	    }finally{
	    	try{
	    		if (data!=null){data.close();}
	            if(connection!=null)connection.close();
	        }catch(SQLException se2){
	            se2.printStackTrace();
	        }
	    }     
	    return id;
	}
	
	public static String getUsername(int userId){

		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT Username FROM Login WHERE UserId = ?");
			stmt.setInt(1, userId);
			data = stmt.executeQuery();

			while(data.next()){
				username1=data.getString(1);
				System.out.println("username: " + username1);
			}
			data.close();
			//connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return username1;
	}
	
	public static String getPassword(int userId){

		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT pass FROM Login WHERE UserId = ?");
			stmt.setInt(1, userId);
			data = stmt.executeQuery();

			while(data.next()){
				password1=data.getString(1);
				System.out.println("password: " + password1);
			}
			data.close();
			//connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return password1;
	}
	
	public static String getUserName(String user){

		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT Username FROM Login WHERE Username = ?");
			stmt.setString(1, user);
			data = stmt.executeQuery();

			while(data.next()){
				username1=data.getString(1);
				System.out.println("username: " + username1);
			}
			data.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return username1;
	}

	public static int getRoll(int loginId){

		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT rolId FROM Medewerker WHERE loginId = ?");
			stmt.setInt(1, loginId);
			data = stmt.executeQuery();

			while(data.next()){
				rollid=data.getInt(1);
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
		return rollid;
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

	/* public static int userId(String username, String password){
		int userId=0;
		try {
			connection = Connection.getDBConnection();
			stmt = connection.prepareStatement("SELECT UserId FROM User WHERE Username = ? AND Password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			data = stmt.executeQuery();

			while(data.next()){
				userId=data.getInt(1);
				System.out.println("userid: " + userId);
			}
			data.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return userId;
	} */


	public static String getWachtwoord(String user){
		try {
			connection = Connection.getDBConnection();            
			stmt = connection.prepareStatement("Select pass from Login where Username=?");
			stmt.setString(1, user);
			data = stmt.executeQuery();

			while(data.next()){
				password1=data.getString(1);
				System.out.println("password1: " + password1);
			}
			data.close();
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch(Exception e) {//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			try{
				if(data!=null) data.close();
				if(connection!=null)connection.close();
			}catch(SQLException se2){
				se2.printStackTrace();
			}
		}
		return password1;
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
