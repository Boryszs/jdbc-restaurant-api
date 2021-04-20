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
public class DtoCustomerDataResponse implements Serializable {

    @JsonProperty("klient")
    private DtoCustomerResponse customer;
    @JsonProperty("osoba")
    private DtoPersonResponse person;
    @JsonProperty("adres")
    private DtoAddressResponse address;
}
