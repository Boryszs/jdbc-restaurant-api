package dm.api.dto.request;

public class DtoPracownikRequest {

    private Double pensja;
    private String Rola;
    private Integer idOsoby;

    public DtoPracownikRequest() {
    }

    public DtoPracownikRequest(Double pensja, String rola, Integer idOsoby) {
        this.pensja = pensja;
        Rola = rola;
        this.idOsoby = idOsoby;
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
        return "PracownikResponse{" +
                " pensja=" + pensja +
                ", Rola='" + Rola + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }
}
