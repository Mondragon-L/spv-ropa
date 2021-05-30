package mx.venado.pvr.vista;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class ModeloAgregarApartado extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        Double.class,
        Double.class,
        String.class
    };

    public ModeloAgregarApartado() {
        super(new String[]{
            "#",
            "Código",
            "Producto",
            "Modelo",
            "Talla",
            "Precio",
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
