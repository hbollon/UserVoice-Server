package entreprisecorp.restservices.models.features;

public class FeatureWithApiKey {

    private String apiKey;
    private Feature feature;

    public FeatureWithApiKey(String apiKey, Feature feature) {
        this.apiKey = apiKey;
        this.feature = feature;
    }

    public FeatureWithApiKey() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
