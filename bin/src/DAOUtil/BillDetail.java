//Product DOA

package com.jdbc.DAOUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BillDetail{
	private Integer bdId;
	private Integer bid;
	private Integer pid;
	private String dcode;
	private Timestamp purchasedate;
	private Double unitprice;
	private Integer qty;
	private Double amountpaid;
	private Timestamp amountpaiddate;

	 public Integer getBdId() {
        return bdId;
    }

    public void setBdId(Integer bdId) {
        this.bdId = bdId;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public Timestamp getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Timestamp purchasedate) {
        this.purchasedate = purchasedate;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getAmountPaid() {
        return amountpaid;
    }

    public void setAmountPaid(Double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Timestamp getAmountPaidDate() {
        return amountpaiddate;
    }

    public void setAmountPaidDate(Timestamp amountpaiddate) {
        this.amountpaiddate = amountpaiddate;
    }		
}