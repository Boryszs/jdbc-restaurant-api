package dm.api.service;

import dm.api.dto.request.DtoAddKlientRequest;
import dm.api.dto.response.DtoKlientDataResponse;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.model.Klient;

import java.util.List;
import java.util.Optional;

public interface KlientService {
    int count();
    void save (Klient klient);
    void update (Klient klient);
    void deleteById (int id);
    void deleteKlientById (Integer id);
    void add(DtoAddKlientRequest klient);
    List<DtoKlientResponse> findAll();
    List<DtoKlientDataResponse> findAllKlient();
    DtoKlientDataResponse findKlientId(int id);
    Optional<DtoKlientResponse> findById(int id);
}
