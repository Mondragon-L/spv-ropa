package mx.venado.pvr.modelo.vo;

public class AbonoInformeVo {

    private int ClienteId = -1;
    private String Cliente = null;
    private String Producto = null;
    private String Lote = null;
    private String Fecha = null;
    private String Hora = null;
    private double Cantidad = -1;
    private String Observación = null;
    private String Estatus = null;

    public AbonoInformeVo() {
    }

    public AbonoInformeVo(String Estatus) {
        this.Estatus = Estatus;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
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

    public String getObservación() {
        return Observación;
    }

    public void setObservación(String Observación) {
        this.Observación = Observación;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getClienteId() {
        return ClienteId;
    }

    public void setClienteId(int ClienteId) {
        this.ClienteId = ClienteId;
    }


}
