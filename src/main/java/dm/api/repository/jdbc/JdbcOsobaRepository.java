package dm.api.repository.jdbc;

import dm.api.mapper.OsobaRowMapper;
import dm.api.model.Osoba;
import dm.api.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        final KeyHolder holder = new GeneratedKeyHolder();
        final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator(){

            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                final PreparedStatement preparedStatement = connection.prepareStatement("insert into restauracja.osoba(imie,nazwisko,pesel,data_urodzenia,email,telefon,id_adresu) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,osoba.getImie());
                preparedStatement.setString(2,osoba.getNazwisko());
                preparedStatement.setString(3,osoba.getPesel());
                preparedStatement.setDate(4,new java.sql.Date(osoba.getDataUrodzenia().getTime()));
                preparedStatement.setString(5,osoba.getEmail());
                preparedStatement.setString(6,osoba.getTelefon());
                preparedStatement.setInt(7,osoba.getIdAdresu());
                return preparedStatement;
            }
        };

        jdbcTemplate.update(preparedStatementCreator,holder);
        final Long Id = (Long) holder.getKeys().get("id_osoby");
        return Id.intValue();
    }

    @Override
    public int update(Osoba osoba) {
        return jdbcTemplate.update("update restauracja.osoba set imie = ?,nazwisko = ? ,pesel = ?,data_urodzenia = ?,email = ?,telefon = ?,id_adresu = ? where id_osoby = ?",osoba.getImie(),osoba.getNazwisko(),osoba.getPesel(),osoba.getDataUrodzenia(),osoba.getEmail(),osoba.getTelefon(),osoba.getIdAdresu(),osoba.getIdOsoby());
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "delete from restauracja.osoba where id_osoby = ?";
        jdbcTemplate.update(SQL, id);
        return id;
    }

    @Override
    public List<Osoba> findAll() {
        return jdbcTemplate.query("select * from restauracja.osoba ORDER BY id_osoby",new OsobaRowMapper());
    }

    @Override
    public Optional<Osoba> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.osoba where id_osoby = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Osoba(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getInt(8))));
    }
}
