package entreprisecorp;

import entreprisecorp.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entreprisecorp.database.UserDbHandler;
import entreprisecorp.restservices.models.User;

@SpringBootApplication
public class App {

	public static UserDbHandler userDbHandler;
	public static void main(String[] args) {

		//init singleton database
		Database database = new Database();

		userDbHandler = new UserDbHandler();

		if(userDbHandler.insertUser(new User("tkubasik", "loloLeBouf", "tom.kubasik@gmail.com"))) {
			System.out.println("User hbollon successfully registered!");
		}

		User connectHbollon = userDbHandler.connectUser("tom.kubasik@gmail.com", "loloLeBouf");
		if(connectHbollon.getUsername() == "tkubasik") {
			System.out.println("User hbollon successfully logged!");
		}

		SpringApplication.run(App.class, args);
	}
}