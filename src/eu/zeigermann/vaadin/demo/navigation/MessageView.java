package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MessageView extends VerticalLayout implements View {
	public static final String NAME = "message";

	public MessageView() {
		setCaption("Messages");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() != null) {
			// split at "/", add each part as a label
			String[] msgs = event.getParameters().split("/");
			for (String msg : msgs) {
				addComponent(new Label(msg));
			}
		}
	}
}