package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Statement;

import source.VerkoopType;

public class DBA {
	private String sql;
	private String tableName;

	private enum Type {
		SELECT, INSERT, UPDATE, NONE
	}

	private Type type = Type.NONE;
	private boolean isWhere = false;
	boolean valueAdded = false;

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

		public String getSql() {
		return sql;
	}


	public void createSelect(String tableName)// SELECT * FROM tableName
	{
		reset();

		type = Type.SELECT;
		this.tableName = tableName;
		sql = "SELECT * FROM " + tableName;
	}

	public void createSelect(String tableName, String columnName)// SELECT
																	// columnName
																	// FROM
																	// tableName
	{
		reset();

		type = Type.SELECT;
		this.tableName = tableName;
		sql = "SELECT " + columnName + " FROM " + tableName;
	}

	public void createTicketstatistiekenSelect() {
		reset();

		type = Type.SELECT;
		sql = "SELECT verkoopDatum, COUNT(verkoopDatum) FROM Ticket GROUP BY verkoopDatum LIMIT 5";
	}

	public void createUpdate(String tableName, String columnName, String value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		reset();

		type = Type.UPDATE;
		this.tableName = tableName;
		sql = "UPDATE " + tableName + " SET " + columnName + " = \"" + value + "\"";
	}

	public void createUpdate(String tableName, String columnName, double value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		reset();

		type = Type.UPDATE;
		this.tableName = tableName;
		sql = "UPDATE " + tableName + " SET " + columnName + " = " + value;
	}

	public void createUpdate(String tableName, String columnName, int value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		createUpdate(tableName, columnName, (double) value);
	}

	public void createUpdate(String tableName, String columnName, boolean value) // UPDATE
																					// tableName
																					// SET
																					// columnName
																					// =
																					// value
	{
		reset();

		type = Type.UPDATE;
		this.tableName = tableName;
		sql = "UPDATE " + tableName + " SET " + columnName + " = ";
		if (value) {
			sql = sql + "1";
		} else {
			sql = sql + "0";
		}
	}

	public void createInsert(String tableName) // INSERT INTO tableName Values
												// (0,... (autoset id)
	{
		reset();

		type = Type.INSERT;
		this.tableName = tableName;
		sql = "INSERT INTO " + tableName + " VALUES (null";
	}

	public void addValue(String value) {
		if (type == Type.INSERT) {
			sql = sql + ", \"" + value + "\"";
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(double value) {
		if (type == Type.INSERT) {
			sql = sql + ", " + value;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(int value) {
		if (type == Type.INSERT) {
			sql = sql + ", " + value;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(boolean value) {
		if (type == Type.INSERT) {
			sql = sql + ", ";
			if (value) {
				sql = sql + "1";
			} else {
				sql = sql + "0";
			}
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(VerkoopType value) // haal type STRING uit enum
	{
		if (type == Type.INSERT) {
			sql = sql + ", " + value;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(Date value) {
		String ss = "";
		if (value.getDate() < 0)
			ss = ss + "0";
		ss = ss + value.getDate() + "-";
		if (value.getMonth() < 10)
			ss = ss + "0";
		ss = ss + value.getMonth() + "-" + (value.getYear() + 1900);
		addValue(ss);
	}

	/*
	 * public void addValue(Calendar value) { if(type == Type.INSERT) { <<<<<<<
	 * HEAD String datum; datum = value; sql = sql + ", \"" + datum + "\"";
	 * ======= String datum; datum = value; sql = sql + ", \"" + datum + "\"";
	 * 
	 * >>>>>>> df38cd824381e0733d77fa46bb270e7066be76ec } else{
	 * System.out.println("can only add values when type = INSERT"); } }
	 */

	public void addWhere(String columnName, String value) // ... WHERE
															// columnName =
															// value
	{

		if (type == Type.UPDATE || type == Type.SELECT) {
			if (!isWhere) {
				sql = sql + " WHERE";
				isWhere = true;
			}
			sql = sql + " " + columnName + " = \"" + value + "\" AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhereLike(String columnName, String value) // ... WHERE
																// columnName =
																// value
	{
		if (type == Type.UPDATE || type == Type.SELECT) {
			if (!isWhere) {
				sql = sql + " WHERE";
				isWhere = true;
			}
			sql = sql + " " + columnName + " LIKE '" + value + "' AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, double value) // ... WHERE
															// columnName =
															// value
	{
		if (type == Type.UPDATE || type == Type.SELECT) {
			if (!isWhere) {
				sql = sql + " WHERE";
				isWhere = true;
			}
			sql = sql + " " + columnName + " = " + value + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, int value) // ... WHERE columnName =
														// value
	{
		if (type == Type.UPDATE || type == Type.SELECT) {
			if (!isWhere) {
				sql = sql + " WHERE";
				isWhere = true;
			}
			sql = sql + " " + columnName + " = " + value + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, boolean value) // ... WHERE
															// columnName =
															// value
	{
		if (type == Type.UPDATE || type == Type.SELECT) {
			if (!isWhere) {
				sql = sql + " WHERE";
				isWhere = true;
			}
			sql = sql + " " + columnName + " = ";
			if (value) {
				sql = sql + "1";
			} else {
				sql = sql + "0";
			}
			sql = sql + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, Date value) {
		String ss = "";
		if (value.getDate() < 0)
			ss = ss + "0";
		ss = ss + value.getDate() + "-";
		if (value.getMonth() < 10)
			ss = ss + "0";
		ss = ss + value.getMonth() + "-" + (value.getYear() + 1900);
		addWhere(columnName, ss);
	}

	public ResultSet commit() {

		if (type == Type.UPDATE || type == Type.SELECT) {
			if (isWhere) {
				sql = sql.substring(0, sql.length() - 4);
			}
			sql = sql + ";";

		}
		if (type == Type.INSERT) {
			sql = sql + ");";
		}
		System.out.println(sql);

		try {
			conn = dao.Connection.getDBConnection();
			stmt = (Statement) conn.prepareStatement(sql);

			if (type == Type.SELECT) {
				rs = stmt.executeQuery(sql);
			} else if (type == Type.UPDATE || type == Type.INSERT) {
				stmt.executeUpdate(sql);
			}

		} catch (SQLException se) {
			System.out.println("SQL STATEMENT: " + sql);
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("SQL STATEMENT: " + sql);
			e.printStackTrace();
		}
		return rs;
	}

	public void reset() {
		isWhere = false;
		type = Type.NONE;

		if (conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stmt != null)
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
