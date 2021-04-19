package dm.api.mapper.impl;

import dm.api.dto.request.DtoOsobaRequest;
import dm.api.dto.response.DtoOsobaResponse;
import dm.api.mapper.Convert;
import dm.api.model.Osoba;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaRowMapper implements RowMapper<Osoba>, Convert<Osoba, DtoOsobaRequest, DtoOsobaResponse> {

    @Override
    public Osoba mapRow(ResultSet resultSet, int i) throws SQLException {

        Osoba osoba = new Osoba().builder()
                .idOsoby(resultSet.getInt("id_osoby"))
                .imie(resultSet.getString("imie"))
                .nazwisko(resultSet.getString("nazwisko"))
                .pesel(resultSet.getString("pesel"))
                .dataUrodzenia(resultSet.getDate("data_urodzenia"))
                .email(resultSet.getString("email"))
                .telefon(resultSet.getString("telefon"))
                .idAdresu(resultSet.getInt("id_adresu"))
                .build();

        return osoba;
    }

    @Override
    public Osoba convert(DtoOsobaRequest osobaRequest) {
        Osoba osoba = new Osoba().builder()
                .imie(osobaRequest.getImie())
                .nazwisko(osobaRequest.getNazwisko())
                .pesel(osobaRequest.getPesel())
                .dataUrodzenia(osobaRequest.getDataUrodzenia())
                .email(osobaRequest.getEmail())
                .telefon(osobaRequest.getTelefon())
                .idAdresu(osobaRequest.getIdAdresu())
                .build();

        return osoba;
    }

    @Override
    public Osoba update(Osoba osoba, DtoOsobaRequest osobaRequest) {
        osoba.setImie(osobaRequest.getImie());
        osoba.setNazwisko(osobaRequest.getNazwisko());
        osoba.setPesel(osobaRequest.getPesel());
        osoba.setDataUrodzenia(osobaRequest.getDataUrodzenia());
        osoba.setEmail(osobaRequest.getEmail());
        osoba.setTelefon(osobaRequest.getTelefon());
        osoba.setIdAdresu(osobaRequest.getIdAdresu());

        return osoba;

    }

    @Override
    public DtoOsobaResponse toDto(Osoba osoba) {
        DtoOsobaResponse dtoOsobaResponse = new DtoOsobaResponse().builder()
                .idOsoby(osoba.getIdOsoby())
                .imie(osoba.getImie())
                .nazwisko(osoba.getNazwisko())
                .pesel(osoba.getPesel())
                .dataUrodzenia(osoba.getDataUrodzenia())
                .email(osoba.getEmail())
                .telefon(osoba.getTelefon())
                .idAdresu(osoba.getIdAdresu())
                .build();
        return dtoOsobaResponse;
    }
}
