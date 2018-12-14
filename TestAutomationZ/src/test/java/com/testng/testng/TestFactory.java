package com.testng.testng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Factory;


import TestAutomationZ.Case.CaseModel;

public class TestFactory {
	@Factory
	public Object[] createInstances() {
		List<String> pathList = getCasePathList();
		Object[] result = new Object[pathList.size()];
		for (int i = 0; i < pathList.size(); i++) {
//			CaseModel.getCaseList(pathList.get(i), CaseType.publicCase);
			result[i] = new WebTest(CaseModel.getCaseList(pathList.get(i)));
		}
		return result;
	}

	static public ArrayList<String> getCasePathList() {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File("caseexcel/");
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
//	@SuppressWarnings("rawtypes")
//	public static void main(String[] args) {
//		TestNG testNG = new TestNG();
//		Class[] adsa = {TestFactory.class};
//		testNG.setTestClasses(adsa);
//		testNG.run();
//
//	}
}
