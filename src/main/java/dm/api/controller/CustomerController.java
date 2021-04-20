package dm.api.controller;


import dm.api.dto.request.DtoAddCustomerRequest;
import dm.api.dto.request.DtoCustomerRequest;
import dm.api.dto.request.DtoUpdateCustomerRequest;
import dm.api.dto.response.DtoError;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.service.AddressService;
import dm.api.service.CustomerService;
import dm.api.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/klient")
@RestController
@CrossOrigin
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final PersonService personService;
    private final AddressService addressService;

    @Autowired
    public CustomerController(CustomerService customerService, PersonService personService, AddressService addressService) {
        this.customerService = customerService;
        this.personService = personService;
        this.addressService = addressService;
    }

    @ResponseBody
    @GetMapping(value = "/size")
    public ResponseEntity<Integer> size() {
        logger.info("Get count customer entity");
       return ResponseEntity.ok(customerService.count());
    }

    @ResponseBody
    @GetMapping(value = "/all-klient")
    public ResponseEntity<List<DtoCustomerDataResponse>> getAllCustomer() {
        logger.info("Get all customer data");
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @ResponseBody
    @GetMapping(value = "/get-klient/{id}")
    public ResponseEntity<DtoCustomerDataResponse> getCustomerId(@PathVariable(value="id") Integer id) {
        logger.info("Get customer data on id {}", id);
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @ResponseBody
    @DeleteMapping(value = "/delete-klient/{id}")
    public ResponseEntity<?> deleteCustomerId(@PathVariable(value="id") Integer id) {
        logger.info("Delete customer on {}",id);
        try{
            customerService.deleteCustomerById(id);
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/all")
    public ResponseEntity<List<DtoCustomerResponse>> getAll() {
        logger.info("Get all customer");
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Integer> add(@RequestBody DtoCustomerRequest dtoCustomerRequest) {
        logger.info("Add customer");
        customerService.save(dtoCustomerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-klient")
    public ResponseEntity<Integer> addCustomer(@RequestBody DtoAddCustomerRequest dtoAddCustomerRequest) {
        logger.info("Add customer");
        customerService.add(dtoAddCustomerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> update(@PathVariable(value="id") Integer id, @RequestBody DtoCustomerRequest dtoCustomerRequest) {
        logger.info("Update customer");
        customerService.update(id,dtoCustomerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-klient/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value="id") Integer id, @RequestBody DtoUpdateCustomerRequest dtoUpdateCustomerRequest) {
        try{
            customerService.update(id, dtoUpdateCustomerRequest.getCustomer());
            personService.update(dtoUpdateCustomerRequest.getCustomer().getIdPerson(),dtoUpdateCustomerRequest.getPerson());
            addressService.update(dtoUpdateCustomerRequest.getPerson().getIdPerson(),dtoUpdateCustomerRequest.getAddress());
            logger.info("Update customer");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find customer on id: "+id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value="id") Integer id) {
        try{
            logger.info("Delete customer id ",id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find customer on id: "+id));
        }
    }

    @ResponseBody
    @GetMapping(value = "get/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable(value="id") Integer id) {
        logger.info("Get customer on id {}",id);
        try {
            Optional<DtoCustomerResponse> customer = customerService.findById(id);
            return ResponseEntity.ok(customer.get());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(400).body(new DtoError("No find customer on id: "+id));
        }
    }
}
