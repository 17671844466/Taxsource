package com.zhidisoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatDate {
	/**
	 * 将日期对象转化为字符串
	 * @param date 日期对象
	 * @return 日期对象对应的字符串
	 */
	public static String format(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * 取得两个字符串对应日期对象的间隔天数
	 * @param executeTime 执行时间
	 * @param recordDate 记录时间
	 * @return 返回间隔天数
	 */
	public static String getTimeOut(String executeTime,String recordDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long out  = 0;
		try {
			if(recordDate!=null&&executeTime!=null){
				out = sdf.parse(recordDate).getTime() - sdf.parse(executeTime).getTime();
				return String.valueOf((out/1000/60/60/24));
			}				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
