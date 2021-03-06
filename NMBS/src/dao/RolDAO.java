package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import source.Rol;

public class RolDAO {
	private static DBA dba = new DBA();

	public static int addRol(Rol rol) {
		if (RolDAO.getRolId(rol) == 0) {
			dba.createInsert("Rol");
			dba.addValue(rol.getRol());
			dba.commit();
		}
		return RolDAO.getRolId(rol);
	}

	public static int getRolId(Rol rol) {
		dba.createSelect("Rol", "rolId");
		dba.addWhere("rol", rol.getRol());
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Rol getRol(int id) {
		dba.createSelect("Rol");
		dba.addWhere("rolId", id);
		ResultSet rs = dba.commit();
		try {
			if (rs.next()) {
				return new Rol(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
