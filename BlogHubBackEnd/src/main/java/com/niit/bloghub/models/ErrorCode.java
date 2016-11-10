package com.niit.bloghub.models;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class ErrorCode {

	@Transient
	private int code;
	
	@Transient
	private String errormsg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
}
