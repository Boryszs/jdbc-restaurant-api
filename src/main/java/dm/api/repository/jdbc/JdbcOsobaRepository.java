package dm.api.repository.jdbc;

import dm.api.mapper.OsobaRowMapper;
import dm.api.model.Osoba;
import dm.api.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOsobaRepository implements OsobaRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOsobaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.osoba",Integer.class);
    }

    @Override
    public int save(Osoba osoba) {
        return jdbcTemplate.update("insert into restauracja.osoba(imie,nazwisko,pesel,data_urodzenia,email,telefon,id_adresu) value(?,?,?,?,?,?,?)",osoba.getImie(),osoba.getNazwisko(),osoba.getPesel(),osoba.getDataUrodzenia(),osoba.getEmail(),osoba.getTelefon(),osoba.getIdAdresu());
    }

    @Override
    public int update(Osoba osoba) {
        return jdbcTemplate.update("update restauracja.osoba set imie = ?,nazwisko = ? ,pesel = ?,data_urodzenia = ?,email = ?,telefon = ?,id_adresu = ? where id_osoby = ?",osoba.getImie(),osoba.getNazwisko(),osoba.getPesel(),osoba.getDataUrodzenia(),osoba.getEmail(),osoba.getTelefon(),osoba.getIdAdresu(),osoba.getIdOsoby());
    }

    //TODO NOT WORK !!!!
    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Osoba> findAll() {
        return jdbcTemplate.query("select * from restauracja.osoba",new OsobaRowMapper());
    }

    @Override
    public Optional<Osoba> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.osoba where id_osoby = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Osoba(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getInt(8))));
    }
}
