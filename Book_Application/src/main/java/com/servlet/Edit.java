package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class Edit extends HttpServlet {
	private static final String query="update book set bookname=?,bookedition=?,bookprice=? where bookname=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 //get printwriter
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get the bookname of record
		String bookname=req.getParameter("bookname");
		String bookedition=req.getParameter("bookedition");
		String bookprice=req.getParameter("bookprice");
		
		 //load jdbc driver 
		try { 			    
					Class.forName("org.postgresql.Driver");
		  		} catch (Exception e) {
					e.printStackTrace();
				}
		//generate connection
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/jdbc", "postgres", "abc");
			PreparedStatement ps=con.prepareStatement((query));
			{
				 ps.setString(1, bookname);
				 ps.setString(2, bookedition);
				 ps.setString(3, bookprice);
				 ps.setString(4, bookname);

 				 int count=ps.executeUpdate( );
				 if(count==1)
				 {
					 pw.println("<h2>Record is updated</h2>");
				 }
				 else
				 {
					 pw.println("<h2>Not updated</h2>");
				 }

			}
				 

				 
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h2>"+e.getMessage()+"</h2>");
 		}
         
		pw.println("<br>");
		pw.println("<a href='home.html'>Home</a>");

 			}
			
			 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}
}