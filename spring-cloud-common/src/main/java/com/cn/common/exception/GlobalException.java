package com.cn.common.exception;

import com.cn.common.pojo.RestCode;

/**
 * @author ngcly
 */
public class GlobalException extends RuntimeException {
    private int code;
    private String message;

    public GlobalException(RestCode restCode){
        this(restCode.code,restCode.msg);
    }

    public GlobalException(String msg){
        this(RestCode.SERVER_ERROR.code,msg);
    }

    public GlobalException(int code, String msg) {
        super(msg);
        this.setCode(code);
        this.setMessage(msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
