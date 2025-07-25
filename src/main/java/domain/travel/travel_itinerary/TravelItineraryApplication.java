package domain.travel.travel_itinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelItineraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelItineraryApplication.class, args);
	}

}
