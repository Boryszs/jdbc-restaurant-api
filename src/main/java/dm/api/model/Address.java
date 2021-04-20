package dm.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Address {

    private Integer idAddress;
    private String town;
    private String street;
    private String nrHome;
    private String postCode;
}
