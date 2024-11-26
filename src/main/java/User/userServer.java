package User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userServer {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/smart-kitchens?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // 随机添加数据
    public void userIntRandom() {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO user(name, password,  phone, address, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < 10; i++) {
                    String username = "用户" + i;
                    String password = "123";
                    String phone = "电话" + i;
                    String address = "地址" + i;
                    String role = "用户";

                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setString(3, phone);
                    pstmt.setString(4, address);
                    pstmt.setString(5, role);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 自主添加数据
    public static boolean userIntAutonomic(User user) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO user(name, password,  phone, address, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getPhone());
                pstmt.setString(4, user.getAddress());
                pstmt.setString(5, user.getRole());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除数据
    public static boolean deleteData(String id) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE id = ?")) {
                pstmt.setString(1, id);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 分页查询
    public static List<User> queryPage(int start, int size) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM user LIMIT ?, ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, start);
                pstmt.setInt(2, size);
                try (ResultSet rs = pstmt.executeQuery()) {
                    List<User> list = new ArrayList<>();
                    while (rs.next()) {
                        User user = new User();
                        user.setId(Integer.parseInt(rs.getString("id")));
                        user.setName(rs.getString("name"));
                        user.setPhone(rs.getString("phone"));
                        user.setAddress(rs.getString("address"));
                        user.setRole(rs.getString("role"));
                        list.add(user);
                    }
                    return list;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询表中的总数，计算
    public static int getTotalCount() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT COUNT(*) FROM user";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 获取数据库连接
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
