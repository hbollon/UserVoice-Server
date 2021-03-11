package entreprisecorp.restservices.controllers;

import com.google.gson.Gson;
import entreprisecorp.App;
import entreprisecorp.restservices.ResponseSuccess;
import entreprisecorp.restservices.models.ApiKey;
import entreprisecorp.restservices.models.features.MatchFeatures;
import entreprisecorp.restservices.models.features.MatchWithApiKey;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ELOController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(
            path = "/returnResultMatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseSuccess returnResultMatch(@RequestBody MatchWithApiKey matchWithApiKey){

        String tableNameFromApiKey = App.adminDbHandler.getTableNameFromApiKey(matchWithApiKey.getApiKey());
        MatchFeatures matchFeatures = App.eloHandler.calculateNewEloMatch(matchWithApiKey.getMatchFeatures());
        boolean featureUpdate1 = App.featuresDbHandler.updateFeature(matchFeatures.getFeature1(), tableNameFromApiKey);
        boolean featureUpdate2 = App.featuresDbHandler.updateFeature(matchFeatures.getFeature2(), tableNameFromApiKey);
        if(featureUpdate1 && featureUpdate2){
            System.err.println("Feature Updates done!");
            Gson gson = new Gson();
            String featureJson = gson.toJson(matchFeatures);
            return new ResponseSuccess(counter.incrementAndGet(), featureJson, true);
        } else {
            System.err.println("Feature Updates failed!");
            return new ResponseSuccess(counter.incrementAndGet(), "", false);
        }

    }

}
