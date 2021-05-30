package mx.venado.pvr.modelo.vo;

public class VntProdVo {

    private int Id = -1;
    private String Lote = null;
    private String producto = null;
    private double Cantidad = -1;
    private double Pcompra = 0;
    private double Pventa = -1;
    private double Total = -1;
    private String Observacion = null;
    private int VentaId = -1;
    private double Existencias = -1;
    private double Descuento = -1;
    private String ConceptoDescuento = null;
    private String Estatus = null;

    public VntProdVo() {
    }

    public VntProdVo(String Estatus) {
        this.Estatus = Estatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getLote() {
        return Lote;
    }

    public void setLote(String Lote) {
        this.Lote = Lote;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPventa() {
        return Pventa;
    }

    public void setPventa(double Pventa) {
        this.Pventa = Pventa;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public int getVentaId() {
        return VentaId;
    }

    public void setVentaId(int VentaId) {
        this.VentaId = VentaId;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public double getExistencias() {
        return Existencias;
    }

    public void setExistencias(double Existencias) {
        this.Existencias = Existencias;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public String getConceptoDescuento() {
        return ConceptoDescuento;
    }

    public void setConceptoDescuento(String ConceptoDescuento) {
        this.ConceptoDescuento = ConceptoDescuento;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the Pcompra
     */
    public double getPcompra() {
        return Pcompra;
    }

    /**
     * @param Pcompra the Pcompra to set
     */
    public void setPcompra(double Pcompra) {
        this.Pcompra = Pcompra;
    }
}
