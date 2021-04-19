package dm.api.mapper;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.model.Adres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresRowMapper implements RowMapper<DtoAdresResponse>, Convert<Adres, DtoAdresRequest>{
    @Override
    public DtoAdresResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoAdresResponse dtoAdresResponse = new DtoAdresResponse().builder()
                .idAdresu(resultSet.getInt("id_adresu"))
                .miejscowosc(resultSet.getString("miejscowosc"))
                .ulica(resultSet.getString("ulica"))
                .nrDomu(resultSet.getString("nr_domu"))
                .kodPocztowy(resultSet.getString("kod_pocztowy"))
                .build();
        return dtoAdresResponse;
    }

    @Override
    public Adres convert(DtoAdresRequest adresRequest) {
        Adres adres = new Adres().builder()
                .miejscowosc(adresRequest.getMiejscowosc())
                .ulica(adresRequest.getUlica())
                .nrDomu(adresRequest.getNrDomu())
                .kodPocztowy(adresRequest.getKodPocztowy())
                .build();
        return adres;
    }
}
