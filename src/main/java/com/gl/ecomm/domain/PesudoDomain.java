package com.gl.ecomm.domain;

public class PesudoDomain extends BaseEntry {
	private String name;
	private Long identityId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Long identityId) {
		this.identityId = identityId;
	}

}
