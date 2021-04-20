package dm.api.mapper.row;

import dm.api.model.Pracownik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowMapper implements RowMapper<Pracownik>{
    @Override
    public Pracownik mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Pracownik().builder()
                .idPracownika(resultSet.getInt("id_pracownika"))
                .pensja(resultSet.getDouble("pensja"))
                .rola(resultSet.getString("rola"))
                .idOsoby(resultSet.getInt("id_osoby"))
                .build();
    }
}
