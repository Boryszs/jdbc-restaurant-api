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
public class DtoPracownikResponse implements Serializable {

    private Integer idPracownika;
    private Double pensja;
    private String Rola;
    private Integer idOsoby;
}
