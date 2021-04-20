package dm.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoPersonResponse implements Serializable {

    @JsonProperty("idOsoby")
    private Integer idPerson;
    @JsonProperty("imie")
    private String name;
    @JsonProperty("nazwisko")
    private String surname;
    private String pesel;
    @JsonProperty("dataUrodzenia")
    private Date dateBirthday;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefon")
    private String telephone;
    @JsonProperty("idAdresu")
    private Integer idAddress;
}
