package dm.api.mapper.impl.row;

import dm.api.dto.request.DtoAddressRequest;
import dm.api.dto.response.DtoAddressResponse;
import dm.api.mapper.Convert;
import dm.api.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Convert<Address, DtoAddressRequest, DtoAddressResponse> {

    @Override
    public Address convert(DtoAddressRequest adresRequest) {
        return new Address().builder()
                .town(adresRequest.getTown())
                .street(adresRequest.getStreet())
                .nrHome(adresRequest.getNrHome())
                .postCode(adresRequest.getPostCode())
                .build();
    }

    @Override
    public Address update(Address address, DtoAddressRequest dtoAddressRequest) {
        address.setTown(dtoAddressRequest.getTown());
        address.setNrHome(dtoAddressRequest.getNrHome());
        address.setStreet(dtoAddressRequest.getStreet());
        address.setPostCode(dtoAddressRequest.getPostCode());
        return address;
    }

    @Override
    public DtoAddressResponse toDto(Address address) {
        return new DtoAddressResponse().builder()
                 .idAddress(address.getIdAddress())
                 .town(address.getTown())
                 .street(address.getStreet())
                 .nrHome(address.getNrHome())
                 .postCode(address.getPostCode())
                 .build();
    }

}
