package mx.venado.pvr.controlador;

import ds.desktop.notify.DesktopNotify;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;
import mx.venado.pvr.modelo.dao.AbonoDao;
import mx.venado.pvr.modelo.dao.ApartadoDao;
import mx.venado.pvr.modelo.dao.ClienteDao;
import mx.venado.pvr.modelo.vo.ApartadoProductoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.modelo.vo.ClienteApartadoVo;
import mx.venado.pvr.modelo.vo.ClienteVo;
import mx.venado.pvr.modelo.vo.DatVo;
import mx.venado.pvr.modelo.vo.ProductoVo;
import mx.venado.pvr.seguridad.AES;
import mx.venado.pvr.seguridad.Login;
import mx.venado.pvr.seguridad.LoginDao;
import mx.venado.pvr.seguridad.LoginVo;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.BloqueadoIcono;
import mx.venado.pvr.utilidades.DescuentoIcono;
import mx.venado.pvr.utilidades.ErrorIcono;
import mx.venado.pvr.utilidades.FuenteUbuntu;
import mx.venado.pvr.utilidades.InformacionIcono;
import mx.venado.pvr.utilidades.UtilidadesPvr;
import mx.venado.pvr.utilidades.Window;
import mx.venado.pvr.vista.ModeloApartados;
import mx.venado.pvr.vista.ModeloClientesApartados;
import mx.venado.pvr.vista.TableCellRendererApartados;
import mx.venado.pvr.vista.TableCellRendererClientes;
import pvr.DfrmAbonos;
import pvr.DfrmAgregarApartados;
import pvr.DfrmCambio;
import pvr.DfrmRegresar;
import pvr.PnlPrecios;
import mx.venado.pvr.utilidades.IPvrConstant;

public class PvrApartado implements IPvrConstant {

    //Columnas tabla apartado producto
    private static final int COL_ID = 0;
    private static final int COL_CODIGO = 1;
    private static final int COL_PRODUCTO = 2;
    private static final int COL_FECHA = 3;
    private static final int COL_HORA = 4;
    private static final int COL_PVENTA = 5;
    private static final int COL_CANTIDAD = 6;
    private static final int COL_DESCUENTO = 7;
    private static final int COL_TOTAL = 8;
    private static final int COL_BTN_ABONAR = 9;
    private static final int COL_ESTATUS = 10;

    JLabel lblArtApartados;
    JLabel lblCorreoCliente;
    JLabel lblIdCliente;
    JLabel lblNombreCliente;
    JLabel lblTelefonoCliente;
    JLabel lblTotal;
    JTable tblApartados;
    JTable tblApartadosClientes;
    JLabel lblPagado;
    JLabel lblResta;
    JLabel lblIdApartado;
    JTextField txtFiltro;
    JLabel lblEstatus;
    JInternalFrame intfrm;
    JButton btnAgregarApartado;
    JFrame parent;

    JButton btnCambiar;
    JButton btnRegresar;
    JButton btnDescuento;

    UtilidadesPvr utilPvr = new UtilidadesPvr();
    ModeloApartados modeloApartado;
    ModeloClientesApartados modeloApartadoCliente;
    TableRowSorter filtro;

    ClienteDao clienteDao;
    ApartadoDao apartadoDao;

    double totalApartado = 0;

    /**
     * Tipo de usuario logeado
     */
    private String tipo = "STD";
    /**
     * Id del usuario logeado
     */
    private int idusuario = -1;

    public PvrApartado(JFrame parent, JInternalFrame intfrm, JLabel lblEstatus,
            String tipo, int idusuario, JLabel lblArtApartados,
            JLabel lblCorreoCliente, JLabel lblIdCliente, JLabel lblNombreCliente,
            JLabel lblTelefonoCliente, JLabel lblTotal, JTable tblApartados,
            JTextField txtFiltro, JTable tblApartadosClientes, JLabel lblPagado,
            JLabel lblIdApartado, JButton btnAgregarApartado, JLabel lblResta,
            JButton btnCambiar, JButton btnRegresar, JButton btnDescuento) {
        this.intfrm = intfrm;
        this.lblEstatus = lblEstatus;
        this.lblArtApartados = lblArtApartados;
        this.lblCorreoCliente = lblCorreoCliente;
        this.lblIdCliente = lblIdCliente;
        this.lblNombreCliente = lblNombreCliente;
        this.lblTelefonoCliente = lblTelefonoCliente;
        this.lblTotal = lblTotal;
        this.tblApartados = tblApartados;
        this.txtFiltro = txtFiltro;
        this.tblApartadosClientes = tblApartadosClientes;
        this.lblPagado = lblPagado;
        this.lblResta = lblResta;
        this.lblIdApartado = lblIdApartado;
        this.btnAgregarApartado = btnAgregarApartado;
        this.parent = parent;
        this.tipo = tipo;
        this.idusuario = idusuario;

        this.btnCambiar = btnCambiar;
        this.btnRegresar = btnRegresar;
        this.btnDescuento = btnDescuento;

        this.clienteDao = new ClienteDao();
        this.apartadoDao = new ApartadoDao();
        this.modeloApartadoCliente = new ModeloClientesApartados();
        this.modeloApartado = new ModeloApartados();

        this.btnCambiar.setIcon(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "cambiar.png", 25, 25));
        this.btnRegresar.setIcon(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "clock.png", 25, 25));
        this.btnDescuento.setIcon(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "descuento.png", 25, 25));

        activarOpcionesApartados(false);
        
        this.intfrm.setTitle(Window.createId("Apartado"));

        Listeners();
        CrearModelos();
    }

    private void ConfigForm() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        if ((d.width <= 900) || (parent.getWidth() <= 900)) {
            tblApartados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            tblApartados.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
    }

    private void CrearModelos() {
        tblApartados.setDefaultRenderer(Object.class, new TableCellRendererApartados());
        tblApartados.setModel(modeloApartado);
        tblApartados.setRowHeight(50);
        tblApartados.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        for (int i = 0; i < tblApartados.getColumnCount() - 2; i++) {
            tblApartados.getColumnModel().getColumn(i).setMinWidth(50);
        }
        tblApartados.getColumnModel().getColumn(0).setMaxWidth(50);
        tblApartados.getColumnModel().getColumn(9).setMaxWidth(50);
        tblApartados.getColumnModel().getColumn(10).setMaxWidth(50);
        tblApartadosClientes.setDefaultRenderer(Object.class, new TableCellRendererClientes());
        tblApartadosClientes.setModel(modeloApartadoCliente);
        tblApartadosClientes.setRowHeight(50);
        tblApartadosClientes.getTableHeader().setFont(new FuenteUbuntu().AplicarLightFont());
        tblApartadosClientes.getColumnModel().getColumn(0).setMaxWidth(50);
        CargarClientes();
    }

    private void Listeners() {
        intfrm.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                ConfigForm();
                CargarClientes();
            }
        });

        intfrm.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                ConfigForm();
            }
        });

        btnAgregarApartado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblApartadosClientes.getSelectedRow() != -1) {
                    ApartadoVo av = new ApartadoVo();
                    av.setId(("-".equals(lblIdApartado.getText())) ? -1 : Integer.parseInt(lblIdApartado.getText()));
                    av.setIdCliente(Integer.parseInt(lblIdCliente.getText()));
                    av.setTotal(totalApartado);
                    av.setEstatusApartado(DEUDA);

                    DfrmAgregarApartados agregarApartados = new DfrmAgregarApartados(parent, true);
                    agregarApartados.setDatosCliente(av);
                    agregarApartados.setVisible(true);

                    InfoApartados(Integer.parseInt(lblIdCliente.getText()));

                } else {
                    DesktopNotify.showDesktopMessage("Ups", "Por favor seleccione un cliente", DesktopNotify.WARNING);
                    utilPvr.SetLog(lblEstatus, ADV, "Por favor seleccione un cliente");
                }
            }
        });

        btnAgregarApartado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, "Apartar un nuevo producto.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });

        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtro = new TableRowSorter(modeloApartadoCliente);
                filtro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText().toUpperCase(), 1, 2));
                tblApartadosClientes.setRowSorter(filtro);
            }
        });
        
        lblNombreCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, lblNombreCliente.getText());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });
        
        lblCorreoCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, lblCorreoCliente.getText());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });
        
        tblApartadosClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblApartadosClientes.getSelectedRow();
                if (fila != -1) {
                    int idCliente = Integer.parseInt(tblApartadosClientes.getValueAt(fila, 0).toString());
                    InfoApartados(idCliente);
                }
            }
        });

        tblApartados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int columna = tblApartados.getSelectedColumn();
                int filaApartadoProducto = tblApartados.getSelectedRow();

                if (filaApartadoProducto == -1) {
                    activarOpcionesApartados(false);
                    return;
                } else {
                    activarOpcionesApartados(true);
                }

                if (columna == 9) {
                    ClienteVo clienteVo = new ClienteVo();
                    clienteVo.setId(Integer.parseInt(lblIdCliente.getText()));
                    clienteVo.setNombre(lblNombreCliente.getText());

                    ProductoVo productoVo = new ProductoVo();
                    productoVo.setId(Integer.parseInt(tblApartados.getValueAt(filaApartadoProducto, 0).toString()));
                    productoVo.setLote(tblApartados.getValueAt(filaApartadoProducto, 1).toString());
                    productoVo.setPventa(Double.parseDouble(tblApartados.getValueAt(filaApartadoProducto, COL_TOTAL).toString()));

                    DfrmAbonos abonos = new DfrmAbonos(parent, true);
                    abonos.setClienteVo(clienteVo);
                    abonos.setProductoVo(productoVo);
                    abonos.setVisible(true);
                    CargarAbonos(Integer.parseInt(lblIdApartado.getText()));
                }
            }
        });

        btnCambiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaApartadoProducto = tblApartados.getSelectedRow();
                if (filaApartadoProducto != -1) {
                    DfrmCambio cambio = new DfrmCambio(parent, true);
                    cambio.Cambiar(Integer.parseInt(lblIdApartado.getText()),
                            Integer.parseInt(tblApartados.getValueAt(filaApartadoProducto, 0).toString()),
                            tblApartados.getValueAt(filaApartadoProducto, 1).toString(),
                            Double.parseDouble(tblApartados.getValueAt(filaApartadoProducto, 5).toString()));
                    cambio.setVisible(true);
                } else {
                    activarOpcionesApartados(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, "Cambiar producto");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });

        btnRegresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaApartadoProducto = tblApartados.getSelectedRow();
                if (!getStringApartTbl(filaApartadoProducto, COL_ESTATUS).equals(LIQUIDADO)) {
                    if (filaApartadoProducto != -1) {
                        double totalApartadoFinal = Double.parseDouble(lblTotal.getText().substring(1)) - getDoubleApartTbl(filaApartadoProducto, COL_TOTAL);
                        DfrmRegresar regresar = new DfrmRegresar(parent, true);

                        regresar.inicializar(
                                Integer.parseInt(lblIdApartado.getText()),
                                getIntApartTbl(filaApartadoProducto, COL_ID),
                                getStringApartTbl(filaApartadoProducto, COL_CODIGO),
                                getStringApartTbl(filaApartadoProducto, COL_PRODUCTO),
                                lblNombreCliente.getText(),
                                getStringApartTbl(filaApartadoProducto, COL_FECHA),
                                totalApartadoFinal);
                        regresar.setVisible(true);

                        InfoApartados(Integer.parseInt(lblIdCliente.getText()));

                    } else {
                        activarOpcionesApartados(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "El producto ya ha sido liquidado, imposible regresarlo al inventario", "Información", JOptionPane.INFORMATION_MESSAGE, new InformacionIcono());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, "Verificar tiempo inactivo y opciones para regresar el producto");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });

        btnDescuento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaApartadoProducto = tblApartados.getSelectedRow();
                if (filaApartadoProducto != -1) {
                    int numabonos = new AbonoDao().countAbonos(Integer.parseInt(tblApartados.getValueAt(filaApartadoProducto, 0).toString()));
                    if (tblApartados.getValueAt(filaApartadoProducto, 10).toString().equals(LIQUIDADO)) {
                        JOptionPane.showMessageDialog(parent, "El producto ya ha sido liquidado", "Información", JOptionPane.INFORMATION_MESSAGE, new InformacionIcono());
                    } else if (Double.parseDouble(tblApartados.getValueAt(filaApartadoProducto, 7).toString()) > 0) {
                        JOptionPane.showMessageDialog(parent, "Ya se ha aplicado un descuento", "Información", JOptionPane.INFORMATION_MESSAGE, new InformacionIcono());
                    } else if (numabonos > 0) {
                        JOptionPane.showMessageDialog(parent, "El producto cuenta con abonos registrados, imposible aplicar descuento", "Advertencia", JOptionPane.INFORMATION_MESSAGE, new AdvertenciaIcono());
                    } else {
                        String clave = confirmar();
                        if (!clave.equals(NR)) {
                            LoginDao verificar = new LoginDao();
                            LoginVo lv = verificar.verificarAdministrador(clave);
                            switch (lv.getEstatus()) {
                                case Login.ACCESO_CONCEDIDO:
                                    double descuento = Descuento();
                                    if (descuento > 0) {
                                        double totalProductoFinal = getDoubleApartTbl(filaApartadoProducto, COL_TOTAL) - descuento;
                                        int idApartado = Integer.parseInt(lblIdApartado.getText());
                                        double totalApartadoFinal = Double.parseDouble(lblTotal.getText().substring(1)) - descuento;
                                        int idapartadoproducto = getIntApartTbl(filaApartadoProducto, COL_ID);
                                        
//                                        System.out.println("totalProductoFinal = totalproducto[" + getDoubleApartTbl(filaApartadoProducto, COL_TOTAL) + "] - descuento[" + descuento + "]:" + totalProductoFinal);
//                                        System.out.println("idApartado:" + idApartado);
//                                        System.out.println("totalApartadoFinal = total[" + lblTotal.getText().substring(1) + "] - totalProductoFinal[" + totalProductoFinal + "]:" + totalApartadoFinal);
//                                        System.out.println("totalApartadoFinal = total[" + lblTotal.getText().substring(1) + "] - descuento[" + descuento + "]:" + totalApartadoFinal2);
//                                        System.out.println("idapartadoproducto:" + idapartadoproducto);
                                        
                                        String estatus = apartadoDao.addDescuento(idApartado, totalApartadoFinal, idapartadoproducto, descuento, totalProductoFinal).getEstatus();
                                        if (estatus.equals(OK)) {
                                            DesktopNotify.showDesktopMessage("Información", "Descuento aplicado", DesktopNotify.SUCCESS, 5000L);
                                            //CargarApartadoItem(Integer.parseInt(lblIdApartado.getText()));
                                            CargarApartado(Integer.parseInt(lblIdCliente.getText()));
                                        } else {
                                            System.out.println(estatus);
                                            DesktopNotify.showDesktopMessage("Información", "Descuento no aplicado", DesktopNotify.ERROR, 5000L);
                                        }
                                    }
                                    break;
                                case Login.ACCESO_DENEGADO:
                                    JOptionPane.showMessageDialog(parent, "Acceso denegado", "Contraseña erronea", JOptionPane.ERROR_MESSAGE, new ErrorIcono());
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(parent, "Ocurrio un error");
                                    break;
                            }
                        }
                    }
                } else {
                    activarOpcionesApartados(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, "Realizar descuento");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
            }
        });
    }

    private void CargarClientes() {
        try {
            modeloApartadoCliente.clear();
            ArrayList<ClienteApartadoVo> dat = clienteDao.ListarNumApartados();
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ClienteApartadoVo vo;
                    for (int i = 0; i < t; i++) {
                        vo = dat.get(i);
                        Object[] dato = new Object[4];
                        dato[0] = vo.getId();
                        dato[1] = vo.getNombre().toUpperCase();
                        dato[2] = vo.getApellidoPaterno().toUpperCase();
                        dato[3] = vo.getProductos();
                        modeloApartadoCliente.addRow(dato);
                    }
                } else {
                    DesktopNotify.showDesktopMessage("Ups", dat.get(0).getEstatus(), DesktopNotify.ERROR);
                    utilPvr.SetLog(lblEstatus, NR, dat.get(0).getEstatus());
                }
            } else {
                DesktopNotify.showDesktopMessage("Información", "Al parecer no hay clientes registrados!", DesktopNotify.INFORMATION);
                utilPvr.SetLog(lblEstatus, STD, "Al parecer no hay clientes registrados!");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }

    private void InfoApartados(int idCliente) {
        utilPvr.SetLog(lblEstatus, STD, MSJ_DEFAULT);
        try {
            lblIdApartado.setText("-");
            lblPagado.setText("-");
            lblResta.setText("-");
            modeloApartado.clear();
            ArrayList<ClienteVo> dat = clienteDao.Listar(idCliente);
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ClienteVo vo = dat.get(0);
                    lblIdCliente.setText("" + vo.getId());
                    lblNombreCliente.setText(vo.getNombre() + " " + vo.getApellidoPaterno() + " " + vo.getApellidoMaterno());
                    lblTelefonoCliente.setText(vo.getTelefono());
                    lblCorreoCliente.setText(vo.getCorreo());
                    CargarApartado(idCliente);
                } else {
                    JOptionPane.showInternalMessageDialog(intfrm,
                            dat.get(0).getEstatus(),
                            "Ups", JOptionPane.OK_OPTION, new ErrorIcono());
                    utilPvr.SetLog(lblEstatus, NR, dat.get(0).getEstatus());
                }
            } else {
                DesktopNotify.showDesktopMessage("Ups", "Cliente no encontrado!", DesktopNotify.ERROR);
                utilPvr.SetLog(lblEstatus, STD, "Cliente no encontrado!");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }

    private void CargarApartado(int idCliente) {
        try {
            ArrayList<ApartadoVo> dat = apartadoDao.ListarApartado(idCliente);
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ApartadoVo vo = dat.get(0);
                    lblIdApartado.setText("" + vo.getId());
                    lblTotal.setText("$" + vo.getTotal());
                    totalApartado = vo.getTotal();
                    CargarApartadoItem(vo.getId());
                    CargarAbonos(vo.getId());
                } else {
                    utilPvr.SetLog(lblEstatus, NR, dat.get(0).getEstatus());
                }
            } else {
                lblArtApartados.setText("0");
                lblTotal.setText("$0");
                totalApartado = 0;
                DesktopNotify.showDesktopMessage("PVR", "Al parecer el cliente no tiene apartados registrados!", DesktopNotify.INFORMATION, 4000L);
                utilPvr.SetLog(lblEstatus, STD, "Al parecer el cliente no tiene apartados registrados!");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }

    private void CargarApartadoItem(int idApartado) {
        try {
            modeloApartado.clear();
            ArrayList<ApartadoProductoVo> dat = apartadoDao.ListarApartadoItems(idApartado);
            int t = dat.size();
            if (t > 0) {
                if (dat.get(0).getEstatus() == null) {
                    ApartadoProductoVo vo;
                    double cantArt = 0;
                    for (int i = 0; i < t; i++) {
                        vo = dat.get(i);
                        Object[] dato = new Object[11];
                        dato[0] = vo.getId();
                        dato[1] = vo.getLote();
                        dato[2] = vo.getProducto();
                        dato[3] = vo.getFecha();
                        dato[4] = vo.getHora();
                        dato[5] = vo.getPrecioventa();
                        dato[6] = vo.getCantidad();
                        dato[7] = vo.getDescuento();
                        dato[8] = vo.getTotal();
                        dato[9] = new JLabel(utilPvr.RedimencionarImagen(URL_MULTIMEDIA + "Abono2.png"));
                        dato[10] = vo.getEstatusProducto();
                        cantArt += vo.getCantidad();
                        modeloApartado.addRow(dato);
                    }
                    lblArtApartados.setText("" + cantArt);
                } else {
                    DesktopNotify.showDesktopMessage("Ups", dat.get(0).getEstatus(), DesktopNotify.ERROR);
                    utilPvr.SetLog(lblEstatus, NR, dat.get(0).getEstatus());
                }
            } else {
                lblArtApartados.setText("0");
                lblTotal.setText("$0");
                utilPvr.SetLog(lblEstatus, STD, "Al parecer el cliente no tiene apartados registrados!");
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            DesktopNotify.showDesktopMessage("Ups", except, DesktopNotify.ERROR);
            utilPvr.SetLog(lblEstatus, NR, except);
        }
    }

    private void CargarAbonos(int idApartado) {
        try {
            DatVo datVo = apartadoDao.TotalAbonos(idApartado);
            if (datVo.getEstatus() == null ? DatVo.OK == null : datVo.getEstatus().equals(DatVo.OK)) {
                if (datVo.getValDouble() > 0) {
                    lblPagado.setText("$" + datVo.getValDouble());
                    lblResta.setText("$" + (totalApartado - datVo.getValDouble()));
                    if ((totalApartado - datVo.getValDouble()) == 0) {
                        setBordePagado(lblIdApartado, "Id de apartado");
                        setBordePagado(lblArtApartados, "Articulos apartados");
                        setBordePagado(lblTotal, "Total");
                        setBordePagado(lblPagado, "Pagado");
                        setBordePagado(lblResta, "Resta");
                    } else {
                        setBordeDeuda(lblIdApartado, "Id de apartado");
                        setBordeDeuda(lblArtApartados, "Articulos apartados");
                        setBordeDeuda(lblTotal, "Total");
                        setBordeDeuda(lblPagado, "Pagado");
                        setBordeDeuda(lblResta, "Resta");
                    }
                } else {
                    setBordeDeuda(lblIdApartado, "Id de apartado");
                    setBordeDeuda(lblArtApartados, "Articulos apartados");
                    setBordeDeuda(lblTotal, "Total");
                    setBordeDeuda(lblPagado, "Pagado");
                    setBordeDeuda(lblResta, "Resta");
                    utilPvr.SetLog(lblEstatus, STD, "Al parecer el cliente no tiene abonos registrados!");
                }
            } else {
                utilPvr.SetLog(lblEstatus, datVo.getEstatus(), NR);
            }
        } catch (Exception e) {
            String except = (e.getMessage() != null) ? e.getMessage() : e.toString();
            utilPvr.SetLog(lblEstatus, NR, except);
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

    private void setBordeDeuda(JLabel l, String titulo) {
        l.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        javax.swing.BorderFactory.createLineBorder(ERROR, 3),
                        titulo,
                        javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.ABOVE_BOTTOM,
                        new FuenteUbuntu().AplicarRegularFont(15),
                        ERROR));
        l.setForeground(ERROR);
    }

    private void activarOpcionesApartados(boolean b) {
        this.btnCambiar.setEnabled(b);
        this.btnRegresar.setEnabled(b);
        this.btnDescuento.setEnabled(b);
    }

    private int getIntApartTbl(int row, int column) {
        return Integer.parseInt(tblApartados.getValueAt(row, column).toString());
    }

    private double getDoubleApartTbl(int row, int column) {
        return Double.parseDouble(tblApartados.getValueAt(row, column).toString());
    }

    private String getStringApartTbl(int row, int column) {
        return String.valueOf(tblApartados.getValueAt(row, column));
    }

    private String confirmar() {
        String request;
        JPasswordField pwd = new JPasswordField();
        pwd.setHorizontalAlignment(JTextField.CENTER);
        pwd.setEchoChar('X');
        int r = JOptionPane.showConfirmDialog(parent, pwd, "Contraseña de administrador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new BloqueadoIcono());
        request = (r == JOptionPane.OK_OPTION) ? AES.sha1(String.valueOf(pwd.getPassword())) : NR;

        return request;
    }

    private double Descuento() {
        double request = -1;
        
        PnlPrecios pnlPrecios = new PnlPrecios();

        int r = JOptionPane.showConfirmDialog(parent, pnlPrecios, "Descuento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new DescuentoIcono());
        if (r == JOptionPane.OK_OPTION) {
            request = pnlPrecios.getValue();
        }
        return request;
    }

}
