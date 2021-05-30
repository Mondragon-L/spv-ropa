package mx.venado.pvr.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.ClienteApartadoVo;
import mx.venado.pvr.modelo.vo.ClienteVo;
import mx.venado.pvr.utilidades.ConstantesPvr;

public class ClienteDao extends ConstantesPvr {

    private final BD bd;
    ClienteVo clienteVo;
    ResultSet rs;

    private final String SQLRegClient = "INSERT INTO "
            + TABLA_CLIENTES
            + " VALUES (null,?,?,?,?,?,?)";

    private final String SQLEditClient = "UPDATE " + TABLA_CLIENTES
            + " SET "
            + CLIENTES_NOMBRE + " = ?, "
            + CLIENTES_AP + " = ?, "
            + CLIENTES_AM + " = ?, "
            + CLIENTES_TELEFONO + " = ?, "
            + CLIENTES_CORREO + " = ?, "
            + CLIENTES_OBSERVACION + " = ? "
            + "WHERE " + CLIENTES_ID + " = ?";

    private final String SQLListClient = "SELECT * FROM "
            + TABLA_CLIENTES + " ORDER BY " + CLIENTES_ID;

    private final String SQLGetClient = "SELECT * FROM "
            + TABLA_CLIENTES + " WHERE " + CLIENTES_ID + " = ";

    private final String SQLElimClient = "DELETE FROM " + TABLA_CLIENTES + " WHERE " + CLIENTES_ID + " = ?";

    public ClienteDao() {
        bd = new BD();
        clienteVo = new ClienteVo();
        rs = null;
    }

    public ClienteVo Registro(ClienteVo vo) {
        try {
            if (bd.AbrirConexion() != null) {
                PreparedStatement pst = bd.getConectar().prepareStatement(SQLRegClient);
                pst.setString(1, vo.getNombre());
                pst.setString(2, vo.getApellidoPaterno());
                pst.setString(3, vo.getApellidoMaterno());
                pst.setString(4, vo.getTelefono());
                pst.setString(5, vo.getCorreo());
                pst.setString(6, vo.getObservacion());
                pst.executeUpdate();
                clienteVo.setEstatus(ConstantesPvr.OK);
            } else {
                clienteVo.setEstatus(bd.getEstatusConexion());
            }
        } catch (SQLException e) {
            clienteVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return clienteVo;
    }

    public ClienteVo Edicion(ClienteVo vo) {
        try {
            if (bd.AbrirConexion() != null) {
                PreparedStatement pst = bd.getConectar().prepareStatement(SQLEditClient);
                pst.setString(1, vo.getNombre());
                pst.setString(2, vo.getApellidoPaterno());
                pst.setString(3, vo.getApellidoMaterno());
                pst.setString(4, vo.getTelefono());
                pst.setString(5, vo.getCorreo());
                pst.setString(6, vo.getObservacion());
                pst.setInt(7, vo.getId());
                pst.executeUpdate();
                clienteVo.setEstatus(ConstantesPvr.OK);
            } else {
                clienteVo.setEstatus(bd.getEstatusConexion());
            }
        } catch (SQLException e) {
            clienteVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return clienteVo;
    }

    public ClienteVo Eliminar(ClienteVo vo) {
        try {
            if (bd.AbrirConexion() != null) {
                PreparedStatement pst = bd.getConectar().prepareStatement(SQLElimClient);
                pst.setInt(1, vo.getId());
                pst.executeUpdate();
                clienteVo.setEstatus(ConstantesPvr.OK);
            } else {
                clienteVo.setEstatus(bd.getEstatusConexion());
            }
        } catch (SQLException e) {
            clienteVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return clienteVo;
    }

    public ArrayList<ClienteVo> Listar() {
        ArrayList<ClienteVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQLListClient);
                while (rs.next()) {
                    ClienteVo cliente = new ClienteVo();
                    cliente.setId(rs.getInt(1));
                    cliente.setNombre(rs.getString(2));
                    cliente.setApellidoPaterno(rs.getString(3));
                    cliente.setApellidoMaterno(rs.getString(4));
                    cliente.setTelefono(rs.getString(5));
                    cliente.setCorreo(rs.getString(6));
                    cliente.setObservacion(rs.getString(7));
                    dat.add(cliente);
                }
            } else {
                dat.clear();
                ClienteVo cliente = new ClienteVo();
                cliente.setEstatus(bd.getEstatusConexion());
                dat.add(cliente);
            }
        } catch (Exception e) {
            dat.clear();
            ClienteVo cliente = new ClienteVo();
            cliente.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(cliente);
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ClienteVo> Listar(int id) {
        ArrayList<ClienteVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQLGetClient + id);
                while (rs.next()) {
                    ClienteVo cliente = new ClienteVo();
                    cliente.setId(rs.getInt(1));
                    cliente.setNombre(rs.getString(2));
                    cliente.setApellidoPaterno(rs.getString(3));
                    cliente.setApellidoMaterno(rs.getString(4));
                    cliente.setTelefono(rs.getString(5));
                    cliente.setCorreo(rs.getString(6));
                    cliente.setObservacion(rs.getString(7));
                    dat.add(cliente);
                }
            } else {
                dat.clear();
                ClienteVo cliente = new ClienteVo();
                cliente.setEstatus(bd.getEstatusConexion());
                dat.add(cliente);
            }
        } catch (Exception e) {
            dat.clear();
            ClienteVo cliente = new ClienteVo();
            cliente.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(cliente);
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ClienteApartadoVo> ListarNumApartados() {
        ArrayList<ClienteApartadoVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                String SQL = "SELECT clientes.clientes_id, clientes.clientes_nombre, "
                        + "clientes.clientes_ap, COUNT(apartprod.apartprod_idapartado) AS 'productos' "
                        + "FROM clientes "
                        + "LEFT JOIN apartado ON apartado.apartado_idcliente = clientes.clientes_id "
                        + "LEFT JOIN apartprod ON apartprod.apartprod_idapartado = apartado.apartado_id "
                        + "GROUP BY clientes.clientes_id ORDER BY clientes.clientes_id ";
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    ClienteApartadoVo cliente = new ClienteApartadoVo();
                    cliente.setId(rs.getInt(1));
                    cliente.setNombre(rs.getString(2));
                    cliente.setApellidoPaterno(rs.getString(3));
                    cliente.setProductos(rs.getInt(4));
                    dat.add(cliente);
                }
            } else {
                dat.clear();
                ClienteApartadoVo cliente = new ClienteApartadoVo();
                cliente.setEstatus(bd.getEstatusConexion());
                dat.add(cliente);
            }
        } catch (SQLException e) {
            dat.clear();
            ClienteApartadoVo cliente = new ClienteApartadoVo();
            cliente.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(cliente);
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ClienteApartadoVo> ListarNumApartados0() {
        ArrayList<ClienteApartadoVo> dat = new ArrayList<>();
        ArrayList<ClienteApartadoVo> tmp2 = new ArrayList<>();
        ArrayList<ClienteVo> tmp;
        BD bd1 = new BD();
        ResultSet rs1;
        try {
            if (bd1.AbrirConexion() != null) {
                tmp = this.Listar();
                if (!tmp.isEmpty()) {
                    String SQL = "SELECT clientes.clientes_id, clientes.clientes_nombre, "
                            + "clientes.clientes_ap, COUNT(apartprod.apartprod_idapartado) AS 'productos' "
                            + "FROM clientes "
                            + "LEFT JOIN apartado ON apartado.apartado_idcliente = clientes.clientes_id "
                            + "LEFT JOIN apartprod ON apartprod.apartprod_idapartado = apartado.apartado_id "
                            + "GROUP BY clientes.clientes_id ORDER BY clientes.clientes_id ";
                    Statement st = bd1.getConectar().createStatement();
                    rs1 = st.executeQuery(SQL);
                    while (rs1.next()) {
                        ClienteApartadoVo cliente = new ClienteApartadoVo();
                        cliente.setId(rs1.getInt(1));
                        cliente.setNombre(rs1.getString(2));
                        cliente.setApellidoPaterno(rs1.getString(3));
                        cliente.setProductos(rs1.getInt(4));
                        tmp2.add(cliente);
                    }
                    if (!tmp2.isEmpty()) {
                        int c = 0;
                        for (int i = 0; i < tmp.size(); i++) {
                            if (c >= tmp2.size()) {
                                ClienteApartadoVo cav = new ClienteApartadoVo();
                                cav.setId(tmp.get(i).getId());
                                cav.setNombre(tmp.get(i).getNombre());
                                cav.setApellidoPaterno(tmp.get(i).getApellidoPaterno());
                                cav.setProductos(0);
                                dat.add(cav);
                            } else {
                                if (tmp.get(i).getId() == tmp2.get(c).getId()) {
                                    dat.add(tmp2.get(c));
                                    c++;
                                } else {
                                    ClienteApartadoVo cav = new ClienteApartadoVo();
                                    cav.setId(tmp.get(i).getId());
                                    cav.setNombre(tmp.get(i).getNombre());
                                    cav.setApellidoPaterno(tmp.get(i).getApellidoPaterno());
                                    cav.setProductos(0);
                                    dat.add(cav);
                                }
                            }
                        }
                    }
                }
            } else {
                dat.clear();
                ClienteApartadoVo cliente = new ClienteApartadoVo();
                cliente.setEstatus(bd1.getEstatusConexion());
                dat.add(cliente);
            }
        } catch (SQLException e) {
            dat.clear();
            ClienteApartadoVo cliente = new ClienteApartadoVo();
            cliente.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(cliente);
        } finally {
            bd1.CerrarConexion();
        }
        return dat;
    }
}
