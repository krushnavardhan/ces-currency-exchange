package com.ce.beans;

public class ErrorResponse {
	private String code;
	private String message;

	public ErrorResponse(String code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
