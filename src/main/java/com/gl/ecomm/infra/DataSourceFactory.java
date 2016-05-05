package com.gl.ecomm.infra;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.jolbox.bonecp.BoneCPDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class DataSourceFactory {
	public static final String KEY_POOL_TYPE = "connection.pool.type";
	public static final String KEY_DRIVER_CLASS = "jdbc.driverClass";
	public static final String KEY_USER = "jdbc.user";
	public static final String KEY_PASSWORD = "jdbc.password";

	public static final String TYPE_C3P0 = "com.mchange.v2.c3p0.ComboPooledDataSource";
	public static final String TYPE_BONECP = "com.jolbox.bonecp.BoneCPDataSource";
	public static final String TYPE_DBCP = "org.apache.commons.dbcp2.BasicDataSource";
	public static final String TYPE_DRUID = "com.alibaba.druid.pool.DruidDataSource";

	public static final String KEY_URL = "jdbc.url";

	private static final String PROPERTY_FILE = "datasource.properties";

	private static final Properties dbConnProps;

	private static DataSource dsHolder;

	static {
		synchronized (DataSourceFactory.class) {
			dbConnProps = new Properties();
			try {
				dbConnProps.load(DataSourceFactory.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
			} catch (IOException e) {
				throw new RuntimeException("Failure while initiate datasource factory.", e);
			}
		}

	}

	private static String driverClass = dbConnProps.getProperty(KEY_DRIVER_CLASS);
	private static String url = dbConnProps.getProperty(KEY_URL);
	private static String uname = dbConnProps.getProperty(KEY_USER);
	private static String pw = dbConnProps.getProperty(KEY_PASSWORD);

	private static DataSource getDbcpDataSource() {

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(uname);
		ds.setPassword(pw);

		return ds;
	}

	private static DataSource getDruidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUsername(uname);
		dataSource.setPassword(pw);
		dataSource.setUrl(url);
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

	private static DataSource getBonecpDataSource() {
		try {
			Class.forName(dbConnProps.getProperty(KEY_DRIVER_CLASS));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to create Bonecp datasource.", e);
		}

		BoneCPDataSource ds = new BoneCPDataSource();

		ds.setJdbcUrl(dbConnProps.getProperty(KEY_URL));
		ds.setUser(dbConnProps.getProperty(KEY_USER));
		ds.setPassword(dbConnProps.getProperty(KEY_PASSWORD));

		return ds;
	}

	private static DataSource getC3P0DataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(dbConnProps.getProperty(KEY_DRIVER_CLASS));
			ds.setJdbcUrl(dbConnProps.getProperty(KEY_URL));
			ds.setUser(dbConnProps.getProperty(KEY_USER));
			ds.setPassword(dbConnProps.getProperty(KEY_PASSWORD));
		} catch (PropertyVetoException e) {
			throw new RuntimeException("Failed to create C3P0 datasource.", e);
		}

		return ds;
	}

	public static DataSource getDataSource() {
		if (dsHolder == null) {
			synchronized (DataSourceFactory.class) {
				if (dsHolder == null) {
					String type = dbConnProps.getProperty(KEY_POOL_TYPE);
					switch (type) {
					case TYPE_C3P0:
						dsHolder = getC3P0DataSource();
						break;
					case TYPE_BONECP:
						dsHolder = getBonecpDataSource();
						break;
					case TYPE_DBCP:
						dsHolder = getDbcpDataSource();
						break;
					case TYPE_DRUID:
						dsHolder = getDruidDataSource();
						break;
					default:
						throw new RuntimeException("Unkown database connection pool type.");

					}
				}
			}
		}
		return dsHolder;
	}

	public static DataSource createDataSource(Properties props) {
		throw new UnsupportedOperationException();
	}
}
