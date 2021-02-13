package dm.api.mapper;

import dm.api.model.Klient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientRowMapper implements RowMapper<Klient> {

    @Override
    public Klient mapRow(ResultSet resultSet, int i) throws SQLException {
        Klient klient = new Klient();
        klient.setIdKlienta(resultSet.getInt("id_klienta"));
        klient.setLogin(resultSet.getString("login"));
        klient.setHaslo(resultSet.getString("haslo"));
        klient.setIdOsoby(resultSet.getInt("id_osoby"));
        return klient;
    }
}
