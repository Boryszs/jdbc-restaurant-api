package dm.api.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Person {

    public Person(Integer idPerson, String name, String surname, String pesel, Date dateBirthday, String email, String telephone) {
        this.idPerson = idPerson;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.dateBirthday = dateBirthday;
        this.email = email;
        this.telephone = telephone;
    }

    private Integer idPerson;
    private String name;
    private String surname;
    private String pesel;
    private Date dateBirthday;
    private String email;
    private String telephone;
    private Address address;
}
