package group5.number_hit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NumberHitApplication {

  public static void main(String[] args) {
    SpringApplication.run(NumberHitApplication.class, args);
  }

}
