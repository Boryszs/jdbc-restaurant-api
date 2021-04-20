package dm.api.mapper.impl.row;

import dm.api.dto.response.DtoAddressResponse;
import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.dto.response.DtoCustomerResponse;
import dm.api.dto.response.DtoPersonResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowListMapper implements RowMapper<DtoCustomerDataResponse> {

    @Override
    public DtoCustomerDataResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoCustomerDataResponse dtoKlientResponse = new DtoCustomerDataResponse().builder()
                .customer(new DtoCustomerResponse().builder()
                        .idCustomer(resultSet.getInt("id_klienta"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("haslo"))
                        .idPerson(resultSet.getInt("id_osoby"))
                        .build())
                .person(new DtoPersonResponse().builder()
                        .idPerson(resultSet.getInt("id_osoby"))
                        .name(resultSet.getString("imie"))
                        .surname(resultSet.getString("nazwisko"))
                        .pesel(resultSet.getString("pesel"))
                        .dateBirthday(resultSet.getDate("data_urodzenia"))
                        .email(resultSet.getString("email"))
                        .telephone(resultSet.getString("telefon"))
                        .idAddress(resultSet.getInt("id_adresu")).build())
                .address(new DtoAddressResponse().builder()
                        .idAddress(resultSet.getInt("id_adresu"))
                        .town(resultSet.getString("miejscowosc"))
                        .street(resultSet.getString("ulica"))
                        .nrHome(resultSet.getString("nr_domu"))
                        .postCode(resultSet.getString("kod_pocztowy"))
                        .build()).build();
        return dtoKlientResponse;
    }
}
