package entreprisecorp.database;

import java.sql.Connection;
import java.sql.Statement;

public abstract class DbHandler {

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    protected String DB_URL;
    protected String DB_USERNAME;
    protected String DB_PASSWORD;
    protected String DB_PORT;
    protected String DB_NAME;

    protected Connection conn;
    protected Statement statement;


    public DbHandler() {
        this.DB_URL = Database.singleton.DB_URL;
        this.DB_USERNAME = Database.singleton.DB_USERNAME;
        this.DB_PASSWORD = Database.singleton.DB_PASSWORD;
        this.DB_PORT = Database.singleton.DB_PORT;
        this.DB_NAME = Database.singleton.DB_NAME;
        this.conn = Database.singleton.conn;
        this.statement = Database.singleton.statement;
    }
}
