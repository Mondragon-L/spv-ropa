package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloVentaProductosInforme extends DefaultTableModel {

    Class[] tipos = new Class[]{
        String.class,
        String.class,
        Integer.class,
        Double.class
    };

    public ModeloVentaProductosInforme() {
        super(new String[]{
            "Código",
            "Producto",
            "Cantidad",
            "Total"
        }, 0);
    }
    
    @Override
    public Class getColumnClass(int columnIndex){
        return tipos[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

}
