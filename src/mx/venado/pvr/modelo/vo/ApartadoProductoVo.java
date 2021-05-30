package mx.venado.pvr.modelo.vo;

public class ApartadoProductoVo {

    private int Id = -1;
    private int IdApartado = -1;
    private String Lote = null;
    private String Producto = null;
    private String Fecha = null;
    private String Hora = null;
    private double Precioventa = -1;
    private double Cantidad = -1;
    private double Total = -1;
    private double Descuento = 0;
    private String EstatusProducto = null;
    private String Estatus = null;
    
    private double DiferenciaPV = -1;

    public ApartadoProductoVo() {
    }
    
    public ApartadoProductoVo(String estatus){
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

    public String getLote() {
        return Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
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

    public double getPrecioventa() {
        return Precioventa;
    }

    public void setPrecioventa(double Precioventa) {
        this.Precioventa = Precioventa;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
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

    public String getEstatusProducto() {
        return EstatusProducto;
    }

    public void setEstatusProducto(String EstatusProducto) {
        this.EstatusProducto = EstatusProducto;
    }

    public double getDiferenciaPV() {
        return DiferenciaPV;
    }

    public void setDiferenciaPV(double DiferenciaPV) {
        this.DiferenciaPV = DiferenciaPV;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }
    
}
