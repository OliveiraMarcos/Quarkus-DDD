package br.com.api.ibyte.infra.data.auth.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import br.com.api.ibyte.domain.auth.entites.User;
import br.com.api.ibyte.domain.auth.interfaces.repository.IUserRepository;
import br.com.api.ibyte.infra.data.base.repository.RepositoryBase;

@ApplicationScoped
public class UserRepository extends RepositoryBase<User, String> implements IUserRepository {

    private EntityManager em;

    public UserRepository(EntityManager em) {
        super(em, User.class);
        this.em = em;
    }
    
}
