package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoOsobaRequest {

    private String imie;
    private String nazwisko;
    private String pesel;
    private Date dataUrodzenia;
    private String email;
    private String telefon;
    private Integer idAdresu;
}
