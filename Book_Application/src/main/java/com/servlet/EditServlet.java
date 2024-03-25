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
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final String query="select bookname,bookedition,bookprice from book where bookname=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 //get printwriter
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get the bookname of record
		String bookname=req.getParameter("bookname");
		//book info
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
				 ResultSet rs=ps.executeQuery();
				 rs.next();
				 pw.println("<form action='editurl?bookname="+bookname+"' method='post'>");
				 pw.println("<table align='center'>");
				 pw.println("<tr>");
				 pw.println("<td>Book Name</td>");
				 pw.println("<td><input type='text' name='bookname' value="+rs.getString(1)+"></td>");
				 pw.println("</tr>");
				 pw.println("<tr>");
				 pw.println("<td>Book Edition</td>");
				 pw.println("<td><input type='text' name='bookedition' value="+rs.getString(2)+"></td>");
				 pw.println("</tr>");
				 pw.println("<tr>");
				 pw.println("<td>Book Price</td>");
				 pw.println("<td><input type='text' name='bookprice' value="+rs.getString(3)+"></td>");
				 pw.println("</tr>");
				 pw.println("<tr>");
 				 pw.println("<td><input type='submit' value='Edit'></td>");
 				 pw.println("<td><input type='reset' value='Cancel'></td>");
				 pw.println("<tr>");
				 pw.println("</table>");
				 pw.println("</form>");

				 


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
