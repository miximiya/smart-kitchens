package Sensor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sensorServer {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/smart-kitchens?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // 随机添加数据
    public void sensorIntRandom() {
        try (Connection conn = getConnection()) {
            for (int i = 0; i < 10; i++) {
                String name = "设备" + i;
                String data = "制造商" + i;
                String time = "2023-12-01 08:00:00";


                String sql = "INSERT INTO sensor(name, data, time) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, data);
                    pstmt.setString(3, time);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 自主添加数据
    public static boolean sensorIntAutonomic(Sensor sensor) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO sensor(name, data, time) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, sensor.getName());
                pstmt.setString(2, sensor.getData());
                pstmt.setString(3,sensor.getTime());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除数据
    public boolean sensorDelete(String id) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM sensor WHERE id = ?";
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
    public List<Sensor> queryPage(int start, int size) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM sensor LIMIT ?, ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, start);
                pstmt.setInt(2, size);
                try (ResultSet rs = pstmt.executeQuery()) {
                    List<Sensor> list = new ArrayList<>();
                    while (rs.next()) {
                        Sensor sensor = new Sensor();
                        sensor.setId(Integer.parseInt(rs.getString("id")));
                        sensor.setName(rs.getString("name"));
                        sensor.setData(rs.getString("data"));
                        sensor.setTime(rs.getString("time"));
                        list.add(sensor);
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
    public int getTotalCount() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT COUNT(*) FROM sensor";
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
