package TestAutomationZ.apiCase;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import TestAutomationZ.Util.Util;



public class APICaseModel {
	String caseName;
	List<APIStepModel> stepModels;



	public List<APIStepModel> getStepModels() {
		return stepModels;
	}

	public void setStepModels(List<APIStepModel> stepModels) {
		this.stepModels = stepModels;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Override
	public String toString() {
		return "CaseModel [caseName=" + caseName + ", stepModels=" + stepModels + "]\n";
	}

	public APICaseModel() {
		// TODO Auto-generated constructor stub
		this.stepModels = new ArrayList<APIStepModel>();
	}

	public static List<APICaseModel> getCaseList(String path) {
		
		Workbook wb = Util.getWorkbook(path);
		List<APICaseModel> caseModels = new ArrayList<APICaseModel>();
		APICaseModel caseModel = new APICaseModel();
	
		int sheetIndex = 1;
		Sheet sheet = wb.getSheetAt(sheetIndex);
		// 获取行数；因已空行作为记号所以+1
		int lastRow = sheet.getLastRowNum() + 1;
		// 获取列数
		int lastcell = sheet.getRow(0).getLastCellNum();
		// System.out.println(lastRow);
		for (int i = 0; i <= lastRow; i++) {
			Row row = sheet.getRow(i);
			if (i == 0) {
				// 第一行是标题
				continue;
			}
			if (row == null) {
				// 到了空行，list添加case，并初始化casemodel
				caseModels.add(caseModel);
				caseModel = new APICaseModel();
				continue;
			}
			APIStepModel stepModel = new APIStepModel();
			for (int j = 0; j < lastcell; j++) {
				String valueStr = "";
				Cell cell = row.getCell(j);
				if (cell == null) {
					valueStr = null;
				} else {
					// 将cell中的内容转化为字符串
					cell.setCellType(Cell.CELL_TYPE_STRING);
					valueStr = cell.getStringCellValue();
					// System.out.println(valueStr);
				}
				// 根据cellIndex添加对应的参数
				switch (j) {
				case 0:
					if (valueStr != null) {
						// System.out.println("到了这里：");
						// System.out.println(valueStr);
						if (valueStr.length() > 0) {
							caseModel.setCaseName(valueStr);
						}

					}
					break;
				case 1:
					stepModel.setPrecondition(valueStr);
					break;
				case 2:
					stepModel.setStep(valueStr);
					break;
				case 3:
					 stepModel.setUrl(valueStr);
					break;
				case 4:
					stepModel.setAction(valueStr);

					break;
				case 5:
					stepModel.setValue(valueStr);
					break;
				case 6:
					stepModel.setExpect(valueStr);
					break;
				default:
					break;
				}
			}
			caseModel.stepModels.add(stepModel);
		}
		return caseModels;		
	}
	public static void main(String[] args) {
		System.out.println(APICaseModel.getCaseList("C:\\Users\\Administrator\\Desktop\\APIExample.xlsx"));
	}
}
