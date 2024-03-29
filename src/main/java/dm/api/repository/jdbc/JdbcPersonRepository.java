package dm.api.repository.jdbc;

import dm.api.mapper.impl.row.PersonRowMapper;
import dm.api.model.Person;
import dm.api.repository.PersonRepository;
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
public class JdbcPersonRepository implements PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.osoba",Integer.class);
    }

    @Override
    public int save(Person person) {
        final KeyHolder holder = new GeneratedKeyHolder();
        final PreparedStatementCreator preparedStatementCreator = connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement("insert into restauracja.osoba(imie,nazwisko,pesel,data_urodzenia,email,telefon,id_adresu) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getPesel());
            preparedStatement.setDate(4,new java.sql.Date(person.getDateBirthday().getTime()));
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.setString(6, person.getTelephone());
            preparedStatement.setInt(7, person.getAddress().getIdAddress());
            return preparedStatement;
        };

        jdbcTemplate.update(preparedStatementCreator,holder);
        final Long Id = (Long) holder.getKeys().get("id_osoby");
        return Id.intValue();
    }

    @Override
    public int update(Person person) {
        return jdbcTemplate.update("update restauracja.osoba set imie = ?,nazwisko = ? ,pesel = ?,data_urodzenia = ?,email = ?,telefon = ?,id_adresu = ? where id_osoby = ?", person.getName(), person.getSurname(), person.getPesel(), person.getDateBirthday(), person.getEmail(), person.getTelephone(), person.getAddress().getIdAddress(), person.getIdPerson());
    }

    @Override
    public int deleteById(Integer id) {
        String SQL = "delete from restauracja.osoba where id_osoby = ?";
        jdbcTemplate.update(SQL, id);
        return id;
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from restauracja.osoba ORDER BY id_osoby",new PersonRowMapper());
    }

    @Override
    public Optional<Person> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.osoba where id_osoby = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7))));
    }
}
