package dm.api.mapper.impl;

import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.mapper.Convert;
import dm.api.model.Klient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlientRowMapper implements RowMapper<Klient>, Convert<Klient, DtoKlientRequest,DtoKlientResponse> {

    @Override
    public Klient mapRow(ResultSet resultSet, int i) throws SQLException {

        Klient klient = new Klient().builder()
                .idKlienta(resultSet.getInt("id_klienta"))
                .login(resultSet.getString("login"))
                .haslo(resultSet.getString("haslo"))
                .idOsoby(resultSet.getInt("id_osoby"))
                .build();

        return klient;
    }

    @Override
    public Klient convert(DtoKlientRequest klientRequest) {
        Klient klient = new Klient().builder().login(klientRequest.getLogin())
                .haslo(klientRequest.getHaslo())
                .idOsoby(klientRequest.getIdOsoby())
                .build();
        return klient;
    }

    @Override
    public Klient update(Klient klient, DtoKlientRequest dtoKlientRequest) {
        klient.setLogin(dtoKlientRequest.getLogin());
        klient.setHaslo(dtoKlientRequest.getHaslo());
        klient.setIdOsoby(dtoKlientRequest.getIdOsoby());
        return klient;
    }

    @Override
    public DtoKlientResponse toDto(Klient klient) {
        DtoKlientResponse dtoKlientResponse = new DtoKlientResponse().builder()
                .idKlienta(klient.getIdKlienta())
                .login(klient.getLogin())
                .haslo(klient.getHaslo())
                .idOsoby(klient.getIdOsoby())
                .build();
        return dtoKlientResponse;
    }

}
