package entreprisecorp;

import entreprisecorp.database.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static UserDbHandler userDbHandler;
	public static FeaturesDbHandler featuresDbHandler;
	public static AdminDbHandler adminDbHandler;
	public static ELOHandler eloHandler;

	public static void main(String[] args) {

		//init singleton database
		Database database = new Database();

		userDbHandler = new UserDbHandler();
		featuresDbHandler = new FeaturesDbHandler();
		adminDbHandler = new AdminDbHandler();
		eloHandler = new ELOHandler();

		SpringApplication.run(App.class, args);
	}
}