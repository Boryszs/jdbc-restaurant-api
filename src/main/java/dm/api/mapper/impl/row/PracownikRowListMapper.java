package dm.api.mapper.impl.row;

import dm.api.dto.response.DtoAdresResponse;
import dm.api.dto.response.DtoOsobaResponse;
import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowListMapper implements RowMapper<DtoPracownikDataResponse> {
    @Override
    public DtoPracownikDataResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoPracownikDataResponse pracownikDataResponse = new DtoPracownikDataResponse().builder()
                .pracownik(new DtoPracownikResponse().builder()
                        .idPracownika(resultSet.getInt("id_pracownika"))
                        .pensja(resultSet.getDouble("pensja"))
                        .Rola(resultSet.getString("rola"))
                        .idOsoby(resultSet.getInt("id_osoby")).build())
                .osoba(new DtoOsobaResponse().builder()
                        .idOsoby(resultSet.getInt("id_osoby"))
                        .imie(resultSet.getString("imie"))
                        .nazwisko(resultSet.getString("nazwisko"))
                        .pesel(resultSet.getString("pesel"))
                        .dataUrodzenia(resultSet.getDate("data_urodzenia"))
                        .email(resultSet.getString("email"))
                        .telefon(resultSet.getString("telefon"))
                        .idAdresu(resultSet.getInt("id_adresu")).build())
                .adres(new DtoAdresResponse().builder()
                        .idAdresu(resultSet.getInt("id_adresu"))
                        .miejscowosc(resultSet.getString("miejscowosc"))
                        .ulica(resultSet.getString("ulica"))
                        .nrDomu(resultSet.getString("nr_domu"))
                        .kodPocztowy(resultSet.getString("kod_pocztowy"))
                        .build())
                .build();
        return pracownikDataResponse;
    }
}
