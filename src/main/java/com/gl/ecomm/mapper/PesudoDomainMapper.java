package com.gl.ecomm.mapper;

import com.gl.ecomm.model.PesudoDomain;

public interface PesudoDomainMapper {
	PesudoDomain selectPesudoDomain(String oid);
	
	int selectCount();
	
	void insertPesudoDomain(PesudoDomain pd);
}
