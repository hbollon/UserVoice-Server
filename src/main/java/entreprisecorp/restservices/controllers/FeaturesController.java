package entreprisecorp.restservices.controllers;

import entreprisecorp.App;
import entreprisecorp.restservices.models.ApiKey;
import entreprisecorp.restservices.models.features.MatchFeatures;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.json.JsonObject;

@RestController
public class FeaturesController {
    @PostMapping(
            path = "/getMatch",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MatchFeatures getMatchFeature(@RequestBody ApiKey apikey){

        String tableNameFromApiKey = App.adminDbHandler.getTableNameFromApiKey(apikey.getApiKey());
        return App.featuresDbHandler.getMatchFeature(tableNameFromApiKey);

    }
}
