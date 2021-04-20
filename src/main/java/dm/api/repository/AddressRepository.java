package dm.api.repository;

import dm.api.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    int count();
    int save (Address address);
    int update (Address address);
    int deleteById (Integer id);
    List<Address> findAll ();
    Optional<Address> findById(int id);
}
