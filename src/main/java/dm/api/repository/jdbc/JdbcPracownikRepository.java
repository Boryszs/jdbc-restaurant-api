package dm.api.repository.jdbc;

import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.mapper.PracownikRowListMapper;
import dm.api.mapper.PracownikRowMapper;
import dm.api.model.Pracownik;
import dm.api.repository.PracownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcPracownikRepository implements PracownikRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPracownikRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.pracownik",Integer.class);
    }

    @Override
    public void save(Pracownik pracownik) {
        jdbcTemplate.update("insert into restauracja.pracownik(pensja,rola,id_osoby) values(?,(? :: restauracja.\"Rola\"),?)",pracownik.getPensja(),pracownik.getRola(),pracownik.getIdOsoby());
    }

    @Override
    public void update(Pracownik pracownik) {
         jdbcTemplate.update("update restauracja.pracownik set pensja = ?, rola = (? :: restauracja.\"Rola\") where id_pracownika = ?",pracownik.getPensja(),pracownik.getRola(),pracownik.getIdPracownika());

    }

    @Override
    public void deleteById(Integer id) {
        String SQL = "delete from restauracja.pracownik where id_pracownika = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<DtoPracownikResponse> findAll() {
        return jdbcTemplate.query("select * from restauracja.pracownik ORDER BY id_pracownika",new PracownikRowMapper());
    }

    @Override
    public List<DtoPracownikDataResponse> findAllEmployee() {
        return jdbcTemplate.query("select * from restauracja.pracownik natural join restauracja.osoba natural join restauracja.adres ORDER BY id_pracownika;",new PracownikRowListMapper());

    }

    @Override
    public Optional<Pracownik> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.pracownik where id_pracownika = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Pracownik(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4))));
    }
}
