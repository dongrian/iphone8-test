package com.gaeasoft.preorder.web.support;

public class Response {

	//response = new Response(ResponseStatus.OK, "정상", cm);
	
	public enum RespStatus {
		OK,
		EMPTY_RESULT,
		WARN,
		FAIL,
		ERROR;
	}
	
	private RespStatus status;
	private String message;
	private Object data;
	
	public Response(RespStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Response(RespStatus status, String message, Object data) {
		this(status, message);
		this.data = data;
	}

	public RespStatus getStatus() {
		return status;
	}

	public void setStatus(RespStatus status) {
		this.status = status;
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
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
}
