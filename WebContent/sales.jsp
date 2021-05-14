<%@page import="com.Sales" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sales Transactions</title>

<!-- Import Bootstrap, Jquery and Main.js Files -->
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/sales.js"></script>

</head>
<body>

	<div class = "container">
		<div class = "row">
			<div class = "col-6">
				<h1 class="m-3"> GadgetBadget Sales </h1>
				<form id="formSales" name = "formSales"  method = "post" action="sales.jsp">
					Invoice ID :
					<input id="invoiceId" name="invoiceId" type="text" class="form-control form-control-sm">
					<br>
					Purchase Date :
					<input id="purchaseDate" name="purchaseDate" type="text" class="form-control form-control-sm">
					<br>
					Total Units : 
					<input id="totalUnits" name="totalUnits" type="text" class="form-control form-control-sm">
					<br>
					Net Amount : 
					<input id="netAmount" name="netAmount" type="text" class="form-control form-control-sm">
					<br>
					Discount Tax : 
					<input id="discountTax" name="discountTax" type="text" class="form-control form-control-sm">
					<br>
					Total Amount :
					<input id="totalAmount" name="totalAmount" type="text" class="form-control form-control-sm">
					<br>
					Payment Type :
					<input id="paymentType" name="paymentType" type="text" class="form-control form-control-sm">
					<br>
					Order Status :
					<input id="orderStatus" name="orderStatus" type="text" class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidInvoiceIDSave" name="hidInvoiceIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				
				<div id="divSalesGrid">
					<%
						Sales saleObj = new Sales();
						out.print(saleObj.readSales());
					%>
				</div>
				
			</div>		
		</div>
	</div>

</body>
</html>

