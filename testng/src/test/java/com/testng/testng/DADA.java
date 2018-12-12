package com.testng.testng;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Util.CaseModel;
import Util.CaseType;
import Util.Driver;
import Util.StepModel;
import Util.WebElementZ;

public class DADA {
	private List<CaseModel> publicCaseList;
	private List<CaseModel> caseList;
	  @SuppressWarnings("static-access")
	public DADA(List<CaseModel> publicCaseList,List<CaseModel> caseList) {
		  this.publicCaseList = publicCaseList;
		  this.caseList = caseList; 
	  }
	 
		 @Test(dataProvider = "dp")
		public void f(CaseModel caseModel) {
			 System.out.println("执行了用例" + caseModel);
			 doCase(caseModel);
		}

		@AfterClass
		public void AfterClass() {
			 Driver.getChromeDriver().quit();
			 Driver.setChromeDriver(null);
		}

		@BeforeClass
		public void BeforeClass() {
		}
		
		@DataProvider
		public Object[] dp() {
			//获取用例
			int size = caseList.size();
			Object[] objects = new Object[size];
			for (int i = 0; i < objects.length; i++) {
				objects[i] = caseList.get(i);
			}
			return objects;
		}
		
		//根据casel模型执行用例
		public void doCase(CaseModel caseModel) {
			for (int i = 0; i < caseModel.getStepModels().size(); i++) {
				StepModel stepModel = caseModel.getStepModels().get(i);
				System.out.println(stepModel);
				String object = stepModel.getObject();
				if (stepModel.getPrecondition() != null) {
					//执行前置条件
					for (CaseModel caseModel2 : publicCaseList) {
						if (stepModel.getPrecondition().equals(caseModel2.getCaseName())) {
							//根据casename查找到说要执行的公共用例
							doCase(caseModel2);
							break;
						}
					}
				}
				if (object.equals("driver")) {
					Driver.action(stepModel);
				} else {
					//懒得写elseif。这里假装判断了是element
					WebElementZ.ElementAction(stepModel);
				}
			}
		}
	  
}
