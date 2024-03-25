package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final String query="delete from book where bookname=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 //get printwriter
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get the bookname of record
		String bookname=req.getParameter("bookname");
		 
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
			 

 				 int count=ps.executeUpdate( );
				 if(count==1)
				 {
					 pw.println("<h2>Record is deleted</h2>");
				 }
				 else
				 {
					 pw.println("<h2>Not deleted</h2>");
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
