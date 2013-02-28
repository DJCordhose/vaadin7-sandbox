package eu.zeigermann.vaadin.demo.forms.masterdetail;

import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("olli")
// braucht den apache validator und die ganzen anderen wirren jars in web-inf/lib
public class AddressFormsUI extends UI {

	private GridLayout form;
	private HorizontalLayout tableControls;
	private Table table;
	private HorizontalLayout formControls;
	private IBackend backend;
	private FieldGroup fieldGroup = new FieldGroup();

	@Override
	protected void init(VaadinRequest request) {
		backend = new DummyBackend();
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		mainLayout.addComponent(buildTableControls());
		mainLayout.addComponent(buildTable());
		mainLayout.addComponent(buildForm());
		mainLayout.addComponent(buildFormControls());

		setContent(mainLayout);

		// to make sure fields are initially bound (otherwise we get exceptions
		// when we directly add a new person)
		editPerson(null);
	}

	private Component buildTable() {
		table = new Table(null);
		table.setWidth("500px");
		table.setSelectable(true);
		table.setImmediate(true);
		updateTableData();
		table.addValueChangeListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				editPerson((Person) table.getValue());
			}
		});
		return table;
	}

	private void updateTableData() {
		List<Person> persons = backend.getPersons();
		BeanItemContainer<Person> container = new BeanItemContainer<Person>(
				Person.class, persons);
		table.setContainerDataSource(container);

		table.setVisibleColumns(new String[] { "firstName", "lastName",
				"phoneNumber", "email", "dateOfBirth" });
		table.setColumnHeaders(new String[] { "First name", "Last name",
				"Phone number", "E-mail address", "Date of birth" });
		table.sort(new Object[] { "firstName", "lastName" }, new boolean[] {
				true, true });
	}

	private Component buildForm() {
		form = new GridLayout(2, 3);

		TextField firstName = new TextField("First name:");
		TextField lastName = new TextField("Last name:");
		TextField phoneNumber = new TextField("Phone Number:");
		TextField email = new TextField("E-mail address:");
		DateField dateOfBirth = new DateField("Date of birth:");
		TextArea comments = new TextArea("Comments:");

		firstName.addValidator(new BeanValidator(Person.class, "firstName"));
		lastName.addValidator(new BeanValidator(Person.class, "lastName"));
		email.addValidator(new BeanValidator(Person.class, "email"));
		dateOfBirth
				.addValidator(new BeanValidator(Person.class, "dateOfBirth"));
		
		// short version if we had all the fields inside a single layout
		// fieldGroup.bindMemberFields(myFormLayout);

		fieldGroup.bind(firstName, "firstName");
		fieldGroup.bind(lastName, "lastName");
		fieldGroup.bind(phoneNumber, "phoneNumber");
		fieldGroup.bind(email, "email");
		fieldGroup.bind(dateOfBirth, "dateOfBirth");
		fieldGroup.bind(comments, "comments");

		form.addComponent(firstName);
		form.addComponent(lastName);
		form.addComponent(phoneNumber);
		form.addComponent(email);
		form.addComponent(dateOfBirth);
		form.addComponent(comments);
		return form;
	}

	private Component buildTableControls() {
		tableControls = new HorizontalLayout();
		Button add = new Button("Add", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				editPerson(null);
			}
		});
		add.setPrimaryStyleName("olli-knopf");
		Button delete = new Button("Delete", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				backend.deletePerson((Person) table.getValue());
				updateTableData();
			}
		});
		tableControls.addComponent(add);
		tableControls.addComponent(delete);
		return tableControls;
	}

	private Component buildFormControls() {
		formControls = new HorizontalLayout();
		Button save = new Button("Save", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					Person person = ((BeanItem<Person>) fieldGroup
							.getItemDataSource()).getBean();
					if (fieldGroup.isValid()) {
						fieldGroup.commit();
						backend.storePerson(person);
						updateTableData();
						editPerson(null);
					}
				} catch (CommitException e) {
					e.printStackTrace();
				}
			}
		});
		Button discard = new Button("Discard", new ClickListener() {
			public void buttonClick(ClickEvent event) {
				fieldGroup.discard();
			}
		});
		formControls.addComponent(save);
		formControls.addComponent(discard);
		return formControls;
	}

	private void editPerson(Person person) {
		if (person == null) {
			person = new Person();
		}
		BeanItem<Person> item = new BeanItem<Person>(person);
		fieldGroup.setItemDataSource(item);
	}
}