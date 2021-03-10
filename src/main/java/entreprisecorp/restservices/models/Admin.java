package entreprisecorp.restservices.models;

import java.util.UUID;

public class Admin {

    private int dbId;
    private String company;
    private String password;
    private String email;
    private String apiKey;
    private String tableFeatures;

    public Admin(int dbId, String company, String password, String email, String apiKey, String tableFeatures) {
        this.dbId = dbId;
        this.company = company;
        this.password = password;
        this.email = email;
        this.apiKey = apiKey;
        this.tableFeatures = tableFeatures;
    }

    public Admin(String company, String password, String email) {
        this.company = company;
        this.password = password;
        this.email = email;
        this.apiKey = generateAPIKEY();
        this.tableFeatures = this.company + "features";
    }

    public Admin() {
    }



    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTableFeatures() {
        return tableFeatures;
    }

    public void setTableFeatures(String tableFeatures) {
        this.tableFeatures = tableFeatures;
    }


    public String generateAPIKEY(){
        UUID uuid = UUID.randomUUID();
        String apikey = uuid.toString();
        return apikey;
    }
}
