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
public class DtoPracownikDataResponse implements Serializable {

    private DtoPracownikResponse pracownik;
    private DtoOsobaResponse osoba;
    private DtoAdresResponse adres;
}
