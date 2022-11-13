package com.CMS.model;

public class Administrator {

	
	private String username;
	private String password;
	
	public Administrator() {
		
	}

	public Administrator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
