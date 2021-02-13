package dm.api.repository.jdbc;

import dm.api.mapper.KlientRowMapper;
import dm.api.model.Klient;
import dm.api.repository.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcKlientRepository implements KlientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKlientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.klient",Integer.class);
    }

    @Override
    public int save(Klient klient) {
        return jdbcTemplate.update("insert into restauracja.klient(login,haslo,id_osoby) value(?,crypt(?, gen_salt('md5'),?)",klient.getLogin(),klient.getHaslo(),klient.getIdOsoby());
    }

    @Override
    public int update(Klient klient) {
        return jdbcTemplate.update("update restauracja.klient set login = ? , haslo = crypt(?, gen_salt('md5')  where id_klienta = ?",klient.getLogin(),klient.getHaslo(),klient.getIdKlienta());
    }

    //TODO NOT WORK !!!!
    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Klient> findAll() {
        return jdbcTemplate.query("select * from restauracja.klient",new KlientRowMapper());
    }

    @Override
    public Optional<Klient> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.klient where id_klienta = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Klient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4))));
    }
}
