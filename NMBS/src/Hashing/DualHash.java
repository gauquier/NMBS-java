package Hashing;

import java.security.*;
import java.math.*;

public class DualHash {	
	
	public String hashString(String meegegevenString) throws Exception{
       String s= meegegevenString;
       MD5 md5Hash = new MD5();
       SHA512 sha2Hash = new SHA512();
      
       s = md5Hash.hashString(s);
       s = sha2Hash.hashString(s);
       
       return s;
     
    }
	
	public static void main(String[] args) throws Exception {
		DualHash test = new DualHash();
		System.out.println(test.hashString("banaan"));
	}
}