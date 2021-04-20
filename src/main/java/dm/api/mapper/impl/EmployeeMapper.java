package dm.api.mapper.impl;

import dm.api.dto.request.DtoEmployeeRequest;
import dm.api.dto.response.DtoEmployeeResponse;
import dm.api.mapper.Convert;
import dm.api.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements Convert<Employee, DtoEmployeeRequest, DtoEmployeeResponse> {

    @Override
    public Employee convert(DtoEmployeeRequest dtoEmployeeRequest) {
        return new Employee().builder()
                .salary(dtoEmployeeRequest.getSalary())
                .role(dtoEmployeeRequest.getRole())
                .idPerson(dtoEmployeeRequest.getIdPerson())
                .build();
    }

    @Override
    public Employee update(Employee employee, DtoEmployeeRequest dtoEmployeeRequest) {
        employee.setSalary(dtoEmployeeRequest.getSalary());
        employee.setRole(dtoEmployeeRequest.getRole());
        employee.setIdPerson(dtoEmployeeRequest.getIdPerson());
        return employee;
    }

    @Override
    public DtoEmployeeResponse toDto(Employee employee) {
        return new DtoEmployeeResponse().builder()
                .idEmployee(employee.getIdEmployee())
                .salary(employee.getSalary())
                .role(employee.getRole())
                .idPerson(employee.getIdPerson()).build();
    }
}
