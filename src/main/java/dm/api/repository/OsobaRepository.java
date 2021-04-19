package dm.api.repository;

import dm.api.dto.response.DtoOsobaResponse;
import dm.api.model.Osoba;

import java.util.List;
import java.util.Optional;

public interface OsobaRepository {

    int count();
    int save (Osoba osoba);
    int update (Osoba osoba);
    int deleteById (Integer id);
    List<DtoOsobaResponse> findAll ();
    Optional<Osoba> findById(int id);
}
