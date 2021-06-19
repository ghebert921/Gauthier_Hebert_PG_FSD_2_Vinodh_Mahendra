package com.simplilearn.workshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

/**
 * Servlet implementation class StoredProcedure
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depart_date = request.getParameter("DATE");
		String source = request.getParameter("SOURCE");
		String destination = request.getParameter("DESTINATION");
		String party_size = request.getParameter("NUMBER");
		
		try {
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			
			
			
			
			String INSERT_SQL = "insert into customer_info(depart_date,sources,destinations,party_size) values(?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
			
			preparedStatement.setString(1, depart_date);
			preparedStatement.setString(2, source);
			preparedStatement.setString(3, destination);
			preparedStatement.setString(4, party_size);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Searching for flights...</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Here are some flights we found for you that meet your travel details</h1>");
			out.println("</body>");
			out.println("</html>");
			
			
			String create_procedure = "create procedure show_flights()" +
					"BEGIN"+ 
					" SELECT * FROM  flights AS f, customer_info AS c" +
					" WHERE (c.sources = f.sources) AND (c.destinations = f.destinations);" +
					" END";
		
			Statement statement2 = connection.createStatement();
			statement2.executeUpdate(create_procedure);
		
			statement2.close();
		
		
			CallableStatement callableStatement = connection.prepareCall("{call show_flights}");
		

			ResultSet rs = callableStatement.executeQuery();
		
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Available Flights</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<hr>");
			out.println("<form action='/fsd-2-project/register.html'");
			out.println("<p>");
			out.println("<span><b>Source</b></span>");
			out.println("<span><b>Destination</b></span>");
			out.println("<span><b>Airline</b></span>");
			out.println("<span><b>Price</b></span>");
			out.println("</p>");
			while(rs.next()) {
				out.println("<p>");
				out.println("<input type='radio' name='FLIGHT'>");
				out.println("<span>"+rs.getString("sources")+"</span>");
				out.println("<span>"+rs.getString("destinations")+"</span>");
				out.println("<span>"+rs.getString("airline")+"</span>");
				out.println("<span>"+rs.getInt("ticket_price")+"</span>");
				out.println("</p>");
				
				String airline = rs.getString("airline");
				Integer ticket_price = rs.getInt("ticket_price");	
				
				String INSERT_SQL2 = "insert into customer_info(airline,ticket_price) values(?,?)";
				
				PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_SQL2);
				
				preparedStatement2.setString(1, airline);
				preparedStatement2.setInt(2, ticket_price);
				
				preparedStatement2.executeUpdate();
				preparedStatement2.close();
			}
			rs.close();
			out.println("<p><input type='submit' value='Continue'></p>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
			callableStatement.close();
			connection.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
	
			e.printStackTrace();
		}

}
}