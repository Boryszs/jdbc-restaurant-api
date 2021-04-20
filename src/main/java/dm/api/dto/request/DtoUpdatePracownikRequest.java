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
public class DtoUpdatePracownikRequest implements Serializable {

    private DtoPracownikRequest pracownik;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
