package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Customer {

    private Integer idCustomer;
    private String login;
    private String password;
    private Integer idPerson;
}
