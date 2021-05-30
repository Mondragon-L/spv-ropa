package mx.venado.pvr.controlador;

import ds.desktop.notify.DesktopNotify;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import mx.venado.pvr.modelo.dao.ProductoDao;
import mx.venado.pvr.modelo.dao.VentaDao;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.modelo.vo.TicketVo;
import mx.venado.pvr.modelo.vo.VentaVo;
import mx.venado.pvr.modelo.vo.VntProdVo;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.CorrectoIcono;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.Ticket;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.Window;
import mx.venado.pvr.vista.ModeloVenta;
import mx.venado.pvr.vista.TableCellRendererVenta;
import pvr.DfrmProductosVentas;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrVenta implements IPvrConstant {

    public static int idSeleccionBusqueda = -1;

    UtilidadesPvr utilPvr = new UtilidadesPvr();
    ProductoDao productoDao;
    VentaDao ventaDao;

    ModeloVenta modeloListaVenta;
    JFrame parent;
    JInternalFrame form;
    JTable tblListaVenta;
    JButton btnNuevaVenta;
    JButton btnVender;
    JButton btnImprimir;
    JLabel lblCantidadProducto;
    JLabel lblIdVendedor;
    JLabel lblTotal;
    JLabel lblProcesando;
    JLabel lblEstatusVenta;
    JTextField txtBuscarProducto;
    JTextField txtCambio;
    JTextField txtImporte;

    // variables operaciones
    /**
     * Cantidad total de dinero de las ventas.
     */
    private double total = 0;
    private double cambio = 0;
    private double importe = 0;
    /**
     * Cantidad de productos en el carrito.
     */
    private int productos = 0;

    /**
     * Tipo de usuario logeado
     */
    private String tipo = "STD";
    /**
     * Id del usuario logeado
     */
    private int idusuario = -1;

    private ArrayList<Object[]> lstBusqueda;

    public PvrVenta(JFrame parent, JInternalFrame form, String tipo, int idusuario, JTable tblListaVenta,
            JButton btnNuevaVenta, JButton btnVender, JLabel lblCantidadProducto,
            JLabel lblIdVendedor, JLabel lblTotal, JTextField txtBuscarProducto,
            JTextField txtCambio, JTextField txtImporte, JLabel lblProcesando,
            JLabel lblEstatusVenta, JButton btnReimprimir) {
        this.parent = parent;
        this.form = form;
        this.tblListaVenta = tblListaVenta;
        this.btnNuevaVenta = btnNuevaVenta;
        this.btnVender = btnVender;
        this.btnImprimir = btnReimprimir;
        this.lblCantidadProducto = lblCantidadProducto;
        this.lblIdVendedor = lblIdVendedor;
        this.lblTotal = lblTotal;
        this.txtBuscarProducto = txtBuscarProducto;
        this.txtCambio = txtCambio;
        this.txtImporte = txtImporte;
        this.tipo = tipo;
        this.idusuario = idusuario;
        this.lblProcesando = lblProcesando;
        this.lblEstatusVenta = lblEstatusVenta;
        this.lstBusqueda = new ArrayList<>();
        this.productoDao = new ProductoDao();
        this.ventaDao = new VentaDao();

        CrearModelo();
        Listeners();
        ConfigForm();
        this.form.setTitle(Window.createId("Venta"));
    }

    private void ConfigForm() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        if ((d.width <= 900) || (parent.getWidth() <= 900)) {
            tblListaVenta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            tblListaVenta.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
        lblIdVendedor.setText(String.valueOf(idusuario));
        lblProcesando.setVisible(false);
        Cambio();
    }

    private void CrearModelo() {
        modeloListaVenta = new ModeloVenta();
        tblListaVenta.setDefaultRenderer(Object.class, new TableCellRendererVenta());
        tblListaVenta.setModel(modeloListaVenta);
        tblListaVenta.setRowHeight(50);
        tblListaVenta.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblListaVenta.getColumnModel().getColumn(0).setMaxWidth(40);
        tblListaVenta.getColumnModel().getColumn(8).setMaxWidth(50);
    }

    private void Listeners() {
        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vender();
            }
        });

        btnNuevaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });

        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket();
            }
        });

        txtBuscarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(txtBuscarProducto.getText())) {
                        BuscarProducto();
                    }
                }
            }
        });

        txtImporte.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
                    evt.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                Cambio();
            }
        });

        tblListaVenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblListaVenta.getSelectedRow();
                int columna = tblListaVenta.getSelectedColumn();
                if (fila != -1 && columna == ModeloVenta.COL_DESCUENTO) {
                    descuento(fila);
                    TotalVentas();
                    Cambio();
                } else if (fila != -1 && columna == ModeloVenta.COL_CONCEPTO) {
                    conceptoDescuento(fila);
                } else if (fila != -1 && columna == ModeloVenta.COL_CANTIDAD) {
                    aumentarCantidad(fila, modeloListaVenta.getString(fila, ModeloVenta.COL_LOTE));
                    TotalVentas();
                    Cambio();
                } else if (fila != -1 && columna == 8) {
                    quitar(fila);
                }
            }
        });

    }

    private void BuscarProducto() {
        try {
            String busqueda = txtBuscarProducto.getText();
            ArrayList<ProductoVo> pvs;
            lstBusqueda.clear();
            int encarrito = ExisteEnCarrito(busqueda);
            if (encarrito == -1) {
                pvs = productoDao.Listar(busqueda, 0);
                Object[] tmp;
                if (pvs.size() == 1) {
                    if (pvs.get(0).getEstatus().equals(ConstantesPvr.OK)) {
                        tmp = new Object[9];
                        tmp[0] = (tblListaVenta.getRowCount() + 1);
                        tmp[1] = pvs.get(0).getLote();
                        tmp[2] = pvs.get(0).getProducto();
                        tmp[3] = 1;
                        tmp[4] = pvs.get(0).getPventa();
                        tmp[5] = 0;
                        tmp[6] = "";
                        tmp[7] = (1 * pvs.get(0).getPventa());
                        tmp[8] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Quitar.png"));
                        int existe = ExisteEnCarrito(tmp[1].toString());
                        if (existe > -1) {
                            int resp = JOptionPane.showConfirmDialog(parent, "El producto ya se encuentra en la lista"
                                    + " Desea aumentar la cantidad?", "Producto existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new AdvertenciaIcono());
                            if (resp == JOptionPane.YES_OPTION) {
                                int cantidad = GetExistenciasCarrito(tmp[1].toString());
                                tblListaVenta.setValueAt((cantidad + 1), existe, ModeloVenta.COL_CANTIDAD);
                                tblListaVenta.setValueAt(((cantidad + 1) * pvs.get(0).getPventa()), existe, ModeloVenta.COL_TOTAL);
                            }
                        } else {
                            modeloListaVenta.addRow(tmp);
                        }
                    } else {
                        utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, "Error: " + pvs.get(0).getEstatus());
                    }
                } else if (pvs.size() > 1) {
                    for (int i = 0; i < pvs.size(); i++) {
                        tmp = new Object[6];
                        tmp[0] = (i + 1);
                        tmp[1] = pvs.get(i).getLote();
                        tmp[2] = pvs.get(i).getProducto();
                        tmp[3] = 1;
                        tmp[4] = pvs.get(i).getPventa();
                        lstBusqueda.add(tmp);
                    }
                    DfrmProductosVentas dpv = new DfrmProductosVentas(parent, true);
                    dpv.SetDatos(lstBusqueda);
                    dpv.setVisible(true);

                    if (idSeleccionBusqueda != -1) {
                        tmp = new Object[9];
                        tmp[0] = (tblListaVenta.getRowCount() + 1);
                        tmp[1] = pvs.get(idSeleccionBusqueda).getLote();
                        tmp[2] = pvs.get(idSeleccionBusqueda).getProducto();
                        tmp[3] = 1;
                        tmp[4] = pvs.get(idSeleccionBusqueda).getPventa();
                        tmp[5] = 0;
                        tmp[6] = "";
                        tmp[7] = (1 * pvs.get(idSeleccionBusqueda).getPventa());
                        tmp[8] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Quitar.png"));
                        int existe = ExisteEnCarrito(tmp[1].toString());
                        if (existe > -1) {
                            int resp = JOptionPane.showConfirmDialog(parent, "El producto ya se encuentra en la lista"
                                    + " Desea aumentar la cantidad?", "Producto existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new AdvertenciaIcono());
                            if (resp == JOptionPane.OK_OPTION) {
                                int cantidad = Integer.parseInt(tblListaVenta.getValueAt(existe, 3).toString());
                                tblListaVenta.setValueAt((cantidad + 1), existe, 3);
                                tblListaVenta.setValueAt(((cantidad + 1) * pvs.get(idSeleccionBusqueda).getPventa()), existe, 5);
                            }
                        } else {
                            modeloListaVenta.addRow(tmp);
                        }
                        idSeleccionBusqueda = -1;
                    }
                } else {
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "Producto no encontrado");
                }
            } else {
                aumentarCantidad(encarrito, busqueda);
            }
        } catch (HeadlessException | NumberFormatException e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
        txtBuscarProducto.setText("");
        CantProductos();
        TotalVentas();
        Cambio();
        txtBuscarProducto.requestFocus();
    }

    private int ExisteEnCarrito(String lote) {
        int existe = -1;
        for (int i = 0; i < modeloListaVenta.getRowCount(); i++) {
            if (lote.equals(modeloListaVenta.getString(i, ModeloVenta.COL_LOTE))) {
                existe = i;
                break;
            }
        }
        return existe;
    }

    private int GetExistenciasCarrito(String lote) {
        int cant = 0;
        for (int i = 0; i < modeloListaVenta.getRowCount(); i++) {
            if (lote.equals(modeloListaVenta.getString(i, ModeloVenta.COL_LOTE))) {
                cant = modeloListaVenta.getInteger(i, ModeloVenta.COL_CANTIDAD);
                break;
            }
        }
        return cant;
    }

    private void aumentarCantidad(int row, String lote) {
        try {
            int aumentar;
            String res = JOptionPane.showInputDialog(parent, "Agregar:");
            if (!"".equals(res)) {
                int encarrito = modeloListaVenta.getInteger(row, ModeloVenta.COL_CANTIDAD);
                double pventa = modeloListaVenta.getDouble(row, ModeloVenta.COL_PVENTA);
                double descuento = modeloListaVenta.getDouble(row, ModeloVenta.COL_DESCUENTO);
                aumentar = Integer.parseInt(res);
                ArrayList<ProductoVo> pvs;
                pvs = productoDao.Listar2(lote, (encarrito + aumentar));
                if (!pvs.isEmpty()) {
                    if (pvs.get(0).getEstatus().equals(ConstantesPvr.OK)) {
                        modeloListaVenta.setValueAt((encarrito + aumentar), row, ModeloVenta.COL_CANTIDAD);
                        modeloListaVenta.setValueAt(
                                ((pventa * (encarrito + aumentar)) - descuento),
                                row,
                                ModeloVenta.COL_TOTAL);
                    } else {
                        utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, pvs.get(0).getEstatus());
                    }
                } else {
                    DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "No cuenta con las existencias suficientes...", DesktopNotify.WARNING, 4000L);
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "No cuenta con las existencias suficientes...");
                }
            } else {
                DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Ingrese un número valido...", DesktopNotify.ERROR, 4000L);
                utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "Ingrese un número valido...");
            }
        } catch (HeadlessException | NumberFormatException e) {
            DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Ingrese solo números...", DesktopNotify.ERROR, 4000L);
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, "Ingrese solo números...");
        }

    }

    private void descuento(int row) {
        try {
            double cant = modeloListaVenta.getDouble(row, ModeloVenta.COL_CANTIDAD);
            double pvta = modeloListaVenta.getDouble(row, ModeloVenta.COL_PVENTA);
            double totalAc = cant * pvta;
            double descuento;
            double totalfin;
            String resp = JOptionPane.showInputDialog(parent, "Descuento: ");
            descuento = ((!"".equals(resp)) ? Double.parseDouble(resp) : 0);
            totalfin = totalAc - descuento;
            tblListaVenta.setValueAt(descuento, row, ModeloVenta.COL_DESCUENTO);
            tblListaVenta.setValueAt(totalfin, row, ModeloVenta.COL_TOTAL);
        } catch (HeadlessException | NumberFormatException e) {
            DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Ingrese números...", DesktopNotify.ERROR, 4000L);
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, "Ingrese números...");
        }
    }

    private void conceptoDescuento(int row) {
        double desc = modeloListaVenta.getDouble(row, ModeloVenta.COL_DESCUENTO);
        if (desc > 0) {
            String cons = JOptionPane.showInputDialog(parent, "Concepto:");
            tblListaVenta.setValueAt(cons, row, 6);
        } else {
            DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Primero debe igresar un monto de descuento...", DesktopNotify.ERROR, 4000L);
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "Primero debe igresar un monto de descuento...");
        }

    }

    private void quitar(int row) {
        int cant = modeloListaVenta.getInteger(row, ModeloVenta.COL_CANTIDAD);
        if (cant > 1) {
            double pvta = modeloListaVenta.getDouble(row, ModeloVenta.COL_PVENTA);
            double totalAc = (cant - 1) * pvta;
            double descuento = modeloListaVenta.getDouble(row, ModeloVenta.COL_DESCUENTO);
            double totalfin = totalAc - descuento;
            tblListaVenta.setValueAt((cant - 1), row, ModeloVenta.COL_CANTIDAD);
            tblListaVenta.setValueAt(totalfin, row, ModeloVenta.COL_TOTAL);
        } else {
            modeloListaVenta.removeRow(row);
        }
        CantProductos();
        TotalVentas();
        Cambio();
    }

    private void CantProductos() {
        try {
            productos = 0;
            lblCantidadProducto.setText(String.valueOf(productos));
            for (int i = 0; i < modeloListaVenta.getRowCount(); i++) {
                productos += modeloListaVenta.getInteger(i, ModeloVenta.COL_CANTIDAD);
            }
            lblCantidadProducto.setText(String.valueOf(productos));
        } catch (NumberFormatException e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
    }

    private void TotalVentas() {
        try {
            total = 0;
            lblTotal.setText("$" + String.valueOf(total));
            for (int i = 0; i < modeloListaVenta.getRowCount(); i++) {
                total += Double.parseDouble(modeloListaVenta.getValueAt(i, ModeloVenta.COL_TOTAL).toString());
            }
            lblTotal.setText("$" + String.valueOf(total));
        } catch (NumberFormatException e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
    }

    private void Cambio() {
        try {
            if (!"".equals(txtImporte.getText())) {
                char[] c = txtImporte.getText().toCharArray();
                if (c[0] != '.') {
                    importe = (!"".equals(txtImporte.getText())) ? Integer.parseInt(txtImporte.getText()) : 0;
                    cambio = importe - total;
                    txtCambio.setText(String.valueOf(cambio));
                }
            } else {
                cambio = 0 - total;
                txtCambio.setText(String.valueOf(cambio));
            }
        } catch (NumberFormatException e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
    }

    private void Vender() {
        if (verificarVenta()) {
            Venta();
//            new EfectuarVenta().start();
        }
    }

    /**
     * Obtiene los datos de la venta a realizar.
     *
     * @return VentaVo
     */
    private VentaVo GetVenta() {
        TotalVentas();
        VentaVo ventaVo = new VentaVo();
        ventaVo.setTotal(total);
        ventaVo.setImporte(Double.parseDouble(txtImporte.getText()));
        ventaVo.setCambio(Double.parseDouble(txtCambio.getText()));
        ventaVo.setVendedor(idusuario);
        return ventaVo;
    }

    /**
     * Obtiene los productos relacionados con la venta actual.
     *
     * @return ArrayList de tipo VntProdVo con todos los productos.
     */
    private ArrayList<VntProdVo> GetItems() {
        ArrayList<VntProdVo> list = new ArrayList<>();
        for (int i = 0; i < modeloListaVenta.getRowCount(); i++) {
            VntProdVo vntProdVo = new VntProdVo();
            vntProdVo.setLote(modeloListaVenta.getString(i, ModeloVenta.COL_LOTE));
            vntProdVo.setProducto(modeloListaVenta.getString(i, ModeloVenta.COL_PRODUCTO));
            vntProdVo.setCantidad(modeloListaVenta.getDouble(i, ModeloVenta.COL_CANTIDAD));
            vntProdVo.setPventa(modeloListaVenta.getDouble(i, ModeloVenta.COL_PVENTA));
            vntProdVo.setTotal(modeloListaVenta.getDouble(i, ModeloVenta.COL_TOTAL));
            vntProdVo.setObservacion("");
            vntProdVo.setDescuento(modeloListaVenta.getDouble(i, ModeloVenta.COL_DESCUENTO));
            vntProdVo.setConceptoDescuento(modeloListaVenta.getString(i, ModeloVenta.COL_CONCEPTO));
            list.add(vntProdVo);
        }
        return list;
    }

    private boolean verificarVenta() {
        boolean correcto = true;
        if (modeloListaVenta.getRowCount() == 0) {
            correcto = false;
            DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Debe tener por lo menos un producto en la lista de venta.", DesktopNotify.ERROR, 4000L);
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "Debe tener por lo menos un producto en la lista de venta.");
        }

        if ("".equals(txtImporte.getText())) {
            correcto = false;
            DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "Ingrese el importe ", DesktopNotify.ERROR, 4000L);
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "Ingrese el importe ");
        }

        if (!"".equals(txtImporte.getText())) {
            TotalVentas();
            if (Double.parseDouble(txtImporte.getText()) < total) {
                correcto = false;
                DesktopNotify.showDesktopMessage(ConstantesPvr.ADV, "El importe ingresado no cubre el total de la venta.", DesktopNotify.ERROR, 4000L);
                utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.ADV, "El importe ingresado no cubre el total de la venta.");
            }
        }

        return correcto;
    }

    private void Venta() {
        try {
            String estatusventa = ventaDao.RegistroVenta(GetVenta(), GetItems()).getEstatus();
            if (estatusventa.equals(ConstantesPvr.OK)) {
                int resp = JOptionPane.showConfirmDialog(parent, "Venta realizada con éxito!\n¿Desea imprimir un tiket?", "NORA", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new CorrectoIcono());
                if (resp == JOptionPane.YES_OPTION) {
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.MSJ, "Venta realizada con éxito! (Imprimiendo ticket)");
                    ticket();
                }
            } else {
                utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, estatusventa);
            }
            Limpiar();
        } catch (HeadlessException e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
        }
    }

    private void Limpiar() {
        modeloListaVenta.clear();
        CantProductos();
        TotalVentas();
        lblEstatusVenta.setText("OK");
        txtImporte.setText("");
        txtCambio.setText("0.0");
        txtBuscarProducto.requestFocus();
    }

    private void LimpiarTablaVentas() {
        for (int i = modeloListaVenta.getRowCount() - 1; i >= 0; i--) {
            modeloListaVenta.removeRow(i);
        }
    }

    private class EfectuarVenta extends Thread {

        @Override
        public void run() {
            try {
                lblProcesando.setVisible(true);
                bloqueo(false);
                String estatusventa = ventaDao.RegistroVenta(GetVenta(), GetItems()).getEstatus();
                if (estatusventa.equals(ConstantesPvr.OK)) {
                    DesktopNotify.showDesktopMessage(MSJ, "Venta realizada con éxito! (Imprimiendo ticket)", DesktopNotify.SUCCESS, 4000L);
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.MSJ, "Venta realizada con éxito! (Imprimiendo ticket)");
                    ticket();
                } else {
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, estatusventa);
                }
                bloqueo(true);
                lblProcesando.setVisible(false);
                Limpiar();
            } catch (Exception e) {
                utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ((e.getMessage() != null) ? e.getMessage() : e.toString()));
            }
        }

        private void bloqueo(boolean b) {
            btnVender.setEnabled(b);
            btnNuevaVenta.setEnabled(b);
            txtBuscarProducto.setEditable(b);
            txtImporte.setEditable(b);
        }
    }

    private void ticket() {
        try {
            Ticket impresora = new Ticket(lblEstatusVenta);
            ArrayList<TicketVo> ticketVos = ventaDao.ticket();
            if (!ticketVos.isEmpty()) {
                if (ticketVos.get(0).getEstatus().equals(ConstantesPvr.OK)) {
                    impresora.ticketVentaSTD(ticketVos);
                } else {
                    utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, ticketVos.get(0).getEstatus());
                }
            } else {
                utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, "Error al imprimir: Venta no encontrada.");
            }
        } catch (Exception e) {
            utilPvr.SetLog(lblEstatusVenta, ConstantesPvr.NR, "Error al imprimir: " + e.getMessage());
        }
    }

}
