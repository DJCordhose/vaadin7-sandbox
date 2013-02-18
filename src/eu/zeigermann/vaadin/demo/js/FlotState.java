package eu.zeigermann.vaadin.demo.js;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.JavaScriptComponentState;

public class FlotState extends JavaScriptComponentState {
	public List<List<List<Double>>> series = new ArrayList<List<List<Double>>>();
}