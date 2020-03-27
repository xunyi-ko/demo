package site.xunyi.aca.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean(name = "DemoBean")
    public String getDemoBean(){
        return "123456";
    }

}
