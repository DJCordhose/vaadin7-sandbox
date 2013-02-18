package eu.zeigermann.vaadin.demo.component.client;

import com.google.gwt.user.client.ui.Label;

public class MyComponentWidget extends Label {

    public static final String CLASSNAME = "mycomponent";

    public MyComponentWidget() {
        setStyleName(CLASSNAME);
    }
}