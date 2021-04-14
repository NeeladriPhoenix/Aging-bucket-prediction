package com.higradius;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.Date;
import java.math.*;
import java.text.SimpleDateFormat;


 	
public class DatabaseWork 
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL  = "jdbc:mysql://localhost/h2h_internship";
	static final String USER = "root";
	static final String PASS = "root";
void batchExecute(String sql,PreparedStatement pstmt,ArrayList<Info> invoiceDetails) throws Exception {
		
		int batch = 1000;
		
		for(int i=0;i<invoiceDetails.size();i++) {
			
			pstmt.setString(1, invoiceDetails.get(i).getBusiness_code());  //business code
			pstmt.setString(2, invoiceDetails.get(i).getCust_number());  //cust number
			pstmt.setString(3, invoiceDetails.get(i).getName_customer());  //name customer
			pstmt.setTimestamp(4, invoiceDetails.get(i).getClear_date()); //clear date
			pstmt.setBigDecimal(5, new BigDecimal(invoiceDetails.get(i).getBusiness_year()));  //business_year
			pstmt.setBigDecimal(6, new BigDecimal(invoiceDetails.get(i).getDoc_id())); //doc_id
			pstmt.setDate(7, invoiceDetails.get(i).getPosting_date());  //posting date
			pstmt.setDate(8, invoiceDetails.get(i).getDocument_create_date());  //document create date
			pstmt.setDate(9, invoiceDetails.get(i).getDue_in_date());  //due in date
			pstmt.setString(10, invoiceDetails.get(i).getInvoice_currency());  //invoice currency
			pstmt.setString(11, invoiceDetails.get(i).getDocument_type());  //document type
			pstmt.setBigDecimal(12, new BigDecimal(invoiceDetails.get(i).getPosting_id()));  // posting id
			pstmt.setString(13, invoiceDetails.get(i).getArea_business());  //area business
			pstmt.setDouble(14, invoiceDetails.get(i).getTotal_open_amount());  //total open amount
			pstmt.setDate(15, invoiceDetails.get(i).getBaseline_create_date());  //baseline create date
			pstmt.setString(16, invoiceDetails.get(i).getCust_payment_terms());   //cust payment terms
			pstmt.setObject(17, invoiceDetails.get(i).getInvoice_id()); //invoice id
			pstmt.setBigDecimal(18, new BigDecimal(invoiceDetails.get(i).getIsOpen())); // is open
			
			pstmt.addBatch();
			
			if(i % batch == 0) {
				pstmt.executeBatch();
			}

		}
		pstmt.executeBatch(); // to execute the remaining queries;
		
	}
	

	
	public static void main(String[] args) {
		DatabaseWork insert = new DatabaseWork();
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		String path = "C:\\Users\\KIIT\\Desktop\\High radius Intern\\1805307.csv";
		
int size = 0;
		
		try 
			{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql = "INSERT INTO invoice_details (`business_code`,`cust_number`,`name_customer`,`clear_date`,`business_year`,`doc_id`,`posting_date`,`document_create_date`,`due_in_date`,`invoice_currency`,`document_type`,`posting_id`,`area_business`,`total_open_amount`,`baseline_create_date`,`cust_payment_terms`,`invoice_id`,`isOpen`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			    try 
			    {
			    FileReader fr = new FileReader(path);
				BufferedReader lineReader = new BufferedReader(fr);
				String line = null;
				lineReader.readLine();
				ArrayList<Info> invoiceDetails = new ArrayList<Info>();
				int index = 0;
				while((line = lineReader.readLine())!=null) 
				{
				Info x = new Info();
				String data[] = line.split(",");
				String businesscode = data[0];			
				x.setBusiness_code(businesscode);
				
				String cust_number = data[1];
				x.setCust_number(cust_number);
				
				String name_customer = data[2];
				x.setName_customer(name_customer);
				//String clear_date = data[3];
				
				
				BigInteger business_year = BigDecimal.valueOf(Double.parseDouble(data[4])).toBigInteger();
				//BigInteger business_year = new BigInteger(data[4]);
				x.setBusiness_year(business_year);
				
				
				BigInteger doc_id = BigDecimal.valueOf(Double.parseDouble(data[5])).toBigInteger();
				//BigInteger doc_id = new BigInteger(data[5]);
				x.setDoc_id(doc_id);
				
				
				
				
				//String posting_date = data[6];
				//String document_create_date = data[7];
				//String due_in_date = data[9];
				
				
				String invoice_currency = data[10];
				x.setInvoice_currency(invoice_currency);
				
				
				String document_type = data[11];
				x.setDocument_type(document_type);
				
				
				BigInteger posting_id = BigDecimal.valueOf(Double.parseDouble(data[12])).toBigInteger();
				//BigInteger posting_id = new BigInteger(data[12]);
				x.setPosting_id(posting_id);
				
				
				String area_business = data[13];
				//if(area_business == " ")
					area_business = null;
				x.setArea_business(area_business);
				
				
				Double total_open_amount = Double.parseDouble(data[14]);
				x.setTotal_open_amount(total_open_amount);
				
				
				//String baseline_create_date = data[15];
				
				
				String cust_payment_terms = data[16];
				x.setCust_payment_terms(cust_payment_terms);
				
				BigInteger invoice_id = null;
				try {
					invoice_id = BigDecimal.valueOf(Double.parseDouble(data[17])).toBigInteger();
					//BigInteger invoice_id = new BigInteger(data[17]);
				}catch(Exception e) {
					invoice_id = null;
				}finally {
					x.setInvoice_id(invoice_id);
				}
				
				
				
				BigInteger isOpen = new BigInteger(data[18]);
				x.setIsOpen(isOpen);
				
				//processing date
				SimpleDateFormat originalFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat originalFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat originalFormat3 = new SimpleDateFormat("yyyyMMdd");
				
				

				java.sql.Date posting_date = null;
				try {
					Date date1 = originalFormat2.parse(data[6]); 
					 posting_date = new java.sql.Date(date1.getTime());
				}catch(Exception e) {
					posting_date = null;
				}finally {
					x.setPosting_date(posting_date);
				}
				
				
				

				java.sql.Date document_create_date = null;
				try {
					Date date2 = originalFormat3.parse(data[7]); 
					document_create_date = new java.sql.Date(date2.getTime());
				}catch(Exception e) {
					document_create_date = null;
				}finally {
					x.setDocument_create_date(document_create_date);
				}
				

		        
				java.sql.Date due_in_date = null;
		        try {
		        	Date date3 = originalFormat3.parse(data[9]); 
		        	due_in_date = new java.sql.Date(date3.getTime());
		        }catch(Exception e) {
		        	due_in_date = null;
		        }finally {
		        	x.setDue_in_date(due_in_date);
		        }
		        
		        
		        
		        java.sql.Date baseline_create_date=null;
		        try {
		        	Date date4 = originalFormat3.parse(data[15]); 
		        	baseline_create_date = new java.sql.Date(date4.getTime());
		        }catch(Exception e) {
		        	baseline_create_date = null;
		        }finally {
		        	x.setBaseline_create_date(baseline_create_date);
		        }
		   
		        
		        
		        java.sql.Timestamp clear_date = null;
		        try {
		        	//String temp = data[3];
		        	Date date5 = originalFormat1.parse(data[3]);
		        	clear_date = new java.sql.Timestamp(date5.getTime());
			        
		        }catch(Exception e) {
		        	clear_date = null;
		        }finally {
		        	x.setClear_date(clear_date);
		        }
		        
				
				//adding object to arraylist
				invoiceDetails.add(x);
				
				
			}
			
			//printing to check the values
//			for(int i=0;i<5;i++) {
//				System.out.print(invoiceDetails.get(i).getBusiness_year() + "  ");
//				System.out.print(invoiceDetails.get(i).getCust_number() + "  ");
//				System.out.print(invoiceDetails.get(i).getName_customer() + "  ");
//				System.out.print(invoiceDetails.get(i).getClear_date() + "  ");
//				System.out.print(invoiceDetails.get(i).getBusiness_year() + "  ");
//				System.out.print(invoiceDetails.get(i).getDoc_id() + "  ");
//				System.out.print(invoiceDetails.get(i).getPosting_date() + "  ");
//				System.out.print(invoiceDetails.get(i).getDocument_create_date() + "  ");
//				System.out.print(invoiceDetails.get(i).getDue_in_date() + "  ");
//				System.out.print(invoiceDetails.get(i).getInvoice_currency() + "  ");
//				System.out.print(invoiceDetails.get(i).getDocument_type() + "  ");
//				System.out.print(invoiceDetails.get(i).getPosting_id() + "  ");
//				System.out.print(invoiceDetails.get(i).getArea_business() + "  ");
//				System.out.print(invoiceDetails.get(i).getTotal_open_amount() + "  ");
//				System.out.print(invoiceDetails.get(i).getBaseline_create_date() + "  ");
//				System.out.print(invoiceDetails.get(i).getCust_payment_terms() + "  ");
//				System.out.print(invoiceDetails.get(i).getInvoice_currency() + "  ");
//				System.out.print(invoiceDetails.get(i).getIsOpen());
//				System.out.println();
//			}
			
			
			try {
				insert.batchExecute(sql,pstmt,invoiceDetails);  //calling batch execute function to do database insertion
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("insert to database completed");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
	}catch(SQLException se) {
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(SQLException se1) {
			se1.printStackTrace();
		}
		
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException se2) {
			se2.printStackTrace();
		}
	}
	
	
}

}

				
				