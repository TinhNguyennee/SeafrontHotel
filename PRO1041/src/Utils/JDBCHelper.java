/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class JDBCHelper {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static String userName = "NHOM4";
    private static String userPassword = "123456";
    private static String databaseName = "SeafrontHotel";
    private static String connectionURL = "jdbc:sqlserver://DoanThanhBut\\DOANTHANHBUT:1433; databaseName ="
            + databaseName
            + ";user=" + userName
            + ";password=" + userPassword
            + ";encrypt=true;trustServerCertificate=true";
    
//    private static String userName = "sa";
//    private static String userPassword = "12345";
//    private static String databaseName = "SeafrontHotel";
//    private static String connectionURL = "jdbc:sqlserver://localhost:1433; databaseName ="
//            + databaseName
//            + ";user=" + userName
//            + ";password=" + userPassword
//            + ";encrypt=true;trustServerCertificate=true";

    static {
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionURL);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);

        }
        return pstmt;
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet query(String sql, Object... args) {
        PreparedStatement stmt;
        try {
            stmt = getStmt(sql, args);
            return stmt.executeQuery();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
// Dua 1 cau lenh SQL mà cau lenh do trả ve 1 gia tri duy nhat thì khong can ResultSet

    public static Object value(String sql, Object... args) {
        try {
            // goi method query(sql,args) de thuc hien cau lênh truy van va 
            // nhan doi tuong ResultSet chua ket qua truy van
            ResultSet rs = query(sql, args);
            // check co dong du liệu dau tien không nếu có thì lây gia trị dau tien 
            //cot dau tien trong dong du lieu do
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
