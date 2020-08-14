/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.validations;

import br.com.luciano.utils.FacesMessageUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Luciano
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern PADRAO = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        String email = value.toString();
        if(!validarEmail(email)){
            FacesMessageUtil.dynamicAlert("Email inválido!");
        }

    }

    public static boolean validarEmail(String email) {
        Matcher matcher = PADRAO.matcher(email);
        return matcher.matches();
    }

}
