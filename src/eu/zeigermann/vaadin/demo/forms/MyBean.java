package eu.zeigermann.vaadin.demo.forms;

import java.io.Serializable;

public class MyBean implements Serializable {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int integer) {
		value = integer;
	}
}