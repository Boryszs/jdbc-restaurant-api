package dm.api.repository;

import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();
    void save (Employee employee);
    void update (Employee employee);
    void deleteById (Integer id);
    List<Employee> findAll();
    List<DtoEmployeeDataResponse> findAllEmployee();
    DtoEmployeeDataResponse findEmployeeById(int id);
    void deleteEmployeeById(Integer id);
    Optional<Employee> findById(int id);
}
