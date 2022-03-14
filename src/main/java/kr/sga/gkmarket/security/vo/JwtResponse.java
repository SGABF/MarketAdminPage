package kr.sga.gkmarket.security.vo;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -654588135942577580L;
	
	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public String getToken() {
		return this.jwttoken;
	}
}
