package dm.api.mapper.impl.row;

import dm.api.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address>{
    @Override
    public Address mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Address().builder()
                .idAddress(resultSet.getInt("id_adresu"))
                .town(resultSet.getString("miejscowosc"))
                .street(resultSet.getString("ulica"))
                .nrHome(resultSet.getString("nr_domu"))
                .postCode(resultSet.getString("kod_pocztowy"))
                .build();
    }
}
