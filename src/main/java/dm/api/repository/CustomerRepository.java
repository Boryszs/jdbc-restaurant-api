package dm.api.repository;

import dm.api.dto.response.DtoCustomerDataResponse;
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
    List<DtoCustomerDataResponse> findAllCustomer();
    DtoCustomerDataResponse findCustomerId(int id);
    Optional<Customer> findById(int id);
}
