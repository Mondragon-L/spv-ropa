package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloVentaBusqueda extends DefaultTableModel {

    public final static int COL_NUMERO = 0;
    public final static int COL_LOTE = 1;
    public final static int COL_PRODUCTO = 2;
    public final static int COL_CANTIDAD = 3;
    public final static int COL_PVENTA = 4;
    public final static int COL_DESCUENTO = 5;
    public final static int COL_CONCEPTO = 6;
    public final static int COL_TOTAL = 7;
    
    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        Double.class,
        Double.class
    };

    public ModeloVentaBusqueda() {
        super(new String[]{
            "#",
            "Código",
            "Producto",
            "Cantidad",
            "P.venta"
        }, 0);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return tipos[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public double getDouble(int rowIndex, int columnIndex) {
        return Double.parseDouble(this.getValueAt(rowIndex, columnIndex).toString());
    }

    public int getInteger(int rowIndex, int columnIndex) {
        return Integer.parseInt(this.getValueAt(rowIndex, columnIndex).toString());
    }
    
    public String getString(int rowIndex, int columnIndex) {
        return (this.getValueAt(rowIndex, columnIndex).toString());
    }

}
