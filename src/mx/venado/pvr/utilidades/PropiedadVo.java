package mx.venado.pvr.utilidades;

public class PropiedadVo {

    private String clave;
    private Object valor;
    private String tipo;

    public PropiedadVo() {
        this(null, null, null);
    }

    public PropiedadVo(String clave, Object valor, String tipo) {
        this.clave = clave;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
