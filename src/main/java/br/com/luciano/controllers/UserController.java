/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.controllers;


import br.com.luciano.domain.Usuario;
import br.com.luciano.enums.Gender;
import br.com.luciano.services.CpfService;
import br.com.luciano.services.UserService;
import br.com.luciano.utils.FacesMessageUtil;
import br.com.luciano.ws.CepClient;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.persistence.exceptions.TransactionException;

/**
 *
 * @author Luciano
 */
@Named("userView")
@ViewScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;
    
    @Inject
    private CepClient cepClient;

    private boolean renderFormControl, controlConsulta;

    private String tipoConsulta, valorConsulta;

    private Usuario user;

    private List<Usuario> users;

    private List<SelectItem> selectItensGender;

    private Gender[] genderValues;

    public Usuario getUser() {
        if (user == null) {
            user = new Usuario();
        }
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void save() throws Exception {
        if(!validaCPF()){
            return;
        }
     
        if(cpfExits() && user.getId() == null){
            FacesMessageUtil.dynamicError("Operação não realizada. O CPF informado já está cadastrado!");
            return;
        }
        try {
            userService.save(user);
            setUser(null);
            messageSuccess();
            // saveLog();
        } catch (Exception ex) {
            messageErro();
            throw new TransactionException(ex.getMessage());

        }
    }

    public void atualizar(Usuario user) throws Exception {
        if (cpfExits()) {
            FacesMessageUtil.dynamicAlert("Atualização não efetuada! O CPF informado já está cadastrado no sistema");
        } else {
            try {
                userService.update(user);
                setUser(null);
                messageSuccess();
                // updateLog(user);
            } catch (Exception ex) {
                messageErro();
                throw new TransactionException(ex.getMessage());
            }
        }

    }

    public void remove() {
        try {
            userService.delete(getUser());
            setUser(null);
            messageSuccess();
            controlConsulta = false;
        } catch (Exception ex) {
            messageErro();
            throw new TransactionException(ex.getMessage());
        }

    }

    public Gender[] getGenderValues() {
        return Gender.values();
    }

    public void setGenderValues(Gender[] genderValues) {
        this.genderValues = genderValues;
    }

    public List<Usuario> getUsers() {
        if (!controlConsulta) {
            users = new ArrayList();
            users = userService.getAll();
        }

        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public boolean isRenderFormControl() {
        return renderFormControl;
    }

    public void setRenderFormControl(boolean renderFormControl) {
        this.renderFormControl = renderFormControl;
    }

    public void changeValue() {
        this.renderFormControl = true;
    }

    public List<SelectItem> getSelectItensGender() {
        if (selectItensGender == null) {
            selectItensGender = new ArrayList<>();
            for (Gender gender : Gender.values()) {
                selectItensGender.add(new SelectItem(gender.getCode(), gender.getGender()));
            }
        }
        return selectItensGender;
    }

    public void setSelectItensGender(List<SelectItem> selectItensGender) {
        this.selectItensGender = selectItensGender;
    }

    public void messageSuccess() {
        FacesMessageUtil.success();
    }

    public void messageErro() {
        FacesMessageUtil.error();
    }

    public void preUpdate(Usuario user) {
        this.user = user;
        setRenderFormControl(true);
    }

    public void preDestroy(Usuario user) {
        setUser(user);
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(String valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public boolean isControlConsulta() {
        return controlConsulta;
    }

    public void setControlConsulta(boolean controlConsulta) {
        this.controlConsulta = controlConsulta;
    }

    public void findByQuery() throws Exception {
        users = new ArrayList();
        controlConsulta = true;
        Map<String, Object> parametros = new HashMap();

        switch (tipoConsulta) {
            case "User.USER_BY_NAME":
                valorConsulta = valorConsulta.toLowerCase();
                parametros.put("nome", "%" + valorConsulta + "%");
                setUsers(userService.getResultListNamedQuery(Usuario.USER_BY_NAME, parametros));
                break;
            case "User.USER_BY_CPF":
                parametros.put("cpf", removeChar(valorConsulta));
                setUsers(userService.getResultListNamedQuery(Usuario.USER_BY_CPF, parametros));
                break;
            case "User.USER_BY_EMAIL":
                parametros.put("email", valorConsulta);
                setUsers(userService.getResultListNamedQuery(Usuario.USER_BY_EMAIL, parametros));
                break;
            case "Todos":
                setUsers(userService.getResultListNamedQuery(Usuario.USERS_GET_ALL, null));
                break;
            default:
               
        }

        valorConsulta = "";
        parametros = new HashMap();

    }

    public boolean cpfExits() throws Exception {
        Map<String, Object> parametros = new HashMap();
        parametros.put("cpf", user.getCpf());
        return (!userService.getResultListNamedQuery(Usuario.USER_BY_CPF, parametros).isEmpty());
    }

    public boolean validaCPF() throws Exception {
        boolean result = CpfService.isValidCPF(user.getCpf());
        if(!result){
            FacesMessageUtil.dynamicError("CPF Inválido!");
        
        }
        return result;
    }
    
    public void buscaEndereco() {
        user.setEndereco(cepClient.buscaEnderecoPorCEP(user.getEndereco().getCep()));
    }
    
    public String removeChar(String value){
        return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
   

}
