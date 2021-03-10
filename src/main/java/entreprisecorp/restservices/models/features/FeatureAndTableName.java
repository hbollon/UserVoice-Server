package entreprisecorp.restservices.models.features;

public class FeatureAndTableName {
    private String email;
    private String tableName;

    public FeatureAndTableName(String email, String tableName) {
        this.email = email;
        this.tableName = tableName;
    }

    public FeatureAndTableName() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
