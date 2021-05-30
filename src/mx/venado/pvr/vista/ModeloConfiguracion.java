
package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloConfiguracion extends DefaultTableModel {
    
    public static final int CLAVE = 0;
    public static final int VALOR = 1;
    public static final int TIPO = 2;

    Class[] tipos = new Class[]{
        String.class,
        Object.class
    };

    public ModeloConfiguracion() {
        super(new String[]{
            "",
            "",
            ""
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
