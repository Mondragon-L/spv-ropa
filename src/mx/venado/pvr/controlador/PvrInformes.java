
package mx.venado.pvr.controlador;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import mx.venado.pvr.graficas.GraficoVentas;
import mx.venado.pvr.modelo.dao.InformesDao;
import mx.venado.pvr.modelo.vo.AbonoInformeVo;
import mx.venado.pvr.modelo.vo.VentasInformesVo;
import mx.venado.pvr.modelo.vo.VentasPorProductosVo;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.Window;
import mx.venado.pvr.vista.ModeloAbonoInforme;
import mx.venado.pvr.vista.ModeloVentaInforme;
import mx.venado.pvr.vista.ModeloVentaProductosInforme;
import mx.venado.pvr.vista.TableCellRendererApartados;
import mx.venado.pvr.vista.TableCellRendererVenta;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrInformes implements IPvrConstant {

    InformesDao informesDao;
    DefaultTableModel modeloVentas;
    DefaultTableModel modeloVentasProductos;

    ModeloAbonoInforme modeloAbono;

    JButton btnBuscar;

    JDateChooser dcFechaInicioVenta;
    JDateChooser dcFechaFinVenta;
    JDateChooser dcFechaInicioApartados;
    JDateChooser dcFechaFinApartados;

    JLabel lblRegistrosItems;
    JLabel lblTotal;
    JLabel lblProcesando;
    JLabel lblConsola;
    JTable tblItems;
    JTable tblSubitems;
    JButton btnBuscar1;

    JPanel pnlAnalisis;

    JLabel lblRegistrosItems1;
    JLabel lblTotal1;
    JLabel lblProcesando1;
    JLabel lblConsola1;
    JTable tblItems1;
    JFrame parent;
    JInternalFrame form;

    JButton btnMostrarGraficaVenta;
    JComboBox cboFiltroGraficaVenta;
    
    ArrayList<VentasInformesVo> listVentas;

    public PvrInformes(JFrame parent, JInternalFrame form, JButton btnBuscar,
            JDateChooser dcFechaInicioVenta, JDateChooser dcFechaFinVenta,
            JDateChooser dcFechaInicioApartados, JDateChooser dcFechaFinApartados,
            JLabel lblRegistrosItems, JLabel lblTotal, JTable tblItems, JTable tblSubitems,
            JLabel lblProcesando, JLabel lblConsola, JButton btnBuscar1,
            JLabel lblRegistrosItems1, JLabel lblTotal1, JTable tblItems1,
            JLabel lblProcesando1, JLabel lblConsola1, JPanel pnlAnalisis,
            JButton btnMostrarGraficaVenta, JComboBox cboFiltroGraficaVenta) {
        this.parent = parent;
        this.form = form;
        this.informesDao = new InformesDao();
        this.btnBuscar = btnBuscar;
        this.dcFechaInicioVenta = dcFechaInicioVenta;
        this.dcFechaFinVenta = dcFechaFinVenta;
        this.lblRegistrosItems = lblRegistrosItems;
        this.lblTotal = lblTotal;
        this.lblConsola = lblConsola;
        this.tblItems = tblItems;
        this.tblSubitems = tblSubitems;
        this.lblProcesando = lblProcesando;

        this.lblProcesando.setVisible(false);

        this.btnBuscar1 = btnBuscar1;
        this.dcFechaInicioApartados = dcFechaInicioApartados;
        this.dcFechaFinApartados = dcFechaFinApartados;
        this.lblRegistrosItems1 = lblRegistrosItems1;
        this.lblTotal1 = lblTotal1;
        this.lblConsola1 = lblConsola1;
        this.tblItems1 = tblItems1;
        this.lblProcesando1 = lblProcesando1;

        this.btnMostrarGraficaVenta = btnMostrarGraficaVenta;
        this.cboFiltroGraficaVenta = cboFiltroGraficaVenta;

        this.lblProcesando1.setVisible(false);

        this.pnlAnalisis = pnlAnalisis;

        this.form.setTitle(Window.createId("Informes"));

        CrearModelo();
        Listeners();
    }

    private void ConfigForm() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        if (d.width <= 900 || parent.getWidth() <= 900) {
            tblItems.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblSubitems.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblItems1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            tblItems.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            tblSubitems.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            tblItems1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
    }

    private void CrearModelo() {
        modeloVentas = new ModeloVentaInforme();
        tblItems.setDefaultRenderer(Object.class, new TableCellRendererVenta());
        tblItems.setModel(modeloVentas);
        tblItems.setRowHeight(40);
        tblItems.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        modeloVentasProductos = new ModeloVentaProductosInforme();
        tblSubitems.setDefaultRenderer(Object.class, new TableCellRendererVenta());//editar renderer
        tblSubitems.setModel(modeloVentasProductos);
        tblSubitems.setRowHeight(40);
        tblSubitems.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        modeloAbono = new ModeloAbonoInforme();
        tblItems1.setDefaultRenderer(Object.class, new TableCellRendererApartados());
        tblItems1.setModel(modeloAbono);
        tblItems1.setRowHeight(40);
        tblItems1.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
    }

    private void Listeners() {
        form.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                ConfigForm();
            }
        });
        form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                ConfigForm();
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechainicio = (dcFechaInicioVenta.getDate() != null) ? formatoFecha(dcFechaInicioVenta.getDate()) : NR;
                String fechafin = (dcFechaFinVenta.getDate() != null) ? formatoFecha(dcFechaFinVenta.getDate()) : NR;
                new ventas(fechainicio, fechafin).start();
            }
        });

        btnBuscar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechainicio = (dcFechaInicioApartados.getDate() != null) ? formatoFecha(dcFechaInicioApartados.getDate()) : NR;
                String fechafin = (dcFechaFinApartados.getDate() != null) ? formatoFecha(dcFechaFinApartados.getDate()) : NR;
                new abono(fechainicio, fechafin).start();
            }
        });

        btnMostrarGraficaVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeloVentas.getRowCount() > 0) {
                    GraficoVentas graficoVentas = new GraficoVentas();
                    graficoVentas.setFiltro(cboFiltroGraficaVenta.getSelectedIndex());
                    graficoVentas.setDatos(listVentas);
                    graficoVentas.crear();
                    graficoVentas.mostrarGrafico();
                }
            }
        });
    }

    private String formatoFecha(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    private class ventas extends Thread {

        String finicio;
        String ffin;

        public ventas(String finicio, String ffin) {
            this.finicio = finicio;
            this.ffin = ffin;
        }

        @Override
        public void run() {
            GetVentas();
        }

        private void GetVentas() {
            try {
                btnMostrarGraficaVenta.setEnabled(false);
                lblConsola.setForeground(SUCCESS);
                lblConsola.setText("Inicio...");
                btnBuscar.setEnabled(false);
                lblProcesando.setVisible(true);
                lblConsola.setText("Cargando ventas...");
                LimpiarVentas();
                LimpiarVentasProductos();
                listVentas = informesDao.GetVentas(finicio, ffin);
                if (listVentas.size() > 0) {
                    if (listVentas.get(0).getEstatus() == null) {
                        lblRegistrosItems.setText(String.valueOf(listVentas.size()));

                        VentasInformesVo viv;
                        Object[] itm;
                        double total = 0;
                        lblTotal.setText("$" + String.valueOf(total));
                        for (int i = 0; i < listVentas.size(); i++) {
                            viv = listVentas.get(i);
                            itm = new Object[6];
                            itm[0] = viv.getId();
                            itm[1] = viv.getFecha();
                            itm[2] = viv.getHora();
                            itm[3] = viv.getTotal();
                            itm[4] = viv.getCantidadProductos();
                            itm[5] = viv.getVendedor();
                            total += viv.getTotal();
                            Thread.sleep(10);
                            modeloVentas.addRow(itm);
                            lblTotal.setText("$" + String.valueOf(total));
                        }

                        //analisis
                        lblConsola.setForeground(SUCCESS);
                        lblConsola.setText("Cargando productos...");
                        ArrayList<VentasPorProductosVo> listVntProd = informesDao.VentasPorProducto(finicio, ffin);
                        if (listVntProd.size() > 0) {
                            if (listVntProd.get(0).getEstatus() == null) {
                                VentasPorProductosVo vppv;
                                Object[] tmp;
                                Object[] itm2;
                                ArrayList<Object[]> lstProductos = new ArrayList<>();
                                int PosicionExistente;
                                for (int i = 0; i < listVntProd.size(); i++) {
                                    vppv = listVntProd.get(i);
                                    itm2 = new Object[4];
                                    itm2[0] = vppv.getLote();
                                    itm2[1] = vppv.getProducto();
                                    itm2[2] = vppv.getCantidad();
                                    itm2[3] = vppv.getTotal();
                                    lblConsola.setForeground(SUCCESS);
                                    lblConsola.setText("Verificando producto...");
                                    PosicionExistente = ExisteLote(lstProductos, itm2[1].toString());
                                    if (PosicionExistente == -1) {
                                        lstProductos.add(itm2);
                                    } else {
                                        tmp = lstProductos.get(PosicionExistente);
                                        tmp[2] = Integer.parseInt(tmp[2].toString()) + Integer.parseInt(itm2[2].toString());
                                        tmp[3] = Double.parseDouble(tmp[3].toString()) + Double.parseDouble(itm2[3].toString());
                                    }
                                }

                                //ordenando cantidades
                                lblConsola.setForeground(SUCCESS);
                                lblConsola.setText("Ordenando productos...");
                                OrdenarCantidades(lstProductos);

                                //pintando en tabla
                                for (int i = 0; i < lstProductos.size(); i++) {
                                    Thread.sleep(10);
                                    modeloVentasProductos.addRow(lstProductos.get(i));
                                }
                                lblConsola.setForeground(SUCCESS);
                                lblConsola.setText("Terminado...");
                            } else {
                                lblConsola.setForeground(ERROR);
                                lblConsola.setText(listVntProd.get(0).getEstatus());
                            }
                        } else {
                            lblConsola.setForeground(ERROR);
                            lblConsola.setText("Error no se han encontrado productos...");
                        }
                    } else {
                        lblConsola.setForeground(ERROR);
                        lblConsola.setText(listVentas.get(0).getEstatus());
                    }
                } else {
                    lblConsola.setForeground(WARNING);
                    lblConsola.setText("No se han encontrado ventas conrrespondientes al rango del "
                            + finicio + " al " + ffin);
                }
                lblProcesando.setVisible(false);
                btnBuscar.setEnabled(true);
                btnMostrarGraficaVenta.setEnabled(true);
            } catch (InterruptedException | NumberFormatException e) {
                lblConsola.setForeground(ERROR);
                lblConsola.setText("Error: " + ((e.getMessage() != null) ? e.getMessage() : e.toString()));
            }
        }

        private int ExisteLote(ArrayList<Object[]> lst, String lote) {
            int pos = -1;
            Object[] item;
            for (int i = 0; i < lst.size(); i++) {
                item = lst.get(i);
                if (String.valueOf(item[1]).equals(lote)) {
                    pos = i;
                    break;
                }
            }
            return pos;
        }

        private void OrdenarCantidades(ArrayList<Object[]> lst) {
            try {
                Object[] temp;
                int t = lst.size();
                if (t > 1) {
                    for (int i = 1; i < t; i++) {
                        for (int k = t - 1; k >= i; k--) {
                            if (Double.parseDouble(lst.get(k)[3].toString()) > Double.parseDouble(lst.get(k - 1)[3].toString())) {
                                temp = lst.get(k);
                                lst.set(k, lst.get(k - 1));
                                lst.set((k - 1), temp);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                lblConsola.setForeground(ERROR);
                lblConsola.setText("Error: " + ((e.getMessage() != null) ? e.getMessage() : e.toString()));
            }
        }

        private void LimpiarVentas() throws InterruptedException {
            for (int i = modeloVentas.getRowCount() - 1; i >= 0; i--) {
                Thread.sleep(10);
                modeloVentas.removeRow(i);
            }
        }

        private void LimpiarVentasProductos() throws InterruptedException {
            for (int i = modeloVentasProductos.getRowCount() - 1; i >= 0; i--) {
                Thread.sleep(10);
                modeloVentasProductos.removeRow(i);
            }
        }

    }

    private class abono extends Thread {

        String finicio;
        String ffin;

        public abono(String finicio, String ffin) {
            this.finicio = finicio;
            this.ffin = ffin;
        }

        @Override
        public void run() {
            GetAbonos();
        }

        private void GetAbonos() {
            try {
                lblConsola1.setForeground(SUCCESS);
                lblConsola1.setText("Inicio...");
                btnBuscar1.setEnabled(false);
                lblProcesando1.setVisible(true);
                lblConsola1.setText("Cargando abonos...");
                modeloAbono.clear();
                ArrayList<AbonoInformeVo> listAbonos = informesDao.GetAbonos(finicio, ffin);
                if (listAbonos.size() > 0) {
                    if (listAbonos.get(0).getEstatus() == null) {
                        lblRegistrosItems1.setText(String.valueOf(listAbonos.size()));

                        AbonoInformeVo aiv;
                        Object[] itm;
                        double total = 0;
                        lblTotal1.setText("$" + String.valueOf(total));
                        for (int i = 0; i < listAbonos.size(); i++) {
                            aiv = listAbonos.get(i);
                            itm = new Object[9];
                            itm[0] = (i + 1);
                            itm[1] = aiv.getCliente();
                            itm[2] = aiv.getProducto();
                            itm[3] = aiv.getLote();
                            itm[4] = aiv.getFecha();
                            itm[5] = aiv.getHora();
                            itm[6] = aiv.getCantidad();
                            itm[7] = aiv.getObservación();
                            total += aiv.getCantidad();
                            Thread.sleep(10);
                            modeloAbono.addRow(itm);
                            lblTotal1.setText("$" + String.valueOf(total));
                        }
                        //analisis
                        lblConsola1.setForeground(SUCCESS);

                    } else {
                        lblConsola1.setForeground(ERROR);
                        lblConsola1.setText(listAbonos.get(0).getEstatus());
                    }
                } else {
                    lblConsola1.setForeground(WARNING);
                    lblConsola1.setText("No se han encontrado abonos conrrespondientes al rango del "
                            + finicio + " al " + ffin);
                }
                lblProcesando1.setVisible(false);
                btnBuscar1.setEnabled(true);
            } catch (InterruptedException | NumberFormatException e) {
                lblConsola1.setForeground(ERROR);
                lblConsola1.setText("Error: " + ((e.getMessage() != null) ? e.getMessage() : e.toString()));
            }
        }

    }

}
