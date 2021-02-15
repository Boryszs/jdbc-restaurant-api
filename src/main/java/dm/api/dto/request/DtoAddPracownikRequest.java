package dm.api.dto.request;

public class DtoAddPracownikRequest {
    private Double pensja;
    private String Rola;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;

    public DtoAddPracownikRequest() {
    }

    public DtoAddPracownikRequest(Double pensja, String rola, DtoOsobaRequest osoba, DtoAdresRequest adres) {
        this.pensja = pensja;
        Rola = rola;
        this.osoba = osoba;
        this.adres = adres;
    }

    public Double getPensja() {
        return pensja;
    }

    public void setPensja(Double pensja) {
        this.pensja = pensja;
    }

    public String getRola() {
        return Rola;
    }

    public void setRola(String rola) {
        Rola = rola;
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
        return "DtoAddPracownikRequest{" +
                "pensja=" + pensja +
                ", Rola='" + Rola + '\'' +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
