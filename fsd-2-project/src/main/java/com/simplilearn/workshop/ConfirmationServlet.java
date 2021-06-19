package com.simplilearn.workshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

@WebServlet("/confirm")
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SELECT_SQL="SELECT"
			+ "					MAX(depart_date) AS depart_date,"
			+ "					MAX(sources) AS sources,"
			+ "					MAX(destinations) AS destinations,"
			+ "					party_size,"
			+ "					MAX(airline) AS airline,"
			+ "					MAX(ticket_price) AS ticket_price,"
			+ "					MAX(cust_name) AS cust_name,"
			+ "					MAX(phone) AS phone,"
			+ "					MAX(email) AS email"
			+ " FROM"
			+ "					customer_info"
			+ " GROUP BY"
			+ "					party_size AND ticket_price;";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		Connection connection;
		try {
			connection = MySQLDatabaseUtils.getConnection();
		
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(SELECT_SQL);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>PThank you for choosing FlyAway</title>");
		out.println("</head>");
		out.println("<body>");
		
		while(rs.next()) {
			String date = rs.getString("depart_date");
			String sources = rs.getString("sources");
			String destination = rs.getString("destinations");
			String airline = rs.getString("airline");
			String name = rs.getString("cust_name");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			Integer party = rs.getInt("party_size");
			Integer ticket = rs.getInt("ticket_price");
			out.println("<p>Name: "+name+"</p>");
			out.println("<p>Phone #: "+phone+"</p>");
			out.println("<p>Email: "+email+"</p>");
			out.println("<p>Date: "+date+"</p>");
			out.println("<p>Airline: "+airline+"</p>");
			out.println("<p>From: "+sources+"</p>");
			out.println("<p>To: "+destination+"</p>");
			out.println("<p>Tickets purchased: "+party+"</p>");
			out.println("<p>Total : $" +(party*ticket)+"</p>");
		}
		rs.close();
		statement.close();
		String DROP_SQL = "drop table customer_info";
		Statement statement2 = connection.createStatement();
		statement2.execute(DROP_SQL);
		statement2.close();
		String DROP_SQL2 = "drop procedure show_flights";
		Statement statement3 = connection.createStatement();
		statement3.execute(DROP_SQL2);
		statement3.close();
		connection.close();
		out.println("<p align='center'><a href='index.html'>Return to homepage</a></p>");
		out.println("</body>");
		out.println("</html>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
