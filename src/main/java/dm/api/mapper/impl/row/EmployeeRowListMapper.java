package dm.api.mapper.impl.row;

import dm.api.model.Address;
import dm.api.model.Employee;
import dm.api.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowListMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {

        Employee employee = new Employee().builder()
                .idEmployee(resultSet.getInt("id_pracownika"))
                .salary(resultSet.getDouble("pensja"))
                .role(resultSet.getString("rola"))
                .person(new Person().builder()
                        .idPerson(resultSet.getInt("id_osoby"))
                        .name(resultSet.getString("imie"))
                        .surname(resultSet.getString("nazwisko"))
                        .pesel(resultSet.getString("pesel"))
                        .dateBirthday(resultSet.getDate("data_urodzenia"))
                        .email(resultSet.getString("email"))
                        .telephone(resultSet.getString("telefon"))
                        .address(new Address().builder()
                                .idAddress(resultSet.getInt("id_adresu"))
                                .town(resultSet.getString("miejscowosc"))
                                .street(resultSet.getString("ulica"))
                                .nrHome(resultSet.getString("nr_domu"))
                                .postCode(resultSet.getString("kod_pocztowy"))
                                .build())
                        .build()).build();
        return employee;
    }
}
