package entreprisecorp.restservices.models.features;

public class MatchWithApiKey {
    private String apiKey;
    private MatchFeatures matchFeatures;

    public MatchWithApiKey() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public MatchFeatures getMatchFeatures() {
        return matchFeatures;
    }

    public void setMatchFeatures(MatchFeatures matchFeatures) {
        this.matchFeatures = matchFeatures;
    }
}
