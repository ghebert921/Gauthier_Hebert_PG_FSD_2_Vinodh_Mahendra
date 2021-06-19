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

/**
 * Servlet implementation class SecuredServlet
 */
@WebServlet("/secured-servlet")
public class SecuredServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SELECT_SQL="SELECT * FROM flyawaydb.locations";
	private static final String SELECT_SQL2="SELECT * FROM flights";
	private static final String SELECT_SQL3="SELECT * FROM airlines";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
	        try {
				Connection connection = MySQLDatabaseUtils.getConnection();
				
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(SELECT_SQL);
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Admin Backend</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1> Welcome to Admin Backend </h1>");
				out.println("<h3> Locations Master List</h3>");
				out.println("<table border=5>");
				out.println("<tr>");
				out.println("<th>Id</th>");
				out.println("<th>Sources</th>");
				out.println("<th>Destinations</th>");
				out.println("</tr>");
				while(rs.next()) {
					out.println("<tr>");
					out.println("<td>"+rs.getInt("id")+"</td>");
					out.println("<td>"+rs.getString("sources")+"</td>");
					out.println("<td>"+rs.getString("destinations")+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				statement.close();
				rs.close();
				
				Statement statement2 = connection.createStatement();
				ResultSet rs2 = statement2.executeQuery(SELECT_SQL2);
			
				out.println("<h3> Flights Master List</h3>");
				out.println("<table border=5>");
				out.println("<tr>");
				out.println("<th>Id</th>");
				out.println("<th>Sources</th>");
				out.println("<th>Destinations</th>");
				out.println("<th>Airlines</th>");
				out.println("<th>Ticket Prices</th>");
				out.println("</tr>");
				while(rs2.next()) {
					out.println("<tr>");
					out.println("<td>"+rs2.getInt("id")+"</td>");
					out.println("<td>"+rs2.getString("sources")+"</td>");
					out.println("<td>"+rs2.getString("destinations")+"</td>");
					out.println("<td>"+rs2.getString("airline")+"</td>");
					out.println("<td>"+rs2.getInt("ticket_price")+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");

				statement2.close();
				rs2.close();
				
				Statement statement3 = connection.createStatement();
				ResultSet rs3 = statement3.executeQuery(SELECT_SQL3);
				
				out.println("<h3> Airlines Master List</h3>");
				out.println("<table border=5>");
				out.println("<tr>");
				out.println("<th>Id</th>");
				out.println("<th>Airlines</th>");
				out.println("</tr>");
				while(rs3.next()) {
					out.println("<tr>");
					out.println("<td>"+rs3.getInt("id")+"</td>");
					out.println("<td>"+rs3.getString("airline")+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<a href='/fsd-2-project/logout' align='right'>Logout</a>");
				out.println("</body>");
				out.println("</html>");
				statement3.close();
				rs3.close();
				
				
				connection.close();
				
				
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}


	}

}
