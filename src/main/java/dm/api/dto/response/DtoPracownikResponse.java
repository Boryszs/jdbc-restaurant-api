package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoPracownikResponse {

    private Integer idPracownika;
    private Double pensja;
    private String Rola;
    private Integer idOsoby;
}
