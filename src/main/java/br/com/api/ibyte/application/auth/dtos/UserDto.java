package br.com.api.ibyte.application.auth.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbProperty;

import br.com.api.ibyte.application.base.dtos.IdentityDto;
import lombok.Data;

@Data
public class UserDto extends IdentityDto<UserDto, String> {
    @Getter(onMethod_ = {@JsonbProperty("login")})
    @Setter(onMethod_ = {@JsonbProperty("login")})
    private String login;
}
