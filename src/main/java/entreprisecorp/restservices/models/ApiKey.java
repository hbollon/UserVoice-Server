package entreprisecorp.restservices.models;

public class ApiKey {

    private String apiKey;

    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ApiKey() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
