package mx.venado.pvr.modelo.vo;

public class ProductoVo {

    private int Id = -1;
    private String Lote = "";
    private String Producto = "";
    private String Modelo = "";
    private String Talla = "";
    private double Pcompra = -1;
    private double Pventa = -1;
    private double Existencias = -1;
    private String Observacion = "";
    private String Columna = null;
    private String Editando = null;
    private String Estatus = null;

    public ProductoVo() {
    }

    /**
     * Constructor para editar datos de los productos directamente de la tabla.
     *
     * @param id ID del producto a editar
     * @param valoreditar Nuevo valor
     * @param columna Columna de la base de datos que se quiere editar
     */
    public ProductoVo(int id, String valoreditar, String columna) {
        this.Id = id;
        this.Editando = valoreditar;
        this.Columna = columna;
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

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public double getPcompra() {
        return Pcompra;
    }

    public void setPcompra(double Pcompra) {
        this.Pcompra = Pcompra;
    }

    public double getPventa() {
        return Pventa;
    }

    public void setPventa(double Pventa) {
        this.Pventa = Pventa;
    }

    public double getExistencias() {
        return Existencias;
    }

    public void setExistencias(double Existencias) {
        this.Existencias = Existencias;
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

    public String getColumna() {
        return Columna;
    }

    public void setColumna(String Columna) {
        this.Columna = Columna;
    }

    public String getEditando() {
        return Editando;
    }

    public void setEditando(String Editando) {
        this.Editando = Editando;
    }

    @Override
    public String toString() {
        return "[" + Lote + " > "
                + Producto + " > "
                + Modelo + " > "
                + Pcompra + " > "
                + Pventa + " > "
                + Existencias + " > "
                + Observacion
                + "]";
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

}
