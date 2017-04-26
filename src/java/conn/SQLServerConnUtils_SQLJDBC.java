/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Khun_Phichz
 */
public class SQLServerConnUtils_SQLJDBC {
    // Connect to SQLServer
    // (Using JDBC Driver: SQLJDBC)    

    public static Connection getSQLServerConnection_SQLJDBC()
            throws ClassNotFoundException, SQLException {

        // Note: Change the connection parameters accordingly.
        String hostName = "KHUN_PHICHZ-PC";
//        String sqlInstanceName = "MSSQLSERVER";
        String database = "testData";
        String userName = "sa";
        String password = "sa";

        return getSQLServerConnection_SQLJDBC(hostName,
                database, userName, password);
    }

    //Connect to SQLServer, using SQLJDBC Library.
    private static Connection getSQLServerConnection_SQLJDBC(String hostName,
            String database, String userName,
            String password) throws ClassNotFoundException, SQLException {

     // Declare the class Driver for SQLServer DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr      
//        String connectionURL = "jdbc:sqlserver://"+hostName+":1433;databaseName="+database;

        Connection conn = DriverManager.getConnection(""+"jdbc:sqlserver://KHUN_PHICHZ-PC:1433;databaseName=testData;user=sa;password=sa");
        return conn;
    }
}
