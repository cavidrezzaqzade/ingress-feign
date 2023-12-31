package az.ingress.ingressfeign.controller;

import az.ingress.ingressfeign.model.UserDetails;
import az.ingress.ingressfeign.model.UserDto;
import az.ingress.ingressfeign.model.criteria.PageCriteria;
import az.ingress.ingressfeign.model.criteria.UserCriteria;
import az.ingress.ingressfeign.model.response.PageableUserResponse;
import az.ingress.ingressfeign.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author caci
 */

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "the User api")
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "get all", description = "get all", tags = {"User"})
    public List<UserDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "add", description = "add", tags = {"User"})
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@RequestBody @Valid @NotNull UserDto dto){
        return service.add(dto);
    }

    @DeleteMapping("/{user-id}")
    @Operation(summary = "delete", description = "delete", tags = {"User"})
    public void delete(@PathVariable("user-id") Long id){
        service.delete(id);
    }

    @PutMapping("/{user-id}")
    @Operation(summary = "update", description = "update", tags = {"User"})
    public UserDto update(@PathVariable("user-id") Long id, @RequestBody @NotNull @Valid UserDto dto){
        return service.update(dto, id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "update by criteria", description = "update by criteria", tags = {"User"})
    public UserDto update(UserDetails user){
        return service.update(user);
    }

    @GetMapping("/pagination")
    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria){
        return service.getUsers(pageCriteria, userCriteria);
    }
}
