package az.ingress.ingressfeign.client;

//import az.ingress.ingressfeign.config.FeignSupportConfig;
import az.ingress.ingressfeign.config.FeignSupportConfig;
import az.ingress.ingressfeign.model.client.InformationDto;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@FeignClient(name = "file-management-client", url = "${file-management.client.url.root}", configuration = FeignSupportConfig.class)
@Configuration
public interface FileManagementClient {

    @PostMapping(value = "/v1/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadImage(@RequestPart("file") MultipartFile file, @RequestPart("info") InformationDto info);

}
