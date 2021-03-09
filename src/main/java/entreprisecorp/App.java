package entreprisecorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entreprisecorp.database.Database;
import entreprisecorp.restservices.models.User;

@SpringBootApplication
public class App {

	public static Database db;
	public static void main(String[] args) {
		db = new Database();
		if(db.insertUser(new User("hbollon", "loloLeBouf", "hugo.bollon@gmail.com"))) {
			System.out.println("User hbollon successfully registered!");
		}

		User connectHbollon = db.connectUser("hugo.bollon@gmail.com", "loloLeBouf");
		if(connectHbollon.getUsername() == "hbollon") {
			System.out.println("User hbollon successfully logged!");
		}

		SpringApplication.run(App.class, args);
	}
}