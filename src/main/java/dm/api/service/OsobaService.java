package dm.api.service;

import dm.api.dto.response.DtoOsobaResponse;
import dm.api.model.Osoba;

import java.util.List;
import java.util.Optional;

public interface OsobaService {

    int count();
    int save (Osoba osoba);
    int update (Osoba osoba);
    int deleteById (int id);
    List<DtoOsobaResponse> findAll();
    Optional<Osoba> findById(int id);
}
