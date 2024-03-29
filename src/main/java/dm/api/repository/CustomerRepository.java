package dm.api.repository;

import dm.api.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    int count();
    void save (Customer customer);
    void update (Customer customer);
    void deleteById (Integer id);
    void deleteCustomerById(Integer id);
    List<Customer> findAll();
    List<Customer> findAllCustomer();
    Customer findCustomerId(int id);
    Optional<Customer> findById(int id);
}
