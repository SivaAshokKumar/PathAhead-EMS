package com.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submitForm")
public class SubmitFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String phone_num = request.getParameter("phone_num");
        String email = request.getParameter("email");
        String work_experience = request.getParameter("work_experience");
        String academic_background = request.getParameter("academic_background");
        String certifications = request.getParameter("certifications");
        String preferred_job_role = request.getParameter("preferred_job_role");
        String resume = request.getParameter("resume");
        String cover_letter = request.getParameter("cover_letter");

        String url = "jdbc:mysql://localhost:3306/JobPortal?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "1234";

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "INSERT INTO user_details (name, phone_num, email, work_experience, academic_background, certifications, preferred_job_role, resume, cover_letter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, phone_num);
                stmt.setString(3, email);
                stmt.setString(4, work_experience);
                stmt.setString(5, academic_background);
                stmt.setString(6, certifications);
                stmt.setString(7, preferred_job_role);
                stmt.setString(8, resume);
                stmt.setString(9, cover_letter);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    // Redirect to the preferred job role page
                    switch (preferred_job_role) {
                        case "Software Engineer":
                            response.sendRedirect("software_engineer_page.html");
                            break;
                        case "Data Analyst":
                            response.sendRedirect("data_analyst_page.html");
                            break;
                        case "Product Manager":
                            response.sendRedirect("product_manager_page.html");
                            break;
                        case "Intern":
                        	response.sendRedirect("intern.html");
                        	break;
                        default:
                            response.sendRedirect("other_roles_page.html");
                            break;
                    }
                } else {
                    response.getWriter().println("Error: Unable to save data.");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error: MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to save data. " + e.getMessage());
        }
    }
}
