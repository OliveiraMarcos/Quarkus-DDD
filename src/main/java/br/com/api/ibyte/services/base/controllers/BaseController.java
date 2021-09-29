package br.com.api.ibyte.services.base.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.api.ibyte.application.base.dtos.ResultJson;
import br.com.api.ibyte.application.base.dtos.enums.StatusCodeEnum;

public abstract class BaseController {
    public ResultJson response(StatusCodeEnum code, Object data, String message, List<String> errors) {
        var result = new ResultJson();
        result.setCode(code);
        if(code.getValue() >= 200 && code.getValue() <= 299){
            result.setSuccess(true);
        }else{
            result.setSuccess(false);
        }
        result.setMessage(message);
        result.setResponseObject(data);
        result.setErrors(errors);
        return result;
    }

    public ResultJson response(StatusCodeEnum code, Object data, String message) {
        return response(code, data, message, null);
    }

    public ResultJson response(StatusCodeEnum code, Object data) {
        var message = "";
        switch (code) {
            case OK_200:
           ACCEPTED_202:
         NO_CONTENT_204:
                message = "Success!";
                break;
            case CREATED_201:
                message = "Created!";
                break;

            case BAD_REQUEST_400:
                message = "Error!";
                break;

            case UNAUTHORIZED_401:
            case FORBIDDEN_403:
                message = "Error of Credential";
                break;

            case NOT_FOUND_404:
                message = "Not found!";
                break;

            case INTERNAL_SERVER_ERROR_500:
            case NOT_IMPLEMENTED_501:
                message = "Error Serious Error!";
                break;

                
            default:
                message = "NoN!";
                break;
        }
        return response(code, data, message, null);
    }

    public ResultJson response(Object data) {
        var code = StatusCodeEnum.OK_200;
        if(data == null){
            code = StatusCodeEnum.NOT_FOUND_404;
        }
        return response(code, data);
    }

    public ResultJson response(Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return response(StatusCodeEnum.NOT_IMPLEMENTED_501, null, "Error!", errors);
    }

    public ResultJson response(StatusCodeEnum code, Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return response(code, null, "Error!", errors);
    }

    public ResultJson response(StatusCodeEnum code, Object data, Exception e) {
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        return response(code, data, "Error!", errors);
    }

}
