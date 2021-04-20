package dm.api.service.impl;

import dm.api.dto.request.DtoAddressRequest;
import dm.api.dto.response.DtoAddressResponse;
import dm.api.mapper.Convert;
import dm.api.model.Address;
import dm.api.repository.AddressRepository;
import dm.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final Convert<Address, DtoAddressRequest, DtoAddressResponse> addressMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, Convert<Address, DtoAddressRequest, DtoAddressResponse> addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public int count() {
        return addressRepository.count();
    }

    @Override
    public int save(DtoAddressRequest dtoAddressRequest) {
        return addressRepository.save(addressMapper.convert(dtoAddressRequest));
    }

    @Override
    public int update(Integer id, DtoAddressRequest dtoAddressRequest) {
        return addressRepository.update(addressMapper.update(addressRepository.findById(id).get(), dtoAddressRequest));
    }

    @Override
    public int deleteById(int id) {
        return addressRepository.deleteById(id);
    }

    @Override
    public List<DtoAddressResponse> findAll() {
        List<DtoAddressResponse> addressResponseList = new LinkedList<>();
        addressRepository.findAll().stream().map(address -> addressMapper.toDto(address)).forEach(addressResponseList::add);
        return addressResponseList;
    }

    @Override
    public Optional<DtoAddressResponse> findById(int id) {
        return Optional.of(addressMapper.toDto(addressRepository.findById(id).get()));
    }
}
