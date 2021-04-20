package dm.api.mapper.row;

import dm.api.model.Klient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientRowMapper implements RowMapper<Klient> {

    @Override
    public Klient mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Klient().builder()
                .idKlienta(resultSet.getInt("id_klienta"))
                .login(resultSet.getString("login"))
                .haslo(resultSet.getString("haslo"))
                .idOsoby(resultSet.getInt("id_osoby"))
                .build();
    }
}
