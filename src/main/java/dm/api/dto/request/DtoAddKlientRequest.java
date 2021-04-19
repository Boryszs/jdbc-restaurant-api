package dm.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoAddKlientRequest {

    private String login;
    private String haslo;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;
}
