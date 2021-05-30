package mx.venado.pvr.seguridad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import pvr.DfrmPerfil;

public class Perfil {

    public static final int EDITAR_USUARIO = 0;
    public static final int EDITAR_CONTRSENIA = 1;
    private boolean editado = false;
    private int type = -1;

    private int Id;
    private String Usuario;
    private String Tipo;

    public Perfil(DfrmPerfil root, int Id, String Usuario, String Tipo) {
        this.root = root;
        this.init();
        listenersBtn();
        this.Id = Id;
        this.Usuario = Usuario;
        this.Tipo = Tipo;

        txtUsuario.setText(this.Usuario);

        pwdContraseniaActual.setEchoChar('X');
        pwdContraseniaNueva.setEchoChar('X');
        pwdRepContraseniaNueva.setEchoChar('X');

    }

    private void listenersBtn() {
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type == EDITAR_USUARIO) {
                    editarUsuario();
                } else if (type == EDITAR_CONTRSENIA) {
                    editarContrasenia();
                }
            }
        });

        btnEditUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = EDITAR_USUARIO;
                txtUsuario.setEnabled(true);
                pwdContraseniaActual.setEnabled(false);
                pwdContraseniaNueva.setEnabled(false);
                pwdRepContraseniaNueva.setEnabled(false);
            }
        });

        btnEditCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = EDITAR_CONTRSENIA;
                txtUsuario.setEnabled(false);
                pwdContraseniaActual.setEnabled(true);
                pwdContraseniaNueva.setEnabled(true);
                pwdRepContraseniaNueva.setEnabled(true);
            }
        });
    }

    private void editarUsuario() {
        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(root, "Ingrese un nombre de usuario");
        } else {
            String tmpUsr = txtUsuario.getText();
            if (!tmpUsr.equals(Usuario)) {
                PerfilDao dao = new PerfilDao();
                String resp = dao.editarUsuario(Id, tmpUsr);
                if (resp.equals("OK")) {
                    JOptionPane.showMessageDialog(root, "Cambio realizado correctamente");
                    editado = true;
                } else {
                    JOptionPane.showMessageDialog(root, resp);
                    txtUsuario.setText(Usuario);
                }
            }
            type = -1;
            txtUsuario.setEnabled(false);
        }
    }

    private void editarContrasenia() {
        String usuario = txtUsuario.getText();
        String claveActual = new String(pwdContraseniaActual.getPassword());
        String claveNueva = new String(pwdContraseniaNueva.getPassword());
        String repClaveNueva = new String(pwdRepContraseniaNueva.getPassword());
        if (claveActual.isEmpty() || claveNueva.isEmpty() || repClaveNueva.isEmpty()) {
            JOptionPane.showMessageDialog(root, "Llene todos los campos");
        } else if (claveNueva.equals(repClaveNueva)) {
            LoginVo loginVo = new LoginVo();
            loginVo.setUsuario(usuario);
            loginVo.setClave(AES.sha1(claveActual));
            if (verificarContrasenia(loginVo)) {
                PerfilDao dao = new PerfilDao();
                String resp = dao.editarContrasenia(Id, AES.sha1(claveNueva));
                if ("OK".equals(resp)) {
                    JOptionPane.showMessageDialog(root, "Cambio realizado correctamente");
                    editado = true;
                } else {
                    JOptionPane.showMessageDialog(root, resp);
                }
            } else {
                JOptionPane.showMessageDialog(root, "Contraseña actual erronea");
            }
        } else {
            JOptionPane.showMessageDialog(root, "Las contraseñas no coinciden");
        }
        pwdContraseniaActual.setText("");
        pwdContraseniaNueva.setText("");
        pwdRepContraseniaNueva.setText("");
        pwdContraseniaActual.setEnabled(false);
        pwdContraseniaNueva.setEnabled(false);
        pwdRepContraseniaNueva.setEnabled(false);
        type = -1;
    }

    private boolean verificarContrasenia(LoginVo loginVo) {
        LoginDao loginDao = new LoginDao();
        return loginDao.Ingresar(loginVo).getEstatus().equals(Login.ACCESO_CONCEDIDO);
    }

    private void init() {
        this.btnAceptar = this.root.getBtnAceptar();
        this.btnEditCon = this.root.getBtnEditCon();
        this.btnEditUsuario = this.root.getBtnEditUsuario();
        this.pwdContraseniaActual = this.root.getPwdContraseniaActual();
        this.pwdContraseniaNueva = this.root.getPwdContraseniaNueva();
        this.pwdRepContraseniaNueva = this.root.getPwdRepContraseniaNueva();
        this.txtUsuario = this.root.getTxtUsuario();
    }

    private DfrmPerfil root;
    private JButton btnAceptar;
    private JButton btnEditCon;
    private JButton btnEditUsuario;
    private JPasswordField pwdContraseniaActual;
    private JPasswordField pwdContraseniaNueva;
    private JPasswordField pwdRepContraseniaNueva;
    private JTextField txtUsuario;

    public boolean isEditado() {
        return editado;
    }

}
