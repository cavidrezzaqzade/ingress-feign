package az.ingress.ingressfeign.service.feign;

import az.ingress.ingressfeign.client.CatFactClient;
import az.ingress.ingressfeign.client.FileManagementClient;
import az.ingress.ingressfeign.model.client.CatFactDto;
import az.ingress.ingressfeign.model.client.InformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@Service
@RequiredArgsConstructor
public class FeignCallImpl implements FeignCall {

    private final CatFactClient catClient;
    private final FileManagementClient fileClient;

    @Override
    public CatFactDto getCatFact() {
        return catClient.getCatFact();
    }

    @Override
    public String fileManagement(MultipartFile file, InformationDto info) {
        return fileClient.uploadImage(file, info);
    }
}
