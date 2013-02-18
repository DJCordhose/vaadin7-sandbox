package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CountView extends VerticalLayout implements View {
	public static final String NAME = "count";

	private static int count = 1;

	public CountView() {
		addComponent(new Label("Created: " + count++));
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}