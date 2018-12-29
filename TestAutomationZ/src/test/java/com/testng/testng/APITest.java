package com.testng.testng;

import org.testng.annotations.Test;

import TestAutomationZ.apiCase.APICaseModel;
import TestAutomationZ.apiCase.APIStepModel;
import TestAutomationZ.okHttp.OkHttpUtil;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.DataProvider;

public class APITest {
	List<APICaseModel> caseList;
	OkHttpUtil okHttpUtil;

	public APITest(List<APICaseModel> caseLiset) {
		// TODO Auto-generated constructor stub
		okHttpUtil = new OkHttpUtil();
		this.caseList = caseLiset;
	}
	@Test(dataProvider = "dp")
	public void f(APICaseModel caseModel) {
		doCase(caseModel);
	}

	// 根据case模型执行用例
	public void doCase(APICaseModel caseModel) {
		for (int i = 0; i < caseModel.getStepModels().size(); i++) {
			APIStepModel apiStepModel = caseModel.getStepModels().get(i);
			if (apiStepModel.getAction().equals("get")) {
				String actual = okHttpUtil.get(apiStepModel.getUrl(), apiStepModel.getValue());
				assertEquals(actual, apiStepModel.getExpect());
			} else if (apiStepModel.getAction().equals("post")) {
				String actual = okHttpUtil.post(apiStepModel.getUrl(), apiStepModel.getValue());
				assertEquals(actual, apiStepModel.getExpect());
			} else {
				System.err.println("haimeixie");
			}

		}
	}

	@DataProvider
	public Object[] dp() {
//		caseList = APICaseModel.getCaseList("caseexcel/apicase/APIExample.xlsx");
		// 获取用例
		int size = caseList.size();
		Object[] objects = new Object[size];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = caseList.get(i);
		}
		return objects;
	}
}
