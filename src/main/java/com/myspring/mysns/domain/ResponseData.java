package com.myspring.mysns.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

// controller에서 쓸 data
// @Component 필수
@Component
public class ResponseData {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseData.class);
	
	private int code;
	private String message;
	private Object data;
	
	// 생성자
	public ResponseData(){
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(HttpStatus httpStatus) {
		this.code = httpStatus.value();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		logger.info("call toString() method in ResponseData");
		return "ResponseData [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
