package com.higradius;

import java.io.IOException;  
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/AddServlet")  
public class AddServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        Info e=new Info();
        String name_cust=request.getParameter("name_customer");  
        String num_cust=request.getParameter("cust_number");  
        String did =request.getParameter("due_in_date");  
        String top=request.getParameter("total_open_amount");  
        String id=request.getParameter("invoice_id");
        Double op = Double.parseDouble(top);
        BigInteger invoice_id = null;
		try {
			invoice_id = BigDecimal.valueOf(Double.parseDouble(id)).toBigInteger();
			//BigInteger invoice_id = new BigInteger(data[17]);
		}catch(Exception s) {
			invoice_id = null;
		}finally {
			e.setInvoice_id(invoice_id);
		}
          
		 SimpleDateFormat originalFormat3 = new SimpleDateFormat("yyyyMMdd");
	        java.sql.Date due_in_date1 = null;
	        try {
	        	Date date3 = originalFormat3.parse(did); 
	        	due_in_date1 = new java.sql.Date(date3.getTime());
	        }catch(Exception z) {
	        	due_in_date1 = null;
	        }finally {
	        	e.setDue_in_date(due_in_date1); 
	        }
        e.setName_customer(name_cust);  
        e.setCust_number(num_cust);  
        e.setTotal_open_amount(op);
       // e.setInvoice_id(id); 
          
        int status=InfoConnection.add(e);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
             
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }  
  
}  