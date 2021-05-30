package mx.venado.pvr.modelo.vo;

public class ApartadoAbonoVo {

    private int Id = -1;
    private int IdApartado = -1;
    private String Fecha = null;
    private String Hora = null;
    private double Cantidad = -1;
    private String Observacion = null;
    private String Estatus = null;

    public ApartadoAbonoVo() {
    }
    
    public ApartadoAbonoVo(String estatus) {
        this.Estatus = estatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdApartado() {
        return IdApartado;
    }

    public void setIdApartado(int IdApartado) {
        this.IdApartado = IdApartado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    
}
