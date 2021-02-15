package dm.api.model;

public class Pracownik {
    private Integer idPracownika;
    private Double pensja;
    private String Rola;
    private Integer idOsoby;

    public Pracownik() {
    }

    public Pracownik(Integer idPracownika, Double pensja, String rola, Integer idOsoby) {
        this.idPracownika = idPracownika;
        this.pensja = pensja;
        Rola = rola;
        this.idOsoby = idOsoby;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
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

    public Integer getIdOsoby() {
        return idOsoby;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "idPracownika=" + idPracownika +
                ", pensja=" + pensja +
                ", Rola='" + Rola + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}
