package entreprisecorp.database;

import entreprisecorp.restservices.models.User;
import entreprisecorp.restservices.models.features.Feature;
import entreprisecorp.restservices.models.features.ListFeatures;
import entreprisecorp.restservices.models.features.MatchFeatures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeaturesDbHandler extends DbHandler {

    private final String FEATURE_DB_TEXTFEATURE = "textfeature";
    private final String FEATURE_DB_ELO = "ELO";
    private final String FEATURE_DB_AUTHOR = "authorEmail";



    public FeaturesDbHandler() {
        super();
    }


    /**
     * Create table for the site
     * @param nameTable
     * @throws SQLException
     */
    public void CreateTable(String nameTable) throws SQLException {

        String FEATURE_DB_SQL = "CREATE TABLE IF NOT EXISTS " + nameTable + "(id INT NOT NULL AUTO_INCREMENT, "
                + FEATURE_DB_TEXTFEATURE + " VARCHAR(255), " + FEATURE_DB_ELO + " INT,"
                + FEATURE_DB_AUTHOR + " VARCHAR(255),"
                +" PRIMARY KEY ( id ))";

        statement = conn.createStatement();
        statement.executeUpdate(FEATURE_DB_SQL);
    }


    /**
     * Create a feature in the table of the site
     * @param feature
     * @param nameTable
     * @return
     */
    public boolean CreateFeature(Feature feature, String nameTable){
        try {
            String sql = "INSERT INTO " + nameTable + " (`" + FEATURE_DB_TEXTFEATURE + "`, `" + FEATURE_DB_ELO + "`, `"
                    + FEATURE_DB_AUTHOR + "`) VALUES (?, ?, ?)";


            // Prepare SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, feature.getTextFeature());
            statement.setInt(2, feature.getELO());
            statement.setString(3, feature.getAuthorEmail());

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
     * Select 2 random features in the table for a Match
     * @param nameTable
     * @return
     */
    public MatchFeatures getMatchFeature(String nameTable) {
        String sql = "SELECT * FROM " + nameTable + " ORDER BY RAND() LIMIT 2";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            MatchFeatures matchFeatures = new MatchFeatures();
            if (rs.next()) {
                matchFeatures.setFeature1(new Feature(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4)));
            }
            if (rs.next()) {
                matchFeatures.setFeature2(new Feature(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            return matchFeatures;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get List of features from the same author
     * @param emailAuthor
     * @param nameTable
     * @return
     */
    public ListFeatures getFeatureByAuthor(String emailAuthor, String nameTable) {
        String sql = "SELECT * FROM " + nameTable + " WHERE `" + FEATURE_DB_AUTHOR + "`='" + emailAuthor + "'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            ListFeatures listFeatures = new ListFeatures();
            while(rs.next()) {
                listFeatures.addFeature(new Feature(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4)));
            }
            return listFeatures;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get all the feature of a table ordered by elo
     * @param nameTable
     * @return
     */
    public ListFeatures getFeatureByTable(String nameTable) {
        String sql = "SELECT * FROM " + nameTable + " ORDER BY " + FEATURE_DB_ELO;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            ListFeatures listFeatures = new ListFeatures();
            while(rs.next()) {
                listFeatures.addFeature(new Feature(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4)));
            }
            return listFeatures;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateFeature(Feature feature, String nameTable) {
        try {
            String sql = "UPDATE " + nameTable + " SET " + FEATURE_DB_ELO + "=? WHERE " + FEATURE_DB_TEXTFEATURE + "=?";

            // Prepare SQL query
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, feature.getELO());
            statement.setString(2, feature.getTextFeature()); // where clause

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




}
