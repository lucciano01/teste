/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luciano.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Luciano
 */
@Entity
@NamedQueries({
@NamedQuery(name = Usuario.USERS_GET_ALL, query = "select u from Usuario u order by u.name"),
@NamedQuery(name = Usuario.USER_BY_NAME, query = "select u from Usuario u where lower(u.name) like :nome"),
@NamedQuery(name = Usuario.USER_BY_CPF, query = "select u from Usuario u where u.cpf = :cpf"),
@NamedQuery(name = Usuario.USER_BY_EMAIL, query = "select u from Usuario u where u.email = :email")
    
})
@Table(name = "usuario")
public class Usuario implements Serializable {
    
    public static final String USERS_GET_ALL = "users.getAll";
    public static final String USER_BY_NAME = "user.ByName";
    public static final String USER_BY_CPF = "user.ByCpf";
    public static final String USER_BY_EMAIL = "user.ByEmail";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    @Size(min = 2)
    private String name;
    
    private String gender;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfBirth;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    private String email;
    
    private String citizenShip;
    
    private String placeOfBirth;
    
    @Embedded
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(String email) {
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCitizenShip() {
        return citizenShip;
    }

    public void setCitizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Endereco getEndereco() {
      if(endereco == null)  {
          endereco = new Endereco();
      }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.gender);
        hash = 97 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 97 * hash + Objects.hashCode(this.cpf);
        hash = 97 * hash + Objects.hashCode(this.citizenShip);
        hash = 97 * hash + Objects.hashCode(this.placeOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.citizenShip, other.citizenShip)) {
            return false;
        }
        if (!Objects.equals(this.placeOfBirth, other.placeOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id:"+  this.getId() + " - Nome: " +this.name+ "- Email: "+ this.email;
    }
    
    
    
}


