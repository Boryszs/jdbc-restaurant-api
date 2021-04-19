package dm.api.repository;

import dm.api.dto.response.DtoAdresResponse;
import dm.api.model.Adres;

import java.util.List;
import java.util.Optional;

public interface AdresRepository {

    int count();
    int save (Adres adres);
    int update (Adres adres);
    int deleteById (Integer id);
    List<DtoAdresResponse> findAll ();
    Optional<DtoAdresResponse> findById(int id);
}
