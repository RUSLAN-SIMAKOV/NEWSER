package ruslan.simakov.newser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NewserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewserApplication.class, args);
    }
}
