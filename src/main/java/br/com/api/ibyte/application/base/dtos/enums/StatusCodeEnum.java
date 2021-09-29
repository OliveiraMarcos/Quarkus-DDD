package br.com.api.ibyte.application.base.dtos.enums;

public enum StatusCodeEnum {
    OK_200(200),
    CREATED_201(201),
    ACCEPTED_202(202),
    NO_CONTENT_204(204),
    BAD_REQUEST_400(400),
    UNAUTHORIZED_401(401),
    FORBIDDEN_403(403),
    NOT_FOUND_404(404),
    INTERNAL_SERVER_ERROR_500(500),
    NOT_IMPLEMENTED_501(501);

    private int value;
    StatusCodeEnum(int code) {
        this.value = code;
    }

    public int getValue() {
        return value;
    }
}
