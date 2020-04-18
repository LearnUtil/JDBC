package com.jdbc.TestUtil;

import com.jdbc.ActionUtil.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Timestamp;
import com.jdbc.DBContext.*;
import com.jdbc.DAOUtil.Bill;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUtil{
	private static final Logger LOGGER = Logger.getLogger(TestUtil.class.getName());
	ContextClass context = null;
	ProductAction prodAct = null;
	CustomerAction custAct = null;
	BillAction billAct = null;
	BillDetailAction billDetAct = null;

	public ContextClass initContext(){
		Properties property = new Properties();
		property.put("user","postgres");
		property.put("password","admin.0p3n()");
		property.put("url","jdbc:postgresql://localhost:5432/trydb");
		return new ContextClass(property);
	}

	public TestUtil(){
		context = initContext();
	}

	//can make this action-function genric such that every class is read and the respective function is invoked.
	public void testUnsoldProduct(){
		prodAct = new ProductAction(context);
		Timestamp from=Timestamp.valueOf("2018-10-04 00:00:00");
		Timestamp to=Timestamp.valueOf("2018-11-03 00:00:00");

		var products = prodAct.getUnsoldProducts(from,to);
		
		try{
			if(products == null || products.size() == 0){
				System.out.println("No such products available!!");
			}
			else{
				System.out.println("FROM -> "+from.toString()+"\tTO -> "+to.toString()+"\n");

				System.out.println("pid\tname\t\t\t\tcode\tprice\tavailstock\ttax\tOfferAvail_Stat\tfirst_date_of_purchase\n");
				products.forEach(product -> {
					System.out.println(product.getPid()+"\t"+product.getName()+"\t\t\t\t"+product.getCode()+"\t"+product.getPrice()+"\t"+product.getAvailableStock()+"\t"+product.getTax()+"\t"+product.getOfferAvailStat()+"\t"+product.getFirstDateOfPurchase());
				});
			}
		}catch(NullPointerException exp){
			System.out.println("caught null"+"\n"+exp.toString());

		}
	}

	public void testTopCustomer(){
		custAct = new CustomerAction(context);
		Timestamp from=Timestamp.valueOf("2018-10-01 00:00:00");
		Timestamp to=Timestamp.valueOf("2018-11-30 00:00:00");

		var customers = custAct.getTopCustomers(from,to);
		try{
			if(customers == null || customers.size() == 0){
				System.out.println("No Customers available!!");
			}
			else{
				System.out.println("FROM -> "+from.toString()+"\tTO -> "+to.toString()+"\n");
				System.out.println("ID\tname\t\taddress\t\t\t\tphone\t\temail\tpoints\tdiscount perks\tduecount\n");
				customers.forEach(customer -> {
					System.out.println(customer.getCid()+"\t"+customer.getName()+"\t\t"+customer.getAddress()+"\t"+customer.getPhone()+"\t\t"+customer.getEmail()+"\t"+customer.getPoints()+"\t"+customer.getDiscountPerks()+"\t"+customer.getDueCount());
				});
			}
		}catch(NullPointerException exp){
			System.out.println("caught null"+"\n"+exp.toString());
		}
	}

	public void testDueCustomer(){
		custAct = new CustomerAction(context);
		Timestamp from=Timestamp.valueOf("2018-10-01 00:00:00");
		Timestamp to=Timestamp.valueOf("2018-11-30 00:00:00");

		var customers = custAct.getDueCustomers(from,to);
		try{
			if(customers == null || customers.size() == 0){
				System.out.println("No Customers available!!");
			}
			else{
				System.out.println("FROM -> "+from.toString()+"\tTO -> "+to.toString()+"\n");
				System.out.println("ID\tname\t\taddress\t\t\t\tphone\t\temail\tpoints\tdiscount perks\tduecount\tAmountToPay\tAmountPaid\tTotalPurchase\n");
				customers.forEach(customer -> {
					System.out.println(customer.getCid()+"\t"+customer.getName()+"\t\t"+customer.getAddress()+"\t"+customer.getPhone()+"\t\t"+customer.getEmail()+"\t"+customer.getPoints()+"\t"+customer.getDiscountPerks()+"\t"+customer.getDueCount()+"\t"+customer.getAmountToPay()+"\t"+customer.getAmountPaid()+"\t"+customer.getTotal()+"\n");
				});
			}
		}catch(NullPointerException exp){
			System.out.println("caught null"+"\n"+exp.toString());
		}
	}

	public void testListAllBills(){
		billAct = new BillAction(context);

		Timestamp from=Timestamp.valueOf("2018-10-01 00:00:00");
		Timestamp to=Timestamp.valueOf("2018-11-30 00:00:00");
		
		var bills = billAct.getAllBills(from,to);
		try{
			if(bills == null || bills.size() == 0){
				System.out.println("No Bills available!!");
			}
			else{
				System.out.println("FROM -> "+from.toString()+"\tTO -> "+to.toString()+"\n");
				System.out.println("ID\tCustomerID\tPurchaseDate\t\tTotalAmount\tTotalTax\tAmountPaid\tDueAmountPaidDate\n");
				bills.forEach(bill -> {
					System.out.println(bill.getBid()+"\t"+bill.getCid()+"\t\t"+bill.getPurchaseDate()+"\t"+bill.getTotalAmount()+"\t\t"+bill.getTotalTax()+"\t\t"+bill.getAmountPaid()+"\t"+bill.getDueAmountPaidDate());
				});
			}
		}catch(NullPointerException exp){

			//System.out.println("caught null"+"\n"+exp.toString());
		}
	}

	public void testViewBillDetailForTheBill(List<Bill> bills) throws IOException,NullPointerException{
		billDetAct = new BillDetailAction(context);
		try{
			bills.forEach(bill ->{
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

				var billDetails = billDetAct.getBillDetails(bill.getBid());
				if(billDetails == null || billDetails.size() == 0){
					System.out.println("No Bills available!!");
				}
				else{
					System.out.println("\nBill -> "+bill.getBid()+"\t"+"Customer -> "+bill.getCid());
					//System.out.println("FROM -> "+from.toString()+"\tTO -> "+to.toString()+"\n");
					System.out.println("\nID\tBillID\tProductID\tDisc.Code\tPurchaseDate\tUnitPrice\tqty\tAmountPaid\tAmountPaidDate\n");
					billDetails.forEach(billDetail -> {
						System.out.println(billDetail.getBdId()+"\t"+billDetail.getBid()+"\t"+billDetail.getPid()+"\t"+billDetail.getPurchasedate()+"\t"+billDetail.getUnitprice()+"\t\t"+billDetail.getQty()+"\t\t"+billDetail.getAmountPaid()+"\t"+billDetail.getAmountPaidDate());
					});
				}
				System.out.println("\n\n\tnext ->");
				String wish = "";
				try{
					wish = bufferRead.readLine();
					if(wish.toLowerCase().equals("skip")){
						throw new NullPointerException();
					}
				}catch(IOException exp){
					wish="";
					System.out.println("\n\n\tCaught yeah !! xD\n");
				}
			});
		}catch(NullPointerException exp){
			System.out.println("caught null"+"\n"+exp.toString());
		}
	}

	public void view() throws IOException,NullPointerException{
		String choice;
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("---------view ONLY mode---------\n\n\t1)Top Customers\n\t2)List Due Cutomers\n\t3)Unsold Products\n\t4)List All Bills\n\tChoice<1-4> : ");
			char letter = bufferRead.readLine().charAt(0);
			switch(letter){
				case '1':
					this.testTopCustomer();
					break;
				case '2' :
					this.testDueCustomer();
					break;
				case '3' :
					this.testUnsoldProduct();
					break;
				case '4' :
					try {
						this.testListAllBills();
						System.out.println("\nDo you wish to view all bill details ??...type no/nope/nah to skip : ");
						String wish = bufferRead.readLine().toLowerCase();
						if(!(wish.equals("nope") || wish.equals("no") || wish.equals("nah") || wish.equals("skip"))) {
							try{
								testViewBillDetailForTheBill(new BillAction(context).getBillsFromCache());
							}catch(IOException exp){
								System.out.println("\n\n\tCaught yeah !! xD\n");
							}
						}
					} catch (Exception ex) {
						LOGGER.log(Level.SEVERE, ex.toString());
					}
					break;
				case '5' :
					return;
				default :
					System.out.println("choice 404!!");
					break;
			}
			System.out.println("\n");
		}while(true);
	}
	public static void main(String...params){
		TestUtil testUtil = new TestUtil();
		try{
			testUtil.view();
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}