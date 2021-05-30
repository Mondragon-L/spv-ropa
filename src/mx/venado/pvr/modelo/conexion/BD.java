package mx.venado.pvr.modelo.conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.PropBd;

/**
 * Clase encargada de la conexión a la base de datos.<br>
 * Clase creada: 08 de diciembre de 2016<br>
 * Ultima edición: 03 de agosto de 2019<br>
 *
 * @version 3.0
 */
public class BD extends PropBd {

    public final static String CLASS_MYSQL = "org.gjt.mm.mysql.Driver";
    public final static String CLASS_MARIADB = "org.mariadb.jdbc.Driver";
    public final static String CLASS_SQLITE = "org.sqlite.JDBC";

    private String BD = "";
    private String Usuario = "";
    private String Contrasenia = "";
    private String url = "jdbc:mysql://localhost/";

    private Connection Conectar = null;

    private String Estatus = null;

    /**
     * Constructor de la clase BD.
     *
     */
    public BD() {
        super();
    }

    /**
     * Abre la conexión a la base de datos.
     *
     * @return null en caso de error en la conexión, de lo contrario devuelve el
     * valor de la conexión a la base de datos.
     */
    public Connection AbrirConexion() {
        try {
            cargar();
            this.BD = get("bd", null);
            this.Usuario = get("user", null);
            this.Contrasenia = get("password", "");
            this.url = get("url", "jdbc:mysql://localhost/");
            Class.forName(CLASS_MYSQL);
            Conectar = DriverManager.getConnection(url + BD, Usuario, Contrasenia);
            setEstatus(ConstantesPvr.OK);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            if (e instanceof IOException) {
                setEstatus(e.getMessage());
            } else if (e instanceof SQLException) {
                setEstatus("Error de conexión con el servidor.");
            } else if (e instanceof ClassNotFoundException) {
                setEstatus("Clase para la conexión no encontrada.");
            } else {
                setEstatus("Error de conexión con el servidor desconocido: " + e);
            }
        }
        return getConectar();
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     */
    public void CerrarConexion() {
        try {
            setEstatus(ConstantesPvr.OK);
            if (getConectar() != null && !getConectar().isClosed()) {
                getConectar().close();
            }
        } catch (SQLException e) {
            setEstatus("Error al cerrar la conexión");
        }
    }

    /**
     * @return Valor de conexión
     */
    public Connection getConectar() {
        return Conectar;
    }

    /**
     * @return Estatus de la conexión
     */
    public String getEstatusConexion() {
        return Estatus;
    }

    /**
     * @param Estatus Estatus de la conexión
     */
    private void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

}
