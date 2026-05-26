// package com.bhavik.utility;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ResourceBundle;

// public class DBUtil {
// 	private static Connection conn;

// 	public DBUtil() {
// 	}

// 	public static Connection provideConnection() {

// 		try {
// 			if (conn == null || conn.isClosed()) {
// 				ResourceBundle rb = ResourceBundle.getBundle("application");
// 				String connectionString = rb.getString("db.connectionString");
// 				String driverName = rb.getString("db.driverName");
// 				String username = rb.getString("db.username");
// 				String password = rb.getString("db.password");
// 				try {
// 					Class.forName(driverName);
// 				} catch (ClassNotFoundException e) {
// 					e.printStackTrace();
// 				}
// 				conn = DriverManager.getConnection(connectionString, username, password);

// 			}
// 		} catch (SQLException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}

// 		return conn;
// 	}

// 	public static void closeConnection(Connection con) {
// 		/*
// 		 * try { if (con != null && !con.isClosed()) {
// 		 * 
// 		 * con.close(); } } catch (SQLException e) { // TODO Auto-generated catch block
// 		 * e.printStackTrace(); }
// 		 */
// 	}

// 	public static void closeConnection(ResultSet rs) {
// 		try {
// 			if (rs != null && !rs.isClosed()) {
// 				try {
// 					rs.close();
// 				} catch (SQLException e) {
// 					// TODO Auto-generated catch block
// 					e.printStackTrace();
// 				}
// 			}
// 		} catch (SQLException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
// 	}

// 	public static void closeConnection(PreparedStatement ps) {
// 		try {
// 			if (ps != null && !ps.isClosed()) {
// 				try {
// 					ps.close();
// 				} catch (SQLException e) {
// 					// TODO Auto-generated catch block
// 					e.printStackTrace();
// 				}
// 			}
// 		} catch (SQLException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
// 	}
// }


package com.bhavik.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
    private static Connection conn;

    public DBUtil() {
    }

    public static Connection provideConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                
                // 1. Try to read from Render's Environment Variables first
                String connectionString = System.getenv("DB_URL");
                String username = System.getenv("DB_USER");
                String password = System.getenv("DB_PASS");
                String driverName = "com.mysql.cj.jdbc.Driver"; // Default modern MySQL driver
                
                // 2. Fallback: If running locally (Environment Variables are null), read from application.properties
                if (connectionString == null || connectionString.trim().isEmpty()) {
                    ResourceBundle rb = ResourceBundle.getBundle("application");
                    connectionString = rb.getString("db.connectionString");
                    driverName = rb.getString("db.driverName");
                    username = rb.getString("db.username");
                    password = rb.getString("db.password");
                }

                try {
                    Class.forName(driverName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
                conn = DriverManager.getConnection(connectionString, username, password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection con) {
        /*
         * try { if (con != null && !con.isClosed()) {
         * * con.close(); } } catch (SQLException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
    }

    public static void closeConnection(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}