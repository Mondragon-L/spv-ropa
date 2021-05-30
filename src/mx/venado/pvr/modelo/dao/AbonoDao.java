
package mx.venado.pvr.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.ApartadoAbonoVo;
import mx.venado.pvr.modelo.vo.ApartadoProductoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.TicketAbonoVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.Fecha;

public class AbonoDao extends BD {

    ApartadoAbonoVo abonoVo;
    ResultSet rs;

    private final String SQLRegAbono = "INSERT INTO "
            + ConstantesPvr.TABLA_APARTADOABONO
            + " VALUES (null,?,?,?,?,?)";

    public AbonoDao() {
        abonoVo = new ApartadoAbonoVo();
        rs = null;
    }

    public ApartadoAbonoVo Registro(ApartadoAbonoVo vo, double total) {
        PreparedStatement psAbono = null;
        try {
            if (AbrirConexion() != null) {
                Fecha fecha = new Fecha();
                //inhablilita la opción de guardado automático
                getConectar().setAutoCommit(false);

                psAbono = getConectar().prepareStatement(SQLRegAbono);

                psAbono.setInt(1, vo.getIdApartado());
                psAbono.setString(2, fecha.getFecha());
                psAbono.setString(3, fecha.getHora());
                psAbono.setDouble(4, vo.getCantidad());
                psAbono.setString(5, vo.getObservacion());
                psAbono.executeUpdate();

                getConectar().commit();
                
                verifLiquidado(vo.getIdApartado(), total);

                abonoVo.setEstatus(ConstantesPvr.OK);
            } else {
                abonoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
            }
            abonoVo.setEstatus(err);
        } finally {
            try {
                if (psAbono != null) {
                    psAbono.close();
                }
                CerrarConexion();
            } catch (SQLException ex) {
            }
        }
        return abonoVo;
    }

    public ArrayList<ApartadoAbonoVo> ListarAbonos(int idApartadoprod) {
        ArrayList<ApartadoAbonoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT * FROM "
                        + ConstantesPvr.TABLA_APARTADOABONO
                        + " WHERE " + ConstantesPvr.APARTADOABONO_ID_APARTADOPRODUCTO + " = " + idApartadoprod
                        + " ORDER BY " + ConstantesPvr.APARTADOABONO_ID + " DESC";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                while (rs.next()) {
                    ApartadoAbonoVo abono = new ApartadoAbonoVo();
                    abono.setId(rs.getInt(1));
                    abono.setIdApartado(rs.getInt(2));
                    abono.setFecha(rs.getString(3));
                    abono.setHora(rs.getString(4));
                    abono.setCantidad(rs.getDouble(5));
                    abono.setObservacion(rs.getString(6));
                    dat.add(abono);
                }
            } else {
                dat.clear();
                dat.add(new ApartadoAbonoVo(getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new ApartadoAbonoVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public String[] getUltimoAbono(int idApartadoprod) {
        String fecha[] = new String[2];
        fecha[0] = null;
        try {
            if (AbrirConexion() != null) {
                String SQL = "SELECT apartadoabono_fecha "
                        + "FROM apartadoabono "
                        + "WHERE apartadoabono_idapartprod = " + idApartadoprod
                        + " ORDER BY apartadoabono_id DESC LIMIT 1 ";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                    fecha[1] = rs.getString(1);
                } else {
                    fecha[1] = ConstantesPvr.NA;
                }
            } else {
                fecha[0] = getEstatusConexion();
            }
        } catch (SQLException e) {
            fecha[0] = (e.getMessage() != null) ? e.getMessage() : e.toString();
        } finally {
            CerrarConexion();
        }
        return fecha;
    }

    public int countAbonos(int idApartadoprod) {
        int numabonos = -1;
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT COUNT(*) AS 'total' FROM apartadoabono "
                        + "WHERE apartadoabono_idapartprod = " + idApartadoprod;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                numabonos = (rs.next() ? rs.getInt(1) : 0);
            } else {
                numabonos = -1;
            }
        } catch (SQLException e) {
            numabonos = -1;
        } finally {
            CerrarConexion();
        }
        return numabonos;
    }

    public double totalAbonos(int idApartadoprod) {
        double total = -1;
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT SUM(apartadoabono_cantidad) "
                        + "FROM apartadoabono "
                        + "WHERE apartadoabono_idapartprod = " + idApartadoprod;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                total = (rs.next() ? rs.getDouble(1) : 0);
            } else {
                total = -1;
            }
        } catch (SQLException e) {
            total = -1;
        } finally {
            CerrarConexion();
        }
        return total;
    }

    public ArrayList<ApartadoVo> ListarApartado(int idCliente) {
        ArrayList<ApartadoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT * FROM "
                        + ConstantesPvr.TABLA_APARTADO
                        + " WHERE " + ConstantesPvr.APARTADO_ID_CLIENTE + " = " + idCliente;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                while (rs.next()) {
                    ApartadoVo apartado = new ApartadoVo();
                    apartado.setId(rs.getInt(1));
                    apartado.setIdCliente(rs.getInt(2));
                    apartado.setTotal(rs.getDouble(3));
                    dat.add(apartado);
                }
            } else {
                dat.clear();
                dat.add(new ApartadoVo(getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new ApartadoVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ApartadoProductoVo> ListarApartadoItems(int idApartado) {
        ArrayList<ApartadoProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT * FROM "
                        + ConstantesPvr.TABLA_APARTADOPRODUCTO
                        + " WHERE " + ConstantesPvr.APARTADOPRODUCTO_ID_APARTADO + " = " + idApartado
                        + " ORDER BY " + ConstantesPvr.APARTADOPRODUCTO_ID;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                while (rs.next()) {
                    ApartadoProductoVo apartado = new ApartadoProductoVo();
                    apartado.setId(rs.getInt(1));
                    apartado.setIdApartado(rs.getInt(2));
                    apartado.setLote(rs.getString(3));
                    apartado.setFecha(rs.getString(4));
                    apartado.setHora(rs.getString(5));
                    apartado.setPrecioventa(rs.getDouble(6));
                    apartado.setCantidad(rs.getDouble(7));
                    apartado.setDescuento(rs.getDouble(8));
                    apartado.setTotal(rs.getDouble(9));
                    dat.add(apartado);
                }
            } else {
                dat.clear();
                dat.add(new ApartadoProductoVo(getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new ApartadoProductoVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public TicketAbonoVo AbonoTicket(int idApartado) {
        TicketAbonoVo tav = new TicketAbonoVo();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT apartadoabono.*, clientes.clientes_nombre, clientes.clientes_ap, "
                        + "apartprod.apartprod_lote, inventario.inventario_producto "
                        + "FROM apartadoabono "
                        + "INNER JOIN apartprod ON apartprod.apartprod_id = apartadoabono.apartadoabono_idapartprod "
                        + "INNER JOIN apartado ON apartado.apartado_id = apartprod.apartprod_idapartado "
                        + "INNER JOIN clientes ON clientes.clientes_id = apartado.apartado_idcliente "
                        + "INNER JOIN inventario ON inventario.inventario_lote = apartprod.apartprod_lote "
                        + "WHERE apartadoabono.apartadoabono_idapartprod = " + idApartado
                        + " ORDER BY apartadoabono.apartadoabono_id DESC LIMIT 1";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                if (rs.next()) {
                    tav.setId(rs.getInt(1));
                    tav.setIdApartado(rs.getInt(2));
                    tav.setFecha(rs.getString(3));
                    tav.setHora(rs.getString(4));
                    tav.setCantidad(rs.getDouble(5));
                    tav.setObservacion(rs.getString(6));
                    tav.setNombre(rs.getString(7));
                    tav.setApellidoPaterno(rs.getString(8));
                    tav.setLote(rs.getString(9) + " > " + rs.getString(10));
                    tav.setEstatus(ConstantesPvr.OK);
                }
            } else {
                tav.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            tav.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        } finally {
            CerrarConexion();
        }
        return tav;
    }

    private void verifLiquidado(int idApartadoprod, double total){
        double abonosReg = totalAbonos(idApartadoprod);
        if (abonosReg == total) {
            setLiquidado(idApartadoprod);
        }
    }
    
    public String setLiquidado(int idapartprod) {
        PreparedStatement psAbono = null;
        String r = "";
        try {
            if (AbrirConexion() != null) {
                String SQL = "UPDATE apartprod SET apartprod_estatus = '" + ConstantesPvr.LIQUIDADO + "' WHERE apartprod.apartprod_id = ?";
                psAbono = getConectar().prepareStatement(SQL);

                psAbono.setInt(1, idapartprod);
                psAbono.executeUpdate();
                r = ConstantesPvr.OK;

//                abonoVo.setEstatus(ConstantesPvr.OK);
            } else {
                r = getEstatusConexion();
//                abonoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            r = err;
//            abonoVo.setEstatus(err);
        } finally {
            CerrarConexion();
        }
        return r;
    }
}
