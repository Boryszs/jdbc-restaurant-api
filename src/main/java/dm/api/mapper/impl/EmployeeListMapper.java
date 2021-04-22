package dm.api.mapper.impl;

import dm.api.dto.response.DtoAddressResponse;
import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.dto.response.DtoEmployeeResponse;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.mapper.ConvertList;
import dm.api.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeListMapper implements ConvertList<DtoEmployeeDataResponse, Employee> {
    @Override
    public List<DtoEmployeeDataResponse> toListDto(List<Employee> employees) {
        return employees.parallelStream().map(employee -> new DtoEmployeeDataResponse().builder()
                .employee(new DtoEmployeeResponse().builder()
                        .idEmployee(employee.getIdEmployee())
                        .salary(employee.getSalary())
                        .role(employee.getRole())
                        .idPerson(employee.getPerson().getIdPerson()).build())
                .person(new DtoPersonResponse().builder()
                        .idPerson(employee.getPerson().getIdPerson())
                        .name(employee.getPerson().getName())
                        .surname(employee.getPerson().getSurname())
                        .pesel(employee.getPerson().getPesel())
                        .dateBirthday(employee.getPerson().getDateBirthday())
                        .email(employee.getPerson().getEmail())
                        .telephone(employee.getPerson().getTelephone())
                        .idAddress(employee.getPerson().getAddress().getIdAddress())
                        .build())
                .address(new DtoAddressResponse().builder()
                        .idAddress(employee.getPerson().getAddress().getIdAddress())
                        .town(employee.getPerson().getAddress().getTown())
                        .street(employee.getPerson().getAddress().getStreet())
                        .nrHome(employee.getPerson().getAddress().getNrHome())
                        .postCode(employee.getPerson().getAddress().getPostCode())
                        .build()).build()).collect(Collectors.toList());
    }

    @Override
    public DtoEmployeeDataResponse toDto(Employee employee) {
       return new DtoEmployeeDataResponse().builder()
                .employee(new DtoEmployeeResponse().builder()
                        .idEmployee(employee.getIdEmployee())
                        .salary(employee.getSalary())
                        .role(employee.getRole())
                        .idPerson(employee.getPerson().getIdPerson()).build())
                .person(new DtoPersonResponse().builder()
                        .idPerson(employee.getPerson().getIdPerson())
                        .name(employee.getPerson().getName())
                        .surname(employee.getPerson().getSurname())
                        .pesel(employee.getPerson().getPesel())
                        .dateBirthday(employee.getPerson().getDateBirthday())
                        .email(employee.getPerson().getEmail())
                        .telephone(employee.getPerson().getTelephone())
                        .idAddress(employee.getPerson().getAddress().getIdAddress())
                        .build())
                .address(new DtoAddressResponse().builder()
                        .idAddress(employee.getPerson().getAddress().getIdAddress())
                        .town(employee.getPerson().getAddress().getTown())
                        .street(employee.getPerson().getAddress().getStreet())
                        .nrHome(employee.getPerson().getAddress().getNrHome())
                        .postCode(employee.getPerson().getAddress().getPostCode())
                        .build()).build();
    }


}
