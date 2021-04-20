package dm.api.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Person {

    private Integer idPerson;
    private String name;
    private String surname;
    private String pesel;
    private Date dateBirthday;
    private String email;
    private String telephone;
    private Integer idAddress;
}
