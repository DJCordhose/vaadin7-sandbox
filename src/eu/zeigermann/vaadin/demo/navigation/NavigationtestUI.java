package eu.zeigermann.vaadin.demo.navigation;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class NavigationtestUI extends UI {

	String user;

	@Override
	public void init(VaadinRequest request) {

		// Create Navigator, use the UI content layout to display the views
		final Navigator navigator = new Navigator(this, this);

		// Add some Views
		navigator.addView(MainView.NAME, new MainView()); // no fragment

		// #count will be a new instance each time we navigate to it, counts:
		navigator.addView(CountView.NAME, CountView.class);

		// #message adds a label with whatever it receives as a parameter
		navigator.addView(MessageView.NAME, new MessageView());

		// #message adds a label with whatever it receives as a parameter
		navigator.addView(SecretView.NAME, new SecretView());

		navigator.addView(LoginView.NAME, new LoginView(navigator,
				MainView.NAME));

		navigator.addView(SettingsView.NAME, new SettingsView(navigator));

		navigator.addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				if (event.getNewView() instanceof SecretView
						&& ((NavigationtestUI) UI.getCurrent()).getUser() == null) {
					// Show to LoginView instead, pass intended view
					String fragmentAndParameters = event.getViewName();
					if (event.getParameters() != null) {
						fragmentAndParameters += "/";
						fragmentAndParameters += event.getParameters();
					}
					navigator.getDisplay().showView(
							new LoginView(navigator, fragmentAndParameters));
					return false;

				} else {
					return true;
				}
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}

		});

		System.out.println(request.getPathInfo());
		// react to initial fragment, received before we created the Navigator
		navigator.navigateTo(MainView.NAME);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
