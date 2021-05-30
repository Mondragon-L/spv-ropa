package mx.venado.pvr.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.venado.pvr.excepciones.IdPvrException;
import mx.venado.pvr.modelo.conexion.BD;
import mx.venado.pvr.modelo.vo.ApartadoAbonoVo;
import mx.venado.pvr.modelo.vo.ApartadoProductoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.DatVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.Fecha;

public class ApartadoDao extends BD {

    ApartadoVo apartadoVo;
    ResultSet rs;

    private final String SQLRegApartado = "INSERT INTO "
            + ConstantesPvr.TABLA_APARTADO
            + " VALUES (?,?,?)";
    private final String SQLRegApartadoItems = "INSERT INTO "
            + ConstantesPvr.TABLA_APARTADOPRODUCTO
            + " VALUES (null,?,?,?,?,?,?,?,?,?,?)";
    private final String SQLEditApartado = "UPDATE "
            + ConstantesPvr.TABLA_APARTADO
            + " SET " + ConstantesPvr.APARTADO_TOTAL + " = ? WHERE "
            + ConstantesPvr.APARTADO_ID + " = ?";

    private final String SQLEditProducto = "UPDATE "
            + ConstantesPvr.TABLA_INVENTARIO
            + " SET " + ConstantesPvr.INVENTARIO_EXISTENCIAS + " = ? WHERE "
            + ConstantesPvr.INVENTARIO_LOTE + " = ?";

    public ApartadoDao() {
        apartadoVo = new ApartadoVo();
        rs = null;
    }

    public ApartadoVo Registro(ApartadoVo vo, ApartadoProductoVo vos, boolean esPrimerRegistro) {
        PreparedStatement addApartado = null;
        PreparedStatement editApartado = null;
        PreparedStatement addApartadoItems = null;
        PreparedStatement editExistencias = null;
        try {
            if (AbrirConexion() != null) {
                Fecha fecha = new Fecha();
                //inhablilita la opción de guardado automático
                getConectar().setAutoCommit(false);
                if (esPrimerRegistro) {
                    //generación de un nuevo id para el apartado
                    int nuevoid = NuevoIdApartdo(getConectar());
                    //como no se cuenta con apartados, se registra uno nuevo
                    addApartado = getConectar().prepareStatement(SQLRegApartado);
                    addApartado.setInt(1, nuevoid);
                    addApartado.setInt(2, vo.getIdCliente());
                    addApartado.setDouble(3, vo.getTotal());
                    addApartado.executeUpdate();

                    //registro del producto a apartar
                    addApartadoItems = getConectar().prepareStatement(SQLRegApartadoItems);
                    addApartadoItems.setInt(1, nuevoid);
                    addApartadoItems.setString(2, vos.getLote());
                    addApartadoItems.setString(3, vos.getProducto());
                    addApartadoItems.setString(4, fecha.getFecha());
                    addApartadoItems.setString(5, fecha.getHora());
                    addApartadoItems.setDouble(6, vos.getPrecioventa());
                    addApartadoItems.setDouble(7, 1);
                    addApartadoItems.setDouble(8, vos.getDescuento());
                    addApartadoItems.setDouble(9, vos.getTotal());
                    addApartadoItems.setString(10, vos.getEstatusProducto());
                    addApartadoItems.executeUpdate();

                    //Edita el total correspondiente al apartado recien creado 
                    editApartado = getConectar().prepareStatement(SQLEditApartado);
                    editApartado.setDouble(1, vos.getTotal());
                    editApartado.setInt(2, nuevoid);
                    editApartado.executeUpdate();

                    //Se editan las existencias en inventario
                    editExistencias = getConectar().prepareStatement(SQLEditProducto);
                    editExistencias.setDouble(1, (vos.getCantidad() - 1));
                    editExistencias.setString(2, vos.getLote());
                    editExistencias.executeUpdate();

                } else {
                    //Como ya se cuenta con apartados previos, se registra solamente el producto a apartar
                    addApartadoItems = getConectar().prepareStatement(SQLRegApartadoItems);
                    addApartadoItems.setInt(1, vo.getId());
                    addApartadoItems.setString(2, vos.getLote());
                    addApartadoItems.setString(3, vos.getProducto());
                    addApartadoItems.setString(4, fecha.getFecha());
                    addApartadoItems.setString(5, fecha.getHora());
                    addApartadoItems.setDouble(6, vos.getPrecioventa());
                    addApartadoItems.setDouble(7, 1);
                    addApartadoItems.setDouble(8, vos.getDescuento());
                    addApartadoItems.setDouble(9, vos.getTotal());
                    addApartadoItems.setString(10, vos.getEstatusProducto());
                    addApartadoItems.executeUpdate();

                    //Actualiza la cantidad total de la deuda correspondiente a los apartados
                    editApartado = getConectar().prepareStatement(SQLEditApartado);
                    editApartado.setDouble(1, (vo.getTotal() + vos.getTotal()));
                    editApartado.setDouble(2, vo.getId());
                    editApartado.executeUpdate();

                    //Se editan las existencias en inventario
                    editExistencias = getConectar().prepareStatement(SQLEditProducto);
                    editExistencias.setDouble(1, (vos.getCantidad() - 1));
                    editExistencias.setString(2, vos.getLote());
                    editExistencias.executeUpdate();
                }

                //Los cambios se hacen permanentes
                getConectar().commit();

                apartadoVo.setEstatus(ConstantesPvr.OK);
            } else {
                apartadoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException | IdPvrException e) { e.printStackTrace();
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
            }
            apartadoVo.setEstatus(err);
        } finally {
            try {
                if (addApartado != null) {
                    addApartado.close();
                }
                if (editApartado != null) {
                    editApartado.close();
                }
                if (addApartadoItems != null) {
                    addApartadoItems.close();
                }
                if (editExistencias != null) {
                    editExistencias.close();
                }
                CerrarConexion();
            } catch (SQLException ex) {
            }
        }
        return apartadoVo;
    }

    public ApartadoVo addDescuento(int idApartado, double totalApartadoFinal, 
            int idapartadoproducto, double descuento, double totalProductoFinal) {
        PreparedStatement pstDescuento = null;
        PreparedStatement pstApartadoTotal = null;
        try {
            if (AbrirConexion() != null) {
                getConectar().setAutoCommit(false);
                boolean[] executes = {false, false};
                String SQLDescuento = "UPDATE apartprod SET apartprod_descuento = ?, apartprod_total = ?  WHERE apartprod_id = ?";
                String SQLApartadoTotal = "UPDATE apartado SET apartado_total = ? WHERE apartado_id = ?";
                
                pstDescuento = getConectar().prepareStatement(SQLDescuento);
                pstDescuento.setDouble(1, descuento);
                pstDescuento.setDouble(2, totalProductoFinal);
                pstDescuento.setInt(3, idapartadoproducto);
                executes[0] = pstDescuento.executeUpdate() > 0;
                
                pstApartadoTotal = getConectar().prepareStatement(SQLApartadoTotal);
                pstApartadoTotal.setDouble(1, totalApartadoFinal);
                pstApartadoTotal.setInt(2, idApartado);
                executes[1] = pstApartadoTotal.executeUpdate() > 0;
                
                if (executes[0] && executes[1]) {
                    getConectar().commit();
                    apartadoVo.setEstatus(ConstantesPvr.OK);
                } else {
                    getConectar().rollback();
                    apartadoVo.setEstatus("Operación no realizada");
                }
                
            } else {
                apartadoVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            String err = "Error: " + ((e.getMessage() != null) ? e.getMessage() : e.toString());
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
            }
            apartadoVo.setEstatus(err);
        } finally {
            try {
                if (pstDescuento != null) {
                    pstDescuento.close();
                }
                if (pstApartadoTotal != null) {
                    pstApartadoTotal.close();
                }
            } catch (SQLException e) {
            }
            CerrarConexion();
        }
        return apartadoVo;
    }

    public ApartadoVo regresarAInventario(int idApartado, int idApartadoProducto, String lote, double totalApartadoFinal) {
        PreparedStatement pstDisminuyeTotalApartado = null;
        PreparedStatement pstEliminarAbonos = null;
        PreparedStatement pstObtenerInventario = null;
        PreparedStatement pstAumentarInventario = null;
        PreparedStatement pstEliminarProductoInventario = null;
        try {
            String SQLDisminuyeTotalApartado = "UPDATE apartado SET apartado_total = ? WHERE apartado_id = ?";
            String SQLEliminarAbonos = "DELETE FROM apartadoabono WHERE apartadoabono_idapartprod = ?";
            String SQLObtenerInventario = "SELECT inventario_existencias FROM inventario WHERE inventario_lote = ?";
            String SQLAumentarInventario = "UPDATE inventario SET inventario_existencias = ? WHERE inventario_lote = ?";
            String SQLEliminarProductoInventario = "DELETE FROM apartprod WHERE apartprod_id = ?";

            if (AbrirConexion() != null) {
                getConectar().setAutoCommit(false);
                boolean[] executes = {false, false, false, false, false};
                // 1 -> disminuye apartado_total => idapartado
                pstDisminuyeTotalApartado = getConectar().prepareStatement(SQLDisminuyeTotalApartado);
                pstDisminuyeTotalApartado.setDouble(1, totalApartadoFinal);
                pstDisminuyeTotalApartado.setInt(2, idApartado);
                executes[0] = pstDisminuyeTotalApartado.executeUpdate() > 0;

                //2 -> eliminan todos los abonos realizados al producto => idproductoapartado
                pstEliminarAbonos = getConectar().prepareStatement(SQLEliminarAbonos);
                pstEliminarAbonos.setInt(1, idApartadoProducto);
                pstEliminarAbonos.executeUpdate();
                executes[1] = true;

                //3 -> se aumenta una unidad al inventario => lote
                pstObtenerInventario = getConectar().prepareStatement(SQLObtenerInventario);
                pstObtenerInventario.setString(1, lote);
                rs = pstObtenerInventario.executeQuery();

                if (rs.next()) {
                    executes[2] = true;
                    double invent = rs.getDouble(1) + 1;
                    pstAumentarInventario = getConectar().prepareStatement(SQLAumentarInventario);
                    pstAumentarInventario.setDouble(1, invent);
                    pstAumentarInventario.setString(2, lote);
                    executes[3] = pstAumentarInventario.executeUpdate() > 0;
                }

                //4 -> se elimina el producto de los apartados => idproductoapartado
                pstEliminarProductoInventario = getConectar().prepareStatement(SQLEliminarProductoInventario);
                pstEliminarProductoInventario.setInt(1, idApartadoProducto);
                executes[4] = pstEliminarProductoInventario.executeUpdate() > 0;
                
                int numexecutes = executes.length;
                int executesok = 0;
                for (int i = 0; i < numexecutes; i++) {
                    if (executes[i]) {
                        executesok++;
                    }
                }
                
                if (numexecutes == executesok) {
                    getConectar().commit();
                    apartadoVo.setEstatus(ConstantesPvr.OK);
                } else {
                    getConectar().rollback();
                    apartadoVo.setEstatus("Operación no realizada");
                }

            } else {
                apartadoVo.setEstatus(getEstatusConexion());
            }

        } catch (SQLException e) {
            String err = "Error: " + ((e.getMessage() != null) ? e.getMessage() : e.toString());
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
            }
            apartadoVo.setEstatus(err);
        } finally {
            try {
                if (pstDisminuyeTotalApartado != null) {
                    pstDisminuyeTotalApartado.close();
                }
                if (pstEliminarAbonos != null) {
                    pstEliminarAbonos.close();
                }
                if (pstObtenerInventario != null) {
                    pstObtenerInventario.close();
                }
                if (pstAumentarInventario != null) {
                    pstAumentarInventario.close();
                }
                if (pstEliminarProductoInventario != null) {
                    pstEliminarProductoInventario.close();
                }
            } catch (SQLException ex) {
            }
            CerrarConexion();
        }
        return apartadoVo;
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
                String SQLList = "SELECT apartprod.*, inventario.inventario_producto "
                        + "FROM apartprod "
                        + "INNER JOIN inventario ON inventario.inventario_lote = apartprod.apartprod_lote "
                        + "WHERE apartprod.apartprod_idapartado = " + idApartado
                        + " ORDER BY apartprod.apartprod_id";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                while (rs.next()) {
                    ApartadoProductoVo apartado = new ApartadoProductoVo();
                    apartado.setId(rs.getInt("apartprod_id"));
                    apartado.setIdApartado(rs.getInt("apartprod_idapartado"));
                    apartado.setLote(rs.getString("apartprod_lote"));
                    apartado.setFecha(rs.getString("apartprod_fecha"));
                    apartado.setHora(rs.getString("apartprod_hora"));
                    apartado.setPrecioventa(rs.getDouble("apartprod_precioventa"));
                    apartado.setCantidad(rs.getDouble("apartprod_cantidad"));
                    apartado.setDescuento(rs.getDouble("apartprod_descuento"));
                    apartado.setTotal(rs.getDouble("apartprod_total"));
                    apartado.setEstatusProducto(rs.getString("apartprod_estatus"));
                    apartado.setProducto(rs.getString("inventario_producto"));
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

    public DatVo TotalAbonos(int idApartado) {
        DatVo datVo;
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT SUM(" + ConstantesPvr.TABLA_APARTADOABONO + "." + ConstantesPvr.APARTADOABONO_CANTIDAD + ") AS pagado "
                        + "FROM " + ConstantesPvr.TABLA_APARTADO
                        + " INNER JOIN " + ConstantesPvr.TABLA_APARTADOPRODUCTO
                        + " ON " + ConstantesPvr.TABLA_APARTADOPRODUCTO + "." + ConstantesPvr.APARTADOPRODUCTO_ID_APARTADO + " = " + ConstantesPvr.TABLA_APARTADO + "." + ConstantesPvr.APARTADO_ID
                        + " INNER JOIN " + ConstantesPvr.TABLA_APARTADOABONO
                        + " ON " + ConstantesPvr.TABLA_APARTADOABONO + "." + ConstantesPvr.APARTADOABONO_ID_APARTADOPRODUCTO + " = " + ConstantesPvr.TABLA_APARTADOPRODUCTO + "." + ConstantesPvr.APARTADOPRODUCTO_ID
                        + " WHERE " + ConstantesPvr.TABLA_APARTADO + "." + ConstantesPvr.APARTADO_ID + " = " + idApartado;
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                if (rs.next()) {
                    datVo = new DatVo(rs.getDouble(1), DatVo.OK);
                } else {
                    datVo = new DatVo(0, DatVo.OK);
                }
            } else {
                datVo = new DatVo(-1, getEstatusConexion());
            }
        } catch (SQLException e) {
            datVo = new DatVo(-1, (e.getMessage() != null) ? e.getMessage() : e.toString());
        } finally {
            CerrarConexion();
        }
        return datVo;
    }

    public ArrayList<ApartadoAbonoVo> ListarApartadoAbono(int idApartadoprod) {
        ArrayList<ApartadoAbonoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {
                String SQLList = "SELECT * FROM "
                        + ConstantesPvr.TABLA_APARTADOABONO
                        + " WHERE " + ConstantesPvr.APARTADOABONO_ID_APARTADOPRODUCTO + " = " + idApartadoprod
                        + " ORDER BY " + ConstantesPvr.APARTADOABONO_ID;
//                String SQLList = "SELECT SUM(" + ConstantesPvr.APARTADOABONO_CANTIDAD + ") AS pagado "
//                        + "FROM " + ConstantesPvr.TABLA_APARTADOABONO
//                        + " WHERE " + ConstantesPvr.APARTADOABONO_ID_APARTADOPRODUCTO + " = " + idApartadoprod 
//                        + " GROUP BY " + ConstantesPvr.APARTADOABONO_ID_APARTADOPRODUCTO;
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

    private int NuevoIdApartdo(Connection conexion) throws IdPvrException {
        int nuevoId = 0;
        try {
            String SQLList = "SELECT " + ConstantesPvr.APARTADO_ID
                    + " FROM " + ConstantesPvr.TABLA_APARTADO
                    + " ORDER BY " + ConstantesPvr.APARTADO_ID + " DESC LIMIT 1";
            Statement st = conexion.createStatement();
            rs = st.executeQuery(SQLList);
            if (rs.next()) {
                nuevoId = (rs.getInt(1) + 1);
            } else {
                nuevoId = 1;
            }
        } catch (SQLException e) {
            throw new IdPvrException("Error al crear nuevo id para apartado."
                    + ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
        return nuevoId;
    }

    public ApartadoProductoVo editarApartadoPoducto(ApartadoProductoVo dat, ApartadoVo datApartadoVo, String loteActual) {
        ApartadoProductoVo estatus = new ApartadoProductoVo();
        try {
            if (AbrirConexion() != null) {
                getConectar().setAutoCommit(false);
                int apartadoTotal = getApartadoTotal(getConectar(), datApartadoVo.getId());
                datApartadoVo.setTotal(apartadoTotal + dat.getDiferenciaPV());
                edit(getConectar(), datApartadoVo);
                editExistenciasSuma(getConectar(), loteActual);
                editExistenciasResta(getConectar(), dat.getLote());
//                editExistenciasSuma(getConectar(), dat.getLote());
//                editExistenciasResta(getConectar(), loteActual);
                editApartadoProd(getConectar(), dat);
                getConectar().commit();
                estatus.setEstatus(ConstantesPvr.OK);
            } else {
                estatus.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            try {
                getConectar().rollback();
                estatus.setEstatus(e.getMessage());
            } catch (SQLException ex) {
                estatus.setEstatus(ex.getMessage());
            }
        }
        return estatus;
    }

    public ApartadoProductoVo editarApartadoPoducto(ApartadoProductoVo dat, ApartadoVo datApartadoVo, double cambio, String loteActual) {
        ApartadoProductoVo estatus = new ApartadoProductoVo();
        try {
            if (AbrirConexion() != null) {
                getConectar().setAutoCommit(false);
                int apartadoTotal = getApartadoTotal(getConectar(), datApartadoVo.getId());
                datApartadoVo.setTotal(apartadoTotal + dat.getDiferenciaPV());
                edit(getConectar(), datApartadoVo);
                editExistenciasSuma(getConectar(), loteActual);
                editExistenciasResta(getConectar(), dat.getLote());
//                editExistenciasSuma(getConectar(), dat.getLote());
//                editExistenciasResta(getConectar(), loteActual);
                editApartadoProd(getConectar(), dat);
                if (cambio > 0) {
                    AbonoDao abonoDao = new AbonoDao();
                    ApartadoAbonoVo aav = new ApartadoAbonoVo();
                    aav.setIdApartado(dat.getId());
                    aav.setCantidad(-cambio);
                    aav.setObservacion("Cambio de productos");
                    abonoDao.Registro(aav, datApartadoVo.getTotal());
                }
                getConectar().commit();
                estatus.setEstatus(ConstantesPvr.OK);
            } else {
                estatus.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            try {
                getConectar().rollback();
                estatus.setEstatus(e.getMessage());
            } catch (SQLException ex) {
                estatus.setEstatus(ex.getMessage());
            }
        } finally {
            CerrarConexion();
        }
        return estatus;
    }

    private int getExistencias(Connection conexion, String lote) throws SQLException {
        int res;
        String SQLList = "SELECT " + ConstantesPvr.INVENTARIO_EXISTENCIAS
                + " FROM " + ConstantesPvr.TABLA_INVENTARIO
                + " WHERE " + ConstantesPvr.INVENTARIO_LOTE + " = '" + lote + "' ";
        Statement st = conexion.createStatement();
        rs = st.executeQuery(SQLList);
        if (rs.next()) {
            res = rs.getInt(1);
        } else {
            res = -1;
        }

        return res;
    }

    private boolean editExistenciasSuma(Connection conexion, String lote) throws SQLException {
        int existencias = getExistencias(conexion, lote);
        if (existencias != -1) {
            String SQL = "UPDATE inventario "
                    + "SET inventario_existencias = ? WHERE inventario_lote = ?";
            PreparedStatement pstInventario = conexion.prepareStatement(SQL);
            pstInventario.setInt(1, (existencias + 1));
            pstInventario.setString(2, lote);
            pstInventario.executeUpdate();
            return true;
        } else {
            throw new SQLException("Error al obtener las existencias del producto.");
        }

    }

    private boolean editExistenciasResta(Connection conexion, String lote) throws SQLException {
        int existencias = getExistencias(conexion, lote);
        if (existencias != -1) {
            String SQL = "UPDATE inventario "
                    + "SET inventario_existencias = ? WHERE inventario_lote = ?";
            PreparedStatement pstInventario = conexion.prepareStatement(SQL);
            pstInventario.setInt(1, (existencias - 1));
            pstInventario.setString(2, lote);
            pstInventario.executeUpdate();
            return true;
        } else {
            throw new SQLException("Error al obtener las existencias del producto.");
        }

    }

    private void editApartadoProd(Connection conexion, ApartadoProductoVo dat) throws SQLException {
        String SQL = "UPDATE apartprod SET apartprod_lote = ?, "
                + "apartprod_precioventa = ?, "
                + "apartprod_total = ?,  "
                + "apartprod_estatus = ? "
                + "WHERE apartprod_id = ?";
        PreparedStatement pstApartadoProd = conexion.prepareStatement(SQL);
        pstApartadoProd.setString(1, dat.getLote());
        pstApartadoProd.setDouble(2, dat.getPrecioventa());
        pstApartadoProd.setDouble(3, dat.getTotal());
        pstApartadoProd.setString(4, dat.getEstatusProducto());
        pstApartadoProd.setInt(5, dat.getId());
        pstApartadoProd.executeUpdate();
    }

    private int getApartadoTotal(Connection conexion, int idApartado) throws SQLException {
        int res;
        String SQLList = "SELECT " + ConstantesPvr.APARTADO_TOTAL
                + " FROM " + ConstantesPvr.TABLA_APARTADO
                + " WHERE " + ConstantesPvr.APARTADO_ID + " = " + idApartado + " ";
        Statement st = conexion.createStatement();
        rs = st.executeQuery(SQLList);
        if (rs.next()) {
            res = rs.getInt(1);
        } else {
            throw new SQLException("No se localizadon datos de apartado");
        }

        return res;
    }

    private void edit(Connection conexion, ApartadoVo dat) throws SQLException {
        String SQL = "UPDATE apartado SET apartado_total = ? "
                + "WHERE apartado_id = ?";
        PreparedStatement pst = conexion.prepareStatement(SQL);
        pst.setDouble(1, dat.getTotal());
        pst.setInt(2, dat.getId());
        pst.executeUpdate();
    }

    public ArrayList<ApartadoProductoVo> ListarApartadoItemsTMP() {
        ArrayList<ApartadoProductoVo> dat = new ArrayList<>();
        try {
            if (AbrirConexion() != null) {

                String SQLList = "SELECT * FROM apartprod";
                Statement st = getConectar().createStatement();
                rs = st.executeQuery(SQLList);
                while (rs.next()) {
                    ApartadoProductoVo apartado = new ApartadoProductoVo();
                    apartado.setId(rs.getInt(1));
                    apartado.setIdApartado(rs.getInt(2));
                    apartado.setLote(rs.getString(3));
                    apartado.setProducto(rs.getString(4));
                    apartado.setFecha(rs.getString(5));
                    apartado.setHora(rs.getString(6));
                    apartado.setPrecioventa(rs.getDouble(7));
                    apartado.setCantidad(rs.getDouble(8));
                    apartado.setDescuento(rs.getDouble(9));
                    apartado.setTotal(rs.getDouble(10));
                    apartado.setEstatusProducto(rs.getString(11));
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

}
