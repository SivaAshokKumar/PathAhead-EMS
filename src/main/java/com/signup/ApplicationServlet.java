

package com.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submitApplication")
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JobApplications";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    private static final String RESUME_SAVE_PATH = "D:\\Resume_of_Applicants\\";
 // Specify the directory to save resumes

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String degree = request.getParameter("degree");
        String collegeName = request.getParameter("collegeName");
        int experience = Integer.parseInt(request.getParameter("experience"));
        int relevantExperience = Integer.parseInt(request.getParameter("relevantExperience"));
        String noticePeriod = request.getParameter("noticePeriod");
        String skills = request.getParameter("skills");

        // Retrieve the uploaded resume
        Part resumePart = request.getPart("resume");
        InputStream resumeInputStream = resumePart.getInputStream();
        
        // Get the file name from the Part object
        String fileName = resumePart.getSubmittedFileName();

        // Define the full file path
        String filePath = RESUME_SAVE_PATH + fileName;

        // Save the resume to the local file system
        try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = resumeInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("Failed to save resume to the local file system: " + e.getMessage());
            return;
        }

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insert form data and resume file into the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO applications (first_name, last_name, mobile, email, degree, college_name, experience, relevant_experience, notice_period, skills, resume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, mobile);
                    statement.setString(4, email);
                    statement.setString(5, degree);
                    statement.setString(6, collegeName);
                    statement.setInt(7, experience);
                    statement.setInt(8, relevantExperience);
                    statement.setString(9, noticePeriod);
                    statement.setString(10, skills);
                    statement.setBlob(11, resumeInputStream); // Store the resume as a BLOB

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Application submitted successfully.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database operation failed: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("MySQL Driver not found: " + e.getMessage());
            return;
        }

        response.sendRedirect("success1.jsp"); // Redirect to success page
    }
}

//THIS CODE IS NOT WORKING
/*package com.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submitApplication")
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JobApplications";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String degree = request.getParameter("degree");
        String collegeName = request.getParameter("collegeName");
        int experience = Integer.parseInt(request.getParameter("experience"));
        int relevantExperience = Integer.parseInt(request.getParameter("relevantExperience"));
        String noticePeriod = request.getParameter("noticePeriod");
        String skills = request.getParameter("skills");
        
        // Retrieve the uploaded resume
        Part resumePart = request.getPart("resume");
        InputStream resumeInputStream = resumePart.getInputStream();

        // Insert form data into the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO applications (first_name, last_name, mobile, email, degree, college_name, experience, relevant_experience, notice_period, skills, resume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, mobile);
                statement.setString(4, email);
                statement.setString(5, degree);
                statement.setString(6, collegeName);
                statement.setInt(7, experience);
                statement.setInt(8, relevantExperience);
                statement.setString(9, noticePeriod);
                statement.setString(10, skills);
                statement.setBlob(11, resumeInputStream); // Store the resume as a BLOB

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Application submitted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("success1.jsp"); // Redirect to success page
    }
}
*/

// THIS BELOW CODE WORKS FOR SUBMITING THE RESUME BUT NOT WORK WHILE INSERTING THE DATA
//THIS BELOW CODE SUBMITS THE FORM AND UPDATES THE TABLE BASED ON USER INPPUT WITH THE RESUME SUBMIT IN MYSQLWORKBENCH
/*
package com.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submitApplication")
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JobApplications";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect form data from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String degree = request.getParameter("degree");
        String collegeName = request.getParameter("collegeName");
        int experience = Integer.parseInt(request.getParameter("experience"));
        int relevantExperience = Integer.parseInt(request.getParameter("relevantExperience"));
        String noticePeriod = request.getParameter("noticePeriod");
        String skills = request.getParameter("skills");

        // Retrieve the uploaded resume
        Part resumePart = request.getPart("resume");
        InputStream resumeInputStream = resumePart.getInputStream();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Insert form data and resume file into the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO applications (first_name, last_name, mobile, email, degree, college_name, experience, relevant_experience, notice_period, skills, resume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, mobile);
                    statement.setString(4, email);
                    statement.setString(5, degree);
                    statement.setString(6, collegeName);
                    statement.setInt(7, experience);
                    statement.setInt(8, relevantExperience);
                    statement.setString(9, noticePeriod);
                    statement.setString(10, skills);
                    statement.setBlob(11, resumeInputStream); // Store the resume as a BLOB

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Application submitted successfully.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database operation failed: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("MySQL Driver not found: " + e.getMessage());
            return;
        }

        response.sendRedirect("success.jsp"); // Redirect to success page
    }
}
 

*/
/*
//THIS BELOW CODE IT DOESN't WORK TO INSERT VALUSE AND SUBMITING THE RESUME

package com.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/submitApplication")
@MultipartConfig
public class ApplicationServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JobApplications"; // Your DB URL
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "1234"; // Replace with your DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String degree = request.getParameter("degree");
        String collegeName = request.getParameter("collegeName");
        int experience = Integer.parseInt(request.getParameter("experience"));
        int relevantExperience = Integer.parseInt(request.getParameter("relevantExperience"));
        String noticePeriod = request.getParameter("noticePeriod");
        String skills = request.getParameter("skills");
        Part resumePart = request.getPart("resume");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO applications (first_name, last_name, mobile, email, degree, college_name, experience, relevant_experience, notice_period, skills, resume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, mobile);
                statement.setString(4, email);
                statement.setString(5, degree);
                statement.setString(6, collegeName);
                statement.setInt(7, experience);
                statement.setInt(8, relevantExperience);
                statement.setString(9, noticePeriod);
                statement.setString(10, skills);
                
                // Store the resume as a BLOB in the database
                try (InputStream resumeInputStream = resumePart.getInputStream()) {
                    statement.setBlob(11, resumeInputStream);
                }

                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors for debugging
        }

        response.sendRedirect("success.jsp"); // Redirect to a success page
    }
}
*/
