package com.zxz.bbs.utils;

import com.zxz.bbs.controller.MessageController;
import com.zxz.bbs.pojo.Message;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/** 临时文件的位置
 * 
 *  上传上来的临时图片，直接就放在 webroot\tmp\目录，以便可以直接URL访问浏览
 *
 */
public class TmpFile
{

	// 获取临时文件
	public static File getFile( String tmpName)
	{

		File dir = new File("D:\\idea_space\\bbs\\src\\main\\resources\\tmp\\");
		dir.mkdirs();
		dir.getAbsolutePath();
		return new File(dir, tmpName);
	}
	
	// 获取临时文件的访问URL
	public static String getUrl( File file)
	{
//		String url = file.getAbsolutePath();
//		String url2 =url.substring(9);

		return "/tmp/"+file.getName();
	}

	//把图片从临时文件tmp目录转移到c盘
	public static String moveTmpToStore(String tmpName,String storePath)
	{

		if(tmpName==null || tmpName.length()==0) return "";

		File tmpFile = TmpFile.getFile( tmpName);
		File storeDir = new File("C:\\img2\\"+storePath);
		try
		{
			FileUtils.moveFileToDirectory(tmpFile, storeDir, true);
			//String path = storePath + tmpName;
			return tmpName;
		} catch (IOException e)
		{
			throw new RuntimeException(e.getMessage());
		}


	}
	
}
