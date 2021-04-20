package dm.api.mapper;

import dm.api.dto.request.DtoOsobaRequest;
import dm.api.dto.response.DtoOsobaResponse;
import dm.api.model.Osoba;
import org.springframework.stereotype.Component;

@Component
public class OsobaMapper implements Convert<Osoba, DtoOsobaRequest, DtoOsobaResponse> {

    @Override
    public Osoba convert(DtoOsobaRequest osobaRequest) {

        return new Osoba().builder()
                .imie(osobaRequest.getImie())
                .nazwisko(osobaRequest.getNazwisko())
                .pesel(osobaRequest.getPesel())
                .dataUrodzenia(osobaRequest.getDataUrodzenia())
                .email(osobaRequest.getEmail())
                .telefon(osobaRequest.getTelefon())
                .idAdresu(osobaRequest.getIdAdresu())
                .build();
    }

    @Override
    public Osoba update(Osoba osoba, DtoOsobaRequest osobaRequest) {
        osoba.setImie(osobaRequest.getImie());
        osoba.setNazwisko(osobaRequest.getNazwisko());
        osoba.setPesel(osobaRequest.getPesel());
        osoba.setDataUrodzenia(osobaRequest.getDataUrodzenia());
        osoba.setEmail(osobaRequest.getEmail());
        osoba.setTelefon(osobaRequest.getTelefon());
        osoba.setIdAdresu(osobaRequest.getIdAdresu());

        return osoba;

    }

    @Override
    public DtoOsobaResponse toDto(Osoba osoba) {
        return new DtoOsobaResponse().builder()
                .idOsoby(osoba.getIdOsoby())
                .imie(osoba.getImie())
                .nazwisko(osoba.getNazwisko())
                .pesel(osoba.getPesel())
                .dataUrodzenia(osoba.getDataUrodzenia())
                .email(osoba.getEmail())
                .telefon(osoba.getTelefon())
                .idAdresu(osoba.getIdAdresu())
                .build();
    }
}
