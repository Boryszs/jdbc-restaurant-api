package dm.api.mapper.impl;

import dm.api.dto.response.DtoAddressResponse;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.mapper.ConvertList;
import dm.api.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerListMapper implements ConvertList<DtoCustomerDataResponse, Customer> {

    @Override
    public List<DtoCustomerDataResponse> toListDto(List<Customer> customers) {
        return customers.parallelStream().map(customer -> new DtoCustomerDataResponse().builder()
                .customer(new DtoCustomerResponse().builder()
                        .idCustomer(customer.getIdCustomer())
                        .login(customer.getLogin())
                        .password(customer.getPassword())
                        .idPerson(customer.getPerson().getIdPerson())
                        .build())
                .person(new DtoPersonResponse().builder()
                        .idPerson(customer.getPerson().getIdPerson())
                        .name(customer.getPerson().getName())
                        .surname(customer.getPerson().getSurname())
                        .pesel(customer.getPerson().getPesel())
                        .dateBirthday(customer.getPerson().getDateBirthday())
                        .email(customer.getPerson().getEmail())
                        .telephone(customer.getPerson().getTelephone())
                        .idAddress(customer.getPerson().getAddress().getIdAddress())
                        .build())
                .address(new DtoAddressResponse().builder()
                        .idAddress(customer.getPerson().getAddress().getIdAddress())
                        .town(customer.getPerson().getAddress().getTown())
                        .street(customer.getPerson().getAddress().getStreet())
                        .nrHome(customer.getPerson().getAddress().getNrHome())
                        .postCode(customer.getPerson().getAddress().getPostCode())
                        .build()).build()).collect(Collectors.toList());
    }

    @Override
    public DtoCustomerDataResponse toDto(Customer customer) {
        return new DtoCustomerDataResponse().builder()
                .customer(new DtoCustomerResponse().builder()
                        .idCustomer(customer.getIdCustomer())
                        .login(customer.getLogin())
                        .password(customer.getPassword())
                        .idPerson(customer.getPerson().getIdPerson())
                        .build())
                .person(new DtoPersonResponse().builder()
                        .idPerson(customer.getPerson().getIdPerson())
                        .name(customer.getPerson().getName())
                        .surname(customer.getPerson().getSurname())
                        .pesel(customer.getPerson().getPesel())
                        .dateBirthday(customer.getPerson().getDateBirthday())
                        .email(customer.getPerson().getEmail())
                        .telephone(customer.getPerson().getTelephone())
                        .idAddress(customer.getPerson().getAddress().getIdAddress())
                        .build())
                .address(new DtoAddressResponse().builder()
                        .idAddress(customer.getPerson().getAddress().getIdAddress())
                        .town(customer.getPerson().getAddress().getTown())
                        .street(customer.getPerson().getAddress().getStreet())
                        .nrHome(customer.getPerson().getAddress().getNrHome())
                        .postCode(customer.getPerson().getAddress().getPostCode())
                        .build()).build();
    }
}
