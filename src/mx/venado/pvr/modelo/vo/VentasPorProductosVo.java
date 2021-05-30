package mx.venado.pvr.modelo.vo;

public class VentasPorProductosVo {

    private int Id = -1;
    private int Cantidad = -1;
    private String Producto = "";
    private String Lote = "";
    private String Fecha = "";
    private String Hora = "";
    private double Total = -1;
    private double PVenta = -1;
    private double PCompra = -1;
    private String Estatus = null;

    public VentasPorProductosVo() {
    }

    public VentasPorProductosVo(String Estatus) {
        this.Estatus = Estatus;
    }
    
    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
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

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getPVenta() {
        return PVenta;
    }

    public void setPVenta(double PVenta) {
        this.PVenta = PVenta;
    }

    public double getPCompra() {
        return PCompra;
    }

    public void setPCompra(double PCompra) {
        this.PCompra = PCompra;
    }
}
