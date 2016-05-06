package com.gl.ecomm.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.gl.ecomm.infra.DataSourceFactory;
import com.gl.ecomm.mapper.DummyModelMapper;
import com.gl.ecomm.model.DummyModel;

public class MyBatisJavaBoot {
	
	public void start(){
		DataSource ds = DataSourceFactory.getDataSource();
		TransactionFactory tf = new JdbcTransactionFactory();
		Environment env = new Environment("development", tf, ds);
		Configuration cfg = new Configuration(env);
		
		cfg.addMapper(DummyModelMapper.class);
		
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(cfg);
		
		SqlSession session =  null;
		try{
			session = ssf.openSession();
			DummyModelMapper mapper = session.getMapper(DummyModelMapper.class);
			DummyModel model = mapper.findDummyModel("test1");
			
			System.out.println(model.getId());
		}finally{
			session.close();
		}
		
		
	}

	public static void main(String[] args) {
		new MyBatisJavaBoot().start();
	}

}
