package dm.api.mapper.impl.row;

import dm.api.dto.response.DtoAddressResponse;
import dm.api.dto.response.DtoPersonResponse;
import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.dto.response.DtoEmployeeResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowListMapper implements RowMapper<DtoEmployeeDataResponse> {
    @Override
    public DtoEmployeeDataResponse mapRow(ResultSet resultSet, int i) throws SQLException {

        DtoEmployeeDataResponse pracownikDataResponse = new DtoEmployeeDataResponse().builder()
                .employee(new DtoEmployeeResponse().builder()
                        .idEmployee(resultSet.getInt("id_pracownika"))
                        .salary(resultSet.getDouble("pensja"))
                        .role(resultSet.getString("rola"))
                        .idPerson(resultSet.getInt("id_osoby")).build())
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
                        .build())
                .build();
        return pracownikDataResponse;
    }
}
