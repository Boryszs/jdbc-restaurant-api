package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoAddPracownikRequest {

    private Double pensja;
    private String Rola;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
