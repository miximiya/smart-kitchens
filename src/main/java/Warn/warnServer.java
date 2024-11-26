package Warn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class warnServer {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/smart-kitchens?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // 随机添加数据
    public void warnIntRandom() {
        try (Connection conn = getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO warn(source, messages, time) VALUES (?, ?, ?)")) {
                for (int i = 0; i < 10; i++) {
                    String source = "来源" + i;
                    String messages = "信息" + i;
                    String time = "2023-12-01 08:00:00";

                    pstmt.setString(1, source);
                    pstmt.setString(2, messages);
                    pstmt.setString(3, time);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 自主添加数据
    public static boolean warnIntAutonomic(Warn warn) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO warn(source, messages, time) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, warn.getSource());
                pstmt.setString(2, warn.getMessages());
                pstmt.setString(3, warn.getTime());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除数据
    public static boolean warnDelete(String id) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM warn WHERE id = ?")) {
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
    public static List<Warn> queryPage(int start, int size) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM warn LIMIT ?, ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, start);
                pstmt.setInt(2, size);
                try (ResultSet rs = pstmt.executeQuery()) {
                    List<Warn> list = new ArrayList<>();
                    while (rs.next()) {
                        Warn warn = new Warn();
                        warn.setId(Integer.parseInt(rs.getString("id")));
                        warn.setSource(rs.getString("source"));
                        warn.setMessages(rs.getString("messages"));
                        warn.setTime(rs.getString("time"));
                        list.add(warn);
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
            String sql = "SELECT COUNT(*) FROM warn";
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
