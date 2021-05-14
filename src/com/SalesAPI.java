package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.Sales;

/**
 * Servlet implementation class SalesAPI
 */
@WebServlet("/SalesAPI")
public class SalesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sales saleObj = new Sales();
		
		String output = saleObj.insertSales(request.getParameter("invoiceId"),
						request.getParameter("purchaseDate"),
						request.getParameter("totalUnits"),
						request.getParameter("netAmount"),
						request.getParameter("discountTax"),
						request.getParameter("totalAmount"),
						request.getParameter("paymentType"),
						request.getParameter("orderStatus"));
		
		response.getWriter().write(output);
		
	}
	
	//Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext()? scanner.useDelimiter("\\A").next():"";
			scanner.close();
			
			String[] params = queryString.split("&");
			for(String param:params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e) {
			
		}
		
		return map;
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sales saleObj = new Sales();
		
		Map paras = getParasMap(request);
		
		String output = saleObj.updateSales(paras.get("hidInvoiceIDSave").toString(),
						paras.get("purchaseDate").toString(),
						paras.get("totalUnits").toString(),
						paras.get("netAmount").toString(),
						paras.get("discountTax").toString(),
						paras.get("totalAmount").toString(),
						paras.get("paymentType").toString(),
						paras.get("orderStatus").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sales saleObj = new Sales();
		
		Map paras = getParasMap(request);
		
		String output = saleObj.deleteSales(paras.get("invoiceId").toString());
		
		response.getWriter().write(output);
	}

}
