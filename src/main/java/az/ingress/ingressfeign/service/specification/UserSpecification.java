package az.ingress.ingressfeign.service.specification;

import az.ingress.ingressfeign.dao.entity.User;
import az.ingress.ingressfeign.dao.entity.User_;
import az.ingress.ingressfeign.model.criteria.UserCriteria;
import az.ingress.ingressfeign.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import static az.ingress.ingressfeign.util.PredicateUtil.applyLikePattern;

/**
 * @author caci
 */

@AllArgsConstructor
public class UserSpecification implements Specification<User> {

    private UserCriteria userCriteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        var predicates = PredicateUtil.builder()
                .addNullSafety(userCriteria.getName(),
                        birthPlace -> cb.like(root.get(User_.NAME), applyLikePattern(birthPlace))
                )
                .addNullSafety(userCriteria.getAgeFrom(), ageFrom -> cb.greaterThanOrEqualTo(root.get("age"), ageFrom))
                .addNullSafety(userCriteria.getAgeTo(), ageTo -> cb.lessThanOrEqualTo(root.get("age"), ageTo))
                .build();

        return cb.and(predicates);
    }
}
