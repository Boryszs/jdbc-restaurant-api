package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Pracownik {

    private Integer idPracownika;
    private Double pensja;
    private String rola;
    private Integer idOsoby;
}
