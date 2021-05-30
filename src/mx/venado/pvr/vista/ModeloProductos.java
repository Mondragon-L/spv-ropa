package mx.venado.pvr.vista;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class ModeloProductos extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        Double.class,
        Double.class,
        Double.class,
        String.class,
        JLabel.class
    };

    public ModeloProductos() {
        super(new String[]{
            "#",
            "Código",
            "Producto",
            "Modelo",
            "Talla",
            "P.Compra",
            "P.Venta",
            "Existencias",
            "Observaciones"
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
    
    /**
     * Elimina todas las filas existentes de la tabla.
     */
    public void clear() {
        for (int i = this.getRowCount() - 1; i >= 0; i--) {
            this.removeRow(i);
        }
    }
    
}
