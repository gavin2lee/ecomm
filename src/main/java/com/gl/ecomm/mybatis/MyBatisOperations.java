package com.gl.ecomm.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisOperations {
	private SqlSessionFactory ssf;
	private static MyBatisOperations instance;
	public static final String CONFIG_FILE = "mybatis-config.xml";

	private static ReentrantLock lock = new ReentrantLock();

	private MyBatisOperations() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(CONFIG_FILE);
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to initialize " + MyBatisOperations.class, e);
		}
	}

	public static MyBatisOperations instance() {
		if (instance == null) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new MyBatisOperations();
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}
	
	public SqlSession createSqlSession(){
		return ssf.openSession();
	}
	
	public void destroy(SqlSession session){
		if(session != null){
			session.close();
		}
	}
}
