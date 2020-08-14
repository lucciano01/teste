/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.validations;

import br.com.luciano.utils.FacesMessageUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Luciano
 */
@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {
    
    private static final int[] WEIGHT_SSN = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        String cpf = value.toString();
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        if(!isValidCPF(cpf)){
           FacesMessageUtil.dynamicAlert("CPF inválido!. Informe um CPF válido");
        }
    }
    
   
    private static int recursiveSum(int[] weight, char[] chr, int number) {
        if (number <= 0) return 0;
        final int chrIndex = number - 1;
        final int weightIndex = weight.length > chr.length ? number : chrIndex;
        return (recursiveSum(weight, chr, chrIndex) +
                Character.getNumericValue(chr[chrIndex]) * weight[weightIndex]);
    }

    private static int calculate(final String str, final int[] weight) {
        final char[] chr = str.toCharArray();
        int sum = recursiveSum(weight, chr, chr.length);
        sum = 11 - (sum % 11);
        return sum > 9 ? 0 : sum;
    }

    private static boolean checkEquals(String tfn, int length, int[] weight) {
        final String number = tfn.substring(0, length);
        final int digit1 = calculate(number, weight);
        final int digit2 = calculate(number + digit1, weight);
        return tfn.equals(number + digit1 + digit2);
    }

    /**
     * Valida CPF
     *
     * @param ssn
     * @return
     */
    public static boolean isValidCPF(String ssn) {
        if (ssn == null || !ssn.matches("\\d{11}") || ssn.matches(ssn.charAt(0) + "{11}")) return false;
        return checkEquals(ssn, 9, WEIGHT_SSN);
    }
    
}
