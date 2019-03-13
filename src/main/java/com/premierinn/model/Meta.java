package com.premierinn.model;

public class Meta {

    private Integer code;
    private String requestId;
    private String errorMessage;

    public Meta() {
    }

    public Meta(Integer code, String requestId, String errorMessage) {
        this.code = code;
        this.requestId = requestId;
        this.errorMessage = errorMessage;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
