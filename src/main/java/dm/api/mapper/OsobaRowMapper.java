package dm.api.mapper;

import dm.api.model.Osoba;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaRowMapper implements RowMapper<Osoba> {

    @Override
    public Osoba mapRow(ResultSet resultSet, int i) throws SQLException {
        Osoba osoba = new Osoba();
        osoba.setIdOsoby(resultSet.getInt("id_osoby"));
        osoba.setImie(resultSet.getString("imie"));
        osoba.setNazwisko(resultSet.getString("nazwisko"));
        osoba.setPesel(resultSet.getString("pesel"));
        osoba.setDataUrodzenia(resultSet.getDate("data_urodzenia"));
        osoba.setEmail(resultSet.getString("email"));
        osoba.setTelefon(resultSet.getString("telefon"));
        osoba.setIdAdresu(resultSet.getInt("id_adresu"));
        return osoba;
    }
}
