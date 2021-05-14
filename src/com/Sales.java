package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sales {

	//DB Connection
	private Connection connect() {
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//DB Connection Details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/salesdb", "root", "root");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}

	//Database CRUD Operations
	public String insertSales(String invoiceId, String purchaseDate, String totalUnits, String netAmount, String discountTax, String totalAmount, String paymentType, String orderStatus ) 
	{
	
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Cannot Connect to Database for Insertion Operation";
			}
			//prepared statement
			String query = " insert into sales"
					+ "(invoiceId, purchaseDate,totalUnits,netAmount,discountTax,totalAmount,paymentType,orderStatus) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1, invoiceId);
			preparedStmt.setString(2,  purchaseDate);
			preparedStmt.setString(3,  totalUnits);
			preparedStmt.setString(4,  netAmount);
			preparedStmt.setString(5,  discountTax);
			preparedStmt.setString(6,  totalAmount);
			preparedStmt.setString(7,  paymentType);
			preparedStmt.setString(8,  orderStatus);
			
			//statement execution
			preparedStmt.execute();
			con.close();
			
			String newSales = readSales();
				
			output = "{\"status\":\"success\", \"data\":\"" + newSales + "\"}";
			//output = "Sales Record Added Successfully";
		}
		catch(Exception e) {
			//output = "Sales Record Not Added";
			output = "{\"status\":\"error\", \"data\":\"Error While Inserting Sales\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readSales() {
		String output = "";
		try
		{
			Connection con = connect();
			
			if(con == null) {
				return "Cannot Connect to Database for Read Operation";
			}
			
			//prepare the html table to be displayed
			output = "<table border='1' class='table table-striped w-auto'><tr><th scope ='col'>Invoice ID</th>"
					+ "<th scope ='col'>Purchase Date</th>"
					+ "<th scope ='col'>Total Units </th>"
					+ "<th scope ='col'>Net Amount</th>"
					+ "<th scope ='col'>Discount Tax</th>"
					+ "<th scope ='col'>Total Amount</th>"
					+ "<th scope ='col'>Payment Type</th>"
					+ "<th scope ='col'>Order Status</th></tr>";
			
			String query = "select * from sales";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterate though the rows in the result set
			while(rs.next()) {
				String invoiceId = rs.getString("invoiceId");
				String purchaseDate = rs.getString("purchaseDate");
				String totalUnits = rs.getString("totalUnits");
				String netAmount = rs.getString("netAmount");
				String discountTax = rs.getString("discountTax");
				String totalAmount = rs.getString("totalAmount");
				String paymentType = rs.getString("paymentType");
				String orderStatus = rs.getString("orderStatus");
				
				//Add into the html table
				output += "<tr><td><input id='hidInvoiceIDUpdate' name='hidInvoiceIDUpdate' type='hidden'"
						+ "value='" + invoiceId + "'>" + invoiceId + "</td>";
				output += "<td>" + purchaseDate + "</td>";
				output += "<td>" + totalUnits + "</td>";
				output += "<td>" + netAmount + "</td>";
				output += "<td>" + discountTax + "</td>";
				output += "<td>" + totalAmount + "</td>";
				output += "<td>" + paymentType + "</td>";
				output += "<td>" + orderStatus + "</td>";
				
				//buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-invoiceid = '" + invoiceId + "'>" +"</td>"
						+ "<td> <input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' "
						+ "data-invoiceid = '" + invoiceId +"'></td></tr>";
				
			}
			con.close();
			
			//complete html table
			output += "</table>";
			
		} catch(Exception e) {
			output = "Sales Data Not Loaded Due to an Error";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String updateSales(String invoiceId, String purchaseDate, String totalUnits, String netAmount, String discountTax, String totalAmount, String paymentType, String orderStatus )
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null) {
				return "Cannot Connect to Database for Update Operation";
			}
			
			//create prepared statement
			String query = "UPDATE sales SET purchaseDate=?,totalUnits=?,"
					+ "netAmount=?,discountTax=?,totalAmount=?,paymentType=?,orderStatus=?"
					+ "Where invoiceId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			
			preparedStmt.setString(1,  purchaseDate);
			preparedStmt.setString(2,  totalUnits);
			preparedStmt.setString(3,  netAmount);
			preparedStmt.setString(4,  discountTax);
			preparedStmt.setString(5,  totalAmount);
			preparedStmt.setString(6,  paymentType);
			preparedStmt.setString(7,  orderStatus);
			preparedStmt.setString(8, invoiceId);
			
			//statement execution
			preparedStmt.execute();
			con.close();
			
			String newSales = readSales();
			//output = "Sales Record Updated Successfully";
			output = "{\"status\":\"success\", \"data\": \"" + newSales + "\"}";
			
		}catch(Exception e) {
			//output = "Sales Record Not Updated";
			output = "{\"status\":\"error\", \"data\":\"Error While Updating Sales\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteSales(String invoiceId) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Cannot Connect to Database for Delete Operation";
			}
			
			//create prepared statement
			String query = "delete from sales where invoiceId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding value
			preparedStmt.setString(1,  invoiceId);
			
			//execution
			preparedStmt.execute();
			con.close();
			
			String newSales = readSales();
			output = "{\"status\":\"success\", \"data\": \"" + newSales + "\"}";
			
			//output = "Sales Record Deleted Successfully";
			
		} catch(Exception e) {
			//output = "Cannot Delete the Sales Record";
			output = "{\"status\":\"error\", \"data\":\"Error While Deleting Sales\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}


