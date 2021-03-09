package entreprisecorp.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static Database singleton;

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    public String DB_URL;
    public String DB_USERNAME;
    public String DB_PASSWORD;
    public String DB_PORT;
    public String DB_NAME;

    private final String USER_TABLE_NAME = "user";
    private final String USER_DB_USERNAME = "username";
    private final String USER_DB_PASSWORD = "password";
    private final String USER_DB_PASSWORD_SALT = "salt";
    private final String USER_DB_EMAIL = "email";
    private final String USER_DB_SQL = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(id INT NOT NULL AUTO_INCREMENT, "
            + USER_DB_USERNAME + " VARCHAR(255), " + USER_DB_PASSWORD + " VARCHAR(255), " + USER_DB_PASSWORD_SALT + " VARCHAR(255),"
            + USER_DB_EMAIL + " VARCHAR(255), PRIMARY KEY ( id ))";

    private final String[] TABLES = { USER_TABLE_NAME };
    private final String[] TABLES_SQL = { USER_DB_SQL };

    public Connection conn;
    public Statement statement;

    public Database() {

        //Create singleton
        if(singleton == null){
            singleton = this;
        }

        // Load all .env file variables
        Dotenv dotenv = Dotenv.load();
        this.DB_URL = dotenv.get("DB_HOST");
        this.DB_USERNAME = dotenv.get("DB_USERNAME");
        this.DB_PASSWORD = dotenv.get("DB_PASSWORD");
        this.DB_PORT = dotenv.get("DB_PORT");
        this.DB_NAME = dotenv.get("DB_NAME");

        // Driver loading & Connection to the database
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(
                    String.format("jdbc:mariadb://%s:%s/%s", DB_URL, DB_PORT, DB_NAME),
                    DB_USERNAME,
                    DB_PASSWORD
            );
            initDb();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize MariaDB database with needed tables and create statement
     * @throws SQLException
     */
    private void initDb() throws SQLException {
        statement = conn.createStatement();
        for (String sql : TABLES_SQL) {
            statement.executeUpdate(sql);
        }
    }
}
