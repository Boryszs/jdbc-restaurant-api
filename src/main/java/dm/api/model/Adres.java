package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Adres {

    private Integer idAdresu;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;
}
