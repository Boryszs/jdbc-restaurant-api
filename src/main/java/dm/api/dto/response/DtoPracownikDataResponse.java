package dm.api.dto.response;

public class DtoPracownikDataResponse {
    private DtoPracownikResponse pracownik;
    private DtoOsobaResponse osoba;
    private DtoAdresResponse adres;

    public DtoPracownikDataResponse() {
    }

    public DtoPracownikDataResponse(DtoPracownikResponse pracownik, DtoOsobaResponse osoba, DtoAdresResponse adres) {
        this.pracownik = pracownik;
        this.osoba = osoba;
        this.adres = adres;
    }

    public DtoPracownikResponse getPracownik() {
        return pracownik;
    }

    public void setPracownik(DtoPracownikResponse pracownik) {
        this.pracownik = pracownik;
    }

    public DtoOsobaResponse getOsoba() {
        return osoba;
    }

    public void setOsoba(DtoOsobaResponse osoba) {
        this.osoba = osoba;
    }

    public DtoAdresResponse getAdres() {
        return adres;
    }

    public void setAdres(DtoAdresResponse adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "DtoPracownikDataResponse{" +
                "pracownik=" + pracownik +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
