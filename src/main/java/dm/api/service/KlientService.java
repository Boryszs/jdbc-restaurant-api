package dm.api.service;

import dm.api.model.Klient;

import java.util.List;
import java.util.Optional;

public interface KlientService {
    int count();
    int save (Klient klient);
    int update (Klient klient);
    int deleteById (int id);
    List<Klient> findAll ();
    Optional<Klient> finById(int id);
}
