package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pojo;
import operation_implementator.Implementator;

import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Implementator implementator = new Implementator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            handleRegistration(req, resp);
        } else if ("login".equals(action)) {
            handleLogin(req, resp);
        } else {
            resp.getWriter().println("<script>alert('Invalid action!');window.location='index.jsp';</script>");
        }
    }

    private void handleRegistration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Processing registration...");

        String portIdStr = req.getParameter("portId");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        String location = req.getParameter("location");
        String role = req.getParameter("userType");

        // Debugging log to check received values
        System.out.println("Received Data -> Port ID: " + portIdStr + ", Password: " + password + 
                           ", Confirm Password: " + confirmPassword + ", Location: " + location + ", Role: " + role);

        if (portIdStr == null || password == null || confirmPassword == null ||
            location == null || role == null || portIdStr.trim().isEmpty() ||
            password.trim().isEmpty() || confirmPassword.trim().isEmpty() ||
            location.trim().isEmpty() || role.trim().isEmpty()) {

            System.out.println("Error: Missing registration fields.");
            resp.getWriter().println("<script>alert('All fields are required.');window.location='register.jsp';</script>");
            return;
        }

        int portId;
        try {
            portId = Integer.parseInt(portIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Port ID format.");
            resp.getWriter().println("<script>alert('Invalid Port ID.');window.location='register.jsp';</script>");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Error: Passwords do not match.");
            resp.getWriter().println("<script>alert('Passwords do not match.');window.location='register.jsp';</script>");
            return;
        }

        Pojo pojo = new Pojo();
        pojo.setPort_id(portId);
        pojo.setPassword(password);
        pojo.setConfirm_password(confirmPassword);
        pojo.setLocation(location);
        pojo.setRole(role);

        String message = implementator.register_user(pojo);
        System.out.println("Registration message: " + message);

        if (message.contains("successfully")) {
            resp.sendRedirect("login.jsp");
        } else {
            resp.getWriter().println("<script>alert('" + message + "');window.location='register.jsp';</script>");
        }
    }


    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String portIdStr = req.getParameter("portId");
        String password = req.getParameter("password");

        if (portIdStr == null || password == null || portIdStr.trim().isEmpty() || password.trim().isEmpty()) {
            resp.getWriter().println("<script>alert('All fields are required.');window.location='login.jsp';</script>");
            return;
        }

        int portId;
        try {
            portId = Integer.parseInt(portIdStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("<script>alert('Invalid Port ID.');window.location='login.jsp';</script>");
            return;
        }

        // Set data in Pojo
        Pojo pojo = new Pojo();
        pojo.setPort_id(portId);
        pojo.setPassword(password);

        // Validate login
        boolean isAuthenticated = implementator.login_user(pojo);

        if (isAuthenticated) {
            resp.sendRedirect("home.jsp"); // Redirect to home page on success
        } else {
            resp.getWriter().println("<script>alert('Invalid credentials.');window.location='login.jsp';</script>");
        }
    }
}
