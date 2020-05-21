package com.sql.conn;

public class AjaxResponse {

	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AjaxResponse [status=" + status + ", message=" + message + "]";
	}

}
