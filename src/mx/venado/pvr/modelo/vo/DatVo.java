package mx.venado.pvr.modelo.vo;

public class DatVo {
    
    public final static String OK = "[OK]";
    
    private int ValInt = -1;
    private double ValDouble = -1;
    private String ValString = null;
    private String Estatus = null;

    public DatVo() {
    }
    
    public DatVo(int val, String estatus) {
        this.ValInt = val;
        this.Estatus = estatus;
    }
    public DatVo(double val, String estatus) {
        this.ValDouble = val;
        this.Estatus = estatus;
    }
    public DatVo(String val, String estatus) {
        this.ValString = val;
        this.Estatus = estatus;
    }

    public int getValInt() {
        return ValInt;
    }

    public double getValDouble() {
        return ValDouble;
    }

    public String getValString() {
        return ValString;
    }

    public String getEstatus() {
        return Estatus;
    }
    
    
    
}
