package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoUpdateKlientRequest {

    private DtoKlientRequest klient;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
