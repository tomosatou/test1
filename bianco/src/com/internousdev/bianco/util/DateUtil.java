package com.internousdev.bianco.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public String getDate(){
		Date date =new Date();
		                  //日付フォーマット                        //年月日時間
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		return simpleDateFormat.format(date);
	}

}
