package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloAbonoInforme extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class
    };
    
    public ModeloAbonoInforme() {
        super(new String[]{
            "#",
            "Cliente",
            "Producto",
            "Lote",
            "Fecha",
            "Hora",
            "Cantidad",
            "Observación"
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
