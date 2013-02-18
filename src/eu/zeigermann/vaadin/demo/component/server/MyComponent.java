package eu.zeigermann.vaadin.demo.component.server;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.AbstractComponent;

import eu.zeigermann.vaadin.demo.component.shared.MyComponentClientRpc;
import eu.zeigermann.vaadin.demo.component.shared.MyComponentServerRpc;
import eu.zeigermann.vaadin.demo.component.shared.MyComponentState;

public class MyComponent extends AbstractComponent {

	private int clickCount = 0;

	private MyComponentServerRpc rpc = new MyComponentServerRpc() {
		public void clicked(MouseEventDetails mouseDetails) {
			clickCount++;

			// nag every 5:th click
			if (clickCount % 5 == 0) {
				getRpcProxy(MyComponentClientRpc.class).alert(
						"Ok, that's enough!");
			}

			setText("You have clicked " + clickCount + " times");
		}
	};

	public MyComponent() {
		registerRpc(rpc);
	}

	@Override
	public MyComponentState getState() {
		return (MyComponentState) super.getState();
	}

	public void setText(String text) {
		getState().text = text;
		// markAsDirty();
	}

	public String getText() {
		return getState().text;
	}

}