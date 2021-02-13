package dm.api.mapper;

import dm.api.model.Adres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresRowMapper implements RowMapper<Adres> {
    @Override
    public Adres mapRow(ResultSet resultSet, int i) throws SQLException {
        Adres adres = new Adres();
        adres.setIdAdresu(resultSet.getInt("id_adresu"));
        adres.setMiejscowosc(resultSet.getString("miejscowosc"));
        adres.setUlica(resultSet.getString("ulica"));
        adres.setNrDomu(resultSet.getString("nr_domu"));
        adres.setKodPocztowy(resultSet.getString("kod_pocztowy"));
        return adres;
    }
}
