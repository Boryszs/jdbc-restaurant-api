package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Employee {

    public Employee(Integer idEmployee, Double salary, String role) {
        this.idEmployee = idEmployee;
        this.salary = salary;
        this.role = role;
    }

    private Integer idEmployee;
    private Double salary;
    private String role;
    private Person person;
}
