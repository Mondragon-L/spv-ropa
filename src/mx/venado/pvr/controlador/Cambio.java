
package mx.venado.pvr.controlador;

import ds.desktop.notify.DesktopNotify;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import mx.venado.pvr.modelo.dao.AbonoDao;
import mx.venado.pvr.modelo.dao.ApartadoDao;
import mx.venado.pvr.modelo.dao.ProductoDao;
import mx.venado.pvr.modelo.vo.ApartadoProductoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.vista.ModeloProductos;
import mx.venado.pvr.vista.TableCellRendererProductos;

public class Cambio {

    JTable tblProductos;
    JLabel lblIdApartado;
    JLabel lblIdApartadoActual;
    JLabel lblIdApartadoCambio;
    JLabel lblLoteActual;
    JLabel lblLoteCambio;
    JLabel lblPvActual;
    JLabel lblPvCambio;
    JTextField txtFiltro;
    JLabel lblEstatus;
    JButton btnCambio;
    JFrame parent;

    UtilidadesPvr utilPvr = new UtilidadesPvr();
    ModeloProductos modelo;
    TableRowSorter filtro;

    ProductoDao productoDao;
    int idDeApartado = -1;

    public Cambio(JTable tblProductos, JLabel lblIdApartado, JLabel lblIdApartadoActual,
            JLabel lblIdApartadoCambio, JLabel lblLoteActual, JLabel lblLoteCambio,
            JLabel lblPvActual, JLabel lblPvCambio, JTextField txtFiltro, JLabel lblEstatus,
            JButton btnCambio) {
        this.tblProductos = tblProductos;
        this.lblIdApartado = lblIdApartado;
        this.lblIdApartadoActual = lblIdApartadoActual;
        this.lblIdApartadoCambio = lblIdApartadoCambio;
        this.lblLoteActual = lblLoteActual;
        this.lblLoteCambio = lblLoteCambio;
        this.lblPvActual = lblPvActual;
        this.lblPvCambio = lblPvCambio;
        this.txtFiltro = txtFiltro;
        this.lblEstatus = lblEstatus;
        this.btnCambio = btnCambio;
//        this.parent = parent;

        ConfigForm();
    }

    public void productoActual(int idDeApartado, int idapartado, String lote, double pv) {
        lblIdApartadoActual.setText(Integer.toString(idapartado));
        lblIdApartadoCambio.setText(Integer.toString(idapartado));
        lblLoteActual.setText(lote);
        lblPvActual.setText(Double.toString(pv));
        this.idDeApartado = idDeApartado;

    }

    private void ConfigForm() {
        productoDao = new ProductoDao();
        CrearModelos();
        Listeners();
        ListarProductos();
    }

    private void CrearModelos() {
        modelo = new ModeloProductos();
        tblProductos.setDefaultRenderer(Object.class, new TableCellRendererProductos(true));
        tblProductos.setModel(modelo);
        tblProductos.setRowHeight(30);
        tblProductos.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(50);
        tblProductos.getColumnModel().getColumn(7).setMaxWidth(50);
    }

    private void Listeners() {
        btnCambio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblProductos.getSelectedRow() != -1) {
                    cambiar();
                }
                else {
                    DesktopNotify.showDesktopMessage("PVR", "Seleccione un producto como cambio!", DesktopNotify.INFORMATION, 5000L);
                }
            }
        });

        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtro = new TableRowSorter(modelo);
                filtro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), 1, 2));
                tblProductos.setRowSorter(filtro);
            }
        });

        tblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblProductos.getSelectedRow();

                if (fila >= 0) {
                    lblLoteCambio.setText(tblProductos.getValueAt(fila, 1).toString());
                    lblPvCambio.setText(tblProductos.getValueAt(fila, 5).toString());
                }
            }
        });
    }

    private void ListarProductos() {
        try {
            modelo.clear();
            ArrayList<ProductoVo> dat = productoDao.Listar();
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ProductoVo test;
                    for (int i = 0; i < t; i++) {
                        test = dat.get(i);
                        if (test.getExistencias() > 0) {
                            Object[] fila = new Object[9];
                            fila[0] = test.getId();
                            fila[1] = test.getLote();
                            fila[2] = test.getProducto();
                            fila[3] = test.getModelo();
                            fila[4] = test.getTalla();
                            fila[5] = test.getPcompra();
                            fila[6] = test.getPventa();
                            fila[7] = test.getExistencias();
                            fila[8] = test.getObservacion();
                            modelo.addRow(fila);
                        }
                    }
                } else {
//                    err = utilPvr.ExtraerMensajeError(dat.get(0).getEstatus());
                    DesktopNotify.showDesktopMessage("PVR", dat.get(0).getEstatus(), DesktopNotify.ERROR, 5000L);
                }
            } else {
                DesktopNotify.showDesktopMessage("PVR", "Al parecer no hay productos registrados!", DesktopNotify.INFORMATION, 5000L);
            }
        } catch (Exception e) {
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("PVR", err, DesktopNotify.ERROR, 5000L);
        }
    }

    private void cambiar() {
        try {
            ApartadoVo apartadoVo = new ApartadoVo();
            apartadoVo.setId(idDeApartado);
            ApartadoProductoVo apartProdVo = new ApartadoProductoVo();
            ApartadoDao apartadoDao = new ApartadoDao();

            double pvActual = Double.parseDouble(lblPvActual.getText());
            double pvCambio = Double.parseDouble(lblPvCambio.getText());
            double totalabono = 0;
            String estatus;

            apartProdVo.setId(Integer.parseInt(lblIdApartadoCambio.getText()));
            apartProdVo.setLote(lblLoteCambio.getText());
            apartProdVo.setPrecioventa(pvCambio);
            apartProdVo.setTotal(pvCambio);
            apartProdVo.setEstatusProducto(ConstantesPvr.DEUDA);

            if (pvCambio == pvActual) {
                apartProdVo.setDiferenciaPV(0);
            } else if (pvCambio < pvActual) {
                apartProdVo.setDiferenciaPV(-(pvActual - pvCambio));
                totalabono = new AbonoDao().totalAbonos(Integer.parseInt(lblIdApartadoActual.getText()));
            } else if (pvCambio > pvActual) {
                apartProdVo.setDiferenciaPV(pvCambio - pvActual);
                totalabono = new AbonoDao().totalAbonos(Integer.parseInt(lblIdApartadoActual.getText()));
            }

            if (totalabono > pvCambio) {
                int res = JOptionPane.showConfirmDialog(parent,
                        "Los abonos realizados anteriormente, cubren el total del "
                        + "producto con un excedente de $" + (totalabono - pvCambio)
                        + " pesos, desea continuar?", "Advertencia",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new AdvertenciaIcono());
                if (res == JOptionPane.YES_OPTION) {
                    apartProdVo.setEstatusProducto(ConstantesPvr.LIQUIDADO);
                    estatus = apartadoDao.editarApartadoPoducto(apartProdVo, apartadoVo, (totalabono - pvCambio), lblLoteActual.getText()).getEstatus();
                    if (estatus.equals(ConstantesPvr.OK)) {
                        DesktopNotify.showDesktopMessage("PVR", "Cambio realizado con éxito", DesktopNotify.SUCCESS, 5000L);
                    } else {
                        DesktopNotify.showDesktopMessage("PVR", estatus, DesktopNotify.ERROR, 5000L);
                    }
                }
            } else {
                estatus = apartadoDao.editarApartadoPoducto(apartProdVo, apartadoVo, lblLoteActual.getText()).getEstatus();
                if (estatus.equals(ConstantesPvr.OK)) {
                    DesktopNotify.showDesktopMessage("PVR", "Cambio realizado con éxito", DesktopNotify.SUCCESS, 5000L);
                } else {
                    DesktopNotify.showDesktopMessage("PVR", estatus, DesktopNotify.ERROR, 5000L);
                }
            }
        } catch (NumberFormatException | HeadlessException e) {
            DesktopNotify.showDesktopMessage("PVR", e.getMessage(), DesktopNotify.ERROR, 5000L);
        }
    }

}
