package entreprisecorp.restservices.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entreprisecorp.App;
import entreprisecorp.restservices.ResponseSuccess;
import entreprisecorp.restservices.models.User;

@RestController
public class RegisterController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(
        path = "/register", 
        consumes = "application/json", 
        produces = "application/json"
    )
    public ResponseSuccess register(
        @RequestBody User user) 
    {
        boolean success = App.userDbHandler.insertUser(user);
        if(success){
            System.err.println("User registration done!");
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            return new ResponseSuccess(counter.incrementAndGet(), userJson, success);
        } else {
            System.err.println("User registration failed!");
            return new ResponseSuccess(counter.incrementAndGet(), "", success);
        }
    }
}
