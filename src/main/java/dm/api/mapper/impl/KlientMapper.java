package dm.api.mapper.impl;

import dm.api.dto.request.DtoKlientRequest;
import dm.api.dto.response.DtoKlientResponse;
import dm.api.mapper.Convert;
import dm.api.model.Klient;
import org.springframework.stereotype.Component;

@Component
public class KlientMapper implements Convert<Klient, DtoKlientRequest,DtoKlientResponse> {

    @Override
    public Klient convert(DtoKlientRequest klientRequest) {
        return new Klient().builder().login(klientRequest.getLogin())
                .haslo(klientRequest.getHaslo())
                .idOsoby(klientRequest.getIdOsoby())
                .build();
    }

    @Override
    public Klient update(Klient klient, DtoKlientRequest dtoKlientRequest) {
        klient.setLogin(dtoKlientRequest.getLogin());
        klient.setHaslo(dtoKlientRequest.getHaslo());
        klient.setIdOsoby(dtoKlientRequest.getIdOsoby());
        return klient;
    }

    @Override
    public DtoKlientResponse toDto(Klient klient) {
        return new DtoKlientResponse().builder()
                .idKlienta(klient.getIdKlienta())
                .login(klient.getLogin())
                .haslo(klient.getHaslo())
                .idOsoby(klient.getIdOsoby())
                .build();
    }

}
