package dm.api.repository;

import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.model.Klient;

import java.util.List;
import java.util.Optional;

public interface KlientRepository {

    int count();
    void save (Klient klient);
    void update (Klient klient);
    void deleteById (Integer id);
    void deleteKlientById (Integer id);
    List<DtoKlientResponse> findAll();
    List<DtoKlientDataResponse> findAllKlient();
    DtoKlientDataResponse findKlientId(int id);
    Optional<DtoKlientResponse> findById(int id);
}
