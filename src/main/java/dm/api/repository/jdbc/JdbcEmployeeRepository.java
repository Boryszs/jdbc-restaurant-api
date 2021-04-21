package dm.api.repository.jdbc;

import dm.api.dto.response.DtoEmployeeDataResponse;
import dm.api.mapper.impl.row.EmployeeRowListMapper;
import dm.api.mapper.impl.row.EmployeeRowMapper;
import dm.api.model.Employee;
import dm.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.pracownik",Integer.class);
    }

    @Override
    public void save(Employee employee) {
        jdbcTemplate.update("insert into restauracja.pracownik(pensja,rola,id_osoby) values(?,(? :: restauracja.\"Rola\"),?)", employee.getSalary(), employee.getRole(), employee.getPerson().getIdPerson());
    }

    @Override
    public void update(Employee employee) {
         jdbcTemplate.update("update restauracja.pracownik set pensja = ?, rola = (? :: restauracja.\"Rola\") where id_pracownika = ?", employee.getSalary(), employee.getRole(), employee.getIdEmployee());

    }

    @Override
    public void deleteById(Integer id) {
        String SQL = "delete from restauracja.pracownik where id_pracownika = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from restauracja.pracownik ORDER BY id_pracownika",new EmployeeRowMapper());
    }

    @Override
    public List<DtoEmployeeDataResponse> findAllEmployee() {
        return jdbcTemplate.query("select * from restauracja.pracownik natural join restauracja.osoba natural join restauracja.adres ORDER BY id_pracownika;",new EmployeeRowListMapper());
    }

    @Override
    public DtoEmployeeDataResponse findEmployeeById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.pracownik natural join restauracja.osoba natural join restauracja.adres WHERE pracownik.id_pracownika = ?",new Object[]{id},new EmployeeRowListMapper());
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        jdbcTemplate.update("DELETE FROM restauracja.pracownik USING restauracja.adres NATURAL JOIN restauracja.osoba WHERE pracownik.id_pracownika = ?", id);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.pracownik where id_pracownika = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Employee(rs.getInt(1),rs.getDouble(2),rs.getString(3))));
    }
}
