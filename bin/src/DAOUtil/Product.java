//Product DOA

package com.jdbc.DAOUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product{
	private Integer pid;
	private String name;
	private String code;
	private Double price;
	private Integer availableStock;
	private Double tax;
	private Boolean offerAvailStat;
	private Timestamp firstDateOfPurchase;


	public void setPid(Integer pid){
		this.pid = pid;
	}

	public Integer getPid(){
		return this.pid;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public Double getPrice(){
		return this.price;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Integer getAvailableStock(){
		return this.availableStock;
	}

	public void setAvailableStock(Integer availableStock){
		this.availableStock = availableStock;
	}

	public Double getTax(){
		return this.tax;
	}

	public void setTax(Double tax){
		this.tax = tax;
	}

	public boolean getOfferAvailStat(){
		return this.offerAvailStat;
	}

	public void setOfferAvailStat(boolean offerAvailStat){
		this.offerAvailStat = offerAvailStat;
	}

	public Timestamp getFirstDateOfPurchase(){
		return this.firstDateOfPurchase;
	}

	public void setFirstDateOfPurchase(Timestamp firstDateOfPurchase){
		this.firstDateOfPurchase = firstDateOfPurchase;
	}
}