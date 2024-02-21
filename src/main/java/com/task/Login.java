package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("username");
		String pwd=req.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/capstone_project","root","Gopi@1044");
			PreparedStatement pst=con.prepareStatement("select Password from login where User_name=?");
			pst.setString(1, name);
			ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                String dbPassword = resultSet.getString("Password");
                if (pwd.equals(dbPassword)) {
                    res.sendRedirect("Cust_Home.html");
                } else {
                    pw.println("Invalid username or password");
                }
            } else {
                pw.println("User not found");
            }
            resultSet.close();
            pw.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            pw.println("An error occurred while processing your request");
        }
	}
}
