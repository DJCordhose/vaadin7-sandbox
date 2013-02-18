package eu.zeigermann.vaadin.demo.forms;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("serial")
public class StringToNameConverter implements Converter<String, Name> {
    public Name convertToModel(String text, Locale locale)
            throws ConversionException {
        if (text == null) {
            return null;
        }
        String[] parts = text.split(" ");
        if (parts.length != 2) {
            throw new ConversionException("Can not convert text to a name: "
                    + text);
        }
        return new Name(parts[0], parts[1]);
    }

    public String convertToPresentation(Name name, Locale locale)
            throws ConversionException {
        if (name == null) {
            return null;
        } else {
            return name.getFirstName() + " " + name.getLastName();
        }
    }

    public Class<Name> getModelType() {
        return Name.class;
    }

    public Class<String> getPresentationType() {
        return String.class;
    }
}