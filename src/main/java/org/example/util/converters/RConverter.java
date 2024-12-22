package org.example.util.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "RConverter")
public class RConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.trim().isEmpty()){
            return null;
        }
        try{
            double r = Double.parseDouble( value.replace(",","."));
            if (r > 4 || r < 1){
                throw new ConverterException("Invalid r");
            }
            return r;
       }    catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid data for r", "\nНеверное значение (1<r<4)");
            facesContext.addMessage(uiComponent.getClientId(facesContext),facesMessage );
            throw new ConverterException(facesMessage);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null){
            return "";
        }

        return value.toString();
    }
}
