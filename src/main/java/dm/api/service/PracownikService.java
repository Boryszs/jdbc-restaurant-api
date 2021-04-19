package dm.api.service;

import dm.api.dto.request.DtoAddPracownikRequest;
import dm.api.dto.request.DtoPracownikRequest;
import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;

import java.util.List;
import java.util.Optional;

public interface PracownikService {

    int count();
    void save (DtoPracownikRequest dtoPracownikRequest);
    void update (Integer id,DtoPracownikRequest dtoPracownikRequest);
    void deleteById (int id);
    void add(DtoAddPracownikRequest pracownikRequest);
    List<DtoPracownikResponse> findAll ();
    List<DtoPracownikDataResponse> findAllPracownik();
    DtoPracownikDataResponse findPracownikById(int id);
    void deletePracownikById(Integer id);
    Optional<Pracownik> findById(int id);
}
