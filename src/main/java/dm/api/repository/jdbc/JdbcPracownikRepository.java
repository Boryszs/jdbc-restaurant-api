package dm.api.repository.jdbc;

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
    public int save(Pracownik pracownik) {
        return jdbcTemplate.update("insert into restauracja.pracownik(pensja,rola,id_osoby) values(?,(? :: restauracja.\"Rola\"),?)",pracownik.getPensja(),pracownik.getRola(),pracownik.getIdOsoby());
//
//        final KeyHolder holder = new GeneratedKeyHolder();
//        final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator(){
//
//            @Override
//            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
//                final PreparedStatement preparedStatement = connection.prepareStatement("insert into restauracja.pracownik(pensja,rola,id_osoby) values(?,(? :: restauracja.\"Rola\"),?)", Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setDouble(1,pracownik.getPensja());
//                preparedStatement.setString(2,pracownik.getRola());
//                preparedStatement.setInt(3,pracownik.getIdPracownika());
//                return preparedStatement;
//            }
//        };

        //jdbcTemplate.update(preparedStatementCreator,holder);
        //final Long Id = (Long) holder.getKeys().get("id_pracownika");
        //return Id.intValue();
    }

    @Override
    public int update(Pracownik pracownik) {
        return jdbcTemplate.update("update restauracja.pracownik set pensja = ?, rola = (? :: restauracja.\"Rola\") where id_pracownika = ?",pracownik.getPensja(),pracownik.getRola(),pracownik.getIdPracownika());

    }

    //TODO NOT WORK !!!!
    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Pracownik> findAll() {
        return jdbcTemplate.query("select * from restauracja.pracownik ORDER BY id_pracownika",new PracownikRowMapper());
    }

    @Override
    public Optional<Pracownik> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.pracownik where id_pracownika = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Pracownik(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getInt(4))));
    }
}
