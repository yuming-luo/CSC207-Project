package data_access;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class SupabaseDataBase {
    private static final HikariDataSource dataSource;

    // static block that will set up a connection pool to database
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:5432/postgres");
        config.setUsername("postgres.sjucaeyniamabwhfequt");
        config.setPassword(System.getenv("password"));
        config.setMaximumPoolSize(5);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}
