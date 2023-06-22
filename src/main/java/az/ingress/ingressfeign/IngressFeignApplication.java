package az.ingress.ingressfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IngressFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngressFeignApplication.class, args);
    }

}
