//Contexte
package com.jdbc.DBContext;

import com.jdbc.DAOUtil.*;
import java.util.*;
import java.sql.*;
import com.jdbc.ConnectionUtil.*;
import java.time.OffsetDateTime;


//Mutable context
public class ContextClass{
	public ConnectionUtil connection=null;
	private List<Product> products = null;
	private List<Customer> customers = null;
	private List<Bill> bills = null;
	private List<BillDetail> billDetails = null;
	private Statement statement = null;
	private PreparedStatement prepareStat = null;
	private ResultSet resSet = null;

	public ContextClass(Properties dbProp) {
		try{
			products = new ArrayList<Product>();
			customers = new ArrayList<Customer>();
			bills = new ArrayList<Bill>();
			billDetails = new ArrayList<BillDetail>();

			connection = new ConnectionUtil(dbProp);
			connection.initConnection();
			connection.getCon().setAutoCommit(false);
			statement = connection.getCon().createStatement();
		}catch(Exception ex){
			System.out.println("handle exp : "+ex.toString());
		}
	}

	private boolean hasColumn(ResultSet resultSet, String columnName) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
 		int coulmnCount = resultSetMetaData.getColumnCount();
 		for (int i = 1; i <= coulmnCount; i++) {
  			if (columnName.equals(resultSetMetaData.getColumnName(i))) {
   				return true;
  			}
 		}
 		return false;
	}

	public List<Bill> getBillsFromCache(){
		return this.bills;
	}
	
	public void loadProducts(ResultSet resSet) throws NullPointerException, SQLException{
		Product product = null;
		while(resSet.next()){
			product = new Product();
			product.setPid(resSet.getInt("pid"));
			product.setName(resSet.getString("name"));
			product.setCode(resSet.getString("code"));
			product.setPrice(resSet.getDouble("price"));
			product.setAvailableStock(resSet.getInt("availablestock"));
			product.setTax(resSet.getDouble("tax"));
			product.setOfferAvailStat(resSet.getBoolean("OfferAvail_Stat"));
			product.setFirstDateOfPurchase(resSet.getTimestamp("firstdateofpurchase"));
			
			this.products.add(product);
		}
	}

	public void loadCustomers(ResultSet resSet) throws NullPointerException, SQLException{
		Customer customer = null;
		while(resSet.next()){
			customer = new Customer();
			customer.setCid(resSet.getInt("id"));
			customer.setName(resSet.getString("name"));
			customer.setAddress(resSet.getString("address"));
			customer.setPhone(resSet.getLong("phone"));
			customer.setEmail(resSet.getString("email"));
			customer.setPoints(resSet.getDouble("points"));
			customer.setDiscountPerks(resSet.getDouble("discountperks"));
			customer.setDueCount(resSet.getInt("total"));

			if(hasColumn(resSet,("amounttopay").toLowerCase())){
				customer.setAmountToPay(resSet.getDouble("amounttopay"));
				customer.setAmountPaid(resSet.getDouble("paid"));
				customer.setTotal(resSet.getDouble("total"));
			}

			this.customers.add(customer);
		}
	}

	public void loadBills(ResultSet resSet)throws NullPointerException, SQLException {
		Bill bill = null;
		while(resSet.next()){
			bill = new Bill();
			bill.setBid(resSet.getInt("bid"));
			bill.setCid(resSet.getInt("cid"));
			bill.setPurchaseDate(resSet.getTimestamp("purchasedate"));
			bill.setTotalAmount(resSet.getDouble("totalamount"));
			bill.setTotalTax(resSet.getDouble("totaltax"));
			bill.setAmountPaid(resSet.getDouble("amountpaid"));
			bill.setDueAmountPaidDate(resSet.getTimestamp("due_amount_paid_date"));

			this.bills.add(bill);
		}
	}

	public void loadBillDetails(ResultSet resSet) throws NullPointerException, SQLException{
		BillDetail billDetail = null;
		while(resSet.next()){
			billDetail = new BillDetail();
			billDetail.setBdId(resSet.getInt("bdid"));
			billDetail.setBid(resSet.getInt("bid"));
			billDetail.setPid(resSet.getInt("pid"));
			billDetail.setPurchasedate(resSet.getTimestamp("purchasedate"));
			billDetail.setUnitprice(resSet.getDouble("unitprice"));
			billDetail.setQty(resSet.getInt("qty"));
			billDetail.setAmountPaid(resSet.getDouble("amountpaid"));
			billDetail.setAmountPaidDate(resSet.getTimestamp("amountpaiddate"));

			this.billDetails.add(billDetail);
		}
	}

	public List<Bill> getAllBills(Timestamp from, Timestamp to)throws SQLException,NullPointerException{
		bills.clear();
		prepareStat = connection.getCon().prepareStatement("select * from bill where purchasedate between ? AND ? order by purchasedate");
		prepareStat.setTimestamp(1,from);
		prepareStat.setTimestamp(2,to);
		loadBills(prepareStat.executeQuery());
		return this.bills;
	}


	public List<Product> getProducts() throws NullPointerException, SQLException{
		/*resSet = statement.executeQuery("Select * from product");*/
		loadProducts(statement.executeQuery("Select * from product"));
		return this.products;
	}

	public List<BillDetail> getBillDetails(Integer billId) throws NullPointerException, SQLException{
		billDetails.clear();
		prepareStat = connection.getCon().prepareStatement("select * from bill_detail where bid = ?");
		prepareStat.setInt(1,billId);
		loadBillDetails(prepareStat.executeQuery());
		return this.billDetails;
	}

	public List<Customer> getDueCustomers(Timestamp from, Timestamp to) throws SQLException,NullPointerException{
		customers.clear();
		prepareStat = connection.getCon().prepareStatement("select customer.*, sum(bill.totalamount - bill.amountpaid) as amountToPay,sum(bill.amountpaid) as paid, sum(bill.totalamount) as total from customer INNER JOIN bill on customer.cid=bill.cid where bill.totalamount > bill.amountpaid AND bill.purchasedate between ? AND ? group by customer.cid;");
		prepareStat.setTimestamp(1,from);
		prepareStat.setTimestamp(2,to);
		loadCustomers(prepareStat.executeQuery());
		return this.customers;
	}

	public List<Product> getUnsoldProducts(Timestamp from, Timestamp to) throws SQLException{
		/*
			edge cases -> F 							T
							2018-11-01 00:00:00			2018-11-01 00:00:00					
		*/ 
		products.clear();
		prepareStat = connection.getCon().prepareStatement("select product.* from product left join bill_detail on product.pid = bill_detail.pid AND bill_detail.purchasedate between ? AND ? where product.availablestock > 0 AND product.firstdateofpurchase < ? AND bill_detail.pid IS NULL;");
		prepareStat.setTimestamp(1,from);
		prepareStat.setTimestamp(2,to);
		prepareStat.setTimestamp(3,to);
		//resSet = 
		loadProducts(prepareStat.executeQuery());

		return this.products;
	}

	public void insertProduct(){
	}

	public void insertProducts(){
	}

	public List<Customer> getTopCustomers(Timestamp from,Timestamp to) throws SQLException{
		customers.clear();
		prepareStat = connection.getCon().prepareStatement("select customer.*,sum(bill.totalamount) as total from customer INNER JOIN bill ON bill.cid = customer.cid where bill.purchasedate between ? AND ? AND bill.totalamount = bill.amountpaid Group by customer.cid order by total DESC;");
		prepareStat.setTimestamp(1,from);
		prepareStat.setTimestamp(2,to);
		//resSet = 
		loadCustomers(prepareStat.executeQuery());
		return this.customers;
	}



	public void submitFinalChanges() throws SQLException{
		connection.getCon().commit();
	}
}