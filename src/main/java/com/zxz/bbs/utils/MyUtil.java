package com.zxz.bbs.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.binary.Hex;

public class MyUtil
{
	static AtomicInteger seed = new AtomicInteger(0);

	// 本方法依赖 lib/commons-codec-1.12.jar
	public static String md5(String text) 
	{
		try {
			// 转成字节数据
			byte[] data = text.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update( data );
			byte[] code = md.digest(); 
			
			// 转成HEX显示
			return Hex.encodeHexString(code, true);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	// 创建guid
	public static String guid()
	{
		 String s = UUID.randomUUID().toString(); 
	     String s2 = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	     return s2.toUpperCase();
	}
	
	public static String guid2()
	{
		int s = seed.incrementAndGet() % 10;
		return System.currentTimeMillis() + "" + s;
	}

	public static boolean isEmpty(String s)
	{
		return s==null || s.length()==0;
	}
	
	// 获取文件名后缀，例如 ".mp4"
	public static String getSuffix(String filePath)
	{
		int p1 = filePath.lastIndexOf('.');
		if(p1 > 0) 
		{
			String suffix = filePath.substring(p1);
			if(suffix.length() < 10) //后缀长度必须小于10
			{
				// 后缀中不能有路径分隔符
				if(suffix.indexOf('/')<0 && suffix.indexOf('\\')<0)
					return suffix.toLowerCase();
			}			
		}
		return "";
	}
	
	// 根据后缀名，推算 Content-Type
	public static String getContentType(String suffix)
	{
		suffix = suffix.toLowerCase();
		if(suffix.equals(".jpg")) return "image/jpeg";
		if(suffix.equals(".jpeg")) return "image/jpeg";
		if(suffix.equals(".png")) return "image/png";
		if(suffix.equals(".gif")) return "image/gif";
		if(suffix.equals(".html")) return "text/html";
		if(suffix.equals(".txt")) return "text/plain";
		if(suffix.equals(".js")) return "application/javascript";
		if(suffix.equals(".mp4")) return "video/mp4";
		
		return "application/octet-stream"; // 一般的二进制文件类型
	}
	
	// 拷贝字节流，从in中读取字节，写入到out中
	public static long copy(InputStream in, OutputStream out) throws Exception
	{
		long count = 0;
		byte[] buf = new byte[8192];
		while (true)
		{
			int n = in.read(buf);
			if (n < 0)
				break;
			if (n == 0)
				continue;
			out.write(buf, 0, n);

			count += n;
		}
		return count;
	}
}
