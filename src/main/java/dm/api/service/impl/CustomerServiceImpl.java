package dm.api.service.impl;

import dm.api.dto.request.DtoAddCustomerRequest;
import dm.api.dto.request.DtoCustomerRequest;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.mapper.Convert;
import dm.api.model.Address;
import dm.api.model.Customer;
import dm.api.model.Person;
import dm.api.repository.AddressRepository;
import dm.api.repository.CustomerRepository;
import dm.api.repository.PersonRepository;
import dm.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final Convert<Customer, DtoCustomerRequest, DtoCustomerResponse> klientMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PersonRepository personRepository, AddressRepository addressRepository, Convert<Customer, DtoCustomerRequest, DtoCustomerResponse> klientMapper) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.klientMapper = klientMapper;
    }

    @Override
    public int count() {
        return customerRepository.count();
    }

    @Override
    public void save(DtoCustomerRequest dtoCustomerRequest) {
         customerRepository.save(klientMapper.convert(dtoCustomerRequest));
    }

    @Override
    public void update(Integer id, DtoCustomerRequest dtoCustomerRequest) {
         customerRepository.update(klientMapper.update(customerRepository.findById(id).get(), dtoCustomerRequest));
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
        List<DtoCustomerResponse> customerResponseList = new LinkedList<>();
        customerRepository.findAll().stream().map(customer -> klientMapper.toDto(customer)).forEach(customerResponseList::add);
        return customerResponseList;
    }

    @Override
    public List<DtoCustomerDataResponse> findAllCustomer() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public DtoCustomerDataResponse findCustomerById(int id) {
        return customerRepository.findCustomerId(id);
    }

    @Override
    public Optional<DtoCustomerResponse> findById(int id) {
        return  Optional.of(klientMapper.toDto(customerRepository.findById(id).get()));
    }
}
