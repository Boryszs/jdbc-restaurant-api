package dm.api.repository.jdbc;

import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.mapper.KlientRowListMapper;
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
    public void save(Klient klient) {
         jdbcTemplate.update("insert into restauracja.klient(login,haslo,id_osoby) values(?,crypt(?, gen_salt('md5')),?)",klient.getLogin(),klient.getHaslo(),klient.getIdOsoby());
    }

    @Override
    public void update(Klient klient) {
         jdbcTemplate.update("update restauracja.klient set login = ? , haslo = crypt(?, gen_salt('md5')) where id_klienta = ?",klient.getLogin(),klient.getHaslo(),klient.getIdKlienta());
    }

    @Override
    public void deleteById(Integer id) {
        String SQL = "delete from restauracja.klient where id_klienta = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public void deleteKlientById(Integer id) {
        jdbcTemplate.update("DELETE FROM restauracja.klientUSING restauracja.adres NATURAL JOIN restauracja.osoba WHERE klient.id_klienta = ?", id);
    }

    @Override
    public List<DtoKlientResponse> findAll() {
        return jdbcTemplate.query("select * from restauracja.klient ORDER BY id_klienta",new KlientRowMapper());
    }

    @Override
    public List<DtoKlientDataResponse> findAllKlient() {
        return jdbcTemplate.query("select * from restauracja.klient natural join restauracja.osoba natural join restauracja.adres ORDER BY id_klienta;",new KlientRowListMapper());

    }

    @Override
    public DtoKlientDataResponse findKlientId(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.klient natural join restauracja.osoba natural join restauracja.adres where id_klienta = ?",new Object[]{id},new KlientRowListMapper());

    }

    @Override
    public Optional<DtoKlientResponse> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.klient where id_klienta = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new DtoKlientResponse(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4))));
    }
}
