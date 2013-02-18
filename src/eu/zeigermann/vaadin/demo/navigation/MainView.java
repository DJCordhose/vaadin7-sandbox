package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends Panel implements View {

	public static final String NAME = "";

	private Navigator navigator;

	private Button logoutButton;

	public MainView() {

		VerticalLayout verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
		verticalLayout.setSpacing(true);

		Link lnk = new Link("Count",
				new ExternalResource("#!" + CountView.NAME + "/parameter"));
		verticalLayout.addComponent(lnk);
		Button button = new Button("Click for count");
		verticalLayout.addComponent(button);
		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(CountView.NAME);
			}
		});
		Link messageLnk1 = new Link("Message: Hello", new ExternalResource("#!"
				+ MessageView.NAME + "/Hello"));
		verticalLayout.addComponent(messageLnk1);

		Link messageLnk2 = new Link("Message: Bye", new ExternalResource("#!"
				+ MessageView.NAME + "/Bye/Goodbye"));
		verticalLayout.addComponent(messageLnk2);

		verticalLayout.addComponent(new Link("Private message: Secret",
				new ExternalResource("#!" + SecretView.NAME + "/Secret")));

		verticalLayout.addComponent(new Link("Private message: Topsecret",
				new ExternalResource("#!" + SecretView.NAME + "/Topsecret")));

		logoutButton = new Button("Login", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (((NavigationtestUI) UI.getCurrent()).getUser() == null) {
					navigator.navigateTo(LoginView.NAME);
				} else {
					((NavigationtestUI) UI.getCurrent()).setUser(null);
					logoutButton.setCaption("Login");
				}
			}
		});
		verticalLayout.addComponent(logoutButton);
		
		 verticalLayout.addComponent(new Link("Settings", new ExternalResource("#!"
	                + SettingsView.NAME)));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator = event.getNavigator();
		// show/hide logout-button depending on whether user is logged in or not
		if (((NavigationtestUI) UI.getCurrent()).getUser() == null) {
			logoutButton.setCaption("Login");
		} else {
			logoutButton.setCaption("Logout");
		}
	}

}