package mx.venado.pvr.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererVenta extends DefaultTableCellRenderer {

    boolean bloquearayuda = false;
    private String ayuda = "";

    public TableCellRendererVenta() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        this.setForeground(new Color(245, 245, 245));
        this.setBackground((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158));
//        this.setHorizontalAlignment(CENTER);

        if (isSelected) {
            if (value instanceof String) {
                value = value.toString().toUpperCase();
            }
        }

        switch (column) {
            case ModeloVenta.COL_DESCUENTO:
                ayuda = "Clic para aplicar un descuento...";
                break;
            case ModeloVenta.COL_CONCEPTO:
                ayuda = "Clic para ingresar un concepto (Debe haber un descuento previo)...";
                break;
            case ModeloVenta.COL_CANTIDAD:
                ayuda = "Clic para aumentar el número de artículos...";
                break;
            default:
                ayuda = "";
        }
        this.setToolTipText(ayuda);

        if (value instanceof JLabel) {
            JLabel lbl = (JLabel) value;
            if (isSelected) {
                lbl.setBackground(new Color(245, 245, 245));
            } else {
                lbl.setBackground((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158));
            }
            lbl.setToolTipText("Quitar");
            lbl.setOpaque(true);
            return lbl;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
