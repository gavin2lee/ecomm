package com.gl.ecomm.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gl.ecomm.mapper.PesudoDomainMapper;
import com.gl.ecomm.model.PesudoDomain;

public class MyBatisXmlBoot {

	public static void testPesudoDomainOps() {
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
			System.out.println("PesudoDomain:" + pesudoDomain);

			int count = mapper.selectCount();
			System.out.println("COUNT:" + count);
			
			PesudoDomain pd = new PesudoDomain();
			pd.setOid(UUID.randomUUID().toString());
			pd.setCreateAt(new Date());
			pd.setUpdateAt(new Date());
			pd.setName("boot"+new Random().nextInt(1000));
			pd.setIdentityId(System.currentTimeMillis());
			
			mapper.insertPesudoDomain(pd);
			
			session.commit();
			

		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		testPesudoDomainOps();
	}

}
