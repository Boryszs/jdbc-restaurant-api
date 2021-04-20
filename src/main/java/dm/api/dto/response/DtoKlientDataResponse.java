package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoKlientDataResponse implements Serializable {

    private DtoKlientResponse klient;
    private DtoOsobaResponse osoba;
    private DtoAdresResponse adres;
}
