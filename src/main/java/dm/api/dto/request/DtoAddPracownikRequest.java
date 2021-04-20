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
public class DtoAddPracownikRequest implements Serializable {

    private Double pensja;
    private String Rola;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
