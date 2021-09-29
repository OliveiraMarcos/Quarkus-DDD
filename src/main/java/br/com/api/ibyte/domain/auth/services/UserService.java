package br.com.api.ibyte.domain.auth.services;

import javax.enterprise.context.ApplicationScoped;

import org.modelmapper.ModelMapper;

import br.com.api.ibyte.domain.auth.entites.User;
import br.com.api.ibyte.domain.auth.interfaces.repository.IUserRepository;
import br.com.api.ibyte.domain.auth.interfaces.services.IUserService;
import br.com.api.ibyte.domain.base.services.ServiceBase;

@ApplicationScoped
public class UserService extends ServiceBase<User, String> implements IUserService {

    private IUserRepository baseRepository;

    public UserService(IUserRepository baseRepository, ModelMapper mapper) {
        super(baseRepository);
        this.baseRepository = baseRepository;
    }
    
}
