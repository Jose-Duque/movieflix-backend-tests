package com.devsuperior.movieflix.dto;

public class UserPasswordDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	private String password;
	
	public UserPasswordDTO() {
		super();
	}

	public UserPasswordDTO(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
