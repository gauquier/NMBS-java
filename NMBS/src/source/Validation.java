package source;

import java.util.regex.Pattern;

public class Validation {
	public static boolean checkLenght(String name, int lenght) {
        String regex = "^.{"+lenght+",}$";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAlphaNumerical(String name) {
        String regex = "[A-Za-z0-9]{2,40}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAlphabetical(String name) {
        String regex = "[A-Za-z]{2,40}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkHouseNumber(String name) {
        String regex = "[1-9][0-9]{0,3}[A-Za-z]{0,2}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkBoxNumber(String name) {
        String regex = "[1-9]{0,1}[0-9]{0,2}[A-Za-z]{0,2}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPostalCode(String name) {
        String regex = "[1-9][0-9]{3}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPhone(String name) {
        String regex = "[\\+]{0,1}[0-9]{5,20}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkFirstName(String firstName) {
        String regex = "[A-Za-z\\s-]{2,40}";
        if (Pattern.matches(regex, firstName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLastName(String lastName) {
        String regex = "[A-Za-z\\s-]{2,40}";
        if (Pattern.matches(regex, lastName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String regex = "[A-Za-z0-9]+@[A-Za-z0-9]+[\\.][A-Za-z]{2,4}";
        if (Pattern.matches(regex, email)) {
            return true;
        } else {
            return false;
        }
    }
}
