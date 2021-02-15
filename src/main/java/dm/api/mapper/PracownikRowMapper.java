package dm.api.mapper;

import dm.api.model.Pracownik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowMapper implements RowMapper<Pracownik> {
    @Override
    public Pracownik mapRow(ResultSet resultSet, int i) throws SQLException {
        Pracownik pracownik = new Pracownik();
        pracownik.setIdPracownika(resultSet.getInt("id_pracownika"));
        pracownik.setPensja(resultSet.getDouble("pensja"));
        pracownik.setRola(resultSet.getString("rola"));
        pracownik.setIdOsoby(resultSet.getInt("id_osoby"));
        return pracownik;
    }
}
