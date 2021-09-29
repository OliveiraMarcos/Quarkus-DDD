package br.com.api.ibyte.application.auth.interfaces.services;

import br.com.api.ibyte.application.auth.dtos.UserDto;
import br.com.api.ibyte.application.base.interfaces.services.IAppServiceBase;
import br.com.api.ibyte.domain.auth.entites.User;

public interface IUserAppService extends IAppServiceBase<UserDto,User> {
    
}
