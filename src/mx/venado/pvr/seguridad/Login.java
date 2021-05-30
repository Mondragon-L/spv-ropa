package mx.venado.pvr.seguridad;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.ErrorIcono;
import pvr.FrmPvr;
import mx.venado.pvr.utilidades.IPvrConstant;

public class Login implements IPvrConstant {

    public final static String ACCESO_CONCEDIDO = "[concedido]";
    public final static String ACCESO_DENEGADO = "[denegado]";

    protected String usuario = "Vendedor";
    protected String tipo = "Vendedor";

    JButton btnAceptar;
    JButton btnCerrar;
    JLabel lblBloqueo;
    JLabel lblTipo;
    JPasswordField pwdtxtClave;
    JToggleButton tgbtnAdministrador;
    JToggleButton tgbtnVendedor;
    JDialog dialog;

    public Login(JDialog dialog, JButton btnAceptar, JButton btnCerrar, JLabel lblBloqueo, JLabel lblTipo,
            JPasswordField pwdtxtClave, JToggleButton tgbtnAdministrador, JToggleButton tgbtnVendedor) {
        this.dialog = dialog;
        this.btnAceptar = btnAceptar;
        this.btnCerrar = btnCerrar;
        this.lblBloqueo = lblBloqueo;
        this.pwdtxtClave = pwdtxtClave;
        this.tgbtnAdministrador = tgbtnAdministrador;
        this.tgbtnVendedor = tgbtnVendedor;
        this.lblTipo = lblTipo;

        ConfigForm();
        Listeners();
    }

    private void ConfigForm() {
        tgbtnVendedor.setSelected(true);
        lblBloqueo.setIcon(new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "BloqueadoVendedor.png")));
        lblTipo.setText("Vendedor");
        pwdtxtClave.requestFocus();
    }

    private void Listeners() {
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pwdtxtClave.getPassword().length > 0) {
                    Acceder();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Ingrese una clave", "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                }
            }
        });

        tgbtnAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tgbtnAdministrador.isSelected()) {
                    usuario = "Administrador";
                    tipo = "Administrador";
                    lblBloqueo.setIcon(new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "BloqueadoAdmin.png")));
                    lblTipo.setText("Administrador");
                }
                pwdtxtClave.setText("");
            }
        });

        tgbtnVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tgbtnVendedor.isSelected()) {
                    usuario = "Vendedor";
                    tipo = "Vendedor";
                    lblBloqueo.setIcon(new ImageIcon(getClass().getResource(URL_MULTIMEDIA + "BloqueadoVendedor.png")));
                    lblTipo.setText("Vendedor");
                }
                pwdtxtClave.setText("");
            }
        });

        pwdtxtClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (pwdtxtClave.getPassword().length > 0) {
                        Acceder();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Ingrese una clave", "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                    }
                }
            }
        });
    }

    private void Acceder() {
        try {
            LoginDao dao = new LoginDao();
            LoginVo get;
            LoginVo set = new LoginVo();
            set.setUsuario(usuario);
            set.setClave(AES.sha1(new String(pwdtxtClave.getPassword())));
            get = dao.Ingresar(set);
            if (null != get.getEstatus()) {
                switch (get.getEstatus()) {
                    case ACCESO_CONCEDIDO:
                        FrmPvr frmPvr = new FrmPvr();
                        frmPvr.setTipo(get.getTipo());
                        frmPvr.setIdUsuario(get.getId());
                        frmPvr.setUsuario(get.getUsuario());
                        frmPvr.setVisible(true);
                        dialog.dispose();
                        break;
                    case ACCESO_DENEGADO:
                        JOptionPane.showMessageDialog(dialog, "Clave incorrecta", "Sesión", JOptionPane.OK_OPTION, new ErrorIcono());
                        break;
                    default:
                        JOptionPane.showMessageDialog(dialog, get.getEstatus(), "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                        break;
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(dialog, ((e.getMessage() != null) ? e.getMessage() : e.toString()), "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
        }
        pwdtxtClave.setText("");
    }

}
