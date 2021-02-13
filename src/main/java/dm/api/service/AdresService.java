package dm.api.service;

import dm.api.model.Adres;

import java.util.List;
import java.util.Optional;

public interface AdresService {
    int count();
    int save (Adres adres);
    int update (Adres adres);
    int deleteById (int id);
    List<Adres> findAll ();
    Optional<Adres> findById(int id);
}
