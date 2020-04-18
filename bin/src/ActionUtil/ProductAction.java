package com.jdbc.ActionUtil;

import com.jdbc.DBContext.*;
import com.jdbc.DAOUtil.*;
import java.util.*;
import java.sql.*;


public class ProductAction{

	public ContextClass dbContext = null;
	
	public ProductAction(ContextClass dbContext){
		this.dbContext = dbContext;
	}

	public void addProduct(){
		
	}

	public void addProducts(){

	}

	public void updateProduct(){

	}

	public void deleteProduct(){

	}

	public List<Product> getUnsoldProducts(Timestamp from, Timestamp to){
		try{
			return dbContext.getUnsoldProducts(from,to);
		}catch(SQLException exp){
			System.out.println(exp.toString());
		}
		return null;
	}
	public List<Product> getAllProducts(){
		try{
			return dbContext.getProducts();
		}catch(SQLException exp){
			System.out.println(exp.toString());
		}
		return null;
	}
}