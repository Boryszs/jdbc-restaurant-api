package dm.api.mapper.impl.row;

import dm.api.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person>{

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Person().builder()
                .idPerson(resultSet.getInt("id_osoby"))
                .name(resultSet.getString("imie"))
                .surname(resultSet.getString("nazwisko"))
                .pesel(resultSet.getString("pesel"))
                .dateBirthday(resultSet.getDate("data_urodzenia"))
                .email(resultSet.getString("email"))
                .telephone(resultSet.getString("telefon"))
                .idAddress(resultSet.getInt("id_adresu"))
                .build();
    }
}
