package az.ingress.ingressfeign.model.criteria;

import lombok.*;

/**
 * @author caci
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCriteria {
    private short ageFrom;
    private short ageTo;
    private String name;
}
