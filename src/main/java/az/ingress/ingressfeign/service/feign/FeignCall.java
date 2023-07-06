package az.ingress.ingressfeign.service.feign;

import az.ingress.ingressfeign.model.client.CatFactDto;
import az.ingress.ingressfeign.model.client.InformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

public interface FeignCall {

    CatFactDto getCatFact();
    ResponseEntity<?> fileManagement(MultipartFile file, InformationDto info);

}
