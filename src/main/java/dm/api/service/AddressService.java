package dm.api.service;

import dm.api.dto.request.DtoAddressRequest;
import dm.api.dto.response.DtoAddressResponse;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    int count();
    int save (DtoAddressRequest dtoAddressRequest);
    int update (Integer id, DtoAddressRequest dtoAddressRequest);
    int deleteById (int id);
    List<DtoAddressResponse> findAll ();
    Optional<DtoAddressResponse> findById(int id);
}
