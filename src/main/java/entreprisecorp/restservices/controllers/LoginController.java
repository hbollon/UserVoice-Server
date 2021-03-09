package entreprisecorp.restservices.controllers;

import com.google.gson.Gson;

import java.util.concurrent.atomic.AtomicLong;

import javax.json.JsonObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entreprisecorp.App;
import entreprisecorp.restservices.Response;
import entreprisecorp.restservices.models.User;

@RestController
public class LoginController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(
        path = "/login", 
        consumes = "application/json", 
        produces = "application/json"
    )
    public Response login(@RequestBody JsonObject user)
    {
        User connectedUser = App.userDbHandler.connectUser(user.getString("email"), user.getString("password"));
        if(connectedUser != null){
            Gson gson = new Gson();
            String userJson = gson.toJson(connectedUser);
            return new Response(counter.incrementAndGet(), userJson);
        } else {
            return new Response(counter.incrementAndGet(), "");
        }
    }
}
