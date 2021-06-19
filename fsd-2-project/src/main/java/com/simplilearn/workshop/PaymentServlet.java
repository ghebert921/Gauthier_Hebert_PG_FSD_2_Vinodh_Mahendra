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


@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
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
		try {
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(SELECT_SQL);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Please pay for your flight here</title>");
			out.println("</head>");
			out.println("<body>");
			
			out.println("<form action='/fsd-2-project/confirm' method='GET'");
			while(rs.next()) {
				Integer party = rs.getInt("party_size");
				Integer ticket = rs.getInt("ticket_price");
				out.println("<p>Your total is : $" +(party*ticket)+"</p>");
			}
			rs.close();
			statement.close();
			connection.close();
			out.println("<p><input type='submit' value='Continue'></p>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

}
