package com.simplilearn.workshop;

import java.io.IOException;


import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


@WebServlet("/registration-info")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("NAME");
		String email = request.getParameter("EMAIL");
		String phone = request.getParameter("PHONE");
		
		
		try {
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			String INSERT_SQL = "insert into customer_info(cust_name,email,phone) values(?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, phone);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Payment</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<hr>");
			out.println("<form action='/fsd-2-project/payment' method='GET'");
			out.println("<p>Please proceed to the payment portal below</p>");
			out.println("<p><input type='submit' value='Continue'></p>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
