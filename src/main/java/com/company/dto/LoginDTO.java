package com.company.dto;

/* DTO(Data Transfer Object) 화면에서 전달되는 데이터를 수집하는 용도 */

public class LoginDTO {
	private String USER_EMAIL;
	private String USER_PASS;
	private boolean useCookie;
	
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	public String getUSER_PASS() {
		return USER_PASS;
	}
	public void setUSER_PASS(String uSER_PASS) {
		USER_PASS = uSER_PASS;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [USER_EMAIL=" + USER_EMAIL + ", USER_PASS=" + USER_PASS + ", useCookie=" + useCookie + "]";
	}
	
	
	
	
	
}
