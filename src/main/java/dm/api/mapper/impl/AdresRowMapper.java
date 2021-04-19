package dm.api.mapper.impl;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.mapper.Convert;
import dm.api.model.Adres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresRowMapper implements RowMapper<Adres>, Convert<Adres, DtoAdresRequest, DtoAdresResponse> {
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

    @Override
    public Adres convert(DtoAdresRequest adresRequest) {
        return new Adres().builder()
                .miejscowosc(adresRequest.getMiejscowosc())
                .ulica(adresRequest.getUlica())
                .nrDomu(adresRequest.getNrDomu())
                .kodPocztowy(adresRequest.getKodPocztowy())
                .build();
    }

    @Override
    public Adres update(Adres adres, DtoAdresRequest dtoAdresRequest) {
        adres.setMiejscowosc(dtoAdresRequest.getMiejscowosc());
        adres.setNrDomu(dtoAdresRequest.getNrDomu());
        adres.setUlica(dtoAdresRequest.getUlica());
        adres.setKodPocztowy(dtoAdresRequest.getKodPocztowy());
        return adres;
    }

    @Override
    public DtoAdresResponse toDto(Adres adres) {
        return new DtoAdresResponse().builder()
                 .idAdresu(adres.getIdAdresu())
                 .miejscowosc(adres.getMiejscowosc())
                 .ulica(adres.getUlica())
                 .nrDomu(adres.getNrDomu())
                 .kodPocztowy(adres.getKodPocztowy())
                 .build();
    }

}
