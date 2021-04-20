package dm.api.mapper.impl;

import dm.api.dto.request.DtoCustomerRequest;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.mapper.Convert;
import dm.api.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Convert<Customer, DtoCustomerRequest, DtoCustomerResponse> {

    @Override
    public Customer convert(DtoCustomerRequest klientRequest) {
        return new Customer().builder().login(klientRequest.getLogin())
                .password(klientRequest.getPassword())
                .idPerson(klientRequest.getIdPerson())
                .build();
    }

    @Override
    public Customer update(Customer customer, DtoCustomerRequest dtoCustomerRequest) {
        customer.setLogin(dtoCustomerRequest.getLogin());
        customer.setPassword(dtoCustomerRequest.getPassword());
        customer.setIdPerson(dtoCustomerRequest.getIdPerson());
        return customer;
    }

    @Override
    public DtoCustomerResponse toDto(Customer customer) {
        return new DtoCustomerResponse().builder()
                .idCustomer(customer.getIdCustomer())
                .login(customer.getLogin())
                .password(customer.getPassword())
                .idPerson(customer.getIdPerson())
                .build();
    }

}
