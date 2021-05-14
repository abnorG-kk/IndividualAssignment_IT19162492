package com;

import java.sql.*;

public class User {

	public String login(String txtUsername, String txtPassword) {
		String output = "";
		
		if(!txtUsername.equals("admin")) //Hardcoded Value
		{
			output = "Invalid Username";
		}
		else if(txtUsername.equals("admin")) //Hardcoded Value
		{
			if(txtPassword.equals("admin")) //Hardcoded Value
			{
				output = "Invalid Valid";
			}
			else if(txtPassword.equals("admin"))//Hardcoded Value
			{
				output = "success";
			}
		}
		return output;
	}
}
