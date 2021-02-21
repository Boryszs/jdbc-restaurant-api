package dm.api.dto.request;

public class DtoUpdateKlientRequest {
    private DtoKlientRequest klient;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;

    public DtoUpdateKlientRequest() {
    }

    public DtoUpdateKlientRequest(DtoKlientRequest klient, DtoOsobaRequest osoba, DtoAdresRequest adres) {
        this.klient = klient;
        this.osoba = osoba;
        this.adres = adres;
    }

    public DtoKlientRequest getKlient() {
        return klient;
    }

    public void setKlient(DtoKlientRequest klient) {
        this.klient = klient;
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
        return "DtoUpdateKlientRequest{" +
                "klientRequest=" + klient +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
