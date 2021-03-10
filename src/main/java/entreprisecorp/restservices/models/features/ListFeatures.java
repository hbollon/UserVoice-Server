package entreprisecorp.restservices.models.features;

import java.util.ArrayList;

public class ListFeatures {
    private ArrayList<Feature> features;

    public ListFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public ListFeatures() {
        features = new ArrayList<Feature>();
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public void addFeature(Feature feature){
        features.add(feature);
    }

    @Override
    public String toString() {
        return "ListFeatures{" +
                "features=" + features +
                '}';
    }
}
