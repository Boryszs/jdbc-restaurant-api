package dm.api.mapper;

import dm.api.dto.response.DtoKlientResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientRowMapper implements RowMapper<DtoKlientResponse> {

    @Override
    public DtoKlientResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoKlientResponse dtoKlientResponse = new DtoKlientResponse().builder()
                .idKlienta(resultSet.getInt("id_klienta"))
                .login(resultSet.getString("login"))
                .haslo(resultSet.getString("haslo"))
                .idOsoby(resultSet.getInt("id_osoby"))
                .build();
        return dtoKlientResponse;
    }
}
