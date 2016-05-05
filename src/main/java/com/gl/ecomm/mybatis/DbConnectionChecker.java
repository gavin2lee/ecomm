package com.gl.ecomm.mybatis;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.jolbox.bonecp.BoneCPDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbConnectionChecker {
	private final String driverClass = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://127.0.0.1:3306/ecomm";
	private final String uname = "gavin";
	private final String pw = "123456";

	private DataSource getDruidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");//6.0.2
		dataSource.setUsername("gavin");
		dataSource.setPassword("123456");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ecomm");
		dataSource.setInitialSize(5);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(10); 
		// 启用监控统计功能
										// dataSource.setFilters("stat");
		// for
										// mysql
										// dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}
	
	private DataSource getDbcpDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(uname);
		ds.setPassword(pw);
		
		return ds;
	}
	
	private DataSource getC3P0DataSource(){
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(driverClass);
			ds.setJdbcUrl(url);
			ds.setUser(uname);
			ds.setPassword(pw);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		return ds;
	}
	
	private DataSource getBonecpDataSource(){
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		BoneCPDataSource ds = new BoneCPDataSource();
		
		ds.setJdbcUrl(url);
		ds.setUser(uname);
		ds.setPassword(pw);
		
		return ds;
	}
	
	private DataSource getDataSource(){
//		return getDruidDataSource();
//		return getDbcpDataSource();
//		return getC3P0DataSource();
		return getBonecpDataSource();
	}
	
	public void testConnection(){
		DataSource ds = this.getDataSource();
		Connection conn =null;
		Statement st = null;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			st.executeQuery("select 1");
			st.executeUpdate("create table if not exists test_conn (id varchar(20))");
			st.executeUpdate("insert test_conn values('100')");
			
			Thread.sleep(5*1000);
			
			st.executeUpdate("drop table if exists test_conn");
			
			System.out.println("DB connection is available.");
			
		} catch (Exception e) {
			throw new RuntimeException("Failure when test connection.", e);
		}finally{
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}

	public static void main(String[] args) {
		DbConnectionChecker checker = new DbConnectionChecker();
		checker.testConnection();
	}

}
