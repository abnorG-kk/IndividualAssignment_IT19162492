$(document).ready(function()
{
	if($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE =====================================================================================
$(document).on("click", "#btnSave", function(event)
{
	//Clear alerts ----------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-----------------
	var status = validateSalesForm();
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	//if valid-------
	//$("#formSales").submit();
	var type = ($("#hidInvoiceIDSave").val() == "")? "POST" : "PUT";
	
	$.ajax(
	{
		url : "SalesAPI",
		type : type,
		data : $("#formSales").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onSalesSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE ===================================================================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidInvoiceIDSave").val($(this).closest("tr").find("#hidInvoiceIDUpdate").val());
	//$("#hidInvoiceIDSave").val($(this).data("invoiceId"));
	$("#invoiceId").val($(this).closest("tr").find('td:eq(0)').text());
	$("#purchaseDate").val($(this).closest("tr").find('td:eq(1)').text());
	$("#totalUnits").val($(this).closest("tr").find('td:eq(2)').text());
	$("#netAmount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#discountTax").val($(this).closest("tr").find('td:eq(4)').text());
	$("#totalAmount").val($(this).closest("tr").find('td:eq(5)').text());
	$("#paymentType").val($(this).closest("tr").find('td:eq(6)').text());
	$("#orderStatus").val($(this).closest("tr").find('td:eq(7)').text());
});


//REMOVE ====================================================================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "SalesAPI",
		type : "DELETE",
		data : "invoiceId=" + $(this).data("invoiceId"),
		//.data("invoiceId"),
		dataType : "text",
		complete : function(response, status)
		{
			onSalesDeleteComplete(response.responseText, status);
		}
	});

});

// CLIENT MODEL =============================================================================
function validateSalesForm()
{
	//Invoice ID
	if($("#invoiceId").val().trim() == "")
	{
		return "Insert Invoice ID";
	}
	
	//Purchase Date
	if($("#purchaseDate").val().trim() == "")
	{
		return "Insert Purchase Date";
	}
	
	//Total Units
	if($("#totalUnits").val().trim() == "")
	{
		return "Insert Total Units";
	}
	
	//Net Amount
	if($("#netAmount").val().trim() == "")
	{
		return "Insert Net Amount";
	}
	
	//Discount Tax
	if($("#discountTax").val().trim() == "")
	{
		return "Insert Discount Tax";
	}
	
	//Total Amount
	if($("#totalAmount").val().trim() == "")
	{
		return "Insert Total Amount";
	}
	
	//Payment Type
	if($("#paymentType").val().trim() == "")
	{
		return "Insert Payment Type";
	}
	
	//Order Status
	if($("#orderStatus").val().trim() == "")
	{
		return "Insert Order Status";
	}
	
	return true;
}

function onSalesSaveComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved");
			$("#alertSuccess").show();
			
			$("#divSalesGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if(status == "error")
	{
			$("#alertError").text("Error while Saving");
			$("#alertError").show();
	} else
	{
			$("#alertError").text("Unknown Error While Saving");
			$("#alertError").show();
	}
	
	$("#hidInvoiceIDSave").val("");
	$("#formSales")[0],reset();
}

function onSalesDeleteComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted");
			$("#alertSuccess").show();
			
			$("#divSalesGrid").html(resultSet.data);
		} else if(resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while Deleting");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown Error While Deleting");
		$("#alertError").show();
	}
}
