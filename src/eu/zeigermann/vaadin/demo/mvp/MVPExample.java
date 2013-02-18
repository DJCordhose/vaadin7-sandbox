package eu.zeigermann.vaadin.demo.mvp;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;


public class MVPExample extends UI {
	@Override
	public void init(VaadinRequest request) {
		HorizontalLayout layout = new HorizontalLayout();
		setContent(layout);
		CalculatorModel model = new CalculatorModel();
		CalculatorViewImpl view = new CalculatorViewImpl();

		// The presenter binds the model and view together
		new CalculatorPresenter(model, view);

		// The view implementation is a Vaadin component
		layout.addComponent(view);
	}

}
