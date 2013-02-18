package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

	public static final String NAME = "login";

	public LoginView(final Navigator navigator,
			final String fragmentAndParameters) {

		final TextField email = new TextField("Email");
		addComponent(email);

		final PasswordField password = new PasswordField("Password");
		addComponent(password);

		final Button login = new Button("Login", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("Ok, let's pretend you're " + email);

				// indicate the user is logged in
				((NavigationtestUI) UI.getCurrent()).setUser(email.getValue());

				// navigate back to the intended place
				navigator.navigateTo(fragmentAndParameters);
			}
		});
		addComponent(login);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
