package dm.api.mapper.impl.row;

import dm.api.model.Osoba;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaRowMapper implements RowMapper<Osoba>{

    @Override
    public Osoba mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Osoba().builder()
                .idOsoby(resultSet.getInt("id_osoby"))
                .imie(resultSet.getString("imie"))
                .nazwisko(resultSet.getString("nazwisko"))
                .pesel(resultSet.getString("pesel"))
                .dataUrodzenia(resultSet.getDate("data_urodzenia"))
                .email(resultSet.getString("email"))
                .telefon(resultSet.getString("telefon"))
                .idAdresu(resultSet.getInt("id_adresu"))
                .build();
    }
}
