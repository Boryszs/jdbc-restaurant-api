package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Klient {

    private Integer idKlienta;
    private String login;
    private String haslo;
    private Integer idOsoby;
}
