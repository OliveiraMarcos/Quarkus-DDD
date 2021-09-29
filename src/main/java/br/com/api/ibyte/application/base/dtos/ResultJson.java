package br.com.api.ibyte.application.base.dtos;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

import br.com.api.ibyte.application.base.dtos.enums.StatusCodeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ResultJson extends BaseDto<ResultJson>{
    @Getter(onMethod_ = {@JsonbProperty("code")})
    @Setter(onMethod_ = {@JsonbProperty("code")})
    private StatusCodeEnum code;

    @Getter(onMethod_ = {@JsonbProperty("data")})
    @Setter(onMethod_ = {@JsonbProperty("data")})
    private Object responseObject;

    @Getter(onMethod_ = {@JsonbProperty("message")})
    @Setter(onMethod_ = {@JsonbProperty("message")})
    private String message;

    @Getter(onMethod_ = {@JsonbProperty("is_success")})
    @Setter(onMethod_ = {@JsonbProperty("is_success")})
    private boolean isSuccess;

    @Getter(onMethod_ = {@JsonbProperty("errors")})
    @Setter(onMethod_ = {@JsonbProperty("errors")})
    private List<String> errors;
}
