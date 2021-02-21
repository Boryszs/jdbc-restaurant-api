package dm.api.dto.request;

public class DtoUpdatePracownikRequest {
    private DtoPracownikRequest pracownik;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;

    public DtoUpdatePracownikRequest() {
    }

    public DtoUpdatePracownikRequest(DtoPracownikRequest pracownik, DtoOsobaRequest osoba, DtoAdresRequest adres) {
        this.pracownik = pracownik;
        this.osoba = osoba;
        this.adres = adres;
    }

    public DtoPracownikRequest getPracownik() {
        return pracownik;
    }

    public void setPracownik(DtoPracownikRequest pracownik) {
        this.pracownik = pracownik;
    }

    public DtoOsobaRequest getOsoba() {
        return osoba;
    }

    public void setOsoba(DtoOsobaRequest osoba) {
        this.osoba = osoba;
    }

    public DtoAdresRequest getAdres() {
        return adres;
    }

    public void setAdres(DtoAdresRequest adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "DtoUpdatePracownikRequest{" +
                "pracownik=" + pracownik +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
