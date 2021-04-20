package dm.api.service;

import dm.api.dto.request.DtoAddCustomerRequest;
import dm.api.dto.request.DtoCustomerRequest;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    int count();
    void save (DtoCustomerRequest dtoCustomerRequest);
    void update (Integer id, DtoCustomerRequest dtoCustomerRequest);
    void deleteById (int id);
    void deleteCustomerById(Integer id);
    void add(DtoAddCustomerRequest dtoAddCustomerRequest);
    List<DtoCustomerResponse> findAll();
    List<DtoCustomerDataResponse> findAllCustomer();
    DtoCustomerDataResponse findCustomerById(int id);
    Optional<DtoCustomerResponse> findById(int id);
}
