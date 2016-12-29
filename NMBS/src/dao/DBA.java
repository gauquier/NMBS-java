package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Statement;

public class DBA {
	private String sql;

	private enum Type {
		SELECT, INSERT, UPDATE, NONE
	}

	private Type type = Type.NONE;
	private boolean isWhere = false;

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public String getSql() {
		return this.sql;
	}

	public void createSelect(String tableName)// SELECT * FROM tableName
	{
		this.reset();

		this.type = Type.SELECT;
		this.sql = "SELECT * FROM " + tableName;
	}

	public void createSelect(String tableName, String columnName)// SELECT
																	// columnName
																	// FROM
																	// tableName
	{
		this.reset();

		this.type = Type.SELECT;
		this.sql = "SELECT " + columnName + " FROM " + tableName;
	}

	public void createTicketstatistiekenSelect() {
		this.reset();

		this.type = Type.SELECT;
		this.sql = "SELECT verkoopDatum, COUNT(verkoopDatum) FROM Ticket GROUP BY verkoopDatum LIMIT 5";
	}

	public void createUpdate(String tableName, String columnName, String value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		this.reset();

		this.type = Type.UPDATE;
		this.sql = "UPDATE " + tableName + " SET " + columnName + " = \"" + value + "\"";
	}

	public void createUpdate(String tableName, String columnName, double value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		this.reset();

		this.type = Type.UPDATE;
		this.sql = "UPDATE " + tableName + " SET " + columnName + " = " + value;
	}

	public void createUpdate(String tableName, String columnName, int value) // UPDATE
																				// tableName
																				// SET
																				// columnName
																				// =
																				// value
	{
		this.createUpdate(tableName, columnName, (double) value);
	}

	public void createInsert(String tableName) // INSERT INTO tableName Values
												// (0,... (autoset id)
	{
		this.reset();

		this.type = Type.INSERT;
		this.sql = "INSERT INTO " + tableName + " VALUES (null";
	}

	public void addValue(String value) {
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ", \"" + value + "\"";
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(double value) {
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ", " + value;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(int value) {
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ", " + value;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(boolean value) {
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ", ";
			if (value) {
				this.sql = this.sql + "1";
			} else {
				this.sql = this.sql + "0";
			}
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}
	public void addValue() {
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ", " + null;
		} else {
			System.out.println("can only add values when type = INSERT");
		}
	}

	public void addValue(Date value) {
		if(value == null){
			addValue();
		}
		else{
		String ss = "";
		if (value.getDate() < 0) {
			ss = ss + "0";
		}
		ss = ss + value.getDate() + "-";
		if (value.getMonth() < 10) {
			ss = ss + "0";
		}
		ss = ss + value.getMonth() + "-" + (value.getYear() + 1900);
		this.addValue(ss);
		}
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

		if (this.type == Type.UPDATE || this.type == Type.SELECT) {
			if (!this.isWhere) {
				this.sql = this.sql + " WHERE";
				this.isWhere = true;
			}
			this.sql = this.sql + " " + columnName + " = \"" + value + "\" AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, double value) // ... WHERE
															// columnName =
															// value
	{
		if (this.type == Type.UPDATE || this.type == Type.SELECT) {
			if (!this.isWhere) {
				this.sql = this.sql + " WHERE";
				this.isWhere = true;
			}
			this.sql = this.sql + " " + columnName + " = " + value + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, int value) // ... WHERE columnName =
														// value
	{
		if (this.type == Type.UPDATE || this.type == Type.SELECT) {
			if (!this.isWhere) {
				this.sql = this.sql + " WHERE";
				this.isWhere = true;
			}
			this.sql = this.sql + " " + columnName + " = " + value + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, boolean value) // ... WHERE
															// columnName =
															// value
	{
		if (this.type == Type.UPDATE || this.type == Type.SELECT) {
			if (!this.isWhere) {
				this.sql = this.sql + " WHERE";
				this.isWhere = true;
			}
			this.sql = this.sql + " " + columnName + " = ";
			if (value) {
				this.sql = this.sql + "1";
			} else {
				this.sql = this.sql + "0";
			}
			this.sql = this.sql + " AND";
		} else {
			System.out.println("can only add WHERE clausule when type = UPDATE or type = SELECT");
		}
	}

	public void addWhere(String columnName, Date value) {
		if(value != null)
		{
		String ss = "";
		if (value.getDate() < 0) {
			ss = ss + "0";
		}
		ss = ss + value.getDate() + "-";
		if (value.getMonth() < 10) {
			ss = ss + "0";
		}
		ss = ss + value.getMonth() + "-" + (value.getYear() + 1900);
		this.addWhere(columnName, ss);
		}
	}

	public ResultSet commit() {

		if (this.type == Type.UPDATE || this.type == Type.SELECT) {
			if (this.isWhere) {
				this.sql = this.sql.substring(0, this.sql.length() - 4);
			}
			this.sql = this.sql + ";";

		}
		if (this.type == Type.INSERT) {
			this.sql = this.sql + ");";
		}
		System.out.println(this.sql);

		try {
			this.conn = dao.Connection.getDBConnection();
			this.stmt = (Statement) this.conn.prepareStatement(this.sql);

			if (this.type == Type.SELECT) {
				this.rs = this.stmt.executeQuery(this.sql);
			} else if (this.type == Type.UPDATE || this.type == Type.INSERT) {
				this.stmt.executeUpdate(this.sql);
			}

		} catch (SQLException se) {
			System.out.println("SQL STATEMENT: " + this.sql);
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("SQL STATEMENT: " + this.sql);
			e.printStackTrace();
		}
		return this.rs;
	}

	public void reset() {
		this.isWhere = false;
		this.type = Type.NONE;

		if (this.conn != null) {
			try {
				this.conn.close();
				this.conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.stmt != null) {
			try {
				this.stmt.close();
				this.stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.rs != null) {
			try {
				this.rs.close();
				this.rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
