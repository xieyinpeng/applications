import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eo.Site;

public class test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
		String str="2017-02-02";
		Date date=format.parse(str);
		System.out.print(date.toGMTString());
	}
}
