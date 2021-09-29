package br.com.api.ibyte.application.base.dtos;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;


import lombok.Getter;
import lombok.Setter;

// @Data
public abstract class IdentityDto<T, I extends Serializable> extends BaseDto<T>{
    
    @Getter(onMethod_ = {@JsonbProperty("id")})
    @Setter(onMethod_ = {@JsonbProperty("id")})
    private I id;

}
