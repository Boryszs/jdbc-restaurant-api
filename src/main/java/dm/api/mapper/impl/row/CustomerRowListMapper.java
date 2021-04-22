package dm.api.mapper.impl.row;

import dm.api.model.Address;
import dm.api.model.Customer;
import dm.api.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowListMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {

        Customer customer = new Customer().builder()
                .idCustomer(resultSet.getInt("id_klienta"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("haslo"))
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
                        .build())
                .build();

        return customer;
    }
}
