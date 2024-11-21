package com.example.UserManagementAccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class SoftwareServlet
 */
@WebServlet("/software")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoftwareServlet() {
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
		String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] accessLevels = request.getParameterValues("accessLevels");

        StringBuilder accessLevelString = new StringBuilder();
        if (accessLevels != null) {
            for (String level : accessLevels) {
                accessLevelString.append(level).append(",");
            }
            accessLevelString.deleteCharAt(accessLevelString.length() - 1);
        }

        try {
        	Class.forName("org.postgresql.Driver");
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "root");
            PreparedStatement ps = con.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, accessLevelString.toString());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("createSoftware.jsp");
		doGet(request, response);
	}

}
