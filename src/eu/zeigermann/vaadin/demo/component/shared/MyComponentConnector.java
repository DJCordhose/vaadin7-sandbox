package eu.zeigermann.vaadin.demo.component.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

import eu.zeigermann.vaadin.demo.component.client.MyComponentWidget;
import eu.zeigermann.vaadin.demo.component.server.MyComponent;

@Connect(MyComponent.class)
public class MyComponentConnector extends AbstractComponentConnector {

	MyComponentServerRpc rpc = RpcProxy
			.create(MyComponentServerRpc.class, this);

	public MyComponentConnector() {
		 registerRpc(MyComponentClientRpc.class, new MyComponentClientRpc() {
	            public void alert(String message) {
	                Window.alert(message);
	            }
	        });
		getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
						.buildMouseEventDetails(event.getNativeEvent(),
								getWidget().getElement());

				rpc.clicked(mouseDetails);
			}
		});
	}

	@Override
	public MyComponentState getState() {
		return (MyComponentState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		final String text = getState().text;
		getWidget().setText(text);
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(MyComponentWidget.class);
	}

	@Override
	public MyComponentWidget getWidget() {
		return (MyComponentWidget) super.getWidget();
	}
}