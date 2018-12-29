package TestAutomationZ.Selenium;

//import static org.testng.Assert.assertEquals;

import java.rmi.RemoteException;

import org.apache.http.util.Asserts;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import TestAutomationZ.webCase.StepModel;

public class WebElementZ {
	
	public static WebElement getWebElement(String type, String element) {
		WebElement webElement = null;
		WebDriverWait wait = new WebDriverWait(Driver.getChromeDriver(), 10);
		if (type.equals("XPath")) {
			webElement = Driver.getChromeDriver().findElementByXPath(element);
		} else if (type.equals("ClassName")) {
			webElement = Driver.getChromeDriver().findElementByClassName(element);
		} else if (type.equals("CssSelector")) {
			webElement = Driver.getChromeDriver().findElementByCssSelector(element);
		} else if (type.equals("TagName")) {
			webElement = Driver.getChromeDriver().findElementByTagName(element);
		} else if (type.equals("PartialLinkText")) {
			webElement = Driver.getChromeDriver().findElementByPartialLinkText(element);
		} else if (type.equals("Name")) {
			webElement = Driver.getChromeDriver().findElementByName(element);
		} else if (type.equals("LinkText")) {
			webElement = Driver.getChromeDriver().findElementByLinkText(element);
		} else if (type.equals("Id")) {
			webElement = Driver.getChromeDriver().findElementById(element);
		} else {
			try {
//				System.out.println(type);
				System.err.println("寻找元素方法除了问题type："+type+" element: "+element);
				throw new RemoteException();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//等待元素出现
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement;
	}

	public static String ElementAction(StepModel step) {
//		WebDriverWait wait = new WebDriverWait(Driver.getChromeDriver(), 10);
		String action = step.getAction();
		String element = step.getElement().getElementValue();
		String type = step.getElement().getElementByType();
		if (action.equals("click")) {
		getWebElement(type, element).click();
		} else if (action.equals("sendkeys")) {
			String value = step.getValue() == null ? "" : step.getValue();
			getWebElement(type, element).clear();
			getWebElement(type, element).sendKeys("a");
			getWebElement(type, element).sendKeys(Keys.BACK_SPACE);
			getWebElement(type, element).sendKeys(value);
		} else if (action.equals("submit")) {
			getWebElement(type, element).submit();
		} else if (action.equals("gettext")) {
			String actual = getWebElement(type, element).getText();
			Assertion asserts = new Assertion();
			asserts.assertEquals(step.getExpect(), actual);
		} else if (action.equals("movetoelement")) {
			Actions actions = new Actions(Driver.getChromeDriver());
			actions.moveToElement(getWebElement(type, element)).perform();
		}else if (action.equals("movetoelementclick")) {
			Actions actions = new Actions(Driver.getChromeDriver());
			actions.moveToElement(getWebElement(type, element)).build().perform();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		System.out.println("在这里输出了状态："+wait.until(ExpectedConditions.cl));	
//		wait.until(ExpectedConditions.elementToBeClickable(getWebElement(type, element)));
			actions.click(getWebElement(type, element)).perform();
			
		}else if (action.equals("get")) {
			Driver.get(element);
		}
		return type;
	}
}
