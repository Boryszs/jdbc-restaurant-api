package dm.api.service;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;

import java.util.List;
import java.util.Optional;

public interface AdresService {
    int count();
    int save (DtoAdresRequest dtoAdresRequest);
    int update (Integer id,DtoAdresRequest dtoAdresRequest);
    int deleteById (int id);
    List<DtoAdresResponse> findAll ();
    Optional<DtoAdresResponse> findById(int id);
}
