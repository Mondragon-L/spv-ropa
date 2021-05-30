package mx.venado.pvr.modelo.vo;

public class AbonoVo {

    private int Id = -1;
    private int IdApartProd = -1;
    private String Fecha = null;
    private String Hora = null;
    private double Cantidad = -1;
    private String Observacion = null;
    private String Estatus = null;

    public AbonoVo() {
    }
    
    public AbonoVo(String Estatus) {
        this.Estatus =  Estatus;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdApartProd() {
        return IdApartProd;
    }

    public void setIdApartProd(int IdApartProd) {
        this.IdApartProd = IdApartProd;
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
