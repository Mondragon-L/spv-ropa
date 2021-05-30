
package mx.venado.pvr.controlador;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import mx.venado.pvr.utilidades.IPvrConstant;

public class Pvr implements IPvrConstant {

    JButton btnApartados;
    JButton btnClientes;
    JButton btnInventario;
    JButton btnVentas;
    JButton btnInformes;
    JButton btnPerfil;

    public Pvr(JButton btnApartados, JButton btnClientes, JButton btnInventario, 
            JButton btnVentas, JButton btnInformes, JButton btnPefil, String tipo) {
        this.btnApartados = btnApartados;
        this.btnClientes = btnClientes;
        this.btnInventario = btnInventario;
        this.btnVentas = btnVentas;
        this.btnInformes = btnInformes;
        this.btnPerfil = btnPefil;
        
        if ("Vendedor".equals(tipo)) {
            this.btnInventario.setVisible(false);
            this.btnInformes.setVisible(false);
        }
    }
    
    public void Activo(int activo){
        JButton[] arrbtn = new JButton[]{
            btnApartados,
            btnClientes,
            btnInventario,
            btnVentas,
            btnInformes,
            btnPerfil
        };
        ImageIcon[] arricon = new ImageIcon[]{
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Apartados/iconApartados.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Clientes/iconClientes.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Inventario/iconInventario.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Ventas/iconVentas.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Informes/iconInformes.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "perfil/iconPerfil.png"))
            
        };
        
        ImageIcon[] arriconroll = new ImageIcon[]{
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Apartados/rolloverIconApartados.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Clientes/rolloverIconClientes.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Inventario/rolloverIconInventario.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Ventas/rolloverIconVentas.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "Informes/rolloverIconInformes.png")),
            new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "perfil/rolloverIconPerfil.png"))
        };
        
        for (int i = 0; i < arrbtn.length; i++) {
            arrbtn[i].setIcon(arricon[i]);
        }
        
        arrbtn[activo].setIcon(arriconroll[activo]);
        
    }
    
}
