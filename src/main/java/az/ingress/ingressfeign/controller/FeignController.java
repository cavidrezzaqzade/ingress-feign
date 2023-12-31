package az.ingress.ingressfeign.controller;

import az.ingress.ingressfeign.client.FileManagementClient;
import az.ingress.ingressfeign.model.client.CatFactDto;
import az.ingress.ingressfeign.model.client.InformationDto;
import az.ingress.ingressfeign.service.feign.FeignCall;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@RestController
@RequestMapping("/v1/feign")
@RequiredArgsConstructor
@Tag(name = "Feign", description = "the File Management api")
public class FeignController {

    private final FeignCall call;
    private final FileManagementClient fileManagementClient;

    @GetMapping
    @Operation(summary = "get cat fact", description = "get cat fact", tags = {"Feign"})
    public CatFactDto getAll(){
        return call.getCatFact();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE/*consumes = {"multipart/form-data", "application/json"}*/)
    @Operation(summary = "upload images", description = "upload images", tags = {"Feign"})
    public String uploadImage(@RequestPart("file") MultipartFile file, @RequestPart("info") InformationDto info){
        System.out.println(file.getOriginalFilename());
        return fileManagementClient.uploadImage(file, info);
    }
}