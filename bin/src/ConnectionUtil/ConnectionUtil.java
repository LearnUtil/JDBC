//JDBC

package com.jdbc.ConnectionUtil;

import java.util.Properties;
import java.sql.*;

public class ConnectionUtil{
	private Properties dbProperty;

	private Connection connection = null;

	public ConnectionUtil(Properties dbProp){
		dbProperty = dbProp;
		/*System.out.println(dbProperty.size());
		dbProperty.forEach((k,v) ->
			System.out.println(k+"\t"+v)
		);*/
	}

	public ConnectionUtil initConnection() throws SQLException,ClassNotFoundException{
		//System.out.println("prop ->"+dbProperty.getProperty("url")+"\n");
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(dbProperty.getProperty("url"),dbProperty);
		//System.out.println("conn here ->"+connection);
		return this;
	}

	public Connection getCon(){
		return this.connection;
	}
}
//"jdbc:postgresql://localhost:5543/trydb";