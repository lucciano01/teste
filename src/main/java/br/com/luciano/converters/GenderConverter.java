/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.converters;

import br.com.luciano.enums.Gender;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luciano
 */
@FacesConverter(value = "genderConverter")
public class GenderConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        System.out.println("Value asObject: " +value);
        if (value != null) {
            return Gender.toEnum(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        System.out.println("Value asString: " +value);
        if (value != null) {
            return Gender.toEnum(Integer.valueOf(value.toString())).getGender();
        }
        return null;
    }

}
