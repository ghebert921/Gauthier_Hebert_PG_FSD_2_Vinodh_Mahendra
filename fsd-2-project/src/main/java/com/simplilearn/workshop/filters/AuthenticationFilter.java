package com.simplilearn.workshop.filters;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/secured-servlet")
public class AuthenticationFilter implements Filter {
	public static String pass;
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		System.out.println("destroy method is called in " + this.getClass().getName());
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter method is called in " + this.getClass().getName());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String ipAddress = request.getRemoteAddr();
		while(password.equals("password")) {
			if(username.equals("admin") && password.equals("password")) {
				System.out.println("User logged in " + ipAddress + " on " + new Date().toString());
				System.out.println("Please change your password");
				Scanner pass2 = new Scanner(System.in);
				String newPass = pass2.nextLine();
				password = newPass;
				
			}
			else{
				PrintWriter out = response.getWriter();
				out.println("<h2>Invalid Login. Please try again</h2>");
			}
		String passChange = password;
		pass = passChange;
		}
		
		if(username.equals("admin")&&password.equals(pass)) {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init method is called in " +this.getClass().getName());
	}

}
