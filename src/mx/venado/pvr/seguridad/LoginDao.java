package mx.venado.pvr.seguridad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mx.venado.pvr.modelo.conexion.BD;

public class LoginDao extends BD {

    private final static String TABLA_USUARIOS = "usuario";
    private final static String USUARIOS_ID = "usuario_id";
    private final static String USUARIOS_USUARIO = "usuario_usuario";
    private final static String USUARIOS_CLAVE = "usuario_clave";
    private final static String USUARIOS_TIPO = "usuario_tipo";

    private ResultSet rs = null;

    LoginVo loginVo = new LoginVo();

    public LoginDao() {
    }

    public LoginVo Ingresar(LoginVo vo) {
        try {
            if (AbrirConexion() != null) {
                Statement st = getConectar().createStatement();
                rs = st.executeQuery("SELECT " + USUARIOS_ID + ", " + USUARIOS_TIPO + ", " + USUARIOS_USUARIO + " FROM "
                        + TABLA_USUARIOS
                        + " WHERE " + USUARIOS_USUARIO + " = '" + vo.getUsuario() + "' AND "
                        + USUARIOS_CLAVE + " = '" + vo.getClave() + "' LIMIT 1");
                if (rs.next()) {
                    loginVo.setId(rs.getInt(1));
                    loginVo.setUsuario(rs.getString(3));
                    loginVo.setTipo(rs.getString(2));
                    loginVo.setEstatus(Login.ACCESO_CONCEDIDO);
                } else {
                    loginVo.setEstatus(Login.ACCESO_DENEGADO);
                }
            } else {
                loginVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            loginVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        } finally {
            CerrarConexion();
        }
        return loginVo;
    }

    public LoginVo verificarAdministrador(String clave) {
        try {
            if (AbrirConexion() != null) {
                Statement st = getConectar().createStatement();
                String SQL = "SELECT " + USUARIOS_ID
                        + " FROM " + TABLA_USUARIOS
                        + " WHERE " + USUARIOS_USUARIO + " = 'Administrador' AND " + USUARIOS_CLAVE + " = '" + clave + "' LIMIT 1";
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                    loginVo.setId(rs.getInt(1));
                    loginVo.setEstatus(Login.ACCESO_CONCEDIDO);
                } else {
                    loginVo.setEstatus(Login.ACCESO_DENEGADO);
                }
            } else {
                loginVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            loginVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        } finally {
            CerrarConexion();
        }
        return loginVo;
    }

}
