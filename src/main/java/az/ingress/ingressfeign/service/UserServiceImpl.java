package az.ingress.ingressfeign.service;

import az.ingress.ingressfeign.dao.entity.User;
import az.ingress.ingressfeign.dao.repository.UserRepository;
import az.ingress.ingressfeign.exception.ApplicationException;
import az.ingress.ingressfeign.exception.Errors;
import az.ingress.ingressfeign.mapper.PageableMapper;
import az.ingress.ingressfeign.mapper.UserMapper;
import az.ingress.ingressfeign.model.UserDetails;
import az.ingress.ingressfeign.model.UserDto;
import az.ingress.ingressfeign.model.criteria.PageCriteria;
import az.ingress.ingressfeign.model.criteria.UserCriteria;
import az.ingress.ingressfeign.model.response.PageableUserResponse;
import az.ingress.ingressfeign.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author caci
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PageableMapper pageableMapper;

    private static final Integer PAGE_DEFAULT = 1;
    private static final Integer COUNT_DEFAULT = 10;

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return mapper.entitiesToDtos(users);
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = mapper.dtoToEntity(userDto);
        return mapper.entityToDto(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", id)));
        repository.delete(user);
    }

    @Override
    public UserDto update(UserDto dto, Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", id)));
        User userNew = mapper.updateUser(user, dto);
        return mapper.entityToDto(repository.save(userNew));
    }

    @Override
    public UserDto update(UserDetails userDetails) {
        User user = repository.findById(userDetails.getId()).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", userDetails.getId())));

        user.setAge(userDetails.getAge());
        user.setName(userDetails.getName());

        return mapper.entityToDto(repository.save(user));
    }

    @Override
    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {

        var pageNumber = pageCriteria.getPage() == null ? PAGE_DEFAULT : pageCriteria.getPage();
        var count = pageCriteria.getCount() == null ? COUNT_DEFAULT : pageCriteria.getCount();

        Page<User> usersPage = repository.findAll(new UserSpecification(userCriteria), PageRequest.of(pageNumber, count));
        return pageableMapper.mapToPageableResponse(usersPage);
    }
}
