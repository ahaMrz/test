package TestAutomationZ;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CaseModel {
	String caseName;
	List<StepModel> stepModels;
	
	
	public List<StepModel> getStepModels() {
		return stepModels;
	}

	public void setStepModels(List<StepModel> stepModels) {
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
		return "CaseModel [caseName=" + caseName + ", stepModels=" + stepModels + "]";
	}
	
	public CaseModel() {
		// TODO Auto-generated constructor stub
		this.stepModels = new ArrayList<StepModel>();
	}

	@SuppressWarnings("resource")
	public static List<CaseModel> getCaseList(String path, CaseType caseType) {
		String fileType = path.substring(path.lastIndexOf(".") + 1);
		List<CaseModel> caseModels = new ArrayList<CaseModel>();
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

			CaseModel caseModel = new CaseModel();
			// 设定第一页未公共用例，第二页未普通用例
			int sheetIndex = 0;
			switch (caseType) {
			case publicCase:
				sheetIndex = 0;
				break;
			case ordinaryCase:
				sheetIndex = 1;
			default:
				break;
			}
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
					caseModel = new CaseModel();
					continue;
				}
				StepModel stepModel = new StepModel();
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
//							System.out.println("到了这里：");
//							System.out.println(valueStr);
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
						stepModel.setElement(valueStr);
						break;
					case 4:
						stepModel.setType(valueStr);
						break;
					case 5:
						stepModel.setObject(valueStr);
						break;
					case 6:
						stepModel.setAction(valueStr);
						break;
					case 7:
						stepModel.setValue(valueStr);
						break;
					case 8:
						stepModel.setExpect(valueStr);
					default:
						break;
					}
				}
				caseModel.stepModels.add(stepModel);
			}

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
		return caseModels;
	}



	public static void main(String[] args) {
		System.out.println(getCaseList("C:\\Users\\Administrator\\Desktop\\3.xlsx", CaseType.publicCase));
	}
}
