package smartdb;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tiago penha pedroso
 * @param <T>
 */
public class SmartDbConnection {

    private static BasicDataSource dataSourceMySql = new BasicDataSource();
    private static BasicDataSource dataSourceSqlServer = new BasicDataSource();
    private static JdbcTemplate jdbcTemplateMySql;
    private static JdbcTemplate jdbcTemplateSqlServer;
    private static String mySqlDriverClassName = "com.mysql.cj.jdbc.Driver";
    private static String sqlServerDriverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static DbType currentDbType = DbType.None;
    private static DbType lastDbType = DbType.None;
    private static String mySqlUrlConnection;
    private static boolean addTimeZone;
    private static boolean lastAddTimeZone;
    private static String timeZoneUtc = "?useTimezone=true&serverTimezone=UTC";

    public enum DbType {
        None,
        MySQL,
        SQLServer
    }

    public static void setCurrentDbType(DbType currentDbType) {
        SmartDbConnection.currentDbType = currentDbType;
    }

    public static DbType getCurrentDbType() {
        return currentDbType;
    }

    public static void setVariablesToMySql(String urlConnection, String username, String password) {
        dataSourceMySql.setDriverClassName(mySqlDriverClassName);
        mySqlUrlConnection = urlConnection;

        if (addTimeZone) {
            dataSourceMySql.setUrl(urlConnection + timeZoneUtc);
        } else {
            dataSourceMySql.setUrl(urlConnection);
        }

        dataSourceMySql.setUsername(username);
        dataSourceMySql.setPassword(password);
        jdbcTemplateMySql = new JdbcTemplate(dataSourceMySql);

        currentDbType = DbType.MySQL;
    }

    public static void setVariablesToSqlServer(String urlConnection, String username, String password) {
        dataSourceSqlServer.setDriverClassName(sqlServerDriverClassName);
        dataSourceSqlServer.setUrl(urlConnection);
        dataSourceSqlServer.setUsername(username);
        dataSourceSqlServer.setPassword(password);
        jdbcTemplateSqlServer = new JdbcTemplate(dataSourceSqlServer);

        currentDbType = DbType.SQLServer;
    }

    public static void addTimeZoneUtcToUrlConnection() {
        addTimeZone = true;
    }

    public static void removeTimeZoneUtcToUrlConnection() {
        addTimeZone = false;
    }

    public static JdbcTemplate getJdbcTemplate() {
        if (addTimeZone != lastAddTimeZone && currentDbType == DbType.MySQL) {
            lastAddTimeZone = addTimeZone;

            if (addTimeZone) {
                dataSourceMySql.setUrl(mySqlUrlConnection + timeZoneUtc);
            } else {
                dataSourceMySql.setUrl(mySqlUrlConnection);
            }

            jdbcTemplateMySql = new JdbcTemplate(dataSourceMySql);
        }

        if (jdbcTemplateMySql != null && currentDbType == DbType.MySQL) {
            return jdbcTemplateMySql;
        } else if (jdbcTemplateSqlServer != null && currentDbType == DbType.SQLServer) {
            return jdbcTemplateSqlServer;
        } else {
            return null;
        }
    }

    public static String toStringStatic() {
        if (currentDbType == DbType.MySQL) {
            return "Connection::" + currentDbType
                    + "{ driverClassName: " + dataSourceMySql.getDriverClassName()
                    + "; urlConnection: " + dataSourceMySql.getUrl()
                    + "; username: " + dataSourceMySql.getUsername()
                    + "; password: " + dataSourceMySql.getPassword()
                    + " }";
        }

        if (currentDbType == DbType.SQLServer) {
            return "Connection::" + currentDbType
                    + "{ driverClassName: " + dataSourceSqlServer.getDriverClassName()
                    + "; urlConnection: " + dataSourceSqlServer.getUrl()
                    + "; username: " + dataSourceSqlServer.getUsername()
                    + "; password: " + dataSourceSqlServer.getPassword()
                    + " }";
        }

        return "";
    }

    public static List<?> executeQueryToReturnList(Class<?> mappedClassToResults, String query) {
        if (currentDbType == DbType.MySQL) {
            try {
                List<?> list = jdbcTemplateMySql.query(
                        query,
                        new BeanPropertyRowMapper(mappedClassToResults)
                );
                return list;
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                List<?> list = jdbcTemplateSqlServer.query(
                        query,
                        new BeanPropertyRowMapper(mappedClassToResults)
                );
                return list;
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }

        return null;
    }
    
    public static List<?> executeQueryToReturnList(Class<?> mappedClassToResults, String query, Object... substitutionsOfSpecialStrings) {
        if (currentDbType == DbType.MySQL) {
            try {
                List<?> list = jdbcTemplateMySql.query(
                        query,
                        new BeanPropertyRowMapper(mappedClassToResults),
                        substitutionsOfSpecialStrings
                );
                return list;
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                List<?> list = jdbcTemplateSqlServer.query(
                        query,
                        new BeanPropertyRowMapper(mappedClassToResults),
                        substitutionsOfSpecialStrings
                );
                return list;
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }

        return null;
    }

    public static Object executeQueryToReturnObject(String query) {
        if (currentDbType == DbType.MySQL) {
            try {
                return jdbcTemplateMySql.queryForObject(
                        query,
                        Object.class
                );
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                return jdbcTemplateSqlServer.queryForObject(
                        query,
                        Object.class
                );
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }

        return null;
    }
    
    public static Object executeQueryToReturnObject(String query, Object... substitutionsOfSpecialStrings) {
        if (currentDbType == DbType.MySQL) {
            try {
                return jdbcTemplateMySql.queryForObject(
                        query,
                        Object.class,
                        substitutionsOfSpecialStrings
                );
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                return jdbcTemplateSqlServer.queryForObject(
                        query,
                        Object.class,
                        substitutionsOfSpecialStrings
                );
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }

        return null;
    }

    public static void executeQuery(String query) {
        if (currentDbType == DbType.MySQL) {
            try {
                jdbcTemplateMySql.execute(query);
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                jdbcTemplateSqlServer.execute(query);
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }
    }
    
    public static void executeQuery(String query, Object... substitutionsOfSpecialStrings) {
        if(query.substring(0, 6).toUpperCase().contains("SELECT")){
            if (currentDbType == DbType.MySQL) {
                System.out.println("<<MYSQL ERROR!>> The \"SELECT\" statement is not allowed in this method! Use the statements \"INSERT\", \"UPDATE\" or \"DELETE\" instead.");
            }
            if (currentDbType == DbType.SQLServer) {
                System.out.println("<<SQLSERVER ERROR!>> The \"SELECT\" statement is not allowed in this method! Use the statements \"INSERT\", \"UPDATE\" or \"DELETE\" instead.");
            }
            return;
        }
        
        if (currentDbType == DbType.MySQL) {
            try {
                jdbcTemplateMySql.update(query, substitutionsOfSpecialStrings);
            } catch (Exception e) {
                System.out.println("<<MYSQL ERROR!>> " + e.getMessage());
            }
        }

        if (currentDbType == DbType.SQLServer) {
            try {
                jdbcTemplateSqlServer.update(query, substitutionsOfSpecialStrings);
            } catch (Exception e) {
                System.out.println("<<SQLSERVER ERROR!>> " + e.getMessage());
            }
        }
    }

}