package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoUpdateKlientRequest implements Serializable {

    private DtoKlientRequest klient;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
