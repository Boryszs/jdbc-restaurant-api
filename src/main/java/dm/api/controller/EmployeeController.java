package dm.api.controller;

import dm.api.dto.request.DtoAddEmployeeRequest;
import dm.api.dto.request.DtoEmployeeRequest;
import dm.api.dto.request.DtoUpdateEmployeeRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.dto.response.DtoEmployeeResponse;
import dm.api.model.Employee;
import dm.api.service.AddressService;
import dm.api.service.PersonService;
import dm.api.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/pracownik")
@RestController
@CrossOrigin
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final PersonService personService;
    private final AddressService addressService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PersonService personService, AddressService addressService) {
        this.employeeService = employeeService;
        this.personService = personService;
        this.addressService = addressService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count employee entity");
        return ResponseEntity.ok(employeeService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all-pracownik")
    public ResponseEntity<List<DtoEmployeeDataResponse>> getAllEmployee() {
        logger.info("Get all employee data");
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }

    @ResponseBody
    @GetMapping(value = "/get-pracownik/{id}")
    public ResponseEntity<DtoEmployeeDataResponse> getEmployeeId(@PathVariable(value = "id") Integer id) {
        logger.info("Get all employee data");
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-pracownik/{id}")
    public ResponseEntity<?> deleteEmployeeId(@PathVariable(value = "id") Integer id) {
        logger.info("Delete employee on {}", id);
        try {
            employeeService.deleteEmployeeById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.ok(true);
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoEmployeeResponse>> getAll() {
        logger.info("Get all employee");
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody DtoEmployeeRequest dtoEmployeeRequest) {
        logger.info("Add employee");
        employeeService.save(dtoEmployeeRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/add-pracownik")
    public ResponseEntity<?> addEmployee(@RequestBody DtoAddEmployeeRequest klientRequest) {
        logger.info("Add employee");
        employeeService.add(klientRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Integer id) {
        try {
            logger.info("Delete employee id ", id);
            employeeService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find employee on id: " + id));
        }
    }

    @PutMapping(value = "/update-pracownik/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody DtoUpdateEmployeeRequest dtoUpdateEmployeeRequest) {
        try {
            employeeService.update(id,dtoUpdateEmployeeRequest.getEmployee());
            personService.update(dtoUpdateEmployeeRequest.getEmployee().getIdPerson(),dtoUpdateEmployeeRequest.getPerson());
            addressService.update(dtoUpdateEmployeeRequest.getPerson().getIdPerson(),dtoUpdateEmployeeRequest.getAddress());
            logger.info("Update employee");
            return ResponseEntity.ok(dtoUpdateEmployeeRequest);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find employee on id: " + id));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> update(@PathVariable(value = "id") Integer id, @RequestBody DtoEmployeeRequest dtoEmployeeRequest) {
        logger.info("Update employee");
        employeeService.update(id, dtoEmployeeRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(value = "id") Integer id) {
        logger.info("Get employee on id {}", id);
        try {
            Optional<Employee> employee = employeeService.findById(id);
            return ResponseEntity.ok(employee.get());
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find employee on id: " + id));
        }
    }
}
