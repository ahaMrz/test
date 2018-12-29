package com.testng.testng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import org.testng.TestNG;
import org.testng.annotations.Factory;

import TestAutomationZ.Case.CaseModel;
import TestAutomationZ.apiCase.APICaseModel;

public class TestFactory {
	@Factory
	public Object[] createInstances() {
		List<String> webPathList = getWebCasePathList();
		List<String> APIPathList = getAPICasePathList();
		int resultSize = webPathList.size() + APIPathList.size();
		Object[] result = new Object[resultSize];
		//生成webTest
		for (int i = 0; i < webPathList.size(); i++) {
			// CaseModel.getCaseList(pathList.get(i), CaseType.publicCase);
			result[i] = new WebTest(CaseModel.getCaseList(webPathList.get(i)));
		}
		//生成APITest
		for (int i = 0, j = webPathList.size(); j < result.length; i++, j++) {
			result[j] = new APITest(APICaseModel.getCaseList(APIPathList.get(i)));
		}
		return result;
	}

	// 获取web测试用例
	static public ArrayList<String> getWebCasePathList() {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File("caseexcel/webcase/");
		File[] tempList = file.listFiles();
		if (tempList.length > 0) {
			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].isFile()) {
					System.out.println("文     件：" + tempList[i]);
					files.add(tempList[i].toString());
				}
				if (tempList[i].isDirectory()) {
					// System.out.println("文件夹：" + tempList[i]);
				}
			}
		} else {
			throw new NullPointerException("没有找到case");
		}

		return files;
	}

	// 获取接口测试用例
	static public ArrayList<String> getAPICasePathList() {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File("caseexcel/apicase/");
		File[] tempList = file.listFiles();
		if (tempList.length > 0) {
			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].isFile()) {
					System.out.println("文     件：" + tempList[i]);
					files.add(tempList[i].toString());
				}
				if (tempList[i].isDirectory()) {
					// System.out.println("文件夹：" + tempList[i]);
				}
			}
		} else {
			throw new NullPointerException("没有找到case");
		}

		return files;
	}
}
