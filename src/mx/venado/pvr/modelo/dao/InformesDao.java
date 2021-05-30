package mx.venado.pvr.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.AbonoInformeVo;
import mx.venado.pvr.modelo.vo.VentasInformesVo;
import mx.venado.pvr.modelo.vo.VentasPorProductosVo;
import mx.venado.pvr.utilidades.ConstantesPvr;

public class InformesDao extends ConstantesPvr {

    ResultSet rs;
    BD bd;

    public InformesDao() {
        bd = new BD();
    }

    public ArrayList<VentasInformesVo> GetVentas(String fi, String ff) {
        ArrayList<VentasInformesVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                String SQL = "SELECT "
                        + VVENTA_ID + ", "
                        + VVENTA_FECHA + ", "
                        + VVENTA_HORA + ", "
                        + VVENTA_TOTAL + ", "
                        + "SUM(" + VVNTPROD_CANTIDAD + ") AS productos, "
                        + UUSUARIO_TIPO
                        + " FROM " + TABLA_VENTA
                        + " INNER JOIN " + TABLA_VNTPROD + " ON " + VVNTPROD_VENTAID + " = " + VVENTA_ID
                        + " INNER JOIN " + TABLA_USUARIO + " ON " + UUSUARIO_ID + " = " + VVENTA_VENDEDOR
                        + " GROUP BY " + VVENTA_ID;
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    VentasInformesVo vent = new VentasInformesVo();
                    vent.setId(rs.getInt(1));
                    vent.setFecha(rs.getString(2));
                    vent.setHora(rs.getString(3));
                    vent.setTotal(rs.getDouble(4));
                    vent.setCantidadProductos(rs.getInt(5));
                    vent.setVendedor(rs.getString(6));
                    if (!fi.equals(ConstantesPvr.NR) && !ff.equals(ConstantesPvr.NR)) {
                        if (DentroRango(fi, ff, vent.getFecha())) {
                            dat.add(vent);
                        }
                    } else {
                        dat.add(vent);
                    }
                }
            } else {
                dat.clear();
                dat.add(new VentasInformesVo(bd.getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new VentasInformesVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }

    public ArrayList<VentasPorProductosVo> VentasPorProducto(String fi, String ff) {
        ArrayList<VentasPorProductosVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                String SQL = "SELECT "
                        + VVENTA_FECHA + ", "
                        + VVENTA_TOTAL + ", "
                        + VVNTPROD_CANTIDAD + ", "
                        + VVNTPROD_PVENTA + ", "
                        + IINVENTARIO_LOTE + ", "
                        + IINVENTARIO_PRODUCTO + ", "
                        + IINVENTARIO_PCOMPRA
                        + " FROM " + TABLA_VNTPROD
                        + " INNER JOIN " + TABLA_INVENTARIO + " ON " + IINVENTARIO_LOTE + " = " + VVNTPROD_LOTE
                        + " INNER JOIN " + TABLA_VENTA + " ON " + VVENTA_ID + " = " + VVNTPROD_VENTAID;
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    VentasPorProductosVo vppv = new VentasPorProductosVo();
                    vppv.setFecha(rs.getString(VENTA_FECHA));
                    vppv.setTotal(rs.getDouble(VENTA_TOTAL));
                    vppv.setCantidad(rs.getInt(VNTPROD_CANTIDAD));
                    vppv.setPVenta(rs.getDouble(VNTPROD_PVENTA));
                    vppv.setLote(rs.getString(INVENTARIO_LOTE));
                    vppv.setProducto(rs.getString(INVENTARIO_PRODUCTO));
                    vppv.setPCompra(rs.getDouble(INVENTARIO_PCOMPRA));

                    if (!fi.equals(ConstantesPvr.NR) && !ff.equals(ConstantesPvr.NR)) {
                        if (DentroRango(fi, ff, vppv.getFecha())) {
                            dat.add(vppv);
                        }
                    } else {
                        dat.add(vppv);
                    }
                }
            } else {
                dat.clear();
                dat.add(new VentasPorProductosVo(bd.getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new VentasPorProductosVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }

    public ArrayList<AbonoInformeVo> GetAbonos(String fi, String ff) {
        ArrayList<AbonoInformeVo> dat = new ArrayList<>();
        try {
            if (bd.AbrirConexion() != null) {
                String SQL = "SELECT "
                        + "clientes.clientes_id, "
                        + "clientes.clientes_nombre, "
                        + "inventario.inventario_producto, "
                        + "apartprod.apartprod_lote, "
                        + "apartadoabono.apartadoabono_fecha, "
                        + "apartadoabono.apartadoabono_hora, "
                        + "apartadoabono.apartadoabono_cantidad, "
                        + "apartadoabono.apartadoabono_observacion "
                        + "FROM apartado "
                        + "INNER JOIN clientes ON clientes.clientes_id = apartado.apartado_idcliente "
                        + "INNER JOIN apartprod ON apartprod.apartprod_idapartado = apartado.apartado_id "
                        + "INNER JOIN apartadoabono ON apartadoabono.apartadoabono_idapartprod = apartprod.apartprod_id "
                        + "INNER JOIN inventario ON inventario.inventario_lote = apartprod.apartprod_lote";
                Statement st = bd.getConectar().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    AbonoInformeVo abono = new AbonoInformeVo();
                    abono.setClienteId(rs.getInt(1));
                    abono.setCliente(rs.getString(2));
                    abono.setProducto(rs.getString(3));
                    abono.setLote(rs.getString(4));
                    abono.setFecha(rs.getString(5));
                    abono.setHora(rs.getString(6));
                    abono.setCantidad(rs.getDouble(7));
                    abono.setObservación(rs.getString(8));
                    if (!fi.equals(ConstantesPvr.NR) && !ff.equals(ConstantesPvr.NR)) {
                        if (DentroRango(fi, ff, abono.getFecha())) {
                            dat.add(abono);
                        }
                    } else {
                        dat.add(abono);
                    }

                }
            } else {
                dat.clear();
                dat.add(new AbonoInformeVo(bd.getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new AbonoInformeVo((e.getMessage() != null) ? e.getMessage() : e.toString()));
        } finally {
            bd.CerrarConexion();
        }
        return dat;
    }
    
    private boolean DentroRango(String rango1, String rango2, String fTest) {
        try {
            SimpleDateFormat sfecha = new SimpleDateFormat("dd/MM/yyyy");
            Date rango_1 = sfecha.parse(rango1);
            Date rango_2 = sfecha.parse(rango2);
            Date test = sfecha.parse(fTest);

            return ((test.after(rango_1) || test.equals(rango_1)) && (test.before(rango_2) || test.equals(rango_2)));
        } catch (ParseException e) {
            return false;
        }
    }
}
