package dm.api.model;

import java.util.Date;

public class Osoba {
    private Integer idOsoby;
    private String imie;
    private String nazwisko;
    private String pesel;
    private Date dataUrodzenia;
    private String email;
    private String telefon;
    private Integer idAdresu;

    public Osoba() {
    }

    public Osoba(Integer idOsoby, String imie, String nazwisko, String pesel, Date dataUrodzenia, String email, String telefon, Integer idAdresu) {
        this.idOsoby = idOsoby;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        this.telefon = telefon;
        this.idAdresu = idAdresu;
    }

    public Integer getIdOsoby() {
        return idOsoby;
    }

    public void setIdOsoby(Integer idOsoby) {
        this.idOsoby = idOsoby;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Integer getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(Integer idAdresu) {
        this.idAdresu = idAdresu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Osoba osoba = (Osoba) o;

        if (idOsoby != null ? !idOsoby.equals(osoba.idOsoby) : osoba.idOsoby != null) return false;
        if (imie != null ? !imie.equals(osoba.imie) : osoba.imie != null) return false;
        if (nazwisko != null ? !nazwisko.equals(osoba.nazwisko) : osoba.nazwisko != null) return false;
        if (pesel != null ? !pesel.equals(osoba.pesel) : osoba.pesel != null) return false;
        if (dataUrodzenia != null ? !dataUrodzenia.equals(osoba.dataUrodzenia) : osoba.dataUrodzenia != null)
            return false;
        if (email != null ? !email.equals(osoba.email) : osoba.email != null) return false;
        if (telefon != null ? !telefon.equals(osoba.telefon) : osoba.telefon != null) return false;
        return idAdresu != null ? idAdresu.equals(osoba.idAdresu) : osoba.idAdresu == null;
    }

    @Override
    public int hashCode() {
        int result = idOsoby != null ? idOsoby.hashCode() : 0;
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (dataUrodzenia != null ? dataUrodzenia.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefon != null ? telefon.hashCode() : 0);
        result = 31 * result + (idAdresu != null ? idAdresu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "idOsoby=" + idOsoby +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", idAdresu=" + idAdresu +
                '}';
    }
}
