package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/capstone_project","root","Gopi@1044");
			PreparedStatement pst=con.prepareStatement("insert into login values(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, username);
			pst.setString(4, password);
			pst.executeUpdate();
			res.sendRedirect("Login.html");
		}
		catch(SQLException | ClassNotFoundException e) {
			pw.println(e);
		}
	}
}
