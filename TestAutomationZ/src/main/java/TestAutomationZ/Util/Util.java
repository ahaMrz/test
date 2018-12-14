package TestAutomationZ.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Util {
	public static Workbook getWorkbook(String path) {
    	String fileType = path.substring(path.lastIndexOf(".") + 1);
		// 读取excel文件
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			// 获取工作薄
			Workbook wb = null;
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				return null;
			}
			return wb;
		  
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
