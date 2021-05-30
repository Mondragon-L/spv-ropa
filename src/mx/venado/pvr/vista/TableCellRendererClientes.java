package mx.venado.pvr.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererClientes extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        this.setForeground(new Color(245, 245, 245));
        this.setBackground((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158));

        if (isSelected) {
            if (value instanceof String) {
                value = value.toString().toUpperCase();
            }
        }

        if (value instanceof JLabel) {
            JLabel lbl = (JLabel) value;
            if (isSelected) {
                lbl.setBackground(new Color(245, 245, 245));
            }
            else {
                lbl.setBackground((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158));
            }
            switch (column) {
                case 7:
                    lbl.setToolTipText("Información detallada");
                    break;
                case 8:
                    lbl.setToolTipText("Editar datos");
                    break;
                case 9:
                    lbl.setToolTipText("Eliminar");
                    break;
            }
            
            lbl.setOpaque(true);
            return lbl;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
