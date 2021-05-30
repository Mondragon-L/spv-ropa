package mx.venado.pvr.modelo.vo;

public class TicketVo {

    private int VentaId = -1;
    private String VentaFecha = null;
    private String VentaHora = null;
    private double VentaTotal = -1;
    private double VentaImporte = -1;
    private double VentaCambio = -1;
    private int VentaVendedor = -1;
    private int ProductoId = -1;
    private String ProductoLote = null;
    private String ProductoDescripcion = null;
    private double ProductoCantidad = -1;
    private double ProductoPventa = -1;
    private double ProductoTotal = -1;
    private String ProductoObservacion = null;
    private double ProductoExistencias = -1;
    private String Estatus = null;

    public TicketVo() {
    }
    
    public TicketVo(String Estatus) {
        this.Estatus = Estatus;
    }

    public int getVentaId() {
        return VentaId;
    }

    public void setVentaId(int VentaId) {
        this.VentaId = VentaId;
    }

    public String getVentaFecha() {
        return VentaFecha;
    }

    public void setVentaFecha(String VentaFecha) {
        this.VentaFecha = VentaFecha;
    }

    public String getVentaHora() {
        return VentaHora;
    }

    public void setVentaHora(String VentaHora) {
        this.VentaHora = VentaHora;
    }

    public double getVentaTotal() {
        return VentaTotal;
    }

    public void setVentaTotal(double VentaTotal) {
        this.VentaTotal = VentaTotal;
    }

    public double getVentaImporte() {
        return VentaImporte;
    }

    public void setVentaImporte(double VentaImporte) {
        this.VentaImporte = VentaImporte;
    }

    public double getVentaCambio() {
        return VentaCambio;
    }

    public void setVentaCambio(double VentaCambio) {
        this.VentaCambio = VentaCambio;
    }

    public int getVentaVendedor() {
        return VentaVendedor;
    }

    public void setVentaVendedor(int VentaVendedor) {
        this.VentaVendedor = VentaVendedor;
    }

    public int getProductoId() {
        return ProductoId;
    }

    public void setProductoId(int ProductoId) {
        this.ProductoId = ProductoId;
    }

    public String getProductoLote() {
        return ProductoLote;
    }

    public void setProductoLote(String ProductoLote) {
        this.ProductoLote = ProductoLote;
    }

    public double getProductoCantidad() {
        return ProductoCantidad;
    }

    public void setProductoCantidad(double ProductoCantidad) {
        this.ProductoCantidad = ProductoCantidad;
    }

    public double getProductoPventa() {
        return ProductoPventa;
    }

    public void setProductoPventa(double ProductoPventa) {
        this.ProductoPventa = ProductoPventa;
    }

    public double getProductoTotal() {
        return ProductoTotal;
    }

    public void setProductoTotal(double ProductoTotal) {
        this.ProductoTotal = ProductoTotal;
    }

    public String getProductoObservacion() {
        return ProductoObservacion;
    }

    public void setProductoObservacion(String ProductoObservacion) {
        this.ProductoObservacion = ProductoObservacion;
    }

    public double getProductoExistencias() {
        return ProductoExistencias;
    }

    public void setProductoExistencias(double ProductoExistencias) {
        this.ProductoExistencias = ProductoExistencias;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public String getProductoDescripcion() {
        return ProductoDescripcion;
    }

    public void setProductoDescripcion(String ProductoDescripcion) {
        this.ProductoDescripcion = ProductoDescripcion;
    }
}
