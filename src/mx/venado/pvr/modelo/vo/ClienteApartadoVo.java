package mx.venado.pvr.modelo.vo;

public class ClienteApartadoVo {
    private int Id = -1;
    private String Nombre = "";
    private String ApellidoPaterno = "";
    private String ApellidoMaterno = "";
    private int Productos = 0;
    private String Estatus = null;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }
    
    @Override
    public String toString(){
        return this.Nombre;
    }

    public int getProductos() {
        return Productos;
    }

    public void setProductos(int Productos) {
        this.Productos = Productos;
    }
}
