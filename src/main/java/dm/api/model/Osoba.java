package dm.api.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Osoba {

    private Integer idOsoby;
    private String imie;
    private String nazwisko;
    private String pesel;
    private Date dataUrodzenia;
    private String email;
    private String telefon;
    private Integer idAdresu;
}
