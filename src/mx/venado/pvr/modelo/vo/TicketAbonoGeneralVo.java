package mx.venado.pvr.modelo.vo;

public class TicketAbonoGeneralVo {
    private String Producto = null;
    private double Pagado = -1;
    private double Total = -1;
    private String Estatus = null;

    public TicketAbonoGeneralVo() {
    }
    
    public TicketAbonoGeneralVo(String Estatus) {
        this.Estatus = Estatus;
    }
    
    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public double getPagado() {
        return Pagado;
    }

    public void setPagado(double Pagado) {
        this.Pagado = Pagado;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }
}
