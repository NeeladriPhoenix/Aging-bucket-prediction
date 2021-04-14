package com.higradius;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;  

public class InfoConnection {
	 public static Connection getConnection() {
		 Connection con=null; 
		  try{  
	            //Class.forName("oracle.jdbc.driver.OracleDriver");  
	            con=DriverManager.getConnection("jdbc:mysql://localhost/h2h_internship","root","root");  
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }  
	   
			public static int add(Info e)
			{  
					int status=0;  
						try{  
							Connection con=InfoConnection.getConnection();  
							PreparedStatement ps=con.prepareStatement(  
							"INSERT INTO invoice_details (`name_customer`,`cust_number`,`due_in_date`,`total_open_amount`,`invoice_id`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");  
							ps.setString(1,e.getName_customer());  
							ps.setString(2,e.getCust_number());  
							ps.setDate(3,e.getDue_in_date());  
							ps.setDouble(4,e.getTotal_open_amount());  
							ps.setObject(5,e.getInvoice_id()); 
                            status=ps.executeUpdate();  
          
                            con.close();  
						}catch(Exception ex){ex.printStackTrace();}  
      
						return status;  
			}   

			 public static int edit(Info e){  
			        int status=0;  
			        try{  
			            Connection con=InfoConnection.getConnection();  
			            PreparedStatement ps=con.prepareStatement(  
			                         "update invoice_details set total_open_amount=?");  
			            ps.setDouble(1,e.getTotal_open_amount());
			              
			            status=ps.executeUpdate();  
			              
			            con.close();  
			        }catch(Exception ex){ex.printStackTrace();}  
			          
			        return status;  
			    } 
			 public static int delete(BigDecimal doc_id){  
			        int status=0;  
			        try{  
			            Connection con=InfoConnection.getConnection();  
			            PreparedStatement ps=con.prepareStatement("delete from invoice_details where Doc_id=?");  
			            ps.setBigDecimal(1,doc_id);  
			            status=ps.executeUpdate();  
			              
			            con.close();  
			        }catch(Exception e){e.printStackTrace();}  
			          
			        return status;  
			    }
			/* public static Info getallEmployeeById(){  
			        Info e=new Info();  
			          
			        try{  
			            Connection con=InfoConnection.getConnection();  
			            PreparedStatement ps=con.prepareStatement("select * from invoice_details ");  
			            //ps.setInt(1,id);  
			            ResultSet rs=ps.executeQuery();  
			            if(rs.next()){  
			                e.setBusiness_code(rs.getString(1));  
			                e.setCust_number(rs.getString(2));  
			                e.setName_customer(rs.getString(3));  
			                e.setClear_date(rs.getString(4));  
			                e.setBusiness_year(rs.getBigDecimal(5));
			                e.setDoc_id1(rs.getBigDecimal(6));
			                e.setPosting_date(rs.getString(7));
			                e.setDocument_create_date(rs.getString(8));
			                e.setDue_in_date(rs.getString(9));
			                
			                e.setInvoice_currency(rs.getString(10));
			                e.setDocument_type(rs.getString(11)); 
			                e.setPosting_id(rs.getBigDecimal(12));
			                e.setArea_business(rs.getString(13));
			                e.setTotal_open_amount(rs.getDouble(14));
			                e.setBaseline_create_date(rs.getString(15));
			                e.setCust_payment_terms(rs.getString(16));
			                e.setInvoice_id(rs.getObject(17));
			                e.setIsOpen(rs.getBigDecimal(18));
			                
			            }  
			            con.close();  
			        }catch(Exception ex){ex.printStackTrace();}  
			          
			        return e;  
			    }*/
			 public static List<Info> getAllInformation(){  
			        List<Info> list=new ArrayList<Info>();  
			          
			        try{  
			            Connection con=InfoConnection.getConnection();  
			            PreparedStatement ps=con.prepareStatement("select * from invoice_details");  
			            ResultSet rs=ps.executeQuery();  
			            while(rs.next()){  
			                Info e=new Info();  
			                e.setBusiness_code(rs.getString(1));  
			                e.setCust_number(rs.getString(2));  
			                e.setName_customer(rs.getString(3));  
			                e.setClear_date(rs.getTimestamp(4));  
			                e.setBusiness_year(rs.getBigDecimal(5));
			                e.setDoc_id(rs.getBigDecimal(6));
			                e.setPosting_date(rs.getDate(7));
			                e.setDocument_create_date(rs.getDate(8));
			                e.setDue_in_date(rs.getDate(9));
			                
			                e.setInvoice_currency(rs.getString(10));
			                e.setDocument_type(rs.getString(11)); 
			                e.setPosting_id(rs.getBigDecimal(12));
			                e.setArea_business(rs.getString(13));
			                e.setTotal_open_amount(rs.getDouble(14));
			                e.setBaseline_create_date(rs.getDate(15));
			                e.setCust_payment_terms(rs.getString(16));
			                e.setInvoice_id(rs.getObject(17));
			                e.setIsOpen(rs.getInt(18));
			                list.add(e);  
			            }  
			            con.close();  
			        }catch(Exception e){e.printStackTrace();}  
			          
			        return list;  
			    }  
		
}  


