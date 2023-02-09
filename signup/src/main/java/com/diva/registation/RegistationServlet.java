package com.diva.registation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistationServlet
 */
@WebServlet("/register")
public class RegistationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String uname=request.getParameter("name");
	String uemail=request.getParameter("email");
	String upwd=request.getParameter("pass");
	String umobile=request.getParameter("contact");
	RequestDispatcher dispacher=null;
	Connection con=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:Mysql://localhost:3306/divakarmysql?useSSL=false","root","Divakar@123");
		PreparedStatement pst=con.prepareStatement("INSERT INTO users(uname,upwd,uemail,umobile) VALUES(?,?,?,?)");
		pst.setString(1, uname);
		pst.setString(2, upwd);
		pst.setString(3, uemail);
		pst.setString(4, umobile);
		
		int n=pst.executeUpdate();
		System.out.println(n+"no of rows inserted");
		dispacher=request.getRequestDispatcher("registration.jsp");
		if(n>0) {
			request.setAttribute("status", "sucess");
		}else {
			request.setAttribute("status", "failed");
		}
		dispacher.forward(request, response);
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			//con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}

}
