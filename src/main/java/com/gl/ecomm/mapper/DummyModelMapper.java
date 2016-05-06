package com.gl.ecomm.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.gl.ecomm.model.DummyModel;


public interface DummyModelMapper {
	@Results(id="dummyModelResults", value={
			@Result(column="oid", property="oid"),
			@Result(column="create_at", property="createAt"),
			@Result(column="update_at", property="updateAt"),
			@Result(column="name", property="name"),
			@Result(column="identity_id", property="id")
	})
	@Select("select * from pesudo_domain where name = #{name}")
	DummyModel findDummyModel(String name);
}
