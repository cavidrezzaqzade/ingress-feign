package az.ingress.ingressfeign.service;

import az.ingress.ingressfeign.model.UserDetails;
import az.ingress.ingressfeign.model.UserDto;
import az.ingress.ingressfeign.model.criteria.PageCriteria;
import az.ingress.ingressfeign.model.criteria.UserCriteria;
import az.ingress.ingressfeign.model.response.PageableUserResponse;
import java.util.List;

/**
 * @author caci
 */

public interface UserService {

    List<UserDto> getAll();

    UserDto add(UserDto userDto);

    void delete(Long id);

    UserDto update(UserDto dto, Long id);

    UserDto update(UserDetails userDetails);

    PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria);
}
