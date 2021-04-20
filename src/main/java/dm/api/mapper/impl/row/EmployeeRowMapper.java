package dm.api.mapper.impl.row;

import dm.api.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee>{
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee().builder()
                .idEmployee(resultSet.getInt("id_pracownika"))
                .salary(resultSet.getDouble("pensja"))
                .role(resultSet.getString("rola"))
                .idPerson(resultSet.getInt("id_osoby"))
                .build();
    }
}
