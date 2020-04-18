package com.jdbc.ActionUtil;

import com.jdbc.DBContext.*;
import com.jdbc.DAOUtil.*;
import com.jdbc.TestUtil.TestUtil;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BillAction{
	private static final Logger LOGGER = Logger.getLogger(BillAction.class.getName());
	public ContextClass dbContext = null;
	
	public BillAction(ContextClass dbContext){
		this.dbContext=dbContext;
	}


	public List<Bill> getAllBills(Timestamp from, Timestamp to){
		try{
			return dbContext.getAllBills(from,to);
		}catch(SQLException exp){
			LOGGER.log(Level.SEVERE, exp.getStackTrace().toString());
			System.out.println(exp.toString());
		}
		return null;
	}

	public List<Bill> getBillsFromCache(){
		return dbContext.getBillsFromCache();
	}
}