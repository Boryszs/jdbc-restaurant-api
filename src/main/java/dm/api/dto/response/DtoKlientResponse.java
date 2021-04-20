package dm.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoKlientResponse implements Serializable {

    private Integer idKlienta;
    private String login;
    private String haslo;
    private Integer idOsoby;
}
