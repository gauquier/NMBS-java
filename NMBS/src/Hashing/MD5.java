package Hashing;

import java.security.*;
import java.math.*;

public class MD5 {	
	
	protected String hashString(String meegegevenString) throws Exception{
       String s= meegegevenString;
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       return new BigInteger(1,m.digest()).toString(16);
       
    }
	
	public static void main(String[] args) throws Exception {
		MD5 test = new MD5();
		System.out.println(test.hashString("banaan"));
	}
}