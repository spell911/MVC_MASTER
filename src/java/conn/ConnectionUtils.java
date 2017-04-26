package conn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Khun_Phichz
 */
public class ConnectionUtils {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        // Here I using Oracle Database.
        // return OracleConnsqUtils.getOracleConnection(); F
        // return MySQLConnUtils.getMySQLConnection();
//         return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
        return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }

}

