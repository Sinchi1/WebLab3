package org.example.util.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.ArrayList;

@FacesConverter(value = "XConverter")
public class XConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            value = value.replace(',', '.');
            Double x = Double.parseDouble(value);
            ArrayList<Double> check = new ArrayList<>();
            for (double i=-2; i >= 2; i += 0.5){
                check.add(i);
            }
            if (check.contains(x)){
                throw new ConverterException("invalid x");
            }
            return x;
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid data for x", "\nНеверное значение x(Кааак?)");
            facesContext.addMessage(uiComponent.getClientId(facesContext), facesMessage);
            throw new ConverterException(facesMessage);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
