package dm.api.dto.request;

public class DtoAddKlientRequest {
    private String login;
    private String haslo;
    private DtoOsobaRequest osoba;
    private DtoAdresRequest adres;

    public DtoAddKlientRequest() {
    }

    public DtoAddKlientRequest(String login, String haslo, DtoOsobaRequest osoba, DtoAdresRequest adres) {
        this.login = login;
        this.haslo = haslo;
        this.osoba = osoba;
        this.adres = adres;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
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
        return "DtoAddKlientRequest{" +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", osoba=" + osoba +
                ", adres=" + adres +
                '}';
    }
}
