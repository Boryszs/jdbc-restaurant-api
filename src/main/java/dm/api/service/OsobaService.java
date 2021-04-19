package dm.api.service;

import dm.api.dto.request.DtoOsobaRequest;
import dm.api.dto.response.DtoOsobaResponse;

import java.util.List;
import java.util.Optional;

public interface OsobaService {

    int count();
    int save (DtoOsobaRequest dtoOsobaRequest);
    int update (Integer id, DtoOsobaRequest dtoOsobaRequest);
    int deleteById (int id);
    List<DtoOsobaResponse> findAll();
    Optional<DtoOsobaResponse> findById(int id);
}
