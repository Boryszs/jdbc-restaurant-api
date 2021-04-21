package dm.api.service.impl;

import dm.api.dto.request.DtoAddEmployeeRequest;
import dm.api.dto.request.DtoEmployeeRequest;
import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.dto.response.DtoEmployeeResponse;
import dm.api.mapper.Convert;
import dm.api.model.Address;
import dm.api.model.Employee;
import dm.api.model.Person;
import dm.api.repository.AddressRepository;
import dm.api.repository.PersonRepository;
import dm.api.repository.EmployeeRepository;
import dm.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final Convert<Employee, DtoEmployeeRequest, DtoEmployeeResponse> employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PersonRepository personRepository, AddressRepository addressRepository, Convert<Employee, DtoEmployeeRequest, DtoEmployeeResponse> employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public int count() {
        return employeeRepository.count();
    }

    @Override
    public void save(DtoEmployeeRequest dtoPracownikResponse) {
        employeeRepository.save(employeeMapper.convert(dtoPracownikResponse));
    }

    @Override
    public void update(Integer id, DtoEmployeeRequest dtoEmployeeRequest) {
         employeeRepository.update(employeeMapper.update(employeeRepository.findById(id).get(), dtoEmployeeRequest));
    }

    @Override
    public void deleteById(int id) {
         employeeRepository.deleteById(id);
    }

    @Override
    public void add(DtoAddEmployeeRequest dtoAddEmployeeRequest) {
        Integer idAddress = addressRepository.save(new Address(null, dtoAddEmployeeRequest.getAddress().getTown(), dtoAddEmployeeRequest.getAddress().getStreet(), dtoAddEmployeeRequest.getAddress().getNrHome(), dtoAddEmployeeRequest.getAddress().getPostCode()));
        Integer idPerson = personRepository.save(new Person(null, dtoAddEmployeeRequest.getPerson().getName(), dtoAddEmployeeRequest.getPerson().getSurname(), dtoAddEmployeeRequest.getPerson().getPesel(), dtoAddEmployeeRequest.getPerson().getDateBirthday(), dtoAddEmployeeRequest.getPerson().getEmail(), dtoAddEmployeeRequest.getPerson().getTelephone(),addressRepository.findById(idAddress).get()));
        employeeRepository.save(new Employee(null, dtoAddEmployeeRequest.getSalary(), dtoAddEmployeeRequest.getRole(),personRepository.findById(idPerson).get()));
    }

    @Override
    public List<DtoEmployeeResponse> findAll() {
        List<DtoEmployeeResponse> employeeResponseList = new LinkedList<>();
        employeeRepository.findAll().stream().map(employee -> employeeMapper.toDto(employee)).forEach(employeeResponseList::add);
        return employeeResponseList;
    }

    @Override
    public List<DtoEmployeeDataResponse> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    @Override
    public DtoEmployeeDataResponse findEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }
}
