package mx.venado.pvr.vista;

import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererConfig extends DefaultTableCellRenderer {

    public TableCellRendererConfig() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JPasswordField) {
            JPasswordField field = (JPasswordField) value;
            field.setEchoChar('X');
            return field;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
