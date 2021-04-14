package com.higradius;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/DeleteServlet")  
public class DeleteServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
    	String sid[] = request.getParameterValues("Doc_id"); 
    	for(int i=0;i<sid.length;i++)
		{
    		BigDecimal doc_id = new BigDecimal(sid[i]);
			 InfoConnection.delete(doc_id);
		}
    	 response.sendRedirect("ViewServlet");  
    }  
}  