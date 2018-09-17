package com.soft.tbk.base;

import java.io.Serializable;

public class ResultResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5024801345215512103L;

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    public static final String DEFAULT_ERR_MSG = "处理失败";

    public static transient final ResultResponse ERROR_RESPONSE = new ResultResponse(ERROR, DEFAULT_ERR_MSG);

    private String code;

    private String msg;

    private Object data;

    public ResultResponse() {
        this.code = SUCCESS;
    }

    public ResultResponse(Object data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public ResultResponse(String errorCode, String errorMsg) {
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
