package TestAutomationZ.WebPage;

import java.util.List;

public class ElementModel {
	String elementName;
	String elementValue;
	String elementByType;

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	public String getElementByType() {
		return elementByType;
	}

	public void setElementByType(String elementByType) {
		this.elementByType = elementByType;
	}

	@Override
	public String toString() {
		return "ElementModel [elementName=" + elementName + ", elementValue=" + elementValue + ", elementByType="
				+ elementByType + "]\n";
	}
	
	

}
