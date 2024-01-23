package miguel.work.ForeignExchangeRatesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "miguel.work.ForeignExchangeRatesService")
@EnableScheduling
public class ForeignExchangeRatesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeRatesServiceApplication.class, args);
	}

}
