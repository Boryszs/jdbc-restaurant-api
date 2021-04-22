package dm.api.repository;

import dm.api.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();
    void save (Employee employee);
    void update (Employee employee);
    void deleteById (Integer id);
    List<Employee> findAll();
    List<Employee> findAllEmployee();
    Employee findEmployeeById(int id);
    void deleteEmployeeById(Integer id);
    Optional<Employee> findById(int id);
}
