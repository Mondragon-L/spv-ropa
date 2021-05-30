package mx.venado.pvr.modelo.vo;

public class ApartadoVo {

    private int Id = -1;
    private int IdCliente = -1;
    private double Total = -1;
    private String EstatusApartado = null;
    private String Estatus = null;

    public ApartadoVo() {
    }

    public ApartadoVo(int IdCliente, double Total, String EstatusApartado) {
        this.IdCliente = IdCliente;
        this.Total = Total;
        this.EstatusApartado = EstatusApartado;
    }

    public ApartadoVo(String estatus) {
        this.Estatus = estatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getEstatusApartado() {
        return EstatusApartado;
    }

    public void setEstatusApartado(String EstatusApartado) {
        this.EstatusApartado = EstatusApartado;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

}
