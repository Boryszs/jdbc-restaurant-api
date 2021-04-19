package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoAdresRequest {

    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;
}
