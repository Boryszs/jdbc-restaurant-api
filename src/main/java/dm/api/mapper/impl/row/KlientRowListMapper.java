package dm.api.mapper.impl.row;

import dm.api.dto.response.DtoAdresResponse;
import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.dto.response.DtoOsobaResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientRowListMapper implements RowMapper<DtoKlientDataResponse> {

    @Override
    public DtoKlientDataResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoKlientDataResponse dtoKlientResponse = new DtoKlientDataResponse().builder()
                .klient(new DtoKlientResponse().builder()
                        .idKlienta(resultSet.getInt("id_klienta"))
                        .login(resultSet.getString("login"))
                        .haslo(resultSet.getString("haslo"))
                        .idOsoby(resultSet.getInt("id_osoby"))
                        .build())
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
                        .build()).build();
        return dtoKlientResponse;
    }
}
