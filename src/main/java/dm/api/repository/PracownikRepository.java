package dm.api.repository;

import dm.api.model.Pracownik;

import java.util.List;
import java.util.Optional;

public interface PracownikRepository {

    int count();
    int save (Pracownik pracownik);
    int update (Pracownik pracownik);
    int deleteById (Integer id);
    List<Pracownik> findAll ();
    Optional<Pracownik> findById(int id);
}
