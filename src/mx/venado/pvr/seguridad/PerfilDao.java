package mx.venado.pvr.seguridad;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.venado.pvr.modelo.conexion.BD;

public class PerfilDao extends BD{

    public PerfilDao() {
    }

    public String editarUsuario(int id, String usr) {
        String r;
        try {
            if (AbrirConexion() != null) {
                String SQL = "UPDATE usuario set usuario_usuario = ? where usuario_id = ?";
                PreparedStatement ps = getConectar().prepareStatement(SQL);
                ps.setString(1, usr);
                ps.setInt(2, id);
                ps.executeUpdate();
                
                r = "OK";
            } else {
                r = getEstatusConexion();
            }
        } catch (SQLException e) {
            r = (e.getMessage() != null) ? e.getMessage() : e.toString();
        } finally {
            CerrarConexion();
        }
        return r;
    }
    
    public String editarContrasenia(int id, String contrasenia) {
        String r;
        try {
            if (AbrirConexion() != null) {
                String SQL = "UPDATE usuario set usuario_clave = ? where usuario_id = ?";
                PreparedStatement ps = getConectar().prepareStatement(SQL);
                ps.setString(1, contrasenia);
                ps.setInt(2, id);
                ps.executeUpdate();
                
                r = "OK";
            } else {
                r = getEstatusConexion();
            }
        } catch (SQLException e) {
            r = (e.getMessage() != null) ? e.getMessage() : e.toString();
        } finally {
            CerrarConexion();
        }
        return r;
    }
}
