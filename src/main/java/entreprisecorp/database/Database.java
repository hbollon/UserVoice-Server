package entreprisecorp.database;

import java.sql.*;

import entreprisecorp.restservices.models.User;
import entreprisecorp.utils.HashUtils;
import io.github.cdimascio.dotenv.Dotenv;

public class Database {
    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private String DB_PORT;
    private String DB_NAME;

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

    private Connection conn;
    private Statement statement;

    public Database() {
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

    /**
     * User interactions
     */

    /**
     * Register new user into database 
     * Hash his password
     * @param u User
     * @return
     */
    public boolean insertUser(User u) {
        try {
            // Check if the user already have an account registered with his email
            String check_existing_request = "SELECT * FROM " + USER_TABLE_NAME + " WHERE `" + USER_DB_EMAIL + "`='" + u.getEmail() + "'";
            try(Statement st = conn.createStatement(); 
                ResultSet rs = st.executeQuery(check_existing_request)){
                if(rs.next()) {
                    System.out.println("Error: User already exists !");
                    return false;
                }
            }

            String sql = "INSERT INTO " + USER_TABLE_NAME + "(`" + USER_DB_USERNAME + "`, `" + USER_DB_PASSWORD + "`, `"
            + USER_DB_PASSWORD_SALT + "`, `" + USER_DB_EMAIL + "`) VALUES (?, ?, ?, ?)";

            // Hash user password by generating salt 
            String salt = HashUtils.getSalt(30);
            String hashedPassword = HashUtils.generateSecurePassword(u.getPassword(), salt);

            // Prepare SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(2, hashedPassword);
            statement.setString(3, salt);
            statement.setString(4, u.getEmail());

            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("A new user was inserted successfully!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update user entry into database
     * Allow modification of: username
     * @param u
     * @return
     */
    public boolean updateUser(User u) {
        try {
            String sql = "UPDATE " + USER_TABLE_NAME + " SET " + USER_DB_USERNAME + "=? WHERE " + USER_DB_EMAIL + "=?";

            // Prepare SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, u.getUsername());
            statement.setString(3, u.getEmail()); // where clause

            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("The user was updated successfully!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete user from db 
     * @param email User's email
     * @return
     */
    public boolean deleteUser(String userEmail) {
        String sql = "DELETE FROM user WHERE `" + USER_DB_EMAIL + "`='" + userEmail + "'";
        try {
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                System.out.println("The user was deleted successfully!");
                return true;
            } else {
                System.out.println("User delete failed!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get user's db id from email
     * @param login
     * @return
     */
    public int getUserId(String email) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE `" + USER_DB_EMAIL + "`='" + email + "'";
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            System.out.println("User not find!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Retrieve all user informations and instanciate User object from db with his email
     * @param email User's email
     * @return User object
     */
    public User getUser(String email) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE `" + USER_DB_EMAIL + "`='" + email + "'";
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(5));
            }
            System.out.println("User not recognized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User connectUser(String email, String password) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE `" + USER_DB_EMAIL + "`='" + email + "'";
        try (Statement st = conn.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                // Check password validity
                if (HashUtils.verifyUserPassword(password, rs.getString(3), rs.getString(4))) {
                    System.out.println("User succesfully logged in!");
                    return new User(rs.getInt(1), rs.getString(2), rs.getString(5));
                }
            }
            System.out.println("User not recognized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
