package dm.api.mapper;

import dm.api.dto.response.DtoPracownikResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikRowMapper implements RowMapper<DtoPracownikResponse>{
    @Override
    public DtoPracownikResponse mapRow(ResultSet resultSet, int i) throws SQLException {
        DtoPracownikResponse pracownikResponse = new DtoPracownikResponse().builder()
                .idPracownika(resultSet.getInt("id_pracownika"))
                .pensja(resultSet.getDouble("pensja"))
                .Rola(resultSet.getString("rola"))
                .idOsoby(resultSet.getInt("id_osoby")).build();
        return pracownikResponse;
    }
}
