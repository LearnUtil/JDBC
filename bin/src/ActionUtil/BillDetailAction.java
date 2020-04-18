package com.jdbc.ActionUtil;

import com.jdbc.DBContext.*;
import com.jdbc.DAOUtil.*;
import java.util.*;
import java.sql.*;


public class BillDetailAction{

	public ContextClass dbContext = null;
	
	public BillDetailAction(ContextClass dbContext){
		this.dbContext=dbContext;
	}


	public List<BillDetail> getBillDetails(Integer billId){
		try{
			return dbContext.getBillDetails(billId);
		}catch(SQLException exp){
			System.out.println(exp.toString());
		}
		return null;
	}
}