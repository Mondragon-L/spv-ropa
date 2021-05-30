package mx.venado.pvr.controlador;

import javax.swing.JDesktopPane;
import pvr.FrmPvr2;
import mx.venado.pvr.utilidades.IPvrConstant;

public class Pvr2 implements IPvrConstant {

    public Pvr2(FrmPvr2 frmNora2) {
        this.frmNora2 = frmNora2;
        this.DeskNora = this.frmNora2.getDeskPvr();
    }

    private void configBackground() {
        
    }

    private FrmPvr2 frmNora2;
    private JDesktopPane DeskNora;
}
