package dm.api.mapper.impl.row;

import dm.api.dto.request.DtoAdresRequest;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.mapper.Convert;
import dm.api.model.Adres;
import org.springframework.stereotype.Component;

@Component
public class AdresMapper implements Convert<Adres, DtoAdresRequest, DtoAdresResponse> {

    @Override
    public Adres convert(DtoAdresRequest adresRequest) {
        return new Adres().builder()
                .miejscowosc(adresRequest.getMiejscowosc())
                .ulica(adresRequest.getUlica())
                .nrDomu(adresRequest.getNrDomu())
                .kodPocztowy(adresRequest.getKodPocztowy())
                .build();
    }

    @Override
    public Adres update(Adres adres, DtoAdresRequest dtoAdresRequest) {
        adres.setMiejscowosc(dtoAdresRequest.getMiejscowosc());
        adres.setNrDomu(dtoAdresRequest.getNrDomu());
        adres.setUlica(dtoAdresRequest.getUlica());
        adres.setKodPocztowy(dtoAdresRequest.getKodPocztowy());
        return adres;
    }

    @Override
    public DtoAdresResponse toDto(Adres adres) {
        return new DtoAdresResponse().builder()
                 .idAdresu(adres.getIdAdresu())
                 .miejscowosc(adres.getMiejscowosc())
                 .ulica(adres.getUlica())
                 .nrDomu(adres.getNrDomu())
                 .kodPocztowy(adres.getKodPocztowy())
                 .build();
    }

}
