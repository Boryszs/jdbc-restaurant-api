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
public class DtoAddCustomerRequest implements Serializable {

    private String login;
    @JsonProperty("haslo")
    private String password;
    @JsonProperty("osoba")
    private DtoPersonRequest person;
    @JsonProperty("adres")
    private DtoAddressRequest address;
}
