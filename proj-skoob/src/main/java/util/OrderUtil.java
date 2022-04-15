package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

	public static String getDateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	public static String getOrderid(String dateFormat, Integer srlnum) {
		// 格式化字串，整數，長度4，不足部分左邊補0
		String pattern = "%04d"; 
		String srln = String.format(pattern, srlnum);
		
		return dateFormat + srln;
	}

}
