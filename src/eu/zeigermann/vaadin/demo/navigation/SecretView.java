package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;

public class SecretView extends MessageView implements View {
	public static final String NAME = "secret";

	public SecretView() {
		setCaption("Private messages");

		addComponent(new Label("Some private stuff."));
	}

}