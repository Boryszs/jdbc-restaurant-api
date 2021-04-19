package dm.api.mapper.impl;

import dm.api.dto.request.DtoPracownikRequest;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.mapper.Convert;
import dm.api.model.Pracownik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowMapper implements RowMapper<Pracownik>, Convert<Pracownik, DtoPracownikRequest, DtoPracownikResponse> {
    @Override
    public Pracownik mapRow(ResultSet resultSet, int i) throws SQLException {
        Pracownik pracownik = new Pracownik().builder()
                .idPracownika(resultSet.getInt("id_pracownika"))
                .pensja(resultSet.getDouble("pensja"))
                .rola(resultSet.getString("rola"))
                .idOsoby(resultSet.getInt("id_osoby"))
                .build();
        return pracownik;
    }

    @Override
    public Pracownik convert(DtoPracownikRequest dtoPracownikRequest) {
        Pracownik pracownik = new Pracownik().builder()
                .pensja(dtoPracownikRequest.getPensja())
                .rola(dtoPracownikRequest.getRola())
                .idOsoby(dtoPracownikRequest.getIdOsoby())
                .build();
        return pracownik;
    }

    @Override
    public Pracownik update(Pracownik pracownik, DtoPracownikRequest dtoPracownikRequest) {
        pracownik.setPensja(dtoPracownikRequest.getPensja());
        pracownik.setRola(dtoPracownikRequest.getRola());
        pracownik.setIdOsoby(dtoPracownikRequest.getIdOsoby());
        return pracownik;
    }

    @Override
    public DtoPracownikResponse toDto(Pracownik pracownik) {
        DtoPracownikResponse pracownikResponse = new DtoPracownikResponse().builder()
                .idPracownika(pracownik.getIdPracownika())
                .pensja(pracownik.getPensja())
                .Rola(pracownik.getRola())
                .idOsoby(pracownik.getIdOsoby()).build();
        return pracownikResponse;
    }
}
