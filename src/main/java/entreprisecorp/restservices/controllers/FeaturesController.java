package entreprisecorp.restservices.controllers;

import com.google.gson.Gson;
import entreprisecorp.App;
import entreprisecorp.restservices.Response;
import entreprisecorp.restservices.ResponseSuccess;
import entreprisecorp.restservices.models.ApiKey;
import entreprisecorp.restservices.models.User;
import entreprisecorp.restservices.models.features.FeatureAndTableName;
import entreprisecorp.restservices.models.features.FeatureWithApiKey;
import entreprisecorp.restservices.models.features.ListFeatures;
import entreprisecorp.restservices.models.features.MatchFeatures;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FeaturesController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(
            path = "/getMatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MatchFeatures getMatchFeature(@RequestBody ApiKey apikey){

        String tableNameFromApiKey = App.adminDbHandler.getTableNameFromApiKey(apikey.getApiKey());
        return App.featuresDbHandler.getMatchFeature(tableNameFromApiKey);

    }

    @PostMapping(
            path = "/addFeature",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseSuccess addFeature(@RequestBody FeatureWithApiKey featureWithApiKey){

        String tableNameFromApiKey = App.adminDbHandler.getTableNameFromApiKey(featureWithApiKey.getApiKey());
        boolean success = App.featuresDbHandler.CreateFeature(featureWithApiKey.getFeature(),tableNameFromApiKey);
        if(success){
            System.err.println("Feature Creation done!");
            Gson gson = new Gson();
            String featureJson = gson.toJson(featureWithApiKey.getFeature());
            return new ResponseSuccess(counter.incrementAndGet(), featureJson, success);
        } else {
            System.err.println("Feature Creation failed!");
            return new ResponseSuccess(counter.incrementAndGet(), "", success);
        }

    }

    @PostMapping(
            path = "/getFeatureByAuthor",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ListFeatures getFeaturesAuthor(@RequestBody FeatureAndTableName featureAndTableName){

        ListFeatures listFeatures = App.featuresDbHandler.getFeatureByAuthor(featureAndTableName.getEmail(),featureAndTableName.getTableName());
        return listFeatures;
    }

    @PostMapping(
            path = "/getFeatureByTable",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ListFeatures getFeaturesTable(@RequestBody FeatureAndTableName featureAndTableName){

        ListFeatures listFeatures = App.featuresDbHandler.getFeatureByTable(featureAndTableName.getTableName());
        return listFeatures;
    }


}
