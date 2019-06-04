package smartdb;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tiago penha pedroso
 */
public class Connection {
 
    private static JdbcTemplate jdbcTemplateMySql;
    private static JdbcTemplate jdbcTemplateSqlServer;
    private static String mySqlDriverClassName = "com.mysql.cj.jdbc.Driver";
    private static String sqlServerDriverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
    private static boolean usingMySql;
    private static boolean usingSqlServer;
    private static String urlConnection = "";
    private static String username = "";
    private static String password = "";
    private static boolean setOk;    
    
    public static void setVariablesToMySql(String urlConnection, String username, String password){
        usingMySql = true;
        usingSqlServer = false;
        setVariables(urlConnection, username, password);
    }
        
    public static void setVariablesToSqlServer(String urlConnection, String username, String password){
        usingMySql = false;
        usingSqlServer = true;
        setVariables(urlConnection, username, password);
    }
    
    private static void setVariables(String urlConnection, String username, String password){
        Connection.urlConnection = urlConnection != null || !"".equals(urlConnection) ? urlConnection : "";
        Connection.username = username != null || !"".equals(username) ? username : "";
        Connection.password = password != null || !"".equals(password) ? password : "";
        setOk = !"".equals(Connection.urlConnection) && !"".equals(Connection.username) && !"".equals(Connection.password);
    }
        
    public static JdbcTemplate getJdbcTemplate(){
        if (jdbcTemplateMySql == null){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(mySqlDriverClassName);
            dataSource.setUrl(urlConnection);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            jdbcTemplateMySql = new JdbcTemplate(dataSource);            
        }    
        
        if (jdbcTemplateSqlServer == null){
            BasicDataSource dataSource = new BasicDataSource();            
            dataSource.setDriverClassName(sqlServerDriverClassName);
            dataSource.setUrl(urlConnection);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            jdbcTemplateSqlServer = new JdbcTemplate(dataSource);            
        }        
        
        if (urlConnection.contains("myssql")) {System.out.println("mysql");
            return jdbcTemplateMySql;
        }
        else if (urlConnection.contains("sqlserver")) {System.out.println("sqlserver");
            return jdbcTemplateSqlServer;
        }
        else{
            return null;
        }
    }
    
    public static String toStaticString(){
        return
                "Connection::" + (usingMySql ? "MySQL" : usingSqlServer ? "SQL Server" : "none") + 
                "{ driverClassName: " + (usingMySql ? mySqlDriverClassName : usingSqlServer ? sqlServerDriverClassName : "none") +
                "; urlConnection: " + urlConnection +
                "; username: " + username +
                "; password: " + password +
                " }";
    }
    
}
