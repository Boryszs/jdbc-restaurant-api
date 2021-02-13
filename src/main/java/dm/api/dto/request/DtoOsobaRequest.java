package dm.api.dto.request;

import java.util.Date;

public class DtoOsobaRequest {
    private String imie;
    private String nazwisko;
    private String pesel;
    private Date dataUrodzenia;
    private String email;
    private String telefon;
    private Integer idAdresu;

    public DtoOsobaRequest() {
    }

    public DtoOsobaRequest(String imie, String nazwisko, String pesel, Date dataUrodzenia, String email, String telefon, Integer idAdresu) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        this.telefon = telefon;
        this.idAdresu = idAdresu;
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
    public String toString() {
        return "DtoOsobaResponse{" +
                " imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", idAdresu=" + idAdresu +
                '}';
    }
}
