package dm.api.repository;

import dm.api.dto.response.DtoPracownikDataResponse;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;

import java.util.List;
import java.util.Optional;

public interface PracownikRepository {

    int count();
    void save (Pracownik pracownik);
    void update (Pracownik pracownik);
    void deleteById (Integer id);
    List<DtoPracownikResponse> findAll();
    List<DtoPracownikDataResponse> findAllEmployee();
    Optional<Pracownik> findById(int id);
}
