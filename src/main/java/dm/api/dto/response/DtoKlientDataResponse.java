package dm.api.dto.response;

public class DtoKlientDataResponse {
    private DtoKlientResponse klient;
    private DtoOsobaResponse osoba;
    private DtoAdresResponse adres;

    public DtoKlientDataResponse() {
    }

    public DtoKlientDataResponse(DtoKlientResponse klient, DtoOsobaResponse osoba, DtoAdresResponse adres) {
        this.klient = klient;
        this.osoba = osoba;
        this.adres = adres;
    }

    public DtoKlientResponse getKlient() {
        return klient;
    }

    public void setKlient(DtoKlientResponse klient) {
        this.klient = klient;
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
        return "DtoKlientDataResponse{" +
                "klient=" + klient +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
