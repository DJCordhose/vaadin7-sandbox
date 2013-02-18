package eu.zeigermann.vaadin.demo.forms;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.data.util.converter.StringToNumberConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("unchecked")
public class FormsUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		setContent(layout);
		addTable(layout);
		addDataSourceField(layout);
		addConversionField(layout);
		addNameField(layout);
		VaadinSession.getCurrent()
				.setConverterFactory(new MyConverterFactory());
		TextField tf = new TextField("This is my double field");
		tf.setImmediate(true);
		tf.setConverter(Double.class);
		layout.addComponent(tf);
		tf.setConvertedValue(50.1);
	}

	private void addNameField(VerticalLayout layout) {
		Name name = new Name("Rudolph", "Reindeer");

		final TextField textField = new TextField("Name");
		textField.setConverter(new StringToNameConverter());
		textField.setConvertedValue(name);

		layout.addComponent(textField);
		layout.addComponent(new Button("Submit value", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					Name name = (Name) textField.getConvertedValue();
					Notification.show("First name: " + name.getFirstName()
							+ "<br />Last name: " + name.getLastName());
				} catch (ConversionException e) {
					Notification.show(e.getCause().getMessage());
				}
			}
		}));
	}

	private void addConversionField(VerticalLayout layout) {
		final TextField textField = new TextField("Text field");
		textField.setConverter(Integer.class);

		Button submitButton = new Button("Submit value", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				String uiValue = textField.getValue();
				try {
					Integer convertedValue = (Integer) textField
							.getConvertedValue();
					Notification.show("UI value (String): " + uiValue
							+ "<br />Converted value (Integer): "
							+ convertedValue);
				} catch (ConversionException e) {
					Notification.show("Could not convert value: " + uiValue);
				}
			}
		});

		layout.addComponent(new Label("Text field type: " + textField.getType()));
		layout.addComponent(new Label("Converted text field type: "
				+ textField.getConverter().getModelType()));
		layout.addComponent(textField);
		layout.addComponent(submitButton);
	}

	private void addDataSourceField(VerticalLayout layout) {
		final MyBean myBean = new MyBean();
		BeanItem<MyBean> beanItem = new BeanItem<MyBean>(myBean);

		final Property<Integer> integerProperty = (Property<Integer>) beanItem
				.getItemProperty("value");
		final TextField textField = new TextField("Text field", integerProperty);

		Button submitButton = new Button("Submit value", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				String uiValue = textField.getValue();
				int dataModelValue = myBean.getValue();

				Notification.show("UI value (String): " + uiValue
						+ "<br />Data model value (int): " + dataModelValue);
			}
		});

		layout.addComponent(new Label("Text field type: " + textField.getType()));
		layout.addComponent(new Label("Text field type: "
				+ integerProperty.getType()));
		layout.addComponent(textField);
		layout.addComponent(submitButton);
	}

	private void addTable(VerticalLayout layout) {
		String PERCENT_PROPERTY = "%";
		String CURRENCY_PROPERTY = "Currency";
		String DEFAULT_PROPERTY = "Default";

		Table table = new Table();
		table.setLocale(Locale.FRANCE);
		table.addContainerProperty(PERCENT_PROPERTY, Double.class, 0);
		table.addContainerProperty(CURRENCY_PROPERTY, Double.class, 0);
		table.addContainerProperty(DEFAULT_PROPERTY, Double.class, 0);

		Object itemId = table.addItem();
		table.getItem(itemId).getItemProperty(PERCENT_PROPERTY)
				.setValue(3.1415);
		table.getItem(itemId).getItemProperty(CURRENCY_PROPERTY)
				.setValue(3.1415);
		table.getItem(itemId).getItemProperty(DEFAULT_PROPERTY)
				.setValue(3.1415);

		Object itemId2 = table.addItem();
		table.getItem(itemId2).getItemProperty(PERCENT_PROPERTY)
				.setValue(3.1415);
		table.getItem(itemId2).getItemProperty(CURRENCY_PROPERTY)
				.setValue(3.1415);
		table.getItem(itemId2).getItemProperty(DEFAULT_PROPERTY)
				.setValue(3.1415);

		table.setConverter(PERCENT_PROPERTY, new StringToNumberConverter() {
			@Override
			protected NumberFormat getFormat(Locale locale) {
				return NumberFormat.getPercentInstance(locale);
			}
		});

		table.setConverter(CURRENCY_PROPERTY, new StringToNumberConverter() {
			@Override
			protected NumberFormat getFormat(Locale locale) {
				return NumberFormat.getCurrencyInstance(locale);
			}
		});

		layout.addComponent(table);
	}

}
