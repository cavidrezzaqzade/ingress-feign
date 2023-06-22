package az.ingress.ingressfeign.client;

import az.ingress.ingressfeign.model.client.CatFactDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author caci
 */

@FeignClient(name = "cat-fact-client", url = "${cat-fact.client.url.root}")
@Configuration
public interface CatFactClient {

    @GetMapping("/fact")
    CatFactDto getCatFact();

}
