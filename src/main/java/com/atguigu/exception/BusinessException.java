package com.atguigu.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 错误状态码
     */
    protected String errorCode;
    /**
     * 错误提示
     */
    protected String errorMsg;

    public BusinessException(){

    }

    public BusinessException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
