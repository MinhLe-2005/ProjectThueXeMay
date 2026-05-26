/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartride.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {

    // =============================================
    // KẾT NỐI SUPABASE (PostgreSQL)
    // =============================================
    // Transaction Pooler - IPv4 compatible
    private static final String DB_URL = "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:6543/postgres?sslmode=require";
    private static final String DB_USER = "postgres.zfvgigfjmbtgwgirdify";
    private static final String DB_PASS = "Bimdiendie1@";

    // Method to obtain a raw standard database connection
    private static Connection getRawConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Connection makeConnection() {
        try {
            Connection raw = getRawConnection();

            // Return a dynamic proxy connection that transparently reconnects if
            // stale/closed/null
            return (Connection) Proxy.newProxyInstance(
                    DBUtil.class.getClassLoader(),
                    new Class<?>[] { Connection.class },
                    new ConnectionProxyHandler(raw));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class ConnectionProxyHandler implements InvocationHandler {
        private Connection realConn;

        public ConnectionProxyHandler(Connection realConn) {
            this.realConn = realConn;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("close".equals(method.getName())) {
                if (realConn != null && !realConn.isClosed()) {
                    realConn.close();
                }
                return null;
            }

            // Auto-reconnect if the connection was closed or has become stale
            try {
                if (realConn == null || realConn.isClosed() || !realConn.isValid(2)) {
                    System.out.println("⚠️ DB Connection stale or closed. Reconnecting transparently...");
                    Connection newConn = getRawConnection();
                    if (newConn != null) {
                        realConn = newConn;
                    }
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error checking connection validity: " + e.getMessage() + ". Reconnecting...");
                Connection newConn = getRawConnection();
                if (newConn != null) {
                    realConn = newConn;
                }
            }

            if (realConn == null) {
                throw new SQLException("Database connection is currently unavailable.");
            }

            try {
                return method.invoke(realConn, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Testing Supabase connection...");
        Connection conn = makeConnection();
        if (conn != null) {
            DatabaseMetaData dm = conn.getMetaData();
            System.out.println("✅ Kết nối thành công!");
            System.out.println("Driver: " + dm.getDriverName());
            System.out.println("Version: " + dm.getDriverVersion());
            closeConnection(conn);
        } else {
            System.out.println("❌ Kết nối thất bại! Kiểm tra lại password.");
        }
    }
}
