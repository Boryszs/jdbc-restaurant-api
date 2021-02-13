package dm.api.repository.jdbc;

import dm.api.mapper.AdresRowMapper;
import dm.api.model.Adres;
import dm.api.repository.AdresRepository;
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
public class JdbcAdresRepository implements AdresRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdresRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from restauracja.adres",Integer.class);
    }

    @Override
    public int save(Adres adres) {
        final KeyHolder holder = new GeneratedKeyHolder();
        final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                final PreparedStatement preparedStatement = connection.prepareStatement("insert into restauracja.adres(miejscowosc,ulica,nr_domu,kod_pocztowy) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,adres.getMiejscowosc());
                preparedStatement.setString(2,adres.getUlica());
                preparedStatement.setString(3,adres.getNrDomu());
                preparedStatement.setString(4,adres.getKodPocztowy());
                return preparedStatement;
            }
        };

        jdbcTemplate.update(preparedStatementCreator,holder);
        final Long Id = (Long) holder.getKeys().get("id_adresu");
        return Id.intValue();
    }

    @Override
    public int update(Adres adres) {
        return jdbcTemplate.update("update restauracja.adres set miejscowosc = ?,ulica = ?,nr_domu = ?,kod_pocztowy = ?  where  id_adresu = ?",adres.getMiejscowosc(),adres.getUlica(),adres.getNrDomu(),adres.getKodPocztowy(),adres.getIdAdresu());
    }

    //TODO NOT WORK !!!!
    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public List<Adres> findAll() {
        return jdbcTemplate.query("select * from restauracja.adres",new AdresRowMapper());
    }

    @Override
    public Optional<Adres> findById(int id) {
        return jdbcTemplate.queryForObject("select * from restauracja.adres where id_adresu = ?",new Object[]{id},(rs,rowNum) -> Optional.of(new Adres(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5))));
    }
}
