package Util;

public class StepModel {
	String step;
	String Element;
    String precondition;
	String type;
	String action;
	String value;
	String expect;
	String object;

	public String getObject() {
		return object;
	}

	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getElement() {
		return Element;
	}

	public void setElement(String element) {
		Element = element;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StepModel [step=" + step + ", Element=" + Element + ", type=" + type + ", action=" + action +"\n"+ ", value="
				+ value + ", expect=" + expect + ", object=" + object + "]"+"\n";
	}


}
