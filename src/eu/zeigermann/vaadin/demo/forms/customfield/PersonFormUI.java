package eu.zeigermann.vaadin.demo.forms.customfield;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PersonFormUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		AddressPopup addressPopup = new AddressPopup();
		mainLayout.addComponent(addressPopup);
		addressPopup.setValue(new Address());
		setContent(mainLayout);
	}
}