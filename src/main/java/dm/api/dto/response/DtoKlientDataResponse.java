package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoKlientDataResponse {

    private DtoKlientResponse klient;
    private DtoOsobaResponse osoba;
    private DtoAdresResponse adres;
}
