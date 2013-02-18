package eu.zeigermann.vaadin.demo.js;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class JsUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		setContent(layout);
		// https://github.com/flot/flot/blob/master/README.md
		Flot flot = new Flot();
		flot.addSeries(1.0, 2.0, 3.0, 4.0, 2, 10, -5);
		flot.setWidth("600px");
		flot.setHeight("300px");
		layout.addComponent(flot);
		// call notify("Hello") from JS console
		JavaScript.getCurrent().addFunction("notify", new JavaScriptFunction() {
			public void call(JSONArray arguments) throws JSONException {
				Notification.show("Index=" + arguments.getString(0) + ", Data="+arguments.getString(1));
			}
		});
	}

}
