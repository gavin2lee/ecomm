package com.gl.ecomm.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gl.ecomm.domain.PesudoDomain;
import com.gl.ecomm.domain.PesudoDomainMapper;

public class MyBatisXmlBoot {
	
	public static void testPesudoDomainOps(){
		String resource = "mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = sessionFactory.openSession();
		try {
		  PesudoDomainMapper mapper = session.getMapper(PesudoDomainMapper.class);
		  PesudoDomain pesudoDomain = mapper.selectPesudoDomain("10001");
		  System.out.println(pesudoDomain.getName());
		} finally {
		  session.close();
		}
	}

	public static void main(String[] args) {
		testPesudoDomainOps();
	}

}
