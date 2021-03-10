package entreprisecorp.database;

import entreprisecorp.App;
import entreprisecorp.restservices.models.Admin;
import entreprisecorp.restservices.models.User;
import entreprisecorp.utils.HashUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDbHandler extends DbHandler{

    private final String ADMIN_TABLE_NAME = "admin";
    private final String ADMIN_DB_USERNAME = "company";
    private final String ADMIN_DB_PASSWORD = "password";
    private final String ADMIN_DB_PASSWORD_SALT = "salt";
    private final String ADMIN_DB_EMAIL = "email";
    private final String ADMIN_DB_APIKEY = "apikey";
    private final String ADMIN_DB_TABLE_NAME_FEATURES = "tablefeatures";


    /**
     * Create admin in database + call createTable() for features
     * @param admin
     * @return
     */
    public boolean insertAdmin(Admin admin) {
        try {
            // Check if the user already have an account registered with his email
            String check_existing_request = "SELECT * FROM " + ADMIN_TABLE_NAME + " WHERE `" + ADMIN_DB_EMAIL + "`='" + admin.getEmail() + "'";
            try(Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(check_existing_request)){
                if(rs.next()) {
                    System.out.println("Error: User already exists !");
                    return false;
                }
            }

            String sql = "INSERT INTO " + ADMIN_TABLE_NAME + "(`" + ADMIN_DB_USERNAME + "`, `" + ADMIN_DB_PASSWORD + "`, `"
                    + ADMIN_DB_PASSWORD_SALT + "`, `" + ADMIN_DB_EMAIL + "`, `" + ADMIN_DB_APIKEY + "`, `" + ADMIN_DB_TABLE_NAME_FEATURES + "`) VALUES (?, ?, ?, ?, ?, ?)";

            // Hash user password by generating salt
            String salt = HashUtils.getSalt(30);
            String hashedPassword = HashUtils.generateSecurePassword(admin.getPassword(), salt);

            // Prepare SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, admin.getCompany());
            statement.setString(2, hashedPassword);
            statement.setString(3, salt);
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getApiKey());
            statement.setString(6, admin.getTableFeatures());

            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("A new admin was inserted successfully!");

                //Create features Table
                App.featuresDbHandler.CreateTable(admin.getTableFeatures());
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
     * Get admin
     * @param email
     * @return
     */
    public Admin getAdmin(String email) {
        String sql = "SELECT * FROM " + ADMIN_TABLE_NAME + " WHERE `" + ADMIN_DB_EMAIL + "`='" + email + "'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }
            System.out.println("Admin not recognized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return admin info if password matches
     * @param email
     * @param password
     * @return
     */
    public Admin connectAdmin(String email, String password) {
        String sql = "SELECT * FROM " + ADMIN_TABLE_NAME + " WHERE `" + ADMIN_DB_EMAIL + "`='" + email + "'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                // Check password validity
                if (HashUtils.verifyUserPassword(password, rs.getString(3), rs.getString(4))) {
                    System.out.println("Admin successfully logged in!");
                    return new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                }
            }
            System.out.println("Admin not recognized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getTableNameFromApiKey(String apiKey){
        String sql = "SELECT " + ADMIN_DB_TABLE_NAME_FEATURES + " FROM " + ADMIN_TABLE_NAME + " WHERE `" + ADMIN_DB_APIKEY + "`='" + apiKey + "'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(1);
            }
            System.out.println("Admin not recognized!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "la clef d'api n'est pas bonne";
    }



}
