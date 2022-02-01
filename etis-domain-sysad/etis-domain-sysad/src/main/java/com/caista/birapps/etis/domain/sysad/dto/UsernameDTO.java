package com.caista.birapps.etis.domain.sysad.dto;

public class UsernameDTO {

	private String username;
	private String office;
	private String officeType;
	
	public UsernameDTO(String username, String office, String officeType) {
		super();
		this.username = username;
		this.office = office;
		this.officeType = officeType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	@Override
	public String toString() {
		return "UsernameDTO [username=" + username + ", office=" + office + ", officeType=" + officeType + "]";
	}
	
	
	
}
