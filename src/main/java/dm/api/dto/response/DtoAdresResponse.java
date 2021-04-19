package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoAdresResponse {

    private Integer idAdresu;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;
}
