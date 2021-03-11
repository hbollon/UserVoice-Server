package entreprisecorp.database;

import entreprisecorp.restservices.models.features.Feature;
import entreprisecorp.restservices.models.features.MatchFeatures;

public class ELOHandler {

    private final double KCoef = 20.0;


    public MatchFeatures calculateNewEloMatch(MatchFeatures matchFeatures){


        double probFeature1 = calculateProb(matchFeatures.getFeature1(), matchFeatures.getFeature2());
        double probFeature2 = calculateProb(matchFeatures.getFeature2(), matchFeatures.getFeature2());

        matchFeatures.setFeature1(calculateElo(matchFeatures.getFeature1(), probFeature1));
        matchFeatures.setFeature2(calculateElo(matchFeatures.getFeature2(), probFeature2));



        return matchFeatures;
    }


    public double calculateProb(Feature feature1, Feature feature2){

        double pow = (feature2.getELO() - feature1.getELO()) / 400;
        double prob = 1/(1 + (Math.pow(10,pow)));

        return prob;

    }


    public Feature calculateElo(Feature feature, double probFeature){
        int won = feature.isWon() ? 1 : 0;
        double eloCalcul = feature.getELO() + KCoef*(won - probFeature);
        feature.setELO((int) eloCalcul);

        return feature;
    }
}
