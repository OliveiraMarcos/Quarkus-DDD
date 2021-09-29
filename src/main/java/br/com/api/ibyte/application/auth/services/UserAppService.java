package br.com.api.ibyte.application.auth.services;

import javax.enterprise.context.ApplicationScoped;

import org.modelmapper.ModelMapper;

import br.com.api.ibyte.application.auth.dtos.UserDto;
import br.com.api.ibyte.application.auth.interfaces.services.IUserAppService;
import br.com.api.ibyte.application.base.services.AppServiceBase;
import br.com.api.ibyte.domain.auth.entites.User;
import br.com.api.ibyte.domain.auth.interfaces.services.IUserService;

@ApplicationScoped
public class UserAppService extends AppServiceBase<UserDto,User> implements IUserAppService {
    private final IUserService _serviceBase;
    public UserAppService(IUserService serviceBase, ModelMapper mapper) {
        super(serviceBase, mapper);
        this._serviceBase = serviceBase;
    }
}
