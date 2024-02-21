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

public class Register extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("fullname");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String hotelname=req.getParameter("hotelname");
		String checkin_date=req.getParameter("checkin-date");
		String checkin_time=req.getParameter("checkin-time");
		String checkout_date=req.getParameter("checkout-date");
		String checkout_time=req.getParameter("checkout-time");
		String roomtype=req.getParameter("roomtype");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/capstone_project","root","Gopi@1044");
			PreparedStatement pst=con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, phone);
			pst.setString(4, roomtype);
			pst.setString(5, checkin_date);
			pst.setString(6, checkin_time);
			pst.setString(7, checkout_date);
			pst.setString(8, checkout_time);
			pst.executeUpdate();
			pw.println("Booking Completed Successfully");
		}
		catch(SQLException | ClassNotFoundException e) {
			pw.println(e);
		}
	}
}
