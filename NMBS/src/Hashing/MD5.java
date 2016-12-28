package Hashing;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {	
	
	protected String hashString(String meegegevenString) throws Exception{
       String s= meegegevenString;
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       return new BigInteger(1,m.digest()).toString(16);
       
    }
}