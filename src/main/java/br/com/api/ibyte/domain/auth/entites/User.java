package br.com.api.ibyte.domain.auth.entites;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.api.ibyte.domain.base.entities.EntityBase;
import lombok.Data;

@Data
@Entity
@Table(name = "user_test")
public class User extends EntityBase<String>{
    private String login;
    
}
