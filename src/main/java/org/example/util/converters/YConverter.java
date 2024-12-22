package org.example.util.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
@FacesConverter(value = "YConverter")
public class YConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            value = value.replace(',', '.');
            Double y = Double.parseDouble(value);
            if ( y > 3 || y < -5){
                throw new ConverterException("invalid y");
            }
            return y;
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid data for y", "\nНеверное значение y(-3<y<3)");
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
