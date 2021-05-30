package mx.venado.pvr.controlador;

import ds.desktop.notify.DesktopNotify;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import mx.venado.pvr.modelo.dao.ApartadoDao;
import mx.venado.pvr.modelo.dao.ClienteDao;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.ClienteVo;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.Window;
import mx.venado.pvr.vista.ModeloClientes;
import mx.venado.pvr.vista.TableCellRendererClientes;
import pvr.DfrmDetallesCliente;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrCliente implements IPvrConstant {
    
    DefaultTableModel modelo;
    TableRowSorter filtro;
    
    JTable tblClientes;
    JButton btnBuscar;
    JButton btnGuardar;
    JButton btnNuevo;
    JTextField txtAM;
    JTextField txtAP;
    JTextField txtCorreo;
    JTextArea txtDireccion;
    JTextField txtFiltro;
    JTextField txtNombre;
    JTextField txtTelefono;
    JLabel lblEstatus;
    JLabel lblMsj;
    JInternalFrame form;
    JFrame frm;
    JScrollPane scrllpnTxtDireccion;
    JLabel lblClientesRegistrados;
    
    UtilidadesPvr utilPvr = new UtilidadesPvr();
    ClienteDao clienteDao;
    ClienteVo clienteVo;

    /**
     * Tipo de usuario logeado
     */
    private String tipo = "STD";
    /**
     * Id del usuario logeado
     */
    private int idusuario = -1;
    
    private int IdCliente = -1;
    
    public PvrCliente(JFrame frm, JInternalFrame form, String tipo, int idusuario, JTable tblClientes,
            JButton btnGuardar, JButton btnNuevo, JTextField txtAM, JTextField txtAP, JTextField txtCorreo,
            JTextArea txtDireccion, JTextField txtFiltro, JTextField txtNombre, JTextField txtTelefono,
            JLabel lblEstatus, JScrollPane scrllpnTxtDireccion, JLabel lblClientesRegistrados, JLabel lblMsj) {
        this.tblClientes = tblClientes;
        this.btnGuardar = btnGuardar;
        this.btnNuevo = btnNuevo;
        this.txtAM = txtAM;
        this.txtAP = txtAP;
        this.txtCorreo = txtCorreo;
        this.txtDireccion = txtDireccion;
        this.txtFiltro = txtFiltro;
        this.txtNombre = txtNombre;
        this.txtTelefono = txtTelefono;
        this.lblEstatus = lblEstatus;
        this.form = form;
        this.frm = frm;
        this.scrllpnTxtDireccion = scrllpnTxtDireccion;
        this.lblClientesRegistrados = lblClientesRegistrados;
        this.lblMsj = lblMsj;
        this.tipo = tipo;
        this.idusuario = idusuario;
        
        this.modelo = new ModeloClientes();
        this.clienteDao = new ClienteDao();
        this.clienteVo = new ClienteVo();
        Listeners();
        CrearModelo();
        form.setTitle(Window.createId("Clientes"));
    }
    
    private void ConfigForm() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        if ((d.width <= 900) || (frm.getWidth() <= 900)) {
            tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
    }
    
    private void CrearModelo() {
        tblClientes.setDefaultRenderer(Object.class, new TableCellRendererClientes());
        tblClientes.setModel(modelo);
        tblClientes.setRowHeight(50);
        tblClientes.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblClientes.getColumnModel().getColumn(0).setMaxWidth(40);
        tblClientes.getColumnModel().getColumn(7).setMaxWidth(50);
        tblClientes.getColumnModel().getColumn(8).setMaxWidth(50);
        tblClientes.getColumnModel().getColumn(9).setMaxWidth(50);
    }
    
    private void Listeners() {
        form.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                ConfigForm();
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                Listar();
            }
            
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (IdCliente == -1) {
                    GuardarCliente();
                } else {
                    EditarCliente();
                }
            }
        });
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nuevo();
                utilPvr.SetLog(lblMsj, MSJ, "<pvr>");
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtNombre, "Nombre");
            }
        });
        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtAP, "Apellido paterno");
            }
        });
        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtAM, "Apellido materno");
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtTelefono, "Teléfono");
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(txtCorreo, "Correo");
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                utilPvr.IndicadorContenido(scrllpnTxtDireccion, txtDireccion, "Dirección");
            }
        });
        tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblClientes.getSelectedRow();
                int columna = tblClientes.getSelectedColumn();
                ClienteVo cliente = new ClienteVo();
                if (columna != -1) {
                    cliente.setId(Integer.parseInt(tblClientes.getValueAt(fila, 0).toString()));
                    cliente.setNombre(tblClientes.getValueAt(fila, 1).toString());
                    cliente.setApellidoPaterno(tblClientes.getValueAt(fila, 2).toString());
                    cliente.setApellidoMaterno(tblClientes.getValueAt(fila, 3).toString());
                    cliente.setTelefono(tblClientes.getValueAt(fila, 4).toString());
                    cliente.setCorreo(tblClientes.getValueAt(fila, 5).toString());
                    cliente.setObservacion(tblClientes.getValueAt(fila, 6).toString());
                    
                    switch (columna) {
                        case 7:
                            DfrmDetallesCliente det = new DfrmDetallesCliente(frm, true);
                            det.setClienteVo(cliente);
                            det.ConfigForm();
                            det.setVisible(true);
                            break;
                        //editar
                        case 8:
                            btnNuevo.setText("CANCELAR");
                            IdCliente = cliente.getId();
                            txtNombre.setText(cliente.getNombre());
                            txtAP.setText(cliente.getApellidoPaterno());
                            txtAM.setText(cliente.getApellidoMaterno());
                            txtCorreo.setText(cliente.getCorreo());
                            txtTelefono.setText(cliente.getTelefono());
                            txtDireccion.setText(cliente.getObservacion());
                            IndicadorContenido();
                            break;
                        //eliminar
                        case 9:
                            IdCliente = cliente.getId();
                            ArrayList<ApartadoVo> ap = new ApartadoDao().ListarApartado(IdCliente);
                            if (ap.size() > 0) {
                                JOptionPane.showMessageDialog(frm, "El cliente a seleccionado tiene vinculados \n"
                                        + ap.size() + " apartado(s), no se puede eliminar!!", "Advertencia", JOptionPane.WARNING_MESSAGE, new AdvertenciaIcono());
                            } else if (ap.isEmpty()) {
                                int resp = JOptionPane.showConfirmDialog(frm, "Esta seguro(a) que desea eliminar a este cliente?", "Confirmación",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new AdvertenciaIcono());
                                if (resp == JOptionPane.YES_OPTION) {
                                    String estatus = clienteDao.Eliminar(cliente).getEstatus();
                                    if (estatus.equals(OK)) {
                                        utilPvr.SetLog(lblMsj, OK, "Cliente eliminado con éxito!");
                                    } else {
                                        utilPvr.SetLog(lblMsj, NR, estatus);
                                    }
                                    Listar();
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    
                }
                
            }
        });
        
        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtro = new TableRowSorter(modelo);
                filtro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText().toUpperCase(), 1, 2, 4, 5));
                tblClientes.setRowSorter(filtro);
            }
        });
    }
    
    private void GuardarCliente() {
        try {
            if (VerificarDatos()) {
                ClienteVo cte = new ClienteVo();
                cte.setNombre(txtNombre.getText());
                cte.setApellidoPaterno(txtAP.getText());
                cte.setApellidoMaterno(txtAM.getText());
                cte.setTelefono(txtTelefono.getText());
                cte.setCorreo(txtCorreo.getText());
                cte.setObservacion(txtDireccion.getText());
                clienteVo = clienteDao.Registro(cte);
                if (clienteVo.getEstatus().equals(OK)) {
                    utilPvr.SetLog(lblMsj, OK, "Cliente registrado con éxito!");
                    Listar();
                    Nuevo();
                } else {
                    utilPvr.SetLog(lblMsj, NR, clienteVo.getEstatus());
                }
            } else {
                utilPvr.SetLog(lblMsj, ADV, "Los campos Nombre, Apellido paterno son obligatorios y debe ingresar un correo o teléfono...");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            utilPvr.SetLog(lblMsj, NR, except);
        }
    }
    
    private void EditarCliente() {
        try {
            if (VerificarDatos()) {
                ClienteVo cte = new ClienteVo();
                cte.setId(IdCliente);
                cte.setNombre(txtNombre.getText());
                cte.setApellidoPaterno(txtAP.getText());
                cte.setApellidoMaterno(txtAM.getText());
                cte.setTelefono(txtTelefono.getText());
                cte.setCorreo(txtCorreo.getText());
                cte.setObservacion(txtDireccion.getText());
                clienteVo = clienteDao.Edicion(cte);
                if (clienteVo.getEstatus().equals(OK)) {
                    utilPvr.SetLog(lblMsj, OK, "Datos del cliente editados con éxito!");
                    Listar();
                    Nuevo();
                } else {
                    utilPvr.SetLog(lblMsj, NR, clienteVo.getEstatus());
                }
            } else {
                utilPvr.SetLog(lblMsj, ADV, "Los campos Nombre, Apellido paterno son obligatorios y debe ingresar un correo o teléfono...");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR, 6000l);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }
    
    public void Listar() {
        try {
            LimpiarTabla();
            ArrayList<ClienteVo> dat = clienteDao.Listar();
            int t = dat.size();
            lblClientesRegistrados.setText("" + t);
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ClienteVo test;
                    for (int i = 0; i < t; i++) {
                        test = dat.get(i);
                        Object[] fila = new Object[10];
                        fila[0] = test.getId();
                        fila[1] = test.getNombre().toUpperCase();
                        fila[2] = test.getApellidoPaterno().toUpperCase();
                        fila[3] = test.getApellidoMaterno().toUpperCase();
                        fila[4] = test.getTelefono();
                        fila[5] = test.getCorreo();
                        fila[6] = test.getObservacion();
                        fila[7] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Informacion2.png"));
                        fila[8] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Editar.png"));
                        fila[9] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Quitar.png"));
                        modelo.addRow(fila);
                    }
                } else {
                    DesktopNotify.showDesktopMessage("Ups", dat.get(0).getEstatus(), DesktopNotify.ERROR, 6000l);
                    utilPvr.SetLog(lblEstatus, NR, dat.get(0).getEstatus());
                    
                }
            } else {
                DesktopNotify.showDesktopMessage("Información", "Al parecer no hay clientes registrados!", DesktopNotify.ERROR, 6000l);
                utilPvr.SetLog(lblEstatus, STD, "Al parecer no hay clientes registrados!");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR, 6000l);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }
    
    private boolean VerificarDatos() {
        utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
        return (!"".equals(txtNombre.getText()) && !"".equals(txtAP.getText())
                && (!"".equals(txtTelefono.getText()) || !"".equals(txtCorreo.getText())));
    }
    
    private void LimpiarTabla() {
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
    
    private void Nuevo() {
        IdCliente = -1;
        txtNombre.setText("");
        txtAP.setText("");
        txtAM.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        
        IndicadorContenido();
        utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
        btnNuevo.setText("NUEVO");
        txtNombre.requestFocus();
    }
    
    private void IndicadorContenido() {
        utilPvr.IndicadorContenido(txtNombre, "Nombre");
        utilPvr.IndicadorContenido(txtAP, "Apellido paterno");
        utilPvr.IndicadorContenido(txtAM, "Apellido materno");
        utilPvr.IndicadorContenido(txtTelefono, "Teléfono");
        utilPvr.IndicadorContenido(txtCorreo, "Correo");
        utilPvr.IndicadorContenido(scrllpnTxtDireccion, txtDireccion, "Dirección");
    }
    
}
