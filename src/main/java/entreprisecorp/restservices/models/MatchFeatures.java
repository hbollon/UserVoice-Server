package entreprisecorp.restservices.models;

public class MatchFeatures {

    private Feature feature1;
    private Feature feature2;

    public MatchFeatures(Feature feature1, Feature feature2) {
        this.feature1 = feature1;
        this.feature2 = feature2;
    }

    public MatchFeatures() {
    }

    public Feature getFeature1() {
        return feature1;
    }

    public void setFeature1(Feature feature1) {
        this.feature1 = feature1;
    }

    public Feature getFeature2() {
        return feature2;
    }

    public void setFeature2(Feature feature2) {
        this.feature2 = feature2;
    }


    @Override
    public String toString() {
        return "MatchFeatures{" +
                "feature1=" + feature1 +
                ", feature2=" + feature2 +
                '}';
    }

}
