package entreprisecorp;

import entreprisecorp.database.Database;
import entreprisecorp.database.FeaturesDbHandler;
import entreprisecorp.restservices.models.Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entreprisecorp.database.UserDbHandler;
import entreprisecorp.restservices.models.User;

import java.sql.SQLException;

@SpringBootApplication
public class App {

	public static UserDbHandler userDbHandler;
	public static FeaturesDbHandler featuresDbHandler;

	public static void main(String[] args) {

		//init singleton database
		Database database = new Database();

		userDbHandler = new UserDbHandler();
		featuresDbHandler = new FeaturesDbHandler();

		try {
			featuresDbHandler.CreateTable("testTable");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		//featuresDbHandler.CreateFeature(new Feature("Mettre un light mode", 0, 0, "tom.kubasik@gmail.com"),"testTable");
		System.out.print(featuresDbHandler.getFeatureByAuthor("tom.kubasik@gmail.com","testTable"));


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