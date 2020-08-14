/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.enums;

import java.util.Objects;

/**
 *
 * @author Luciano
 */

public enum Gender {
    
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino");

    
    private Integer code;
    private String gender;
    
    private Gender(Integer code, String gender){
        this.code = code;
        this.gender = gender;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public static Gender toEnum(Integer code){
        
        for(Gender gender: Gender.values()){
            if(Objects.equals(gender.getCode(), code)){
                return gender;
            }
        }
        
        return null;
    }
     public static Gender toEnum(String value){
        
        for(Gender gender: Gender.values()){
            if(Objects.equals(gender.gender, value)){
                return gender;
            }
        }
        
        return null;
    }
    
}
