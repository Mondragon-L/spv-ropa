
package mx.venado.pvr.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.venado.pvr.modelo.dao.AbonoDao;
import mx.venado.pvr.modelo.dao.ProductoDao;
import mx.venado.pvr.modelo.vo.ApartadoAbonoVo;
import mx.venado.pvr.modelo.vo.ClienteVo;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.modelo.vo.TicketAbonoVo;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.Ticket;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.vista.ModeloAbono;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrAbono implements IPvrConstant {

    JTable tblAbonos;

    JLabel lblIdApartadoProducto;
    JLabel lblLoteApartadoProducto;
    JLabel lblNombreApartadoProducto;
    JLabel lblNombreCliente;
    JLabel lblRestaApartadoProducto;
    JLabel lblTotalApartadoProducto;
    JLabel lblEstatus;
    JTextField txtAbonar;
    JTextArea txtConcepto;
    JButton btnAbonar;

    DefaultTableModel modeloAbono;

    ProductoVo productoVo;
    ClienteVo clienteVo;
    AbonoDao abonoDao;
    
    JDialog root;
    
    public PvrAbono() {
    }

    public PvrAbono(JDialog root, JTable tblAbonos, JLabel lblIdApartadoProducto, JLabel lblLoteApartadoProducto,
            JLabel lblNombreApartadoProducto, JLabel lblNombreCliente, JLabel lblRestaApartadoProducto,
            JLabel lblTotalApartadoProducto, JTextField txtAbonar, JTextArea txtConcepto, JButton btnAbonar) {

        this.root = root;
        this.tblAbonos = tblAbonos;
        this.lblIdApartadoProducto = lblIdApartadoProducto;
        this.lblLoteApartadoProducto = lblLoteApartadoProducto;
        this.lblNombreApartadoProducto = lblNombreApartadoProducto;
        this.lblNombreCliente = lblNombreCliente;
        this.lblRestaApartadoProducto = lblRestaApartadoProducto;
        this.lblTotalApartadoProducto = lblTotalApartadoProducto;
        this.txtAbonar = txtAbonar;
        this.txtConcepto = txtConcepto;
        this.btnAbonar = btnAbonar;

        this.abonoDao = new AbonoDao();

        this.modeloAbono = new ModeloAbono();

        CrearModelo();
    }

    private void CrearModelo() {
        tblAbonos.setModel(modeloAbono);
        tblAbonos.setRowHeight(50);
        tblAbonos.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblAbonos.getColumnModel().getColumn(0).setMaxWidth(50);
    }

    public void Init(ProductoVo prodVo, ClienteVo clientVo) {
        this.productoVo = prodVo;
        this.clienteVo = clientVo;
        lblNombreCliente.setText(clienteVo.getNombre() + " " + clienteVo.getApellidoPaterno());
        lblIdApartadoProducto.setText(String.valueOf(productoVo.getId()));
        CargarProducto();
        CargarAbonos();
        Listeners();
    }

    private void Listeners() {
        btnAbonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarAbono();
            }
        });

        txtAbonar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.') {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!"".equals(txtAbonar.getText())) {
                    double cantidad = Double.parseDouble(txtAbonar.getText());
                    double resto = Double.parseDouble(lblRestaApartadoProducto.getText().substring(1));
                    if (cantidad > resto) {
                        txtAbonar.setText("");
                        UtilidadesPvr.warning("El monto ingresado [" + cantidad + "] es mayor a la cantidad restante [" + resto + "].");
                    }
                }
            }
        });
    }

    private void CargarProducto() {
        try {
            ArrayList<ProductoVo> dat = new ProductoDao().GetProducto(productoVo.getLote());
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ProductoVo vo = dat.get(0);
                    lblLoteApartadoProducto.setText(vo.getLote());
                    lblNombreApartadoProducto.setText(vo.getProducto());
                    lblTotalApartadoProducto.setText(String.valueOf(productoVo.getPventa()));
//                    productoVo.setPventa(vo.getPventa());
                } else {
                    UtilidadesPvr.warning(dat.get(0).getEstatus());
                }
            } else {
                root.setTitle(root.getTitle() + " - Sin abonos registrados");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            UtilidadesPvr.error(except);
        }
    }

    private void CargarAbonos() {
        try {
            limpiarTablaAbonos();
            ArrayList<ApartadoAbonoVo> dat = abonoDao.ListarAbonos(productoVo.getId());
            int t = dat.size();
            double total = 0;
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    root.setTitle("Abonos");
                    ApartadoAbonoVo vo;
                    for (int i = 0; i < t; i++) {
                        vo = dat.get(i);
                        Object[] dato = new Object[5];
                        dato[0] = (i + 1);
                        dato[1] = vo.getFecha();
                        dato[2] = vo.getHora();
                        dato[3] = vo.getCantidad();
                        dato[4] = vo.getObservacion();

                        total += vo.getCantidad();
                        modeloAbono.addRow(dato);
                    }
                    lblRestaApartadoProducto.setText("$" + (productoVo.getPventa() - total));
                    if ((productoVo.getPventa() - total) == 0) {
                        btnAbonar.setEnabled(false);
                        btnAbonar.setText("PAGADO");
                        btnAbonar.setForeground(SUCCESS);
                        setBordePagado(lblNombreCliente, "Cliente");
                        setBordePagado(lblIdApartadoProducto, "Id apartado");
                        setBordePagado(lblLoteApartadoProducto, "Lote");
                        setBordePagado(lblNombreApartadoProducto, "Producto");
                        setBordePagado(lblTotalApartadoProducto, "Total");
                        setBordePagado(lblRestaApartadoProducto, "Resta");
                        txtAbonar.setEditable(false);
                    }
                } else {
                    UtilidadesPvr.warning(dat.get(0).getEstatus());
                }
            } else {
                lblRestaApartadoProducto.setText("$" + (productoVo.getPventa() - total));
                root.setTitle(root.getTitle() + " - Sin abonos registrados");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            UtilidadesPvr.warning(except);
        }
    }

    private void RegistrarAbono() {
        try {
            if (!"".equals(txtAbonar.getText())) {
                ApartadoAbonoVo aav = new ApartadoAbonoVo();
                aav.setIdApartado(productoVo.getId());
                aav.setCantidad(Integer.parseInt(txtAbonar.getText()));
                aav.setObservacion(txtConcepto.getText());
                double t = Double.parseDouble(lblTotalApartadoProducto.getText());
                ApartadoAbonoVo abonoVo = abonoDao.Registro(aav, t);
                if (abonoVo.getEstatus().equals(OK)) {
                    UtilidadesPvr.success("Abono registrado correctamente");
                    CargarAbonos();
                    TicketAbonoVo tav = abonoDao.AbonoTicket(aav.getIdApartado());
                    if (tav.getEstatus().equals(OK)) {
                        new Ticket().ticketAbonoSTD(tav);
                    } else {
                        UtilidadesPvr.error(tav.getEstatus());
                    }

                    txtAbonar.setText("");
                    txtConcepto.setText("");
                } else {
                    UtilidadesPvr.error(abonoVo.getEstatus());
                }
            } else {
                UtilidadesPvr.warning("Ingrese un monto para abonar!");
            }
        } catch (NumberFormatException e) {
            String err = (e.getMessage() != null) ? e.getMessage() : e.toString();
            UtilidadesPvr.error(err);
        }
    }
    
    public void limpiarTablaAbonos() {
        for (int i = modeloAbono.getRowCount() - 1; i >= 0; i--) {
            modeloAbono.removeRow(i);
        }
    }

    private void setBordePagado(JLabel l, String titulo) {
        l.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createLineBorder(SUCCESS, 3),
                        titulo,
                        javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.ABOVE_BOTTOM,
                        new FuenteUbuntu().AplicarRegularFont(15),
                        SUCCESS));
        l.setForeground(SUCCESS);
    }

}
