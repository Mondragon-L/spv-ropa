package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloClientesApartados extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        Integer.class
    };

    public ModeloClientesApartados() {
        super(new String[]{
            "ID",
            "Nombre",
            "Apellido",
            "Productos"
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

    /**
     * Elimina todas las filas existentes de la tabla.
     */
    public void clear() {
        for (int i = this.getRowCount() - 1; i >= 0; i--) {
            this.removeRow(i);
        }
    }

}
