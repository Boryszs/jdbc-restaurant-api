package dm.api.mapper.row;

import dm.api.model.Adres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresRowMapper implements RowMapper<Adres>{
    @Override
    public Adres mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Adres().builder()
                .idAdresu(resultSet.getInt("id_adresu"))
                .miejscowosc(resultSet.getString("miejscowosc"))
                .ulica(resultSet.getString("ulica"))
                .nrDomu(resultSet.getString("nr_domu"))
                .kodPocztowy(resultSet.getString("kod_pocztowy"))
                .build();
    }
}
