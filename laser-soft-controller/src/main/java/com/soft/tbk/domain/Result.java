package com.soft.tbk.domain;

import java.io.Serializable;

public class Result implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5024801345215512103L;

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    public static final String DEFAULT_ERR_MSG = "处理失败";

    public static transient final Result ERROR_RESPONSE = new Result(ERROR, DEFAULT_ERR_MSG);

    private String code;

    private String msg;

    private Object data;

    public Result() {
        this.code = SUCCESS;
    }

    public Result(Object data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public Result(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.msg = errorMsg;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

    public Object getData() {

        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public boolean isSuccess() {

        return SUCCESS.equals(code);
    }

}
