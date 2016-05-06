package com.gl.ecomm.model;

public class PesudoDomain extends BaseEntry {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4860107924170553008L;
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

	@Override
	public String toString() {
		return "PesudoDomain [getName()=" + getName() + ", getIdentityId()=" + getIdentityId() + ", getOid()="
				+ getOid() + ", getCreateAt()=" + getCreateAt() + ", getUpdateAt()=" + getUpdateAt() + "]";
	}

	
	
	

}
