package dm.api.model;

public class Klient {
    private Integer idKlienta;
    private String login;
    private String haslo;
    private Integer idOsoby;

    public Klient() {
    }

    public Klient(Integer idKlienta, String login, String haslo, Integer idOsoby) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klient klient = (Klient) o;

        if (idKlienta != null ? !idKlienta.equals(klient.idKlienta) : klient.idKlienta != null) return false;
        if (login != null ? !login.equals(klient.login) : klient.login != null) return false;
        if (haslo != null ? !haslo.equals(klient.haslo) : klient.haslo != null) return false;
        return idOsoby != null ? idOsoby.equals(klient.idOsoby) : klient.idOsoby == null;
    }

    @Override
    public int hashCode() {
        int result = idKlienta != null ? idKlienta.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        result = 31 * result + (idOsoby != null ? idOsoby.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "idKlienta=" + idKlienta +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}
