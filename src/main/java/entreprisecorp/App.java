package entreprisecorp;

import entreprisecorp.database.AdminDbHandler;
import entreprisecorp.database.Database;
import entreprisecorp.database.FeaturesDbHandler;
import entreprisecorp.restservices.models.features.Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entreprisecorp.database.UserDbHandler;

import java.sql.SQLException;

@SpringBootApplication
public class App {

	public static UserDbHandler userDbHandler;
	public static FeaturesDbHandler featuresDbHandler;
	public static AdminDbHandler adminDbHandler;

	public static void main(String[] args) {

		//init singleton database
		Database database = new Database();

		userDbHandler = new UserDbHandler();
		featuresDbHandler = new FeaturesDbHandler();
		adminDbHandler = new AdminDbHandler();



		//featuresDbHandler.CreateFeature(new Feature("Mettre un light mode", 0, 0, "tom.kubasik@gmail.com"),"testfeatures");
		//featuresDbHandler.CreateFeature(new Feature("Mettre un dark mode", 0, 0, "tom.kubasik@gmail.com"),"testfeatures");


/*		if(userDbHandler.insertUser(new User("tkubasik", "loloLeBouf", "tom.kubasik@gmail.com"))) {
			System.out.println("User hbollon successfully registered!");
		}

		User connectHbollon = userDbHandler.connectUser("tom.kubasik@gmail.com", "loloLeBouf");
		if(connectHbollon.getUsername() == "tkubasik") {
			System.out.println("User hbollon successfully logged!");
		}*/

		SpringApplication.run(App.class, args);
	}
}