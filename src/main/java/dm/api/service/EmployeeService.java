package dm.api.service;

import dm.api.dto.request.DtoAddEmployeeRequest;
import dm.api.dto.request.DtoEmployeeRequest;
import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.dto.response.DtoEmployeeResponse;
import dm.api.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    int count();
    void save (DtoEmployeeRequest dtoEmployeeRequest);
    void update (Integer id, DtoEmployeeRequest dtoEmployeeRequest);
    void deleteById (int id);
    void add(DtoAddEmployeeRequest dtoAddEmployeeRequest);
    List<DtoEmployeeResponse> findAll ();
    List<DtoEmployeeDataResponse> findAllEmployee();
    DtoEmployeeDataResponse findEmployeeById(int id);
    void deleteEmployeeById(Integer id);
    Optional<Employee> findById(int id);
}
