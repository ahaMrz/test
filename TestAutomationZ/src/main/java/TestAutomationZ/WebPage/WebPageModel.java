package TestAutomationZ.WebPage;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.tools.ant.taskdefs.optional.Cab;

import TestAutomationZ.Util.Util;

public class WebPageModel {
	String pageName;
	List<ElementModel> elements;
	// List<WebPageModel> webPageList;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<ElementModel> getElements() {
		return elements;
	}

	public void setElements(List<ElementModel> elements) {
		this.elements = elements;
	}

	public WebPageModel() {
		// TODO Auto-generated constructor stub
		super();
		this.elements = new ArrayList<ElementModel>();
	}

	public static List<WebPageModel> getWebPageList(String path) {
		Workbook workbook = Util.getWorkbook(path);
		Sheet sheet = workbook.getSheetAt(0);
		// int lastrow = sheet.getLastRowNum();
		int lastcell = sheet.getRow(0).getLastCellNum();
		List<WebPageModel> webPageList = new ArrayList<WebPageModel>();
		WebPageModel webPage = new WebPageModel();
		
//		Boolean isTitle = true;
		Boolean isFristPage = true;
		int lastRow = sheet.getLastRowNum();
//		System.out.println(lastRow);
		for (int j = 1; j < lastRow + 1; j++) {
			Row row = sheet.getRow(j);
			ElementModel element = new ElementModel();
			// if (isTitle) {
			// isTitle = false;
			// continue;
			// }
//			System.out.println(row.getCell(0));
			for (int i = 0; i < lastcell; i++) {
				
				String valueStr = "";
				Cell cell = row.getCell(i);
				if (cell == null) {
					valueStr = null;
				} else {
					// 将cell中的内容转化为字符串
					cell.setCellType(Cell.CELL_TYPE_STRING);
					valueStr = cell.getStringCellValue();
				}
//				 System.out.println(cell);
				switch (i) {
				case 0:
					if (null != valueStr) {
						if (isFristPage) {
							isFristPage = false;
						}else {
							webPageList.add(webPage);
						}
						webPage = new WebPageModel();
						webPage.pageName = valueStr;
//						System.out.println(valueStr);
					}

//					 System.out.println(valueStr);
					break;
				case 1:
					element.elementName = valueStr;
					break;
				case 2:
					element.elementByType = valueStr;
					break;
				case 3:
					element.elementValue = valueStr;
					webPage.elements.add(element);
					break;
				default:
					break;
				}
			}
		}
		webPageList.add(webPage);
		return webPageList;
	}

	public static void main(String[] args) {
		List<WebPageModel> webPageModels = getWebPageList("C:\\Users\\Administrator\\Desktop\\新建文件夹\\搜狗.xlsx");
		 System.out.println(webPageModels);
	}

	@Override
	public String toString() {
		return "WebPageModel\n [pageName=" + pageName + ", elements=" + elements + "]\n";
	}

}
