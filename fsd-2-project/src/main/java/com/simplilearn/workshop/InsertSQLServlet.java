package com.simplilearn.workshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

@WebServlet("/insert-data")
public class InsertSQLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String airline = request.getParameter("airline");
		String price = request.getParameter("ticket_price");

		
		try {
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			String INSERT_SQL = "insert into customer_info(airline,ticket_price) values(?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
			
			preparedStatement.setString(1, airline);
			preparedStatement.setString(2, price);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
			
			response.setContentType("text/html");
			
			//PrintWriter out = response.getWriter();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PrintWriter out = response.getWriter();
		
		out.println("insert servlet works");
	}

}
