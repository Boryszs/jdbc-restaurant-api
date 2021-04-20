package dm.api.dto.request;

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
public class DtoPersonRequest implements Serializable {

    @JsonProperty("imie")
    private String name;
    @JsonProperty("nazwisko")
    private String surname;
    @JsonProperty("pesel")
    private String pesel;
    @JsonProperty("dataUrodzenia")
    private Date dateBirthday;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefon")
    private String telephone;
    @JsonProperty("idAdresu")
    private Integer idPerson;
}
