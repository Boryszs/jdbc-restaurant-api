package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Employee {

    private Integer idEmployee;
    private Double salary;
    private String role;
    private Integer idPerson;
}
