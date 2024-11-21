package com.example.UserManagementAccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = null;

        try
        {
        	Class.forName("org.postgresql.Driver");
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "root"); 
        	PreparedStatement ps = con.prepareStatement("SELECT role FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", role);

        if ("Employee".equals(role)) {
            response.sendRedirect("request");
        } else if ("Manager".equals(role)) {
            response.sendRedirect("request");
        } else if ("Admin".equals(role)) {
            response.sendRedirect("createSoftware.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
		doGet(request, response);
	}

}
