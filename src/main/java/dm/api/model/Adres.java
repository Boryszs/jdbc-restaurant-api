package dm.api.model;

public class Adres {
    private Integer idAdresu;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String kodPocztowy;

    public Adres() {
    }

    public Adres(Integer idAdresu, String miejscowosc, String ulica, String nrDomu, String kodPocztowy) {
        this.idAdresu = idAdresu;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.kodPocztowy = kodPocztowy;
    }

    public Integer getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(Integer idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adres adres = (Adres) o;

        if (idAdresu != null ? !idAdresu.equals(adres.idAdresu) : adres.idAdresu != null) return false;
        if (miejscowosc != null ? !miejscowosc.equals(adres.miejscowosc) : adres.miejscowosc != null) return false;
        if (ulica != null ? !ulica.equals(adres.ulica) : adres.ulica != null) return false;
        if (nrDomu != null ? !nrDomu.equals(adres.nrDomu) : adres.nrDomu != null) return false;
        return kodPocztowy != null ? kodPocztowy.equals(adres.kodPocztowy) : adres.kodPocztowy == null;
    }

    @Override
    public int hashCode() {
        int result = idAdresu != null ? idAdresu.hashCode() : 0;
        result = 31 * result + (miejscowosc != null ? miejscowosc.hashCode() : 0);
        result = 31 * result + (ulica != null ? ulica.hashCode() : 0);
        result = 31 * result + (nrDomu != null ? nrDomu.hashCode() : 0);
        result = 31 * result + (kodPocztowy != null ? kodPocztowy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "idAdresu=" + idAdresu +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nrDomu='" + nrDomu + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                '}';
    }
}
