/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.venado.pvr.utilidades;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JToolTip;

/**
 *
 * @author Luis Antonio
 */
public class CustomTooltip extends JToolTip {

    public CustomTooltip(JComponent component) {
        super();
        this.setComponent(component);
        this.setBackground(new Color(255,255,255));
        this.setForeground(new Color(0,0,0));
    }

}
