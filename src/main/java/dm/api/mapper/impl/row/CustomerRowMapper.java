package dm.api.mapper.impl.row;

import dm.api.model.Customer;
import dm.api.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Customer().builder()
                .idCustomer(resultSet.getInt("id_klienta"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("haslo"))
                .person(new Person().builder().idPerson(resultSet.getInt("id_osoby")).build())
                .build();
    }
}
