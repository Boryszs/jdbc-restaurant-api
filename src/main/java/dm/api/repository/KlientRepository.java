package dm.api.repository;

import dm.api.model.Klient;

import java.util.List;
import java.util.Optional;

public interface KlientRepository {

    int count();
    int save (Klient klient);
    int update (Klient klient);
    int deleteById (int id);
    List<Klient> findAll ();
    Optional<Klient> findById(int id);
}
