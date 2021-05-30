package mx.venado.pvr.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.venado.pvr.excepciones.IdPvrException;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.TicketVo;
import mx.venado.pvr.modelo.vo.VentaVo;
import mx.venado.pvr.modelo.vo.VntProdVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.Fecha;

public class VentaDao extends BD {

    VentaVo ventaVo;
    ResultSet rs;

    public VentaDao() {
        ventaVo = new VentaVo();
        rs = null;
    }

    public VentaVo RegistroVenta(VentaVo vo, ArrayList<VntProdVo> items) {
        PreparedStatement psVenta = null;
        PreparedStatement psItems = null;
        PreparedStatement psUpProd = null;
        try {
            if (AbrirConexion() != null) {
                
                Fecha fecha = new Fecha();
                //inhablilita la opción de guardado automático
                getConectar().setAutoCommit(false);

                int nuevoid = NuevoId(getConectar());

                String SQLVenta = "INSERT INTO " + ConstantesPvr.TABLA_VENTA
                        + " VALUES (?,?,?,?,?,?,?)";

                psVenta = getConectar().prepareStatement(SQLVenta);
                psVenta.setInt(1, nuevoid);
                psVenta.setString(2, fecha.getFecha());
                psVenta.setString(3, fecha.getHora());
                psVenta.setDouble(4, vo.getTotal());
                psVenta.setDouble(5, vo.getImporte());
                psVenta.setDouble(6, vo.getCambio());
                psVenta.setInt(7, vo.getVendedor());
                psVenta.executeUpdate();

                String SQLItems = "INSERT INTO " + ConstantesPvr.TABLA_VNTPROD
                        + " VALUES (null,?,?,?,?,?,?,?,?,?,?)";
                psItems = getConectar().prepareStatement(SQLItems);
                VntProdVo prodVo;
                for (int i = 0; i < items.size(); i++) {
                    prodVo = items.get(i);
                    psItems.setString(1, prodVo.getLote());
                    psItems.setString(2, prodVo.getProducto());
                    psItems.setDouble(3, prodVo.getCantidad());
                    psItems.setDouble(4, prodVo.getPcompra());
                    psItems.setDouble(5, prodVo.getPventa());
                    psItems.setDouble(6, prodVo.getTotal());
                    psItems.setString(7, prodVo.getObservacion());
                    psItems.setDouble(8, prodVo.getDescuento());
                    psItems.setString(9, prodVo.getConceptoDescuento());
                    psItems.setInt(10, nuevoid);
                    psItems.addBatch();
                }
                psItems.executeBatch();

                String SQLUpdateProd = "UPDATE " + ConstantesPvr.TABLA_INVENTARIO
                        + " SET " + ConstantesPvr.INVENTARIO_EXISTENCIAS + " = ?"
                        + " WHERE " + ConstantesPvr.INVENTARIO_LOTE + " = ?";
                psUpProd = getConectar().prepareStatement(SQLUpdateProd);
                editExistencias(getConectar(), psUpProd, items, nuevoid);
                
                getConectar().commit();

                ventaVo.setEstatus(ConstantesPvr.OK);
            } else {
                ventaVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException | IdPvrException e) {
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : "");
            }
            ventaVo.setEstatus(err);
        } finally {
            try {
                if (psVenta != null) {
                    psVenta.close();
                }
                if (psItems != null) {
                    psItems.close();
                }
                if (psUpProd != null) {
                    psUpProd.close();
                }
            } catch (SQLException ex) {
            }
            CerrarConexion();
        }
        return ventaVo;
    }

    public ArrayList<TicketVo> ticket() {
        ArrayList<TicketVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT * FROM "
                        + ConstantesPvr.TABLA_VENTA
                        + " ORDER BY " + ConstantesPvr.VENTA_ID + " DESC LIMIT 1";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                if (rs.next()) {
                    TicketVo ticketVo = new TicketVo();
                    ticketVo.setVentaId(rs.getInt(1));
                    ticketVo.setVentaFecha(rs.getString(2));
                    ticketVo.setVentaHora(rs.getString(3));
                    ticketVo.setVentaTotal(rs.getDouble(4));
                    ticketVo.setVentaImporte(rs.getDouble(5));
                    ticketVo.setVentaCambio(rs.getDouble(6));
                    ticketVo.setVentaVendedor(rs.getInt(7));
                    ticketVo.setEstatus(ConstantesPvr.OK);
                    dat.add(ticketVo);
                    SQLList = "SELECT vntprod.*, inventario.inventario_producto "
                            + "FROM vntprod "
                            + "INNER JOIN inventario "
                            + "ON inventario.inventario_lote = vntprod.vntprod_lote "
                            + "WHERE vntprod.vntprod_venta_id = " + ticketVo.getVentaId();
//                    SQLList = "SELECT vntprod.*, inventario.inventario_producto "
//                            + "FROM vntprod "
//                            + "INNER JOIN inventario "
//                            + "ON inventario.inventario_lote = vntprod.vntprod_lote "
//                            + "WHERE vntprod.vntprod_venta_id = " + ticketVo.getVentaId();
                    try (Statement st2 = getConectar().createStatement();
                            ResultSet rsp = st2.executeQuery(SQLList)) {
                        while (rsp.next()) {
                            TicketVo tv = new TicketVo();
                            tv.setProductoId(rsp.getInt("vntprod_id"));
                            tv.setProductoLote(rsp.getString("vntprod_lote"));
                            tv.setProductoDescripcion(rsp.getString("vntprod_producto"));
                            tv.setProductoCantidad(rsp.getDouble("vntprod_cantidad"));
                            tv.setProductoPventa(rsp.getDouble("vntprod_pventa"));
                            tv.setProductoTotal(rsp.getDouble("vntprod_total"));
                            tv.setProductoObservacion(rsp.getString("vntprod_observacion"));
//                            tv.setProductoDescripcion(rsp.getString(10));
                            dat.add(tv);
                        }
                    }
                }
            } else {
                dat.clear();
                dat.add(new TicketVo(getEstatusConexion()));
            }
        } catch (SQLException e) {
            dat.clear();
            dat.add(new TicketVo((e.getMessage() != null) ? e.getMessage() : "Error"));
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    private int NuevoId(Connection conexion) throws IdPvrException {
        int nuevoId = 0;
        try {
            String SQLList = "SELECT " + ConstantesPvr.VENTA_ID
                    + " FROM " + ConstantesPvr.TABLA_VENTA
                    + " ORDER BY " + ConstantesPvr.VENTA_ID + " DESC LIMIT 1";
            Statement st = conexion.createStatement();
            rs = st.executeQuery(SQLList);
            if (rs.next()) {
                nuevoId = (rs.getInt(1) + 1);
            } else {
                nuevoId = 1;
            }
        } catch (SQLException e) {
            throw new IdPvrException("Error al crear nuevo id para la venta."
                    + ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
        return nuevoId;
    }

    private void editExistencias(Connection conexion, PreparedStatement ps, ArrayList<VntProdVo> items, int idventa) throws SQLException {
        String sql = "SELECT inventario.inventario_existencias, inventario.inventario_lote "
                + "FROM inventario "
                + "INNER JOIN venta ON venta.venta_id = " + idventa
                + " INNER JOIN vntprod ON vntprod.vntprod_venta_id = venta.venta_id "
                + "AND vntprod.vntprod_lote = inventario.inventario_lote";
        Statement st = conexion.createStatement();
        ResultSet pst = st.executeQuery(sql);
        ArrayList<Object[]> array = new ArrayList<>();
        while (pst.next()) {
            array.add(new Object[]{pst.getInt(1), pst.getString(2)});
        }
        if (!array.isEmpty()) {
            double nuevaexist;
            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < array.size(); j++) {
                    if (items.get(i).getLote().equals(array.get(j)[1].toString())) {
                        nuevaexist = Double.parseDouble(array.get(i)[0].toString()) - items.get(i).getCantidad();
                        ps.setDouble(1, nuevaexist);
                        ps.setString(2, items.get(i).getLote());
                        ps.addBatch();
                        break;
                    }
                }
            }
            ps.executeBatch();
        } else {
            throw new SQLException("Existencias no encontradas...");
        }
    }

}
