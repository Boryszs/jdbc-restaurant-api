package dm.api.service;

import dm.api.dto.request.DtoPersonRequest;
import dm.api.dto.response.DtoPersonResponse;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    int count();
    int save (DtoPersonRequest dtoPersonRequest);
    int update (Integer id, DtoPersonRequest dtoPersonRequest);
    int deleteById (int id);
    List<DtoPersonResponse> findAll();
    Optional<DtoPersonResponse> findById(int id);
}
