package Equipment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class equipmentServer {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/smart-kitchens?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // 随机添加数据
    public void equipmentIntRandom() {
        try (Connection conn = getConnection()) {
            for (int i = 0; i < 10; i++) {
                String name = "设备" + i;
                String manufacturer = "制造商" + i;
                String status = "关闭";
                String location = "设备位置" + i;

                String sql = "INSERT INTO equipment(name, manufacturer, status, location) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, manufacturer);
                    pstmt.setString(3, status);
                    pstmt.setString(4, location);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 自主添加数据
    public static boolean equipmentIntAutonomic(Equipment equipment) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO equipment(name, manufacturer, status, location) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, equipment.getName());
                pstmt.setString(2, equipment.getManufacturer());
                pstmt.setString(3, equipment.getStatus());
                pstmt.setString(4, equipment.getLocation());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除数据
    public static boolean equipmentDelete(String id) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM equipment WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
    public static List<Equipment> queryPage(int start, int size) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM equipment LIMIT ?, ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, start);
                pstmt.setInt(2, size);
                try (ResultSet rs = pstmt.executeQuery()) {
                    List<Equipment> list = new ArrayList<>();
                    while (rs.next()) {
                        Equipment equipment = new Equipment();
                        equipment.setId(rs.getInt("id"));
                        equipment.setName(rs.getString("name"));
                        equipment.setStatus(rs.getString("status"));
                        equipment.setManufacturer(rs.getString("manufacturer"));
                        equipment.setLocation(rs.getString("location"));
                        list.add(equipment);
                    }
                    return list;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询数据库中的所有内容，将其分页
    public static int getTotalCount() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT COUNT(*) FROM equipment";
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
