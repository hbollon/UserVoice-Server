package entreprisecorp.restservices.controllers;

import com.google.gson.Gson;
import entreprisecorp.App;
import entreprisecorp.restservices.Response;
import entreprisecorp.restservices.ResponseSuccess;
import entreprisecorp.restservices.models.Admin;
import entreprisecorp.restservices.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AdminController {
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(
            path = "/admin/login",
            consumes = "application/json",
            produces = "application/json"
    )
    public Response login(@RequestBody Admin admin)
    {
        Admin connectedAdmin = App.adminDbHandler.connectAdmin(admin.getEmail(), admin.getPassword());
        if(connectedAdmin != null){
            Gson gson = new Gson();
            String adminJson = gson.toJson(connectedAdmin);
            return new Response(counter.incrementAndGet(), adminJson);
        } else {
            return new Response(counter.incrementAndGet(), "");
        }
    }

    @PostMapping(
            path = "/admin/register",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseSuccess register(@RequestBody Admin admin)
    {
        Admin createdAdmin = new Admin(admin.getCompany(), admin.getPassword(), admin.getEmail());
        boolean success = App.adminDbHandler.insertAdmin(createdAdmin);
        if(success){
            System.err.println("Admin registration done!");
            createdAdmin.setPassword(null);
            Gson gson = new Gson();
            String adminJson = gson.toJson(createdAdmin);
            return new ResponseSuccess(counter.incrementAndGet(), adminJson, success);
        } else {
            System.err.println("Admin registration failed!");
            return new ResponseSuccess(counter.incrementAndGet(), "", success);
        }
    }

}
