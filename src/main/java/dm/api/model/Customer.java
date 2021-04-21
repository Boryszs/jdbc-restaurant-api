package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Customer {


    public Customer(Integer idCustomer, String login, String password) {
        this.idCustomer = idCustomer;
        this.login = login;
        this.password = password;
    }

    private Integer idCustomer;
    private String login;
    private String password;
    private Person person;
}
