package dm.api.mapper;

import dm.api.dto.response.DtoOsobaResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaRowMapper implements RowMapper<DtoOsobaResponse> {

    @Override
    public DtoOsobaResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoOsobaResponse dtoOsobaResponse = new DtoOsobaResponse().builder()
                .idOsoby(resultSet.getInt("id_osoby"))
                .imie(resultSet.getString("imie"))
                .nazwisko(resultSet.getString("nazwisko"))
                .pesel(resultSet.getString("pesel"))
                .dataUrodzenia(resultSet.getDate("data_urodzenia"))
                .email(resultSet.getString("email"))
                .telefon(resultSet.getString("telefon"))
                .idAdresu(resultSet.getInt("id_adresu")).build();

        return dtoOsobaResponse;
    }
}
