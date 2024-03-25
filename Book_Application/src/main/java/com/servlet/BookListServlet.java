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

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	private static final String query="select bookname,bookedition,bookprice from book";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 //get printwriter
		PrintWriter pw=res.getWriter();
		//set content type
		res.setContentType("text/html");
		//book info
		 
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
				ResultSet rs=ps.executeQuery();
				pw.println("<table border='2' align='center'>");
				pw.println("<tr>");
 				pw.println("<th>Book Name</th>");
				pw.println("<th>Book Edition</th>");
				pw.println("<th>Book Price</th>");
				pw.println("<th>Edit</th>");
				pw.println("<th>Delete</th>");

				pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
 				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td><a href='edit?bookname="+rs.getString(1)+"'>Edit</a></td>");
				pw.println("<td><a href='delete?bookname="+rs.getString(1)+"'>Delete</a></td>");

				pw.println("</tr>");
			}
			pw.println("</table");
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
