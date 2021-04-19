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
        Adres adres = new Adres().builder()
                .idAdresu(resultSet.getInt("id_adresu"))
                .miejscowosc(resultSet.getString("miejscowosc"))
                .ulica(resultSet.getString("ulica"))
                .nrDomu(resultSet.getString("nr_domu"))
                .kodPocztowy(resultSet.getString("kod_pocztowy"))
                .build();
        return adres;
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
       DtoAdresResponse dtoAdresResponse = new DtoAdresResponse().builder()
                .idAdresu(adres.getIdAdresu())
                .miejscowosc(adres.getMiejscowosc())
                .ulica(adres.getUlica())
                .nrDomu(adres.getNrDomu())
                .kodPocztowy(adres.getKodPocztowy())
                .build();
       return dtoAdresResponse;
    }

}
