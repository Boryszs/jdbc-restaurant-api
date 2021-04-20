package dm.api.repository.jdbc;

import dm.api.mapper.impl.row.AddressRowMapper;
import dm.api.model.Address;
import dm.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAddressRepository implements AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.adres order by id_adresu ",Integer.class);
    }

    @Override
    public int save(Address address) {
        final KeyHolder holder = new GeneratedKeyHolder();
        final PreparedStatementCreator preparedStatementCreator = connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement("insert into restauracja.adres(miejscowosc,ulica,nr_domu,kod_pocztowy) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getTown());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNrHome());
            preparedStatement.setString(4, address.getPostCode());
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator,holder);
        final Long Id = (Long) holder.getKeys().get("id_adresu");
        return Id.intValue();
    }

    @Override
    public int update(Address address) {
        return jdbcTemplate.update("update restauracja.adres set miejscowosc = ?,ulica = ?,nr_domu = ?,kod_pocztowy = ?  where  id_adresu = ?", address.getTown(), address.getStreet(), address.getNrHome(), address.getPostCode(), address.getIdAddress());
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "delete from restauracja.adres where id_adresu = ?";
        jdbcTemplate.update(SQL, id);
        return id;
    }

    @Override
    public List<Address> findAll() {
        return jdbcTemplate.query("select * from restauracja.adres ORDER BY id_adresu",new AddressRowMapper());
    }

    @Override
    public Optional<Address> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.adres where id_adresu = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Address(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5))));
    }
}
