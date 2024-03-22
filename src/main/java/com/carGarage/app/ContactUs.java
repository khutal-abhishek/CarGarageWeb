package com.carGarage.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class ContactUs
 */
public class ContactUs extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("uname");
		String email=request.getParameter("umail");
		String text=request.getParameter("utext");
//		out.println(name);
//		out.println(email);
//		out.println(text);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/carlogin","root","@Abhi2409");
			PreparedStatement stmt=conn.prepareStatement("insert into contactus values('0',?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, text);
			int value=stmt.executeUpdate();
			if(value>0) {
				out.println("Your Form Submitted!!");
			}
			else {
				out.print("not Submited");
			}
			
			
		}
		catch(Exception e) {
			out.print("Error is"+e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
