package operation_implementator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.GetConnection;
import model.Pojo;

public class Implementator {

    public boolean login_user(Pojo pojo) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isAuthenticated = false;

        try {
            connection = GetConnection.getConnection();

            if (connection == null) {
                return false;
            }

            // Check if user exists in either Consumer_port or Seller_port
            String sql = "SELECT COUNT(*) AS count FROM ( " +
                         "SELECT c_port_id AS port_id, cpassword AS password FROM Consumer_port WHERE c_port_id = ? AND cpassword = SHA2(?, 256) " +
                         "UNION ALL " +
                         "SELECT s_port_id AS port_id, spassword AS password FROM Seller_port WHERE s_port_id = ? AND spassword = SHA2(?, 256) " +
                         ") AS combined_users";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, pojo.getPort_id());
            pstmt.setString(2, pojo.getPassword());
            pstmt.setInt(3, pojo.getPort_id());
            pstmt.setString(4, pojo.getPassword());

            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt("count") > 0) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isAuthenticated;
    }
    
    
    public String register_user(Pojo pojo) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message = "Registration failed";

        try {
            connection = GetConnection.getConnection();

            if (connection == null) {
                return "Database connection failed!";
            }

            callableStatement = connection.prepareCall("{CALL register_user(?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, pojo.getPort_id());
            callableStatement.setString(2, pojo.getPassword());
            callableStatement.setString(3, pojo.getConfirm_password());
            callableStatement.setString(4, pojo.getLocation());
            callableStatement.setString(5, pojo.getRole());
System.out.println(pojo.getConfirm_password()+ "hdiii");
            boolean hasResultSet = callableStatement.execute();

            if (hasResultSet) {
                ResultSet rs = callableStatement.getResultSet();
                if (rs.next()) {
                    message = rs.getString("message");
                }
                rs.close();
            }
        } catch (SQLException e) {
            message = "An error occurred during registration: " + e.getMessage();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return message;
    }
    
}
