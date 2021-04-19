package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoKlientResponse {

    private Integer idKlienta;
    private String login;
    private String haslo;
    private Integer idOsoby;
}
