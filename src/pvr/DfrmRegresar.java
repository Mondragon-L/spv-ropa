package pvr;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mx.venado.pvr.modelo.dao.AbonoDao;
import mx.venado.pvr.modelo.dao.ApartadoDao;
import mx.venado.pvr.modelo.vo.ApartadoAbonoVo;
import mx.venado.pvr.modelo.vo.ApartadoVo;
import mx.venado.pvr.seguridad.AES;
import mx.venado.pvr.seguridad.Login;
import mx.venado.pvr.seguridad.LoginDao;
import mx.venado.pvr.seguridad.LoginVo;
import mx.venado.pvr.utilidades.AdvertenciaIcono;
import mx.venado.pvr.utilidades.BloqueadoIcono;
import mx.venado.pvr.utilidades.ConstantesPvr;
import mx.venado.pvr.utilidades.CorrectoIcono;
import mx.venado.pvr.utilidades.ErrorIcono;
import mx.venado.pvr.utilidades.Fecha;
import mx.venado.pvr.utilidades.FuenteUbuntu;

public class DfrmRegresar extends javax.swing.JDialog {

    private int idproducto;
    private int idapartadoproducto;
    private String lote;
    private String producto;
    private String ultimo_abono;
    private int transcurridos;
    private String cliente;
    private String fechaapartado;
    
    int idApartado;
    double totalApartadoFinal;

    private AbonoDao abonoDao;
    private ApartadoDao apartadoDao;

    private boolean bloqueo = true;

    public DfrmRegresar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configForm(parent);
    }

    private void configForm(java.awt.Frame parent) {
        this.setLocationRelativeTo(parent);
    }

    public void inicializar(int idapartado, int idapartadoproducto, 
            String lote, String producto, String cliente, 
            String fechaapartado, double totalApartadoFinal) {
        this.idApartado = idapartado;
        this.idapartadoproducto = idapartadoproducto;
        this.lote = lote;
        this.producto = producto;
        this.cliente = cliente;
        this.fechaapartado = fechaapartado;
        this.totalApartadoFinal = totalApartadoFinal;
        this.addDetalles("Cliente", cliente);
        this.addDetalles();
        this.addDetalles("Apartado", idapartado);
        this.addDetalles("Código", lote);
        this.addDetalles("Producto", producto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        scrllpnDetalles = new javax.swing.JScrollPane();
        txtDetalles = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Regresar producto");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnRegresar.setBackground(ConstantesPvr.SUCCESS);
        btnRegresar.setFont(new FuenteUbuntu().AplicarRegularFont(16, Font.BOLD));
        btnRegresar.setForeground(ConstantesPvr.DEFAULT);
        btnRegresar.setText("Regresar a inventario");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        scrllpnDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)), "Detalles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new FuenteUbuntu().AplicarRegularFont()));

        txtDetalles.setEditable(false);
        txtDetalles.setBackground(new Color(0,0,0,0));
        txtDetalles.setColumns(20);
        txtDetalles.setFont(new FuenteUbuntu().AplicarLightFont());
        txtDetalles.setLineWrap(true);
        txtDetalles.setRows(5);
        txtDetalles.setWrapStyleWord(true);
        scrllpnDetalles.setViewportView(txtDetalles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrllpnDetalles)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 235, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrllpnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.dias();
    }//GEN-LAST:event_formWindowOpened

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        if (!bloqueo) {
            int respuesta = JOptionPane.showConfirmDialog(rootPane, "Los registros vinculados a este apartado seran \neliminados, desea regresarlo a inventario?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new AdvertenciaIcono());
            if (respuesta == JOptionPane.YES_OPTION) {
                String clave = confirmar();
                if (!clave.equals(ConstantesPvr.NR)) {
                    LoginDao verificar = new LoginDao();
                    LoginVo lv = verificar.verificarAdministrador(clave);
                    switch (lv.getEstatus()) {
                        case Login.ACCESO_CONCEDIDO:
                            this.RegresarAInventario(idApartado, idapartadoproducto, lote, totalApartadoFinal);
                            break;
                        case Login.ACCESO_DENEGADO:
                            JOptionPane.showMessageDialog(rootPane, "Acceso denegado", "Contraseña erronea", JOptionPane.ERROR_MESSAGE, new ErrorIcono());
                            break;
                        default:
                            JOptionPane.showMessageDialog(rootPane, "Ocurrio un error");
                            break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ocurrio un error, no es posible realizar la accion", "Error", JOptionPane.ERROR_MESSAGE, new ErrorIcono());
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void RegresarAInventario(int idApartado, int idApartadoProducto, String lote, double totalApartadoFinal) {
        apartadoDao = new ApartadoDao();
        ApartadoVo apartadoVo = apartadoDao.regresarAInventario(idApartado, idApartadoProducto, lote, totalApartadoFinal);
        if (apartadoVo.getEstatus().equals(ConstantesPvr.OK)) {
            JOptionPane.showMessageDialog(rootPane, "Correcto", "Información", JOptionPane.INFORMATION_MESSAGE, new CorrectoIcono());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane, apartadoVo.getEstatus(), "Error", JOptionPane.ERROR_MESSAGE, new ErrorIcono());
        }
    }
    
    private void dias() {
        abonoDao = new AbonoDao();
        String[] response = abonoDao.getUltimoAbono(this.idapartadoproducto);
        if (response[0] == null) {
            if (!response[1].equals(ConstantesPvr.NA)) {
                this.transcurridos = Fecha.getNumeroDias(response[1], new Fecha().getFecha());
                this.addDetalles();
                this.addDetalles("Ultimo abono", response[1]);
                this.addDetalles("Dias transcurridos", this.transcurridos + " (" + (this.transcurridos / 30) + "  meses aprox.)");
                this.addDetalles();
                this.detallesRegresarInventario();
            } else {
                this.transcurridos = Fecha.getNumeroDias(this.fechaapartado, new Fecha().getFecha());
                this.addDetalles();
                this.addDetalles("Ultimo abono", "No se han realizado abonos");
                this.addDetalles("Dias transcurridos desde el apartado", this.transcurridos + " (" + (this.transcurridos / 30) + "  meses aprox.)");
                this.addDetalles();
                this.bloqueo = false;
            }
        } else {
            this.addDetalles();
            this.addDetalles(response[0]);
            this.addDetalles();
        }
    }

    private void detallesRegresarInventario() {
        this.addDetalles("Datos que se eliminaran si se regresa al inventario\n(NO SE PODRAN RECUPERAR UNA VEZ ELIMINADOS)", "");
        this.addDetalles();
        ArrayList<ApartadoAbonoVo> abonos = abonoDao.ListarAbonos(idapartadoproducto);
        if (!abonos.isEmpty()) {
            if (abonos.get(0).getEstatus() == null) {
                String itemab;
                ApartadoAbonoVo aav;
                this.addDetalles("Abonos registrados", "");
                this.addDetalles();
                for (int i = 0; i < abonos.size(); i++) {
                    this.addDetalles('*');
                    aav = abonos.get(i);
                    itemab = "[";
                    itemab += aav.getId() + " > ";
                    itemab += aav.getIdApartado() + " > ";
                    itemab += aav.getFecha() + " > ";
                    itemab += aav.getHora() + " > $";
                    itemab += aav.getCantidad() + " > ";
                    itemab += aav.getObservacion() + "]";
                    this.addDetallesAbonoItem(itemab);
                }
                this.bloqueo = false;
            } else {
                this.bloqueo = true;
            }
        } else {
            this.bloqueo = false;
        }

    }

    private void addDetalles() {
        String linea = "";
        for (int i = 0; i < 70; i++) {
            linea += "-";
        }
        txtDetalles.append(linea + "\n");
    }

    private void addDetalles(char carac) {
        String linea = "";
        for (int i = 0; i < 55; i++) {
            linea += carac;
        }
        txtDetalles.append(linea + "\n");
    }

    private void addDetalles(String str) {
        txtDetalles.append(str + "\n");
    }

    private void addDetalles(String etiqueta, Object valor) {
        txtDetalles.append(etiqueta + ":  " + String.valueOf(valor) + "\n");
    }

    private void addDetallesAbonoItem(String str) {
        txtDetalles.append("=> " + str + "\n");
    }

    private String confirmar() {
        String request;
        JPasswordField pwd = new JPasswordField();
        pwd.setHorizontalAlignment(JTextField.CENTER);
        pwd.setEchoChar('X');
        int r = JOptionPane.showConfirmDialog(rootPane, pwd, "Contraseña de administrador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new BloqueadoIcono());
        request = (r == JOptionPane.OK_OPTION) ? AES.sha1(String.valueOf(pwd.getPassword())) : ConstantesPvr.NR;

        return request;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DfrmRegresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DfrmRegresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DfrmRegresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DfrmRegresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DfrmRegresar dialog = new DfrmRegresar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane scrllpnDetalles;
    private javax.swing.JTextArea txtDetalles;
    // End of variables declaration//GEN-END:variables
}
