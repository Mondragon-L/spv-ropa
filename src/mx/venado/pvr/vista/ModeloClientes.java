package mx.venado.pvr.vista;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class ModeloClientes extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        JLabel.class,
        JLabel.class,
        JLabel.class
    };

    public ModeloClientes() {
        super(new String[]{
            "ID",
            "Nombre",
            "Apellido paterno",
            "Apellido materno",
            "Teléfono",
            "Correo",
            "Dirección",
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

}
