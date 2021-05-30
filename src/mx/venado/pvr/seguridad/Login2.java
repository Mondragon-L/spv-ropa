package mx.venado.pvr.seguridad;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.ErrorIcono;
import pvr.DfrmSesion2;
import pvr.FrmPvr2;
import mx.venado.pvr.utilidades.IPvrConstant;

public class Login2 implements IPvrConstant {

    public final static String ACCESO_CONCEDIDO = "[concedido]";
    public final static String ACCESO_DENEGADO = "[denegado]";

    protected String usuario = "Vendedor";
    protected String tipo = "Vendedor";

    JButton btnAceptar;
    JPasswordField pwdtxtClave;
    JTextField txtUsuario;
    DfrmSesion2 root;

    public Login2(DfrmSesion2 root) {
        this.root = root;
        this.btnAceptar = this.root.getBtnAceptar();
        this.txtUsuario = this.root.getTxtUsuario();
        this.pwdtxtClave = this.root.getPwdtxtClave();

        ConfigForm();
        Listeners();
    }

    private void ConfigForm() {
        pwdtxtClave.setEchoChar('X');
        txtUsuario.requestFocus();
    }

    private void Listeners() {

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtUsuario.getText().isEmpty() && pwdtxtClave.getPassword().length > 0) {
                    Acceder();
                } else {
                    JOptionPane.showMessageDialog(root, "Complete los campos", "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                }
            }
        });

        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!txtUsuario.getText().isEmpty() && pwdtxtClave.getPassword().length > 0) {
                        Acceder();
                    } else {
                        JOptionPane.showMessageDialog(root, "Complete los campos", "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                    }
                }
            }
        });
        
        pwdtxtClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!txtUsuario.getText().isEmpty() && pwdtxtClave.getPassword().length > 0) {
                        Acceder();
                    } else {
                        JOptionPane.showMessageDialog(root, "Complete los campos", "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
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
            set.setUsuario(txtUsuario.getText());
            set.setClave(AES.sha1(new String(pwdtxtClave.getPassword())));
            get = dao.Ingresar(set);
            if (null != get.getEstatus()) {
                switch (get.getEstatus()) {
                    case ACCESO_CONCEDIDO:
//                        JOptionPane.showMessageDialog(root, get.toString());
                        FrmPvr2 frmPvr2 = new FrmPvr2();
                        frmPvr2.setTipo(get.getTipo());
                        frmPvr2.setIdUsuario(get.getId());
                        frmPvr2.setUsuario(get.getUsuario());
                        frmPvr2.setVisible(true);
                        root.dispose();
//                        JOptionPane.showMessageDialog(root, "OK", "Sesión", JOptionPane.OK_OPTION, new CorrectoIcono());
                        break;
                    case ACCESO_DENEGADO:
                        JOptionPane.showMessageDialog(root, "Usuario o clave incorrecta", "Sesión", JOptionPane.OK_OPTION, new ErrorIcono());
                        break;
                    default:
                        JOptionPane.showMessageDialog(root, get.getEstatus(), "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
                        break;
                }
            }
        } catch (HeadlessException e) {
//            String el = "";
//            for (StackTraceElement element : e.getStackTrace()) {
//                el += (element.toString() + "\n");
//            }
//            JOptionPane.showMessageDialog(root, ">>> " + el);
            JOptionPane.showMessageDialog(root, ((e.getMessage() != null) ? e.getMessage() : e.toString()), "Sesión", JOptionPane.OK_OPTION, new AdvertenciaIcono());
        }
        pwdtxtClave.setText("");
    }

}
