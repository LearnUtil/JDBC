//Product DOA

package com.jdbc.DAOUtil;

import java.sql.Timestamp;

public class Customer{
	private Integer cid;
	private String name;
	private String address;
	private long phone;
	private String email;
	private Double points;
	private Double discountPerks;
	private Integer dueCount;

	private Double amountToPay,amountPaid,Total;


	public void setCid(Integer cid){
		this.cid = cid;
	}

	public Integer getCid(){
		return this.cid;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public long getPhone(){
		return this.phone;
	}

	public void setPhone(long phone){
		this.phone = phone;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Double getPoints(){
		return this.points;
	}

	public void setPoints(Double points){
		this.points = points;
	}

	public Double getDiscountPerks(){
		return this.discountPerks;
	}

	public void setDiscountPerks(Double discountPerks){
		this.discountPerks = discountPerks;
	}

	public Integer getDueCount(){
		return this.dueCount;
	}

	public void setDueCount(Integer dueCount){
		this.dueCount = dueCount;
	}

	public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }
}