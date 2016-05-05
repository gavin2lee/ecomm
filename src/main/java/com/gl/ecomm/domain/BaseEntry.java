package com.gl.ecomm.domain;

import java.util.Date;

public class BaseEntry {
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
