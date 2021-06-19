package com.simplilearn.workshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


@WebServlet("/travel-details")
public class TravelDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection connection = MySQLDatabaseUtils.getConnection();

			
			 String CREATE_SQL =
			 "create table customer_info (customer_id int(1), depart_date varchar(45), sources varchar(45), destinations varchar(45), party_size int(100), airline varchar(45), ticket_price int(100), cust_name varchar(45), phone varchar(45), email varchar(45))";
			 
			 Statement statement = connection.createStatement();
			 
			 statement.execute(CREATE_SQL);
			 
			
			/*
			 * String ALTER_SQL = "ALTER TABLE department add depthead varchar(15)";
			 * 
			 * Statement statement = connection.createStatement();
			 * statement.execute(ALTER_SQL);
			 */
			
			/*String DROP_SQL = "drop table department";
			Statement statement = connection.createStatement();
			statement.execute(DROP_SQL);
			
			statement.close();
			*/
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Flights Details</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Please enter your flight details below: </h1>");
			out.println("<hr>");
			out.println("<form action='/fsd-2-project/search' method='POST'>");
			out.println("<table>");
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<td>When :</td>");
			out.println("<td><input type='text' name='DATE'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>From :</td>");
			out.println("<td><input type='text' name='SOURCE'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>To :</td>");
			out.println("<td><input type='text' name='DESTINATION'></td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td>How Many :</td>");
			out.println("<td><input type='text' name='NUMBER'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>&nbsp;</td>");
			out.println("<td>");
			out.println("<input type='submit' value='Register' name='btnRegister'>&nbsp;");
			out.println("<input type='reset' value='Clear' name='btnClear'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
}
