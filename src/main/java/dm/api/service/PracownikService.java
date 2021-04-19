package dm.api.service;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;

import java.util.List;
import java.util.Optional;

public interface PracownikService {

    int count();
    void save (Pracownik pracownik);
    void update (Pracownik pracownik);
    void deleteById (int id);
    void add(DtoAddPracownikRequest pracownikRequest);
    List<DtoPracownikResponse> findAll ();
    List<DtoPracownikDataResponse> findAllEmployee();
    Optional<Pracownik> findById(int id);
}
