package Login;

import java.sql.*;

public class loginServlet {
    public static boolean validateUser(String name, String password) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smart-kitchens?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false", "root", "123456");
            String query = "SELECT * FROM user WHERE name=? AND password=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getUserRote(String name) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sx?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false", "root", "123456");
            String query = "SELECT rote FROM user WHERE name=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("rote");
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
