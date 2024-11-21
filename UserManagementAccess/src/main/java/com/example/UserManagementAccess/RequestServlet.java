package com.example.UserManagementAccess;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RequestServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String role1 = (String) request.getSession().getAttribute("role");
        if (role1 != null && (role1.equals("Employee"))) {

        List<String> softwareNames = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "root");
            String sql = "SELECT name FROM software";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String softwareName = rs.getString("name");
                softwareNames.add(softwareName);
                System.out.println("Fetched software: " + softwareName);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("softwareNames", softwareNames);
        System.out.println("Total software names fetched: " + softwareNames.size());
        request.getRequestDispatcher("/requestAccess.jsp").forward(request, response);
    }
        
        String role = (String) request.getSession().getAttribute("role");
        if (role != null && (role.equals("Manager") || role.equals("Admin"))) {
            List<Map<String, Object>> requests = new ArrayList<>();

            try {
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "root");
                String query = "SELECT r.id, u.username AS employee_name, s.name AS software_name, " +
                               "r.access_type, r.reason " +
                               "FROM requests r " +
                               "JOIN users u ON r.user_id = u.id " +
                               "JOIN software s ON r.software_id = s.id " +
                               "WHERE r.status = 'Pending'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Map<String, Object> req = new HashMap<>();
                    req.put("id", rs.getInt("id"));
                    req.put("employeeName", rs.getString("employee_name"));
                    req.put("softwareName", rs.getString("software_name"));
                    req.put("accessType", rs.getString("access_type"));
                    req.put("reason", rs.getString("reason"));
                    requests.add(req);
                }

                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("/pendingRequests.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/requestAccess.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute("role");
        String username = (String) request.getSession().getAttribute("username");
        String software = request.getParameter("software");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "root");
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (" +
                         "(SELECT id FROM users WHERE username = '" + username + "'), " +
                         "(SELECT id FROM software WHERE name = '" + software + "'), " +
                         "'" + accessType + "', '" + reason + "', 'Pending')";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if ("Employee".equals(role)) {
            response.sendRedirect("request");
        } else if ("Manager".equals(role) || "Admin".equals(role)) {
            response.sendRedirect("request");
        }
    }
}
