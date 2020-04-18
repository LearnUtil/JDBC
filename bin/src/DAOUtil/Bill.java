//Product DOA

package com.jdbc.DAOUtil;
import java.sql.Timestamp;

public class Bill{
	private Integer bid;
	private Integer cid;
	private Timestamp purchaseDate;
	private Double totalAmount;
	private Double totaldiscount;
	private Double totalTax;
	private Double amountPaid;
	//private Integer cancelProductCount;
	private Timestamp dueAmountPaidDate;

	 public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotaldiscount() {
        return totaldiscount;
    }

    public void setTotaldiscount(Double totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Timestamp getDueAmountPaidDate() {
        return dueAmountPaidDate;
    }

    public void setDueAmountPaidDate(Timestamp dueAmountPaidDate) {
        this.dueAmountPaidDate = dueAmountPaidDate;
    }
}