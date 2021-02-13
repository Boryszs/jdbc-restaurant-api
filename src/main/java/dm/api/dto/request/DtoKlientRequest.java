package dm.api.dto.request;

public class DtoKlientRequest {
    private String login;
    private String haslo;
    private Integer idOsoby;

    public DtoKlientRequest() {
    }

    public DtoKlientRequest(String login, String haslo, Integer idOsoby) {
        this.login = login;
        this.haslo = haslo;
        this.idOsoby = idOsoby;
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
                " login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}
