package com.gl.ecomm.domain;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4117605025249296416L;
	private String oid;// UUID
	private Date createAt;
	private Date updateAt;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createTime) {
		this.createAt = createTime;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateTime) {
		this.updateAt = updateTime;
	}

}
