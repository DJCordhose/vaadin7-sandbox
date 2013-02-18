package eu.zeigermann.vaadin.demo.component.shared;

import com.vaadin.shared.communication.ClientRpc;

public interface MyComponentClientRpc extends ClientRpc {

	public void alert(String message);

}