package com.zxz.bbs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

// 当天的，显示时间；以前的，显示日期
public class TimeStrUtil
{
	String today;
	
	public TimeStrUtil()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.format(new Date());
	}
	
	public String format(String timestr)
	{
		//yyyy-MM-dd HH:mm:ss
		if(timestr.indexOf(today)==0)
			return timestr.substring(11,16);
		else
			return timestr.substring(5,10);
	}
	
	
}
