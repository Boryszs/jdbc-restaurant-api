package dm.api.service;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.model.Pracownik;

import java.util.List;
import java.util.Optional;

public interface PracownikService {

    int count();
    int save (Pracownik pracownik);
    int update (Pracownik pracownik);
    int deleteById (int id);
    int add(DtoAddPracownikRequest pracownikRequest);
    List<Pracownik> findAll ();
    Optional<Pracownik> findById(int id);
}
