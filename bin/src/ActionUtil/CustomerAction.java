package com.jdbc.ActionUtil;

import com.jdbc.DBContext.*;
import com.jdbc.DAOUtil.*;
import java.util.*;
import java.sql.*;


public class CustomerAction{

	public ContextClass dbContext = null;
	
	public CustomerAction(ContextClass dbContext){
		this.dbContext=dbContext;
	}


	public List<Customer> getTopCustomers(Timestamp from, Timestamp to){
		try{
			return dbContext.getTopCustomers(from,to);
		}catch(SQLException exp){
			System.out.println(exp.toString());
		}
		return null;
	}

	public List<Customer> getDueCustomers(Timestamp from, Timestamp to){
		try{
			return dbContext.getDueCustomers(from,to);
		}catch(SQLException exp){
			System.out.println(exp.toString());
		}
		return null;
	}
}