package dm.api.service.impl;

import dm.api.dto.request.DtoAddCustomerRequest;
import dm.api.dto.request.DtoCustomerRequest;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.mapper.Convert;
import dm.api.mapper.ConvertList;
import dm.api.model.Address;
import dm.api.model.Customer;
import dm.api.model.Person;
import dm.api.repository.AddressRepository;
import dm.api.repository.CustomerRepository;
import dm.api.repository.PersonRepository;
import dm.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final Convert<Customer, DtoCustomerRequest, DtoCustomerResponse> customerMapper;
    private final ConvertList<DtoCustomerDataResponse, Customer> customerListMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PersonRepository personRepository, AddressRepository addressRepository, Convert<Customer, DtoCustomerRequest, DtoCustomerResponse> customerMapper, ConvertList<DtoCustomerDataResponse, Customer> customerListMapper) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.customerMapper = customerMapper;
        this.customerListMapper = customerListMapper;
    }

    @Override
    public int count() {
        return customerRepository.count();
    }

    @Override
    public void save(DtoCustomerRequest dtoCustomerRequest) {
         customerRepository.save(customerMapper.convert(dtoCustomerRequest));
    }

    @Override
    public void update(Integer id, DtoCustomerRequest dtoCustomerRequest) {
         customerRepository.update(customerMapper.update(customerRepository.findById(id).get(), dtoCustomerRequest));
    }

    @Override
    public void deleteById(int id) {
         customerRepository.deleteById(id);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerRepository.deleteCustomerById(id);
    }

    @Override
    public void add(DtoAddCustomerRequest dtoAddCustomerRequest) {
       Integer idAddress = addressRepository.save(new Address(null, dtoAddCustomerRequest.getAddress().getTown(), dtoAddCustomerRequest.getAddress().getStreet(), dtoAddCustomerRequest.getAddress().getNrHome(), dtoAddCustomerRequest.getAddress().getPostCode()));
       Integer idPerson = personRepository.save(new Person(null, dtoAddCustomerRequest.getPerson().getName(), dtoAddCustomerRequest.getPerson().getSurname(), dtoAddCustomerRequest.getPerson().getPesel(), dtoAddCustomerRequest.getPerson().getDateBirthday(), dtoAddCustomerRequest.getPerson().getEmail(), dtoAddCustomerRequest.getPerson().getTelephone(),addressRepository.findById(idAddress).get()));
       customerRepository.save(new Customer(null, dtoAddCustomerRequest.getLogin(), dtoAddCustomerRequest.getPassword(),personRepository.findById(idPerson).get()));
    }

    @Override
    public List<DtoCustomerResponse> findAll() {
        return customerRepository.findAll().parallelStream().map(customer -> customerMapper.toDto(customer)).collect(Collectors.toList());
    }

    @Override
    public List<DtoCustomerDataResponse> findAllCustomer() {
        return customerListMapper.toListDto(customerRepository.findAllCustomer());
    }

    @Override
    public DtoCustomerDataResponse findCustomerById(int id) {
        return customerListMapper.toDto(customerRepository.findCustomerId(id));
    }

    @Override
    public Optional<DtoCustomerResponse> findById(int id) {
        return  Optional.of(customerMapper.toDto(customerRepository.findById(id).get()));
    }
}
