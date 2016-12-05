package source;

public enum VerkoopType {
	STANDAARD, GROEP, STUDENT, PLUS60;

	public static VerkoopType VerkoopTypeCasting(String cast){
		
		switch(cast){
		case "STANDAARD" : return VerkoopType.STANDAARD;
		case "GROEP" : return VerkoopType.GROEP;
		case "STUDENT" : return VerkoopType.STUDENT;
		case "PLUS60" : return VerkoopType.PLUS60;
		case "standaard" : return VerkoopType.STANDAARD;
		case "groep" : return VerkoopType.GROEP;
		case "student" : return VerkoopType.STUDENT;
		case "60+" : return VerkoopType.PLUS60;
		}
		
		return null;
	}
}