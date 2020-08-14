/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.test;


import br.com.luciano.domain.User;
import br.com.luciano.services.CpfService;
import br.com.luciano.services.UserService;
import br.com.luciano.validations.EmailValidator;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Luciano
 */

public class UserTest {
    
    private User user;
  
    private UserService userService;
      
    
    @Before
    public void setUp(){
        user = new User();
          
    }
    
    @Test
    public void shouldBeValidateCpf(){
        String cpfValido = "578.593.490-60";
        String cpfInvalido = "111.111.111-11";
        assertTrue(cpfValido, CpfService.isValidCPF(cpfValido));
        assertFalse(cpfInvalido, CpfService.isValidCPF(cpfInvalido));
    }
    
    @Test(expected = Exception.class)
    public void shouldBeSaveUser(){
        user.setId(1l);
        user.setEmail("luciano@email.com");
        user.setName("Teste");
        user.setGender("Masculino");
        user.setCpf("");
        userService = Mockito.mock(UserService.class);
        Mockito.doThrow(new Exception()).doNothing().when(userService).save(user);
    }
    
    
    @Test
    public void shouldBeListAllUsers(){
        user.setId(1l);
        user.setEmail("luciano@email.com");
        user.setName("Teste");
        user.setGender("Masculino");
        user.setCpf("");
        
        userService = Mockito.mock(UserService.class);
        Mockito.doReturn(Arrays.asList(user));
    }
    
    
    @Test
    public void shouldBeValidateEmail(){
        String emailValido = "luciano@email.com";
        String emailInvalido = "luciano.com";
        assertTrue(EmailValidator.validarEmail(emailValido));
        assertFalse(EmailValidator.validarEmail(emailInvalido));
                
    }
 
    
    
}
