package com.soft.tbk.exception;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4077399861047800805L;

	private String errCode;
	private String errMsg;

	public ApiException() {
		super();
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
		this.errCode = message;
		this.errMsg = "操作失败";
	}

	public ApiException(String message) {
		super(message);
		this.errCode = message;
		this.errMsg = "操作失败";
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(String errCode, String errMsg, Throwable cause) {
		super(errCode + ":" + errMsg, cause);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public ApiException(String errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public ApiException(String errCode, String errMsg, String logMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
