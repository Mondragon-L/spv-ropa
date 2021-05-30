package mx.venado.pvr.vista;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class ListCellRendererCliente extends JLabel implements ListCellRenderer {

    Font fuenteDefault;
    Font fuenteSelected;
    
    public ListCellRendererCliente() {
        fuenteDefault = new FuenteUbuntu().AplicarLightFont(15);
        fuenteSelected = new FuenteUbuntu().AplicarLightFont(15, Font.BOLD);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setFont(fuenteDefault);
        if (cellHasFocus) {
            this.setFont(fuenteSelected);
        }
        return this;
    }

}
