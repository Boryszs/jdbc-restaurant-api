package dm.api.dto.request;

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
public class DtoAddEmployeeRequest implements Serializable {

    @JsonProperty("pensja")
    private Double salary;
    @JsonProperty("Rola")
    private String role;
    @JsonProperty("osoba")
    private DtoPersonRequest person;
    @JsonProperty("adres")
    private DtoAddressRequest address;
}
