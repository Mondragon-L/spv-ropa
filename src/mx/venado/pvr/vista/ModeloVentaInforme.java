package mx.venado.pvr.vista;

import javax.swing.table.DefaultTableModel;

public class ModeloVentaInforme extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class
    };

    public ModeloVentaInforme() {
        super(new String[]{
            "#",
            "Fecha",
            "Hora",
            "Total",
            "Productos",
            "Vendedor"
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
