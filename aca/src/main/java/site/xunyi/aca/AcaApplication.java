package site.xunyi.aca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AcaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcaApplication.class, args);
    }
}
