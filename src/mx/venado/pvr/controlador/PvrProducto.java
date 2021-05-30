
package mx.venado.pvr.controlador;

import ds.desktop.notify.DesktopNotify;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;
import mx.venado.pvr.modelo.dao.ApartadoDao;
import mx.venado.pvr.modelo.dao.ProductoDao;
import mx.venado.pvr.modelo.vo.ApartadoProductoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.InformacionIcono;
import mx.venado.pvr.utilidades.PDF;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.Window;
import mx.venado.pvr.vista.ModeloAgregarApartado;
import mx.venado.pvr.vista.ModeloProductos;
import mx.venado.pvr.vista.TableCellRendererProductos;
import pvr.PnlExistencias;
import pvr.PnlPrecios;
import pvr.PnlTexto;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrProducto implements IPvrConstant {

    ModeloProductos modelo;
    ModeloAgregarApartado modAgregarApartado;
    TableRowSorter filtro;

    JTable tblProductos;
    JButton btnGuardar;
    JButton btnNuevo;
    JScrollPane scrllpnObservaciones;
    JTextField txtBuscar;
    JTextField txtExistencias;
    JTextField txtLote;
    JTextField txtModelo;
    JTextArea txtObservaciones;
    JTextField txtPrecioCompra;
    JTextField txtPrecioVenta;
    JTextField txtProducto;
    JTextField txtTalla;
    JInternalFrame form;
    JLabel lblEstatus;
    JLabel lblProductosRegistrados;
    JLabel lblIdCliente;
    JButton btnAgregarApartado;
    JButton btnCambio;
    JButton btnExportar;
    JLabel lblIdApartado;
    JLabel lblMsj;
    JLabel lblProductoAnalisis;
    JDialog frm;
    JFrame parent;

    UtilidadesPvr utilPvr = new UtilidadesPvr();
    ProductoVo productoVo;
    ProductoDao productoDao;
    ApartadoVo datApartadoVo;
    String err;

    /**
     * Tipo de usuario logeado
     */
    private String tipo = "STD";
    /**
     * Id del usuario logeado
     */
    private int idusuario = -1;

    public PvrProducto(JFrame parent, JInternalFrame form, String tipo, int idusuario, JTable tblProductos,
            JButton btnGuardar, JButton btnNuevo, JScrollPane scrllpnObservaciones,
            JTextField txtBuscar, JTextField txtExistencias, JTextField txtLote,
            JTextField txtModelo, JTextArea txtObservaciones, JTextField txtPrecioCompra,
            JTextField txtPrecioVenta, JTextField txtProducto, JLabel lblEstatus,
            JLabel lblProductosRegistrados, JLabel lblMsj, JButton btnExportar, JLabel lblProductoAnalisis,
            JTextField txtTalla) {
        this.form = form;
        this.tblProductos = tblProductos;
        this.btnGuardar = btnGuardar;
        this.btnNuevo = btnNuevo;
        this.scrllpnObservaciones = scrllpnObservaciones;
        this.txtBuscar = txtBuscar;
        this.txtExistencias = txtExistencias;
        this.txtLote = txtLote;
        this.txtModelo = txtModelo;
        this.txtObservaciones = txtObservaciones;
        this.txtPrecioCompra = txtPrecioCompra;
        this.txtPrecioVenta = txtPrecioVenta;
        this.txtProducto = txtProducto;
        this.txtTalla = txtTalla;
        this.lblEstatus = lblEstatus;
        this.lblProductosRegistrados = lblProductosRegistrados;
        this.lblMsj = lblMsj;
        this.parent = parent;
        this.tipo = tipo;
        this.idusuario = idusuario;
        this.btnExportar = btnExportar;
        this.lblProductoAnalisis = lblProductoAnalisis;

        modelo = new ModeloProductos();
        productoDao = new ProductoDao();

        this.form.setTitle(Window.createId("Inventario"));

        CrearModelo();
        Listeners();
    }

    private void ConfigForm() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        if ((d.width <= 900) || (parent.getWidth() <= 900)) {
            tblProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            tblProductos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
    }

    /**
     * Constructor especial para el formulario DfrmAgregarApartados
     *
     * @param frm
     * @param txtBuscar
     * @param tblProductos
     * @param lblIdCliente
     * @param lblIdApartado
     * @param btnAgregarApartado
     */
    public PvrProducto(JDialog frm, JTextField txtBuscar, JTable tblProductos,
            JLabel lblIdCliente, JLabel lblIdApartado, JButton btnAgregarApartado) {
        this.txtBuscar = txtBuscar;
        this.tblProductos = tblProductos;
        this.lblIdCliente = lblIdCliente;
        this.btnAgregarApartado = btnAgregarApartado;
        this.lblIdApartado = lblIdApartado;
        this.frm = frm;

        modAgregarApartado = new ModeloAgregarApartado();
        productoDao = new ProductoDao();
        CrearModeloApartado();
        ListenersApartado();

    }

    public void setDatosApartado(ApartadoVo apartadoVo) {
        this.datApartadoVo = apartadoVo;
    }

    private void CrearModelo() {
        tblProductos.setDefaultRenderer(Object.class, new TableCellRendererProductos());
        tblProductos.setModel(modelo);
        tblProductos.setRowHeight(50);
        tblProductos.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(40);
    }

    private void CrearModeloApartado() {
        tblProductos.setDefaultRenderer(Object.class, new TableCellRendererProductos(true));
        tblProductos.setModel(modAgregarApartado);
        tblProductos.setRowHeight(50);
        tblProductos.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(40);
        ListarAp();
    }

    private void ListenersApartado() {
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtro = new TableRowSorter(modAgregarApartado);
                filtro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 1, 2));
                tblProductos.setRowSorter(filtro);
            }
        });

        btnAgregarApartado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblProductos.getSelectedRow() != -1) {
                    NuevoApartado("-".equals(lblIdApartado.getText()));
                    frm.dispose();
                } else {
                    DesktopNotify.showDesktopMessage("Ups", "Seleccione un producto...", DesktopNotify.FAIL);
//                utilPvr.SetLog(lblEstatus, ConstantesPvr.NR, "Seleccione un producto...");
                }
            }
        });
    }

    private void Listeners() {
        form.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                ConfigForm();
                Listar();
            }
        });
        form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                ConfigForm();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Guardar();
            }
        });
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nuevo();
                utilPvr.SetLog(lblMsj, ConstantesPvr.MSJ, "<pvr>");
            }
        });
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportar();
            }
        });
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtLote, "Lote");
            }
        });
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtProducto, "Producto");
            }
        });
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtModelo, "Modelo (opcional)");
            }
        });
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtPrecioCompra, "Precio de compra");
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtPrecioVenta, "Precio de venta");
            }
        });
        txtExistencias.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtExistencias, "Existencias");
            }
        });
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(scrllpnObservaciones, txtObservaciones, "Observaciones (opcional)");
            }
        });
        tblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblProductos.getSelectedRow();
                int columna = tblProductos.getSelectedColumn();
                Object valorActual;
                Object nuevovalor;
                int resp;
                PnlTexto pnlTexto;

                if (evt.isAltDown() && columna == 8) {
                    int idprod = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
                    valorActual = tblProductos.getValueAt(fila, columna).toString();
                    pnlTexto = new PnlTexto();
                    pnlTexto.setValue(valorActual.toString());
                    resp = JOptionPane.showConfirmDialog(parent, pnlTexto, "Editar observaciones del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (resp == JOptionPane.OK_OPTION) {
                        nuevovalor = pnlTexto.getValue();
                        if (!nuevovalor.toString().equals(valorActual.toString())) {
                            Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_OBSERVACION));
                        }
                    }
                } else if (evt.getClickCount() == 1) {
                    if (columna == 7) {
                        Object val = tblProductos.getValueAt(fila, columna);
                        JOptionPane.showInternalMessageDialog(tblProductos, val, "Observacion", JOptionPane.OK_OPTION, new InformacionIcono());
                    }
                } else if (evt.getClickCount() == 2) {
                    int idprod = Integer.parseInt(tblProductos.getValueAt(fila, 0).toString());
                    PnlPrecios pnlPrecios;
                    switch (columna) {
                        case 2:
                            pnlTexto = new PnlTexto();
                            valorActual = tblProductos.getValueAt(fila, columna);
                            pnlTexto.setValue(valorActual.toString());
                            resp = JOptionPane.showConfirmDialog(parent, pnlTexto, "Editar nombre del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (resp == JOptionPane.OK_OPTION) {
                                nuevovalor = pnlTexto.getValue();
                                if (!nuevovalor.toString().equals(valorActual.toString())) {
                                    Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_PRODUCTO));
                                }
                            }
                            break;
                        case 3:
                            pnlTexto = new PnlTexto();
                            valorActual = tblProductos.getValueAt(fila, columna).toString();
                            pnlTexto.setValue(valorActual.toString());
                            resp = JOptionPane.showConfirmDialog(parent, pnlTexto, "Editar modelo del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (resp == JOptionPane.OK_OPTION) {
                                nuevovalor = pnlTexto.getValue();
                                if (!nuevovalor.toString().equals(valorActual.toString())) {
                                    Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_MODELO));
                                }
                            }
                            break;
                        case 4:
                            pnlTexto = new PnlTexto();
                            valorActual = tblProductos.getValueAt(fila, columna).toString();
                            pnlTexto.setValue(valorActual.toString());
                            resp = JOptionPane.showConfirmDialog(parent, pnlTexto, "Editar talla del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (resp == JOptionPane.OK_OPTION) {
                                nuevovalor = pnlTexto.getValue();
                                if (!nuevovalor.toString().equals(valorActual.toString())) {
                                    Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_TALLA));
                                }
                            }
                            break;
                        case 5:
                            valorActual = tblProductos.getValueAt(fila, columna).toString();
                            pnlPrecios = new PnlPrecios();
                            pnlPrecios.setValue(valorActual.toString());
                            resp = JOptionPane.showConfirmDialog(parent, pnlPrecios, "Editar precio de compra del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            nuevovalor = pnlPrecios.display;
                            if (resp == JOptionPane.OK_OPTION && nuevovalor != "-") {
                                if (!nuevovalor.toString().equals(valorActual.toString())) {
                                    if (utilPvr.esNumero(nuevovalor.toString())) {
                                        Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_PCOMPRA));
                                    } else {
                                        utilPvr.SetLog(lblMsj, ConstantesPvr.ADV, "El valor [ " + nuevovalor + " ] no es un número valido!");
                                    }
                                }
                            }
                            break;
                        case 6:
                            valorActual = tblProductos.getValueAt(fila, columna).toString();
                            pnlPrecios = new PnlPrecios();
                            pnlPrecios.setValue(valorActual.toString());
                            resp = JOptionPane.showConfirmDialog(parent, pnlPrecios, "Editar precio de venta del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            nuevovalor = pnlPrecios.display;
                            if (resp == JOptionPane.OK_OPTION && nuevovalor != "-") {
                                if (!nuevovalor.toString().equals(valorActual.toString())) {
                                    if (utilPvr.esNumero(nuevovalor.toString())) {
                                        Editar(new ProductoVo(idprod, nuevovalor.toString(), ConstantesPvr.INVENTARIO_PVENTA));
                                    } else {
                                        utilPvr.SetLog(lblMsj, ConstantesPvr.ADV, "El valor [ " + nuevovalor + " ] no es un número valido!");
                                    }
                                }
                            }

                            break;
                        case 7:
                            PnlExistencias pe = new PnlExistencias();
                            valorActual = tblProductos.getValueAt(fila, columna).toString();
                            pe.configForm((int) Double.parseDouble(valorActual.toString()));
                            resp = JOptionPane.showConfirmDialog(parent, pe, "Editar existencias del producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (resp == JOptionPane.OK_OPTION) {
                                if (pe.get() > 0) {
                                    Editar(new ProductoVo(idprod, String.valueOf(pe.get()), ConstantesPvr.INVENTARIO_EXISTENCIAS));
                                }
                            }
                            break;
                        default:

                    }
                }

            }
        });

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtro = new TableRowSorter(modelo);
                filtro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase(), 1, 2));
                tblProductos.setRowSorter(filtro);
            }
        });
    }

    private void Guardar() {
        try {
            if (VerificarDatos()) {
                ProductoVo producto = new ProductoVo();
                producto.setLote(txtLote.getText());
                producto.setProducto(txtProducto.getText());
                producto.setModelo(txtModelo.getText());
                producto.setTalla(txtTalla.getText());
                producto.setPcompra(Double.parseDouble(txtPrecioCompra.getText()));
                producto.setPventa(Double.parseDouble(txtPrecioVenta.getText()));
                producto.setExistencias(Double.parseDouble(txtExistencias.getText()));
                producto.setObservacion(txtObservaciones.getText());

                productoVo = productoDao.Registro(producto);
                if (productoVo.getEstatus().equals(ConstantesPvr.OK)) {
                    utilPvr.SetLog(lblMsj, ConstantesPvr.OK, "Registro exitoso!");
                    Listar();
                    Nuevo();
                } else {
                    err = utilPvr.ExtraerMensajeError(productoVo.getEstatus());
                    utilPvr.SetLog(lblMsj, ConstantesPvr.NR, err);
                }
            } else {
                utilPvr.SetLog(lblMsj, ConstantesPvr.ADV, "Complete los campos...");
            }
        } catch (NumberFormatException e) {
            err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            utilPvr.SetLog(lblMsj, ConstantesPvr.NR, err);
        }
    }

    private void Editar(ProductoVo vo) {
        try {
            productoVo = productoDao.Editar(vo);
            if (productoVo.getEstatus().equals(ConstantesPvr.OK)) {
                utilPvr.SetLog(lblMsj, ConstantesPvr.OK, "Edición exitosa!");
                Listar();
            } else {
                err = utilPvr.ExtraerMensajeError(productoVo.getEstatus());
                utilPvr.SetLog(lblMsj, ConstantesPvr.NR, err);
            }
        } catch (Exception e) {
            err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            utilPvr.SetLog(lblMsj, ConstantesPvr.NR, err);
        }
    }

    private void Listar() {
        try {
            modelo.clear();
            ArrayList<ProductoVo> dat = productoDao.Listar();
            int t = dat.size();
            lblProductosRegistrados.setText("" + t);
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ProductoVo test;
                    for (int i = 0; i < t; i++) {
                        test = dat.get(i);
                        Object[] fila = new Object[9];
                        fila[0] = test.getId();
                        fila[1] = test.getLote().toUpperCase();
                        fila[2] = test.getProducto().toUpperCase();
                        fila[3] = test.getModelo().toUpperCase();
                        fila[4] = test.getTalla();
                        fila[5] = test.getPcompra();
                        fila[6] = test.getPventa();
                        fila[7] = test.getExistencias();
                        fila[8] = test.getObservacion();
                        modelo.addRow(fila);
                    }
                } else {
                    err = utilPvr.ExtraerMensajeError(dat.get(0).getEstatus());
                    utilPvr.SetLog(lblEstatus, err, ConstantesPvr.NR);
                }
                lblProductoAnalisis.setText("Productos agotados: " + String.valueOf(productoDao.getAgotados()));
            } else {
                utilPvr.SetLog(lblEstatus, "Al parecer no hay productos registrados!", ConstantesPvr.MSJ_DEFAULT);
            }
        } catch (Exception e) {
            err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            utilPvr.SetLog(lblEstatus, ConstantesPvr.NR, err);
        }
    }

    private void ListarAp() {
        try {
            modAgregarApartado.clear();
            ArrayList<ProductoVo> dat = productoDao.Listar();
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ProductoVo test;
                    for (int i = 0; i < t; i++) {
                        test = dat.get(i);
                        if (test.getExistencias() > 0) {
                            Object[] fila = new Object[8];
                            fila[0] = test.getId();
                            fila[1] = test.getLote();
                            fila[2] = test.getProducto();
                            fila[3] = test.getModelo();
                            fila[4] = test.getTalla();
                            fila[5] = test.getPventa();
                            fila[6] = test.getExistencias();
                            fila[7] = test.getObservacion();
                            modAgregarApartado.addRow(fila);
                        }
                    }
                } else {
                    err = utilPvr.ExtraerMensajeError(dat.get(0).getEstatus());
                    DesktopNotify.showDesktopMessage("PVR", err, DesktopNotify.ERROR, 5000L);
                }
            } else {
                DesktopNotify.showDesktopMessage("PVR", "Al parecer no hay productos registrados!", DesktopNotify.INFORMATION, 5000L);
            }
        } catch (Exception e) {
            err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("PVR", err, DesktopNotify.ERROR, 5000L);
        }
    }

    private void exportar() {
        try {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(parent);
            File f = jfc.getSelectedFile();
            if (f != null) {
                ArrayList<ProductoVo> dat = productoDao.Listar();
                int t = dat.size();
                if (t > 0) {
                    if (dat.get(0).getEstatus() == null) {
                        PDF pdf = new PDF();
                        pdf.CrearPDF(f.getPath(), dat);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al generar pdf: " + dat.get(0).getEstatus());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al generar pdf: Sin productos");
                }

            }
        } catch (HeadlessException e) {
        }
    }

    private boolean VerificarDatos() {
        utilPvr.SetLog(lblEstatus, ConstantesPvr.STD, ConstantesPvr.MSJ_DEFAULT);
        return (!"".equals(txtLote.getText())
                && !"".equals(txtProducto.getText())
                && !"".equals(txtPrecioCompra.getText())
                && !"".equals(txtPrecioVenta.getText())
                && !"".equals(txtExistencias.getText()));
    }

    private void Nuevo() {
        txtLote.setText("");
        txtProducto.setText("");
        txtModelo.setText("");
        txtTalla.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtExistencias.setText("");
        txtObservaciones.setText("");

        utilPvr.IndicadorContenido(txtLote, "Lote");
        utilPvr.IndicadorContenido(txtProducto, "Producto");
        utilPvr.IndicadorContenido(txtModelo, "Modelo (opcional)");
        utilPvr.IndicadorContenido(txtPrecioCompra, "Precio de compra");
        utilPvr.IndicadorContenido(txtPrecioVenta, "Precio de venta");
        utilPvr.IndicadorContenido(txtExistencias, "Existencias");
        utilPvr.IndicadorContenido(scrllpnObservaciones, txtObservaciones, "Observaciones (opcional)");

        utilPvr.SetLog(lblEstatus, ConstantesPvr.STD, ConstantesPvr.MSJ_DEFAULT);

        txtLote.requestFocus();
    }

    //Apartado
    private void NuevoApartado(boolean esPrimerRegistro) {
        try {
            String resp;
            int fila = tblProductos.getSelectedRow();
            ApartadoProductoVo apartadoProductoVo = new ApartadoProductoVo();
            apartadoProductoVo.setLote(ValorTabProd(fila, 1).toString());
            apartadoProductoVo.setProducto(ValorTabProd(fila, 2).toString());
            apartadoProductoVo.setPrecioventa((double) ValorTabProd(fila, 5));
            apartadoProductoVo.setCantidad((double) ValorTabProd(fila, 6));
            apartadoProductoVo.setTotal((double) ValorTabProd(fila, 5));
            apartadoProductoVo.setEstatusProducto(ConstantesPvr.DEUDA);

            resp = new ApartadoDao().Registro(datApartadoVo, apartadoProductoVo, esPrimerRegistro).getEstatus();
            if (resp.equals(ConstantesPvr.OK)) {
                DesktopNotify.showDesktopMessage("Enhorabuena", "Apartado registrado correctamente", DesktopNotify.SUCCESS);
//                utilPvr.SetLog(lblEstatus, ConstantesPvr.OK, "Apartado registrado correctamente");
            } else {
                DesktopNotify.showDesktopMessage("Ups", resp, DesktopNotify.FAIL);
//                utilPvr.SetLog(lblEstatus, ConstantesPvr.NR, resp);
            }

        } catch (Exception e) {
            err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", err, DesktopNotify.FAIL);
        }
    }

    private Object ValorTabProd(int row, int column) {
        return tblProductos.getValueAt(row, column);
    }

}
