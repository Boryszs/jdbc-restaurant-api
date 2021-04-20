package dm.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoAddressResponse implements Serializable {

    @JsonProperty("idAdresu")
    private Integer idAddress;
    @JsonProperty("miejscowosc")
    private String town;
    @JsonProperty("ulica")
    private String street;
    @JsonProperty("nrDomu")
    private String nrHome;
    @JsonProperty("kodPocztowy")
    private String postCode;
}
