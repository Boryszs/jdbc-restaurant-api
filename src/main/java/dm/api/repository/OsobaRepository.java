package dm.api.repository;

import dm.api.model.Osoba;

import java.util.List;
import java.util.Optional;

public interface OsobaRepository {

    int count();
    int save (Osoba osoba);
    int update (Osoba osoba);
    int deleteById (int id);
    List<Osoba> findAll ();
    Optional<Osoba> findById(int id);
}
