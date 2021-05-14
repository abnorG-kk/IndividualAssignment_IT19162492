$(document).on("click", "#btnLogin", function(event)
{
	//Clear Alerts ---------------------
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form Validation
	var status = validateLoginForm();
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		
		return;
	}
	
	// If valid -----------------------
	$.ajax(
	{
		url : "AuthAPI",
		type : "POST",
		data : $("#formLogin").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onLoginComplete(response.responseText, status);
		}
	});
	
});

//Logout =============================================
$(document).on("click", "#btnLogout", function(event)
{
	$.ajax({
		url : "AuthAPI",
		type : "DELETE",
		data : "",
		dataType : "text",
		complete : function(response, status)
		{
			onLogoutComplete(response.responseText, status);
		}
	});
});

function onLoginComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success")
		{
			// Redirect the valid user -------------------
			document.location = "sales.jsp";
		}
		else if(resultSet.status.trum() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error")
	{
		$("#alertError").text("Error while Login");
		$("#alertError").show();
	}
	else
	{
		$("#alertError").text("Unknown error while Login");
		$("#alertError").show();
	}
	
	$("#hidInvoiceIDSave").val("");
	$("#formSales")[0].reset();
}
	
function validateLoginForm()
{
	//Username
	if($("#txtUsername").val().trim() == "")
	{
		return "Insert Username";
	}
	
	//Password
	if($("#txtPassword").val().trim() == "")
	{
		return "Insert Password";
	}
	
	return true;
}


function onLogoutComplete(response, status)
{
	if(status == "success")
	{
		if(response.trim() == "success")
		{
			//Redirect to index ------------
			document.location = "index.jsp";
		}
	}
}
	
		
		
		
		
		
		
		
		