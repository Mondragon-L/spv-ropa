package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloAbono extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        Double.class,
        String.class
    };

    public ModeloAbono() {
        super(new String[]{
            "#",
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

}
