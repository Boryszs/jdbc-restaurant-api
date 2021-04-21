package dm.api.repository.jdbc;

import dm.api.dto.response.DtoCustomerDataResponse;
import dm.api.mapper.impl.row.CustomerRowListMapper;
import dm.api.mapper.impl.row.CustomerRowMapper;
import dm.api.model.Customer;
import dm.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.klient",Integer.class);
    }

    @Override
    public void save(Customer customer) {
         jdbcTemplate.update("insert into restauracja.klient(login,haslo,id_osoby) values(?,crypt(?, gen_salt('md5')),?)", customer.getLogin(), customer.getPassword(), customer.getPerson().getIdPerson());
    }

    @Override
    public void update(Customer customer) {
         jdbcTemplate.update("update restauracja.klient set login = ? , haslo = crypt(?, gen_salt('md5')) where id_klienta = ?", customer.getLogin(), customer.getPassword(), customer.getIdCustomer());
    }

    @Override
    public void deleteById(Integer id) {
        String SQL = "delete from restauracja.klient where id_klienta = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        jdbcTemplate.update("DELETE FROM restauracja.klient USING restauracja.adres NATURAL JOIN restauracja.osoba WHERE klient.id_klienta = ?", id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("select * from restauracja.klient ORDER BY id_klienta",new CustomerRowMapper());
    }

    @Override
    public List<DtoCustomerDataResponse> findAllCustomer() {
        return jdbcTemplate.query("select * from restauracja.klient natural join restauracja.osoba natural join restauracja.adres ORDER BY id_klienta;",new CustomerRowListMapper());

    }

    @Override
    public DtoCustomerDataResponse findCustomerId(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.klient natural join restauracja.osoba natural join restauracja.adres where id_klienta = ?",new Object[]{id},new CustomerRowListMapper());
    }

    @Override
    public Optional<Customer> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.klient where id_klienta = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3))));
    }
}
