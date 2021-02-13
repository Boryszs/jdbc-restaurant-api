package dm.api.dto.response;

public class DtoKlientResponse {
    private Integer idKlienta;
    private String login;
    private String haslo;
    private Integer idOsoby;

    public DtoKlientResponse() {
    }

    public DtoKlientResponse(Integer idKlienta, String login, String haslo, Integer idOsoby) {
        this.idKlienta = idKlienta;
        this.login = login;
        this.haslo = haslo;
        this.idOsoby = idOsoby;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
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

    public Integer getIdOsoby() {
        return idOsoby;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    @Override
    public String toString() {
        return "DtoKlientResponse{" +
                "idKlienta=" + idKlienta +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}
