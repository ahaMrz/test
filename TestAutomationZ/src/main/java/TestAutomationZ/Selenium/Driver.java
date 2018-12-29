package TestAutomationZ.Selenium;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.chrome.ChromeDriver;

import TestAutomationZ.webCase.StepModel;

public class Driver {
    static	ChromeDriver chromeDriver;
    public static synchronized ChromeDriver getChromeDriver() {
    	if (null==chromeDriver) {
		 System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	     chromeDriver = new ChromeDriver();
	     chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	     System.out.println("初始化");
	}
		return chromeDriver;
	}
    
    
//    public static ChromeDriver getChromeDriver() {
//		 System.setProperty("webdriver.chrome.driver", "D:\\TEST\\chromedriver.exe");
//		 ChromeDriver chromeDriver = new ChromeDriver();
//	     chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		 return chromeDriver;
//	}
    
    public static void setChromeDriver(ChromeDriver chromeDriver) {
		Driver.chromeDriver = chromeDriver;
	}


	public static void get(String url) {
		getChromeDriver().get(url);
	}
    
    public static void refresh() {
		getChromeDriver().navigate().refresh();
	}
    
    public static void action(StepModel step) {
    	String action = step.getAction();
    	if (action.equals("get")) {
			get(step.getElement().getElementValue());
		}else if (action.equals("refresh")) {
			refresh();
		}else {
//			System.out.println(action);
			System.err.println("不是写错了，就是还没写"+action);
		}
    }
    
}
