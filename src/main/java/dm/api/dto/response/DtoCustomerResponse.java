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
public class DtoCustomerResponse implements Serializable {

    @JsonProperty("idKlienta")
    private Integer idCustomer;
    private String login;
    @JsonProperty("haslo")
    private String password;
    @JsonProperty("idOsoby")
    private Integer idPerson;
}
