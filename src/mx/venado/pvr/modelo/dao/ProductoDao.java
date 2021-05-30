package mx.venado.pvr.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.utilidades.ConstantesPvr;

public class ProductoDao extends BD {

    ProductoVo productoVo;
    ResultSet rs;

    private final String SQLRegProd = "INSERT INTO "
            + ConstantesPvr.TABLA_INVENTARIO
            + " VALUES (null,?,?,?,?,?,?,?,?)";

    private String SQLEditProd = "";

    private final String SQLListProd = "SELECT * FROM "
            + ConstantesPvr.TABLA_INVENTARIO + " ORDER BY " + ConstantesPvr.INVENTARIO_ID;

    public ProductoDao() {
        productoVo = new ProductoVo();
        rs = null;
    }

    public ProductoVo Registro(ProductoVo vo) {
        try {
            if (AbrirConexion() != null) {
                PreparedStatement pst = getConectar().prepareStatement(SQLRegProd);
                pst.setString(1, vo.getLote());
                pst.setString(2, vo.getProducto());
                pst.setString(3, vo.getModelo());
                pst.setString(4, vo.getTalla());
                pst.setDouble(5, vo.getPcompra());
                pst.setDouble(6, vo.getPventa());
                pst.setDouble(7, vo.getExistencias());
                pst.setString(8, vo.getObservacion());
                pst.executeUpdate();
                productoVo.setEstatus(ConstantesPvr.OK);
            } else {
                productoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            productoVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return productoVo;
    }

    public ProductoVo Editar(ProductoVo vo) {
        try {
            if (AbrirConexion() != null) {
                SQLEditProd = "UPDATE "
                        + ConstantesPvr.TABLA_INVENTARIO
                        + " SET " + vo.getColumna() + " = ?"
                        + " WHERE " + ConstantesPvr.INVENTARIO_ID + " = ?";
                PreparedStatement pst = getConectar().prepareStatement(SQLEditProd);
                pst.setString(1, vo.getEditando());
                pst.setInt(2, vo.getId());
                pst.executeUpdate();
                productoVo.setEstatus(ConstantesPvr.OK);
            } else {
                productoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            productoVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return productoVo;
    }

    public ArrayList<ProductoVo> GetProducto(String loteproducto) {
        ArrayList<ProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                Statement st = getConectar().createStatement();
                rs = st.executeQuery("SELECT * FROM "
                        + ConstantesPvr.TABLA_INVENTARIO
                        + " WHERE " + ConstantesPvr.INVENTARIO_LOTE + " = '" + loteproducto
                        + "' ORDER BY " + ConstantesPvr.INVENTARIO_ID);
                while (rs.next()) {
                    ProductoVo producto = new ProductoVo();
                    producto.setId(rs.getInt(1));
                    producto.setLote(rs.getString(2));
                    producto.setProducto(rs.getString(3));
                    producto.setModelo(rs.getString(4));
                    producto.setTalla(rs.getString(5));
                    producto.setPcompra(rs.getDouble(6));
                    producto.setPventa(rs.getDouble(7));
                    producto.setExistencias(rs.getDouble(8));
                    producto.setObservacion(rs.getString(9));
                    dat.add(producto);
                }
            } else {
                dat.clear();
                ProductoVo producto = new ProductoVo();
                producto.setEstatus(getEstatusConexion());
                dat.add(producto);
            }
        } catch (SQLException e) {
            dat.clear();
            ProductoVo producto = new ProductoVo();
            producto.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(producto);
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ProductoVo> Listar() {
        ArrayList<ProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLListProd);
                while (rs.next()) {
                    ProductoVo producto = new ProductoVo();
                    producto.setId(rs.getInt(1));
                    producto.setLote(rs.getString(2));
                    producto.setProducto(rs.getString(3));
                    producto.setModelo(rs.getString(4));
                    producto.setTalla(rs.getString(5));
                    producto.setPcompra(rs.getDouble(6));
                    producto.setPventa(rs.getDouble(7));
                    producto.setExistencias(rs.getDouble(8));
                    producto.setObservacion(rs.getString(9));
                    dat.add(producto);
                }
            } else {
                dat.clear();
                ProductoVo producto = new ProductoVo();
                producto.setEstatus(getEstatusConexion());
                dat.add(producto);
            }
        } catch (SQLException e) {
            dat.clear();
            ProductoVo producto = new ProductoVo();
            producto.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(producto);
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ProductoVo> Listar(String busqueda, int enCarrito) {
        ArrayList<ProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String sql = "SELECT * FROM " + ConstantesPvr.TABLA_INVENTARIO
                        + " WHERE ("
                        + ConstantesPvr.INVENTARIO_LOTE + " LIKE '%" + busqueda
                        + "%' OR " + ConstantesPvr.INVENTARIO_PRODUCTO + " LIKE '%" + busqueda + "%') "
                        + "AND " + ConstantesPvr.INVENTARIO_EXISTENCIAS + " >= '" + (enCarrito + 1)
                        + "' ORDER BY " + ConstantesPvr.INVENTARIO_ID;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    ProductoVo producto = new ProductoVo();
                    producto.setId(rs.getInt(1));
                    producto.setLote(rs.getString(2));
                    producto.setProducto(rs.getString(3));
                    producto.setModelo(rs.getString(4));
                    producto.setTalla(rs.getString(5));
                    producto.setPcompra(rs.getDouble(6));
                    producto.setPventa(rs.getDouble(7));
                    producto.setExistencias(rs.getDouble(8));
                    producto.setObservacion(rs.getString(9));
                    producto.setEstatus(ConstantesPvr.OK);
                    dat.add(producto);
                }
            } else {
                dat.clear();
                ProductoVo producto = new ProductoVo();
                producto.setEstatus(getEstatusConexion());
                dat.add(producto);
            }
        } catch (SQLException e) {
            dat.clear();
            ProductoVo producto = new ProductoVo();
            producto.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(producto);
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public ArrayList<ProductoVo> Listar2(String busqueda, int enCarrito) {
        ArrayList<ProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String sql = "SELECT * FROM " + ConstantesPvr.TABLA_INVENTARIO
                        + " WHERE "
                        + ConstantesPvr.INVENTARIO_LOTE + " = '" + busqueda
                        + "' AND " + ConstantesPvr.INVENTARIO_EXISTENCIAS + " >= '" + enCarrito
                        + "' ORDER BY " + ConstantesPvr.INVENTARIO_ID;
                
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    ProductoVo producto = new ProductoVo();
                    producto.setId(rs.getInt(1));
                    producto.setLote(rs.getString(2));
                    producto.setProducto(rs.getString(3));
                    producto.setModelo(rs.getString(4));
                    producto.setTalla(rs.getString(5));
                    producto.setPcompra(rs.getDouble(6));
                    producto.setPventa(rs.getDouble(7));
                    producto.setExistencias(rs.getDouble(8));
                    producto.setObservacion(rs.getString(9));
                    producto.setEstatus(ConstantesPvr.OK);
                    dat.add(producto);
                }
            } else {
                dat.clear();
                ProductoVo producto = new ProductoVo();
                producto.setEstatus(getEstatusConexion());
                dat.add(producto);
            }
        } catch (SQLException e) {
            dat.clear();
            ProductoVo producto = new ProductoVo();
            producto.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
            dat.add(producto);
        } finally {
            CerrarConexion();
        }
        return dat;
    }

    public ProductoVo testreg(ProductoVo vo) {
        try {
            if (AbrirConexion() != null) {
                PreparedStatement pst = getConectar().prepareStatement(SQLRegProd);
                for (int i = 0; i < 20000; i++) {
                    vo.setLote("CS" + i);
                    vo.setProducto("SUDADERA0" + i);
                    vo.setModelo("H" + i);

                    pst.setString(1, vo.getLote());
                    pst.setString(2, vo.getProducto());
                    pst.setString(3, vo.getModelo());
                    pst.setDouble(4, vo.getPcompra());
                    pst.setDouble(5, vo.getPventa());
                    pst.setDouble(6, vo.getExistencias());
                    pst.setString(7, vo.getObservacion());
                    pst.addBatch();
                }

                pst.executeBatch();
                productoVo.setEstatus(ConstantesPvr.OK);
            } else {
                productoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            productoVo.setEstatus((e.getMessage() != null) ? e.getMessage() : e.toString());
        }
        return productoVo;
    }
    
    public Object getAgotados() {
        Object resp = 0;
        try {
            if (AbrirConexion() != null) {
                Statement st = getConectar().createStatement();
                String SQL = "SELECT COUNT(*) AS 'agotados' FROM inventario WHERE inventario_existencias = 0";
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                    resp = rs.getInt(1);
                }
                
            } else {
                resp = getEstatusConexion();
            }
        } catch (SQLException e) {
            resp = (e.getMessage() != null) ? e.getMessage() : e.toString();
        } finally {
            CerrarConexion();
        }
        
        return resp;
    }

}
