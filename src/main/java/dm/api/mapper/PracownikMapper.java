package dm.api.mapper;

import dm.api.dto.request.DtoPracownikRequest;
import dm.api.dto.response.DtoPracownikResponse;
import dm.api.model.Pracownik;
import org.springframework.stereotype.Component;

@Component
public class PracownikMapper implements Convert<Pracownik, DtoPracownikRequest, DtoPracownikResponse> {

    @Override
    public Pracownik convert(DtoPracownikRequest dtoPracownikRequest) {
        return new Pracownik().builder()
                .pensja(dtoPracownikRequest.getPensja())
                .rola(dtoPracownikRequest.getRola())
                .idOsoby(dtoPracownikRequest.getIdOsoby())
                .build();
    }

    @Override
    public Pracownik update(Pracownik pracownik, DtoPracownikRequest dtoPracownikRequest) {
        pracownik.setPensja(dtoPracownikRequest.getPensja());
        pracownik.setRola(dtoPracownikRequest.getRola());
        pracownik.setIdOsoby(dtoPracownikRequest.getIdOsoby());
        return pracownik;
    }

    @Override
    public DtoPracownikResponse toDto(Pracownik pracownik) {
        return new DtoPracownikResponse().builder()
                .idPracownika(pracownik.getIdPracownika())
                .pensja(pracownik.getPensja())
                .Rola(pracownik.getRola())
                .idOsoby(pracownik.getIdOsoby()).build();
    }
}
