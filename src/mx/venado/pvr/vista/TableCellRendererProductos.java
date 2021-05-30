package mx.venado.pvr.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererProductos extends DefaultTableCellRenderer {

    boolean bloquearayuda = false;

    public TableCellRendererProductos() {
    }

    public TableCellRendererProductos(boolean bloquearayuda) {
        this.bloquearayuda = bloquearayuda;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        this.setForeground(new Color(245, 245, 245));
        this.setBackground((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158));
        this.setToolTipText(value.toString());

        if (isSelected) {
            if (value instanceof String) {
                value = value.toString().toUpperCase();
            }
        }

        if (column == 2 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7) {
            this.setBackground((column == 6 && value.toString().equals("0.0")) ? new Color(153, 0, 0)
                    : ((row % 2) == 0 ? new Color(97, 97, 97) : new Color(158, 158, 158)));
            if (!bloquearayuda) {
                switch (column) {
                    case 2:
                        this.setToolTipText("Doble clic para editar el nombre del producto.");
                        break;
                    case 3:
                        this.setToolTipText("Doble clic para editar el modelo del producto.");
                        break;
                    case 4:
                        this.setToolTipText("Doble clic para editar la talla del producto.");
                        break;
                    case 5:
                        this.setToolTipText("Doble clic para editar el precio de compra del producto.");
                        break;
                    case 6:
                        this.setToolTipText("Doble clic para editar el precio de venta del producto.");
                        break;
                    case 7:
                        this.setToolTipText((value.toString().equals("0.0"))
                                ? "El producto se encuentra agotado (Doble clic para editar las existencias del producto)"
                                : "Doble clic para editar las existencias del producto.");
                        break;
                    case 8:
                        this.setToolTipText("Clic para ver las observaciones ó presione Alt + clic para editar.");
                        break;
                }
            }
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
